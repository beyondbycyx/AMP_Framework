package com.hugo.api.net;

import com.hugo.api.INetCallback;
import com.hugo.api.entites.Response;

/**
 * Created by hq on 2015/11/13.
 */
public  abstract class NetCallback implements INetCallback<Object,Response> {


  @Override public void onEmpty(Response response) {
    throw  new IllegalStateException("the method must be override");
  }

  @Override public void onSuccess(Object result) {
    throw  new IllegalStateException("the method must be override");
  }


}
