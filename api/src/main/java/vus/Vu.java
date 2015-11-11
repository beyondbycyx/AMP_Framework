package vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hq on 2015/11/11.
 *  顶级View 的接口，由它的子类决定如何初始化该View，并将该View的引用传递给下一个层级Presenter
 *
 */
public interface Vu  {
    void initView(LayoutInflater inflater, ViewGroup container);
    View getView();
}
