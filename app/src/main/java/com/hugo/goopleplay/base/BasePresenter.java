package com.hugo.goopleplay.base;

import com.hugo.api.IPresenter;
import com.hugo.api.Vu;

/**
 * Created by hq on 2015/11/12.
 */
public abstract class BasePresenter<V extends Vu> implements IPresenter {

  protected   V mViewImpl;
  public BasePresenter(V vImpl) {

    mViewImpl = vImpl;
  }

  @Override public void start() {
    throw new IllegalStateException("executeRequest method must be override be before to used");
  }

  @Override public void stop() {
    throw new IllegalStateException("removeRequest method must be override be before to used");
  }
}
