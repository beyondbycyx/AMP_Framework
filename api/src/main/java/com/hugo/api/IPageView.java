package com.hugo.api;

import com.hugo.api.*;

/**
 * Created by hq on 2015/11/12.
 */
public interface IPageView extends com.hugo.api.Vu {

    /**
     * 显示等待页面
     */
    void showLoadingView();

    /**
     * 显示成功的页面
     */
    void showSuccessView();

    /**
     * 显示错误提示
     */
    void showErrorView();

    /**
     * 空白页面提示
     */
    void showEmptyView();
}
