package com.h2kresearch.iepread;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);

    // Home Activity Call
    startLoading();
  }

  private void startLoading() {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        //Intent intent = new Intent(getBaseContext(), Test1Vowel2Activity.class);
        //Intent intent = new Intent(getBaseContext(), InfoActivity.class);
        Intent intent = new Intent(getBaseContext(), Test9ReadingActivity.class);
        //Intent intent = new Intent(getBaseContext(), ResultActivity.class);
        startActivity(intent);
        finish();
      }
    }, 2000);
  }
}
