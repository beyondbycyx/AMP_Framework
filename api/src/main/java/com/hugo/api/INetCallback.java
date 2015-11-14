package com.hugo.api;

/**
 * Created by hq on 2015/11/13.
 */
public interface INetCallback<T,K> {

  void onSuccess(T result, K response);

  void onEmpty(K response);
  void onError(Exception error);
}
