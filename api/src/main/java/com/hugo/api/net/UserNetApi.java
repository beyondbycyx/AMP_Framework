package com.hugo.api.net;

/**
 * Created by hq on 2015/11/13.
 */
public interface UserNetApi<R> {

    R login(String url);

    R register(String url);
}
