package com.hugo.api;

/**
 * Created by hq on 2015/11/13.
 */
public interface INetCallback<T,K> extends ICallback{

  void onSuccess(T result, K response);

  void onEmpty(K response);

}
