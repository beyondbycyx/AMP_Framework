package com.hugo.common.utils;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by hq on 2015/10/29.
 */
public class PackageUtils {
    private static final String TAG = PackageUtils.class.getSimpleName();

    /**
     * @param context
     * @return could be null if NameNotFoundException n happened
     */
    public static PackageInfo getPackageInfo(Context context) {

        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = new PackageInfo();
            LogUtils.d(TAG, e.getMessage());
        }
        return  packageInfo;
    }
}
