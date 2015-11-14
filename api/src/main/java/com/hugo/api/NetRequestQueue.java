package com.hugo.api;

import android.os.Handler;
import android.os.Looper;
import com.hugo.api.entites.Request;
import com.hugo.api.entites.Response;
import com.hugo.common.utils.LogUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by hq on 2015/11/13.
 * 全局单例
 * tag --- 每个 activity，fragment(内部的tag)
 * uniqueId --- 每个单次的请求 id 号，
 * 同一用uniqueId 表示 一个 request, response ,callback
 *
 */
public class NetRequestQueue {

  private static final java.lang.String TAG = "NetRequestQueue";
  private Map<String ,Map<String,Request>> requestWithTagPool;  //所有的请求以tag (来自request 内部) 分类

  private Map<String ,NetCallback> idWithCallsMap;
  private int threadSize ;

  public NetWorkThread [] netWorkThreads ;
  private BlockingQueue<Request> blockingQueue ;
  private Handler handler ;

  private static NetRequestQueue instance ;

  public ConcurrentMap<String, Response> testMap = new ConcurrentHashMap<String,Response>();

  private NetRequestQueue() {
    this(4);
  }
  public NetRequestQueue(int threadSize) {
    this.threadSize = threadSize;
    this.requestWithTagPool = new HashMap<String ,Map<String,Request>>();
    this.idWithCallsMap = new HashMap<String ,NetCallback>();

    this.netWorkThreads = new NetWorkThread[threadSize];
    //this.handler = new Handler(Looper.getMainLooper());
    this.blockingQueue = new PriorityBlockingQueue<Request>();
  }

  public static NetRequestQueue getInstance() {
    if (instance == null) {
      synchronized (NetRequestQueue.class){
        if (instance == null) {
          instance = new NetRequestQueue();
        }
      }
    }


    return instance;
  }

   public void start() {

    //启动4(默认)个线程
    for (int i = 0; i < threadSize; i++) {
      NetWorkThread netWorkThread = new NetWorkThread(this.blockingQueue,new NetCallbackHelper(this.handler,this.idWithCallsMap));
      netWorkThreads[i] = netWorkThread;
      netWorkThread.start();


    }
  }

  /**
   * 只可以在主线程中调用
   * @param request
   * @param netCallback
   */
  public void executeRequest(Request request, NetCallback netCallback) {

    //checkOnMainThread();

    if (request == null || netCallback == null) {
      throw new IllegalStateException("request and netCallback cannot be null");
    }
    Map<String, Request> requestMap = requestWithTagPool.get(request.tag);

    if (requestMap == null) { //tag == null
      requestMap = new HashMap<String, Request>();
      requestMap.put(request.getUniqueId(), request); //put-- id,request

      requestWithTagPool.put(request.tag, requestMap);   //put-- tag,map
    }else{  //tag != null
      if (requestMap.get(request.getUniqueId()) == null) { // id == null
        requestMap.put(request.getUniqueId(), request);    // put-- id, request

      }else{ // id != null
        LogUtils.d(TAG,request.getUniqueId()+":进行重复的请求"); //重复是指 UniqueId = tag + urlPath 的值
        requestMap.put(request.getUniqueId(), request); //重复添加，覆盖原来保存在map中的
      }

    }

    this.idWithCallsMap.put(request.getUniqueId(), netCallback);

    this.blockingQueue.add(request);



  }

  /**
   * 删除一个 tag-- map 的一组请求。
   * @param tag
   */
  public void removeRequest(String tag) {

    Map<String, Request> requestMapWithId = this.requestWithTagPool.get(tag);
    Iterator<Map.Entry<String, Request>> iterator = requestMapWithId.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, Request> next = iterator.next();

      Request request = next.getValue();
      try {
        this.blockingQueue.remove(request);
      } catch (Exception e) {
        LogUtils.d(TAG, e.getMessage());
      }
    }
    requestMapWithId.clear();

  }

  private void checkOnMainThread() {
        if (Looper.myLooper()!= Looper.getMainLooper()) throw new IllegalStateException("请在主线程调用该方法");
  }
}
