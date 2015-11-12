package com.hugo.goopleplay;

import android.os.Bundle;

import com.hugo.goopleplay.base.BaseActivity;
import com.hugo.goopleplay.views.MainVu;

public class MainActivity extends BaseActivity<MainVu> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Class<MainVu> getVuClass() {
        return MainVu.class;
    }


}
