package com.hugo.goopleplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    new Thread(){
      @Override public void run() {
        super.run();
        HttpURLConnection httpUrl = null;
        try {
          URL url = new URL("http://10.0.3.2/myfile.txt");
          httpUrl = (HttpURLConnection) url.openConnection();

          httpUrl.setRequestMethod("GET");
          httpUrl.setDoInput(true);

          int responseCode = httpUrl.getResponseCode();
          Log.d(TAG, responseCode + ":dfds");

        } catch (Exception e) {
          e.printStackTrace();   
        }
      }
    }.start();
  }
}
