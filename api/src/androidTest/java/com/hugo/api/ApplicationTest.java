package com.hugo.api;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.test.ApplicationTestCase;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import junit.framework.Assert;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test() {
        PackageManager packageManager = getContext().getPackageManager();
        PermissionInfo info = new PermissionInfo();

        HttpURLConnection httpUrl = null;
        try {
            URL url = new URL("http://10.0.3.2/myfile.txt");
             httpUrl = (HttpURLConnection) url.openConnection();

            httpUrl.setRequestMethod("GET");
            httpUrl.setDoInput(true);
            InputStream inputStream = httpUrl.getInputStream();
            Assert.assertNotNull(inputStream);

            int responseCode = httpUrl.getResponseCode();


            Assert.assertEquals(20, responseCode);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNotNull(httpUrl);
        }

    }
/*
    public void test() {
        NetRequestQueue netRequestQueue = NetRequestQueue.getInstanceWithStart();
        netRequestQueue.start();
        Request request = new Request("http://127.0.0.1/myfile.txt","tag");
        NetCallback callback = new NetCallback() {
            @Override public void onSuccess(Object result, Response response) {

            }

            @Override public void onEmpty(Response response) {

            }

            @Override public void onError(Exception error) {

            }
        };
        netRequestQueue.executeRequest(request, callback);


        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size = netRequestQueue.testMap.size();

        Assert.assertEquals(11, size);


    }*/
}