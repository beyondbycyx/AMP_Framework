package com.hugo.api;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by hq on 2015/11/12.
 * 顶级的View 接口：具备创建与获取分离，并可以获取子view
 */
public interface Vu {


     <C extends Context> C getContext();

    /**
     * @param id  获取View 的id
     * @return   在rootview 中的子View
     */
    View getViewById(@IdRes int id);

    void rollbackView(Bundle lastState);
}
