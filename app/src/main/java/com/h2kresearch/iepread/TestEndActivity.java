package com.h2kresearch.iepread;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestEndActivity extends AppCompatActivity {

  // for recording selected answers
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;
  int[] t4Answers;
  int[] t6Answers;
  int[] t7Answers;

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
        // for recording selected answers
        Intent pre_intent = getIntent();
        t1Answers = pre_intent.getIntArrayExtra("t1Answers");
        t2Answers = pre_intent.getIntArrayExtra("t2Answers");
        t3Answers = pre_intent.getIntArrayExtra("t3Answers");
        t4Answers = pre_intent.getIntArrayExtra("t4Answers");
        t6Answers = pre_intent.getIntArrayExtra("t6Answers");
        t7Answers = pre_intent.getIntArrayExtra("t7Answers");

        Intent intent = new Intent(getBaseContext(), ResultActivity.class);

        intent.putExtra("t1Answers", t1Answers);
        intent.putExtra("t2Answers", t2Answers);
        intent.putExtra("t3Answers", t3Answers);
        intent.putExtra("t4Answers", t4Answers);
        intent.putExtra("t6Answers", t6Answers);
        intent.putExtra("t7Answers", t7Answers);
        intent.putExtra("info", pre_intent.getStringExtra("info"));
        intent.putExtra("result", pre_intent.getStringExtra("result"));

        startActivity(intent);
        finish();
      }
    }, 1000);
  }
}
