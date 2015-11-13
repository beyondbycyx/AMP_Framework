package com.hugo.api;

/**
 * Created by hq on 2015/11/12.
 * 顶层的Presenter 类接口 ：
 * execute 方法:激活初始化操作
 * cancel 方法：取消所有的操作
 *
 */
public interface IPresenter {

  void execute();
  void cancel();
}
