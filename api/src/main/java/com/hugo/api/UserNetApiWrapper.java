package com.hugo.api;

import com.hugo.api.entites.UserResponse;
import com.hugo.api.net.UserNetApi;
import com.hugo.common.utils.EncryptUtil;

/**
 * Created by hq on 2015/11/13.
 */
public class UserNetApiWrapper {
  private UserNetApi api;

  public UserNetApiWrapper(UserNetApi api) {
    api = api;
  }

  UserResponse login(String userName,String password) {
    String encryptPwd = EncryptUtil.makeMD5(password);

    return null;
  }

  UserResponse register() {

    return null;
  }

}
