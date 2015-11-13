package com.hugo.goopleplay.presenter;

import android.content.Context;
import android.view.View;
import com.hugo.goopleplay.R;
import com.hugo.goopleplay.base.BasePresenter;
import com.hugo.goopleplay.manager.EventHelper;

/**
 * Created by hq on 2015/11/12.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

  public LoginPresenter(ILoginView vImpl) {
    super(vImpl);
  }

  //先显示loading 界面几秒，再显示登录界面
  @Override public void execute() {
    final View loadingView = mViewImpl.getViewById(R.id.loading_view);
    final View loginView = mViewImpl.getViewById(R.id.login_view);
    loginView.setVisibility(View.GONE);

    Context context = mViewImpl.getContext();
    EventHelper.getInstance().register(context);

    EventHelper.getInstance().sendMessage();
  }

  public void onLoginClick() {

  }

  public void onRegisterClick() {

  }

  @Override public void cancel() {

  }
}
