package com.hugo.goopleplay.exception;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.hugo.common.utils.LogUtils;
import com.hugo.goopleplay.App;

/**
 * Created by hq on 2015/11/10.
 * <p/>
 * 全局静态单例模式
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final  String TAG = "CrashHandler";
    private static CrashHandler mCrashHandler;
    private Context mContext;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            synchronized (CrashHandler.class) {
                if (mCrashHandler == null) {
                    mCrashHandler = new CrashHandler();
                }
            }
        }

        return mCrashHandler;
    }

    public  void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }

    /**
     * 出现异常时的提示
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
    //打印异常，最好打印到日志文件中
        LogUtils.e(TAG, ex.getMessage());
    //提示用户出现异常
        new Thread(){
            @Override
            public void run() {
                super.run();
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("出现异常");
                builder.setPositiveButton("重新启动", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //重新启动该应用的启动activity
                        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
                        //FLAG_ACTIVITY_CLEAR_TOP，回到打开的activity，在该activity之栈上的activity都会清除掉。
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        //当前系统应用退出。
                        App.getInstance().exit();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        }.start();

    }


}
