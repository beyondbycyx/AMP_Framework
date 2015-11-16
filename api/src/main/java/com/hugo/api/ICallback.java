package com.hugo.api;

/**
 * Created by hq on 2015/11/15.
 */
public interface ICallback<T> {

  void onSuccess(T result);
  void onError(Exception error);
}
