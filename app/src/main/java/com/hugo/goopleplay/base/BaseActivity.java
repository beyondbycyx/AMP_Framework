package com.hugo.goopleplay.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.ButterKnife;
import com.hugo.goopleplay.manager.ActivitySetManager;

/**
 * Created by hq on 2015/10/27.
 */
public   class BaseActivity<P extends BasePresenter> extends AppCompatActivity
    implements Vu {

  private static final String TAG = "BaseActivity";

  private SparseArray<View> mViewSet;
  private View mView;

  @Override

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
  }

  private void init() {
    ActivitySetManager.getInstance().addActivity(this);
    mViewSet = new SparseArray<View>();
  }

  /**
   * 子类必须继承并使用它初始化
   */
  protected void bindView(@LayoutRes int layoutId) {
    mView = LayoutInflater.from(this).inflate(layoutId, null);
    setContentView(mView);
    ButterKnife.bind(this);
  }

  /**
   * @return Vu sub class
   * the subclass must implement the method to provider a Vu class for the base class used
   */

  @Override protected void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ActivitySetManager.getInstance().removeActivity(this);
  }

  @Override public Context getContext() {
    return this;
  }

  @Override public View getViewById(@IdRes int id) {
    View view = mViewSet.get(id);
    if (view == null) {
      view = mView.findViewById(id);
    }

    return view;
  }


}
