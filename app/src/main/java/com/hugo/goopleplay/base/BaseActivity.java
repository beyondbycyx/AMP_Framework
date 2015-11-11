package com.hugo.goopleplay.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.hugo.goopleplay.manager.ActivitySetManager;

import butterknife.ButterKnife;

/**
 * Created by hq on 2015/10/27.
 */
public abstract class BaseActivity extends AppCompatActivity  implements BaseFragment.OnCallActivity{

    private static  final  String TAG = "BaseActivity";

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatchInitEvent();
    }

    public void dispatchInitEvent() {
         ActivitySetManager.getInstance().addActivity(this);
    }

    public void bindView(@LayoutRes int layoutId) {
        setContentView(layoutId);
        ButterKnife.bind(this);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         ActivitySetManager.getInstance().removeActivity(this);

    }

}
