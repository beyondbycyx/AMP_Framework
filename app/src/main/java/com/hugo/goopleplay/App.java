package com.hugo.goopleplay;

import android.app.Application;
import com.hugo.common.utils.DpUtils;
import com.hugo.common.utils.LogUtils;
import com.hugo.goopleplay.exception.CrashHandler;
import com.hugo.goopleplay.manager.ActivitySetManager;



/**
 * Created by hq on 2015/11/10.
 * 单例，全局共享，常用的资源
 */
public final class App extends Application {

    private static final String TAG = "GooglePlayApp";
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();

    }

    public static App getInstance() {
        if (instance == null) {
            throw new IllegalStateException("App not initialized");
        }
        return instance;
    }

    /**
     * @des to finish all the opened activities and exit
     * @call only call when the system is crashed or the user want to exit the app
     */
    public void exit() {
        try {

            ActivitySetManager.getInstance().clearAll();
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        } finally {
            System.exit(0);
        }
    }

    private void init() {
        DpUtils.init(this);
        ActivitySetManager.init(this);
        CrashHandler.getInstance().init(this);

    }
}
