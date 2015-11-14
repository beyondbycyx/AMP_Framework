package com.hugo.api;

import android.app.Application;
import android.test.ApplicationTestCase;
import java.net.HttpURLConnection;
import java.net.URL;
import junit.framework.Assert;

/**
 * Created by hq on 2015/11/14.
 */
public class NetFunctionTest  extends ApplicationTestCase<Application> {

  public NetFunctionTest() {
    super(Application.class);
  }

  public void test() {
    try {
      URL url = new URL("http://10.0.3.2/myfile.txt");
      HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();

      httpUrl.setRequestMethod("GET");
      httpUrl.setDoInput(true);

      int responseCode = httpUrl.getResponseCode();

      httpUrl.disconnect();
      Assert.assertEquals(20, responseCode);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
