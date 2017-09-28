package com.h2kresearch.iepread;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestEndActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_end);

    startLoading(); //comment for test, another commit test
  }
  private void startLoading() {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(getBaseContext(), ResultActivity.class);
        startActivity(intent);
        finish();
      }
    }, 1000);
  }
}
