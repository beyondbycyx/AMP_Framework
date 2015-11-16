package com.hugo.api.net;

import com.hugo.api.entites.Request;
import com.hugo.api.entites.Response;

/**
 * Created by hq on 2015/11/13.
 */
public interface UserNetApi<R> {

    String TAG = "UserNetApi";
    R login(String url);

    R register(String url);
    //TODO 需要参考RXJava 的 异步监听回调，以返回值的形式返回。不是在方法参数那里进行回调监听
    UserNetApi userApi = new UserNetApi() {
        @Override public Object login(String url) {
            Request request = new Request(url,TAG);
            NetCallback callBack = new NetCallback() {
                @Override public void onSuccess(Object result, Response response) {

                }

                @Override public void onError(Exception error) {

                }
            };
            NetRequestQueue.getInstanceWithStart().executeRequest(request ,callBack);
            return null;
        }

        @Override public Object register(String url) {

            return null;
        }
    };

}
