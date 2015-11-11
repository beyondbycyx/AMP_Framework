package com.hugo.goopleplay.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hq on 2015/10/7.
 */
public class ToastUtils {
    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
