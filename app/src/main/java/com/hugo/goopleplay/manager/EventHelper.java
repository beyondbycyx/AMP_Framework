package com.hugo.goopleplay.manager;

import android.content.Context;
import android.os.Handler;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hq on 2015/11/12.
 */
public class EventHelper {

  private static final java.lang.String TAG = "EventHelper";
  private static EventHelper instance ;

  private Set<Handler> pool = new HashSet<Handler>();
  private EventHelper() {

  }
  public static EventHelper getInstance() {
    if (instance == null) {
      synchronized (EventHelper.class) {
        if (instance == null) {
          instance = new EventHelper();
        }
      }
    }

    return instance;
  }

  public void register(Context context) {

    Class clazz = context.getClass();
    Handler handler = new Handler(context.getMainLooper());
    pool.add(handler);
  }

  public void sendMessage() {

  }

  public void cancelMessage() {

  }


}
