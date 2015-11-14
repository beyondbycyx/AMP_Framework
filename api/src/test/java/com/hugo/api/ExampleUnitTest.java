package com.hugo.api;

import com.hugo.api.entites.Request;
import com.hugo.api.entites.Response;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import junit.framework.Assert;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    int t = 1;
   // @Test
    public void addition_isCorrect() throws Exception {

        new Thread(){
            @Override public void run() {
                super.run();
                t = 10;
            }
        }.start();

    }
    @Test
    public void testNet() {

        NetRequestQueue netRequestQueue = NetRequestQueue.getInstance();
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

        Assert.assertEquals(11,size);


    }



    public void urlCon() {
        try {
            URL url = new URL("http://127.0.0.1/myfile.txt");
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();

            httpUrl.setRequestMethod("GET");
            httpUrl.setDoInput(true);
            InputStream inputStream = httpUrl.getInputStream();
            int responseCode = httpUrl.getResponseCode();

            httpUrl.disconnect();
            Assert.assertEquals(20, responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}