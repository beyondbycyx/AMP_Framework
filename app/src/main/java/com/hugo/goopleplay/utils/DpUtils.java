package com.hugo.goopleplay.utils;

import android.content.Context;

/**
 * Created by hq on 2015/10/28.
 */
public class DpUtils {

    private static float fontDensity;
    private static float density;

    /**
     * 字体的转换
     * @param dp
     * @return
     */
    public static int fontDp2Px( int dp) {

        return  (int) (fontDensity*dp + 0.5f);

    }

    public static int fontPx2Dp(int px) {

       return  (int) (px/fontDensity + 0.5f);
    }

    /**
     * view 控件的转换
     * @param dp
     * @return
     */
    public static int viewDp2Px(int dp) {

      return (int) (dp * density + 0.5f);

    }

    public static int viewPx2Dp(int px) {

        return  (int) (px / density + 0.5f);

    }


    public static void init(Context  app) {
        density = app.getResources().getDisplayMetrics().density;
        fontDensity = app.getResources().getDisplayMetrics().scaledDensity;

    }
}
