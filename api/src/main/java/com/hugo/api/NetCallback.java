package com.hugo.api;

import com.hugo.api.entites.Response;

/**
 * Created by hq on 2015/11/13.
 */
public class NetCallback implements INetCallback<Object,Response> {
  @Override public void onSuccess(Object result,Response response) {

  }

  @Override public void onError(Exception error) {

  }
}
