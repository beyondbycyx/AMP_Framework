package com.hugo.api.net;

import com.hugo.api.entites.UserResponse;
import com.hugo.common.utils.EncryptUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by hq on 2015/11/13.
 */
public class UserNetApiWrapper {
  private UserNetApi api;

  public UserNetApiWrapper(UserNetApi api) {
    api = api;
  }

  /**
   * 进行get 请求
   * @param userName
   * @param password
   * @return
   */
  //TODO 需要参考RXJava 的 异步监听回调，以返回值的形式返回。不是在方法参数那里进行回调监听

  UserResponse login(String userName,String password) {
    String encryptPwd = EncryptUtil.makeMD5(password);
    String encodeName = "";
    try {
       encodeName = URLEncoder.encode(userName, "UTF-8");

    } catch (UnsupportedEncodingException e) {
    }

    String url = NetConst.USER_URL+"/"+encodeName+"&"+password;
    Object login = api.login(url); // TODO 异步请求。

    return null;
  }

  /**
   * 进行 get 请求
   * @return
   */
  UserResponse register() {

    return null;
  }

}
