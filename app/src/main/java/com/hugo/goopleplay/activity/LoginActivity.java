package com.hugo.goopleplay.activity;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.hugo.api.ILoginView;
import com.hugo.goopleplay.R;
import com.hugo.goopleplay.base.BaseActivity;
import com.hugo.goopleplay.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

  public static final String TAG = "LoginActivity";

  private LoginPresenter loginPresenter;

  @OnClick({ R.id.login_bt, R.id.register_bt }) public void onClick(View view) {

    int id = view.getId();
    switch (id) {
      case  R.id.login_bt : loginPresenter.onLoginClick();
        break;
      case  R.id.register_bt : loginPresenter.onRegisterClick();
        break;
    }
  }
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
  }

  public void init() {
    bindView(R.layout.activity_login);
    loginPresenter = new LoginPresenter(this);
    loginPresenter.start();
  }


  @Override public void showLoadingView() {

  }

  @Override public void showErrorView() {

  }

  @Override public void navigateToHome() {

  }


}
