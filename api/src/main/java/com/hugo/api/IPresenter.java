package com.hugo.api;

/**
 * Created by hq on 2015/11/12.
 * 顶层的Presenter 类接口 ：
 * start 方法:激活初始化操作
 * stop 方法：取消所有的操作
 *
 */
public interface IPresenter<V extends com.hugo.api.Vu> {

  boolean attachView(V viewImpl);
  void start();
  void stop();
}
