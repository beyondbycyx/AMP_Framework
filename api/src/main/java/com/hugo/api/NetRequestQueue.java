package com.hugo.api;

import android.os.Handler;
import android.os.Looper;
import com.hugo.api.entites.Request;
import com.hugo.api.entites.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hq on 2015/11/13.
 * 单例
 */
public class NetRequestQueue {

  private Map<Request,Response> requestMap;
  private int threadSize ;
  private NetWorkThread [] netWorkThreads ;
  private Handler handler ;

  private static NetRequestQueue instance ;
  private NetRequestQueue() {
    this(4);
  }
  public NetRequestQueue(int threadSize) {
    this.threadSize = threadSize;
    this.requestMap = new HashMap<>();
    this.netWorkThreads = new NetWorkThread[threadSize];
    this.handler = new Handler(Looper.getMainLooper());
  }

  public static NetRequestQueue newInstance() {
    if (instance == null) {
      synchronized (NetRequestQueue.class){
        if (instance == null) {
          instance = new NetRequestQueue();
        }
      }
    }

    instance.start();
    return instance;
  }

  private void start() {

    //启动4(默认)个线程
    for (int i = 0; i < threadSize; i++) {
      NetWorkThread netWorkThread = new NetWorkThread();
      netWorkThread.start();

      netWorkThreads[i] = netWorkThread;
    }
  }

  public void execute(Request request , NetCallback netCallback) {
    checkOnMainThread();
    requestMap.add(request); // 不能从夫
  }

  private void checkOnMainThread() {
        if (Looper.myLooper()!= Looper.getMainLooper()) throw new IllegalStateException("请在主线程调用该方法");
  }
}
