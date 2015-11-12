package com.hugo.goopleplay.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hugo.goopleplay.manager.ActivitySetManager;

import butterknife.ButterKnife;
import utils.LogUtils;
import vus.Vu;

/**
 * Created by hq on 2015/10/27.
 */
public abstract class BaseActivity<V extends Vu> extends AppCompatActivity   {

    private static  final  String TAG = "BaseActivity";

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       try {
           ActivitySetManager.getInstance().addActivity(this);

           Vu vu = getVuClass().newInstance();
           View view = vu.getView();
           setContentView(view);
           ButterKnife.bind(this);

       } catch (Exception e) {
           LogUtils.d(TAG,e.getMessage());
       }
   }


    /**
     *
     * @return Vu sub class
     * the subclass must implement the method to provider a Vu class for the base class used
     */
    public abstract Class<V>  getVuClass();
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
