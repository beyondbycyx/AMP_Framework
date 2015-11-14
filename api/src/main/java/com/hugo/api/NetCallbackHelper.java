package com.hugo.api;

import android.os.Handler;
import com.hugo.api.entites.Response;
import java.util.Map;

/**
 * Created by hq on 2015/11/13.
 */
public class NetCallbackHelper {

  private Handler postHandler;
  private  Map<String, NetCallback> uniqueIdWithCalls;

  public NetCallbackHelper(Handler handler, Map<String, NetCallback> uniqueIdWithCalls) {
    this.postHandler = handler ;
    this.uniqueIdWithCalls = uniqueIdWithCalls;
  }

  public void postResponse(final Response response) {

    NetCallback netCallback = this.uniqueIdWithCalls.get(response.getUniqueId());
    Delivery delivery = Delivery.createDelivery(response);
    this.postHandler.post(delivery.execute(netCallback));
  }

  /**
   * 发表响应，并处响应
   */
  static  class  Delivery implements Runnable {

    private Response response ;
    private NetCallback responseCallback;

    public Delivery(Response response) {
      this.response = response;
    }

    /**
     * 静态方法工厂模式
     * @param response
     * @return
     */
    public static Delivery createDelivery(Response response) {

      return  new Delivery(response);
    }

    /**
     * 处理响应结果方法
     * @param callback
     * @return
     */
    private Delivery execute(NetCallback callback) {
      this.responseCallback = callback;
      return this;
    }

    @Override public void run() {
      if (response == null || responseCallback == null) {
        throw new IllegalStateException("response , responseCallback must not be null");
      }

      switch (response.getDataState()) {
        case Response.DataSate_Exist:
          responseCallback.onSuccess(response.contentStr, response);
          break;
        case Response.DataSate_Empty:
          responseCallback.onEmpty(response);
          break;
        case Response.DataSate_Error:
          if (response.error != null) {
            responseCallback.onError(response.error);
          }
          break;
      }
    };
  }

}
