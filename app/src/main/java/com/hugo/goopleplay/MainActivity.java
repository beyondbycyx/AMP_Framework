package com.hugo.goopleplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.hugo.api.NetRequestQueue;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final NetRequestQueue instance = NetRequestQueue.getInstanceWithStart();



  }
}

