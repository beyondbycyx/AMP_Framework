package com.hugo.api.net;

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

    private Response response ; //从构造方法传入。
    private NetCallback responseCallback; //从静态方法闯入

    public Delivery(Response response) {
      this.response = response;
    }
    /**
     * Delivery 类总结：
     * 1.是一个小的容器：成员变量保存了某个方法内部需要的变量。
     * 2.重写run方法：加入一段执行的代码块(最后作为runnable交给了handler进行post请求)
     * 3.一个类可以柔和多个方法，并临时保存携带方法需要用到的变量。
     * 4.一个类的内部的成员变量的赋值，可以在a.构造方法中传入(某个时段)，b.静态/非静态方法(某个时段)，c.总之就是方法名(Xxx 参数)，丢进来。
   * */

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
