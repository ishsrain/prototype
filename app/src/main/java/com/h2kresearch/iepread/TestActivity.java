package com.h2kresearch.iepread;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

  TextView test1;
  TextView test2;
  Button button;

  boolean test1Touch = false;
  boolean test2Touch = false;

  String[] test1String = {"이", "야", "에", "아", "어", "오"};
  String[] test2String = {"에", "유", "오", "에", "오", "으"};
  int indexString = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);

    test1 = (TextView)findViewById(R.id.textView);
    test2 = (TextView)findViewById(R.id.textView2);
    button = (Button)findViewById(R.id.button2);
    button.setEnabled(false);

    test1.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
          if(test1Touch == false) {
            test1Touch = true;
            test2Touch = false;
            test1.setBackgroundColor(Color.rgb(255, 0, 0));
            test2.setBackgroundColor(Color.rgb(255, 255, 255));
          } else {
            test1.setBackgroundColor(Color.rgb(0, 255, 0));

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                test1.setText(test1String[indexString]);
                test2.setText(test2String[indexString]);
                indexString++;

                if(indexString >= test1String.length) {
                  indexString = 0;
                }

                test1.setBackgroundColor(Color.rgb(255, 255, 255));
                test2.setBackgroundColor(Color.rgb(255, 255, 255));
                test1Touch = false;
                test2Touch = false;

              }
            }, 1000);
          }
        }
        return false;
      }
    });

    test2.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
          if (test2Touch == false) {
            test1Touch = false;
            test2Touch = true;
            test1.setBackgroundColor(Color.rgb(255, 255, 255));
            test2.setBackgroundColor(Color.rgb(255, 0, 0));
          } else {
            test2.setBackgroundColor(Color.rgb(0, 255, 0));
          }
        }
        return false;
      }
    });
  }
}
