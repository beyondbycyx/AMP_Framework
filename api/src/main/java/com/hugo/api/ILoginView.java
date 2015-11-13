package com.hugo.api;

/**
 * Created by hq on 2015/11/12.
 */
public interface ILoginView  extends com.hugo.api.Vu {

    void showLoadingView();

    void showErrorView();

    void navigateToHome();


}
