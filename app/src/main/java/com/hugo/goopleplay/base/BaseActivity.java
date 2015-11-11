package com.hugo.goopleplay.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by hq on 2015/10/27.
 */
public abstract class BaseActivity extends AppCompatActivity  implements BaseFragment.OnCallActivity{

    private static  final  String TAG = "BaseActivity";

//    private RequestHelper mRequestHelper; //由它内部的tag来标识它与当前activity的联系
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatchInitEvent();
    }

    public void dispatchInitEvent() {
//        ActivitySetManager.getInstance().addActivity(this);
    }

    public void bindView(@LayoutRes int layoutId) {
        setContentView(layoutId);
        ButterKnife.bind(this);
    }

    protected  void initView(){}

    protected  void initData(){}

    protected  void initEvent(){}

    @Override
    protected void onStop() {
        super.onStop();
        if (getRequestHelper() != null) {
//            getRequestHelper().cancelRequest();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActivitySetManager.getInstance().removeActivity(this);

    }

/*    @Override
    public RequestHelper getRequestHelper() {

        if (mRequestHelper == null) {
            String tag = this.getClass().getSimpleName();
            mRequestHelper = new RequestHelper(tag);
        }
        return mRequestHelper;
    }*/

    @Override
    public void setTitleText(String text) {

    }
}
