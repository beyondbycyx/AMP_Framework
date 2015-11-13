package com.hugo.goopleplay.base;

/**
 * Created by hq on 2015/11/12.
 */
public abstract class BasePresenter<V extends Vu> implements IPresenter {

  protected   V mViewImpl;
  public BasePresenter(V vImpl) {

    mViewImpl = vImpl;
  }

  @Override public void execute() {
    throw new IllegalStateException("execute method must be override be before to used");
  }

  @Override public void cancel() {
    throw new IllegalStateException("cancel method must be override be before to used");
  }
}
