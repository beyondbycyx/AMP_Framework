package com.hugo.goopleplay.manager;

import android.app.Activity;
import android.content.Context;

import com.hugo.goopleplay.utils.LogUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hq on 2015/10/29.
 *
 * 管理所有的activity的最后退出
 */
public final class ActivitySetManager {

    private static final String TAG = "ActivitySetManager";
    private List<WeakReference<Activity>> mList = new ArrayList<WeakReference<Activity>>();
    private static final Object mLock = new Object();
    private static  ActivitySetManager manager ;

    private ActivitySetManager() {

    }
    public static void init(Context context) {
         if (manager == null)
             manager = new ActivitySetManager();
    }

    public synchronized  static ActivitySetManager getInstance() {
        if(manager == null)
            throw  new  IllegalStateException("ActivitySetManager not initialized");
        return manager;
    }
    public boolean addActivity(Activity activity) {
        WeakReference<Activity> wk = new WeakReference<Activity>(activity);
        boolean add = false;
        synchronized (mLock) {
            add = mList.add(wk);
        }
        LogUtils.d(TAG, "add activity " + activity);
        return add;
    }
    public boolean removeActivity(Activity activity){
        boolean removed = false;
        synchronized (mLock) {
            removed = mList.remove(activity);
        }
        LogUtils.d(TAG,"remove activity "+activity);
        return removed;
    }

    /**
     *  调用了activity的finish方法
     */
    public void clearAll(){
        Iterator<WeakReference<Activity>> iterator = mList.iterator();
        while (iterator.hasNext()) {
            WeakReference<Activity> next = iterator.next();
            next.get().finish();
        }
        mList.clear();
        mList = null;
    }
}
