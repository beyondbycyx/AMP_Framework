package com.hugo.api.bus;

import android.os.Looper;

/**
 * Created by hq on 2015/11/16.
 * visitor 模式： 检查当前线程所处在的位置
 */
public interface ThreadRuler {

  void enforce(Bus bus);

  //静态常量实例
  public ThreadRuler MAIN_RULER = new ThreadRuler() {
    @Override public void enforce(Bus bus) {
      if(Looper.myLooper() !=Looper.getMainLooper())
        throw  new IllegalStateException("the Bus' methods  must be invoke in main-thread");

    }
  };
}
