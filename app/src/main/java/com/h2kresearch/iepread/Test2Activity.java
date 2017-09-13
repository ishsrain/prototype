package com.h2kresearch.iepread;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Test2Activity extends AppCompatActivity {

  TextView test;
  ProgressBar progressBar;
  Thread thread;

  String[] testString = {"소나문", "소나무", "자전거", "자동차", "가지", "가재"};
  int indexString = 1;

  // Record Time (ms)
  int msTime = 1500;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test2);

    thread = new Thread(new Runnable() {
      @Override
      public void run() {

        // Progress Bar Working
        for (int i = 0; i < msTime; i++) {
          progressBar.setProgress(i);
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        // Progress Bar Init
        progressBar.setProgress(0);

        // TextView Change
        Message msg = Message.obtain();
        msg.what = indexString++;
        handler.sendMessage(msg);
      }
    });

    // TextView
    test = (TextView) findViewById(R.id.textView9);
    test.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(indexString < testString.length) {
          thread.start();
        } else {
          Intent intent = new Intent(getBaseContext(), Test3Activity.class);
          startActivity(intent);
        }
      }
    });

    // ProgressBar
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setMax(msTime);

  }

  // Handler
  Handler handler = new Handler() {
    public void handleMessage(Message msg) {
      test.setText(testString[msg.what]);
    }
  };
}
