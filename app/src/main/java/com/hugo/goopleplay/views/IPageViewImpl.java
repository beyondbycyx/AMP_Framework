package com.hugo.goopleplay.views;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by hq on 2015/11/12.
 */
public class IPageViewImpl implements IPageView {

    private  static  final  String TAG = "LoadPageVuImpl";
    private SparseArray<View> mViews ;
    private View rootView;


    private boolean isInitialized = false;
    private View errorView;
    private View loadView;
    private View emptyView;
    //
    //@Override
    //public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup group) {
    //
    //    //默认初始化成功的视图
    //    rootView = inflater.inflate(R.layout.fragment_page, null);
    //    mViews = new SparseArray<View>();
    //
    //    loadView = inflater.inflate(R.layout.pager_loading, null);
    //    errorView = inflater.inflate(R.layout.pager_error, null);
    //    emptyView = inflater.inflate(R.layout.pager_empty, null);
    //
    //    isInitialized = true;
    //
    //}
    //
    //@Override
    //public View getView() {
    //    if (!isInitialized) {
    //        throw  new IllegalStateException(TAG + "has not invoke initView() method   ");
    //    }
    //
    //    return rootView;
    //}

    @Override public Context getContext() {
        return null;
    }

    /**
     * @call  必须先调用initView 方法
     * @param id 获取View 的id
     * @return
     */
    @Override
    public View getViewById(@IdRes int id) {
        if (!isInitialized) {
            throw  new IllegalStateException(TAG + "has not invoke initView() method   ");
        }
        View view = mViews.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
        }

        return view;
    }
    @Override
    public void showLoadingView() {


    }

    @Override
    public void showSuccessView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showEmptyView() {

    }


}
