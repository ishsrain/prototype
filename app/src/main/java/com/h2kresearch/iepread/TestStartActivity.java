package com.h2kresearch.iepread;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestStartActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_start);

    startLoading();
  }

  private void startLoading() {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(getBaseContext(), Test1Vowel1Activity.class);

        intent.putExtra("result", getIntent().getStringExtra("result"));
        intent.putExtra("info", getIntent().getStringExtra("info"));
        startActivity(intent);
        finish();
      }
    }, 3000);
  }
}
