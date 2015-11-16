package com.hugo.api.net;

import android.os.Handler;
import android.os.Looper;
import com.hugo.api.entites.Request;
import com.hugo.common.utils.LogUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
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

  //集合类
  private Map<String ,Map<String,Request>> requestWithTagMap;  //所有的请求以tag (来自request 内部) 分类
  private BlockingQueue<Request> blockingQueue ;
  private Map<String ,NetCallback> idWithCallsMap;
  private NetWorkThread [] netWorkThreads ;

  //单个元素
  private int threadSize ;
  private Handler handler ;
  private static NetRequestQueue instance ;

  private static boolean isRun  = false;

  private NetRequestQueue() {
    this(4);
  }
  private NetRequestQueue(int threadSize) {
    //单个元素初始化
    this.threadSize = threadSize;
    this.handler = new Handler(Looper.getMainLooper());

    //集合初始化
    this.requestWithTagMap = new HashMap<String ,Map<String,Request>>();
    this.idWithCallsMap = new HashMap<String ,NetCallback>();
    this.netWorkThreads = new NetWorkThread[threadSize];
    this.blockingQueue = new PriorityBlockingQueue<Request>();

  }

  public static NetRequestQueue getInstanceWithStart() {
    if (instance == null) {
      synchronized (NetRequestQueue.class){
        if (instance == null) {
          instance = new NetRequestQueue();
        }
      }
    }
    if (!isRun) {
      instance.start();
    }

    return instance;
  }

   public void start() {

    //启动4(默认)个线程
    for (int i = 0; i < this.threadSize; i++) {
      NetWorkThread netWorkThread = new NetWorkThread(this.blockingQueue,new NetCallbackHelper(this.handler,this.idWithCallsMap));
      netWorkThread.setName(TAG+":"+i);
      netWorkThreads[i] = netWorkThread;

      netWorkThread.start();
    }
     isRun = true;

  }

  public void stop() {
    for (NetWorkThread thread : netWorkThreads) {
      thread.quit();
    }
    isRun = false;
  }


  /**
   * 只可以在主线程中调用
   * put 一个Request 时 ，从最外层的大的集合开始 get 并判断是否为null，再初始化。
   * 总结：由外到里
   * 1.即从外部的集合开始get ,
   * 2.判断是否为null,
   * 3.初始化 new
   * 4.put 进集合
   *
   * @param request
   * @param netCallback
   */
  public void executeRequest(Request request, NetCallback netCallback) {

    checkOnMainThread();

    if (request == null || netCallback == null) {
      throw new IllegalStateException("request and netCallback cannot be null");
    }
    Map<String, Request> requestMap = requestWithTagMap.get(request.tag);

    if (requestMap == null) { //tag == null
      requestMap = new HashMap<String, Request>();
      requestMap.put(request.getUniqueId(), request); //put-- id,request

      requestWithTagMap.put(request.tag, requestMap);   //put-- tag,map
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
   *
   * 总结：由里到外的删除，
   * 1.先删除最里面的元素
   * 2.再逐步删除外面的元素。
   * 记住：有put的地方，就需要remove
   * 集合的put和reomve的方法要成对出现并处理元素，对应了某个对象的executeRequest , removeRequest 方法
   *
   * @param tag
   */
  public void removeRequest(String tag) {

    Map<String, Request> requestMapWithId = this.requestWithTagMap.get(tag);
    Iterator<Map.Entry<String, Request>> iterator = requestMapWithId.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, Request> next = iterator.next();

      Request request = next.getValue();
      try {
        this.blockingQueue.remove(request);
        this.idWithCallsMap.remove(next.getKey());
      } catch (Exception e) {
        LogUtils.d(TAG, e.getMessage());
      }
    }

    requestMapWithId.clear();
    requestWithTagMap.remove(tag);


  }

  private void checkOnMainThread() {
        if (Looper.myLooper()!= Looper.getMainLooper()) throw new IllegalStateException("请在主线程调用该方法");
  }

  public boolean isRun() {
    return isRun;
  }

}
