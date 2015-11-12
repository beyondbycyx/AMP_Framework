package com.hugo.goopleplay.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hugo.goopleplay.R;

import vus.Vu;

/**
 * Created by hq on 2015/11/11.
 */
public class MainVu implements Vu {

    private View mView;

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container) {

        mView = inflater.inflate(R.layout.activity_main, null);

    }

    @Override
    public View getView() {
        if (mView == null) {
            throw new IllegalStateException("MainVu:mView must be init before used ");
        }
        return mView;
    }
}
