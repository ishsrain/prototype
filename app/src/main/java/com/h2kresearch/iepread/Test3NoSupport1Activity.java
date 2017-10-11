package com.h2kresearch.iepread;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Test3NoSupport1Activity extends AppCompatActivity {

  TextView test1;
  TextView test2;
  TextView test3;

  Button button;
  ProgressBar progressBar;

  String[] test1String = {"너", "로", "나", "브", "수", "쿠", "뱌", "여", "가", "표", "라", "디", "뎌", "캬"};
  String[] test2String = {"기", "브", "치", "더", "아", "자", "갸", "켜", "퓨", "뵤", "하", "니", "뗘", "꺄"};
  String[] test3String = {"", "", "", "", "", "", "", "리", "뮤", "더", "오", "자", "텨", "갸"}; // 얘는 9번 문제부터 보기가 주어짐

  int indexString = 1;
  int threeQuestionStartIndex = 7; // 8 + 1 = 9번부터 세 문제가 시작됨

  // for playing question audio
  ImageView replayImage;
  MediaPlayer mediaPlayer;
  int resourceNumber;

  // for recording selected answers
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;

  private void playQuestionAudio(int questionNumber) {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("t3_"+questionNumber, "raw", getPackageName());

    mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();
  }

  private void killMediaPlayer() {
    if (mediaPlayer != null) {
      try {
        mediaPlayer.release();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test3nosupport1);

    progressBar = (ProgressBar) findViewById(R.id.progressBar33);
    progressBar.setMax(test1String.length);
    progressBar.setProgress(1);

    test1 = (TextView) findViewById(R.id.option1);
    test2 = (TextView) findViewById(R.id.option2);
    test3 = (TextView) findViewById(R.id.option3);

    // for recording selected answers
    t3Answers = new int[test1String.length];

    // for playing question audio
    playQuestionAudio(indexString);
    replayImage = (ImageView) findViewById(R.id.imageView8);

    replayImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        playQuestionAudio(indexString);
      }
    });

    button = (Button) findViewById(R.id.nextButton);
    button.setEnabled(false);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (indexString >= test1String.length) {
          // for playing question audio
          killMediaPlayer();

          // for recording selected answers
          Intent pre_intent = getIntent();
          t1Answers = pre_intent.getIntArrayExtra("t1Answers");
          t2Answers = pre_intent.getIntArrayExtra("t2Answers");

          Intent intent = new Intent(getBaseContext(), Test3NoSupport2Activity.class);

          intent.putExtra("t1Answers", t1Answers);
          intent.putExtra("t2Answers", t2Answers);
          intent.putExtra("t3Answers", t3Answers);
          intent.putExtra("info", pre_intent.getStringExtra("info"));

          startActivity(intent);
        } else {
          button.setEnabled(false);
          //button.setBackgroundColor(Color.parseColor("#EDEDED"));

          test1.setBackground(getDrawable(R.drawable.roundcorner));
          test1.setTextColor(Color.parseColor("#404040"));
          test2.setBackground(getDrawable(R.drawable.roundcorner));
          test2.setTextColor(Color.parseColor("#404040"));

          test1.setText(test1String[indexString]);
          test2.setText(test2String[indexString]);

          if (indexString >= threeQuestionStartIndex) {
            test3.setVisibility(View.VISIBLE);
            test3.setBackground(getDrawable(R.drawable.roundcorner));
            test3.setText(test3String[indexString]);
            test3.setTextColor(Color.parseColor("#404040"));
          }

          indexString++;
          progressBar.setProgress(indexString);

          // for playing question audio
          playQuestionAudio(indexString);
        }
      }
    });

    test1.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
          button.setEnabled(true);
          //button.setBackgroundColor(Color.parseColor("#7ABC4F"));

          test1.setBackground(getDrawable(R.drawable.roundcorner_clicked));
          test1.setTextColor(Color.parseColor("#ffffff"));
          test2.setBackground(getDrawable(R.drawable.roundcorner));
          test2.setTextColor(Color.parseColor("#404040"));
          test3.setBackground(getDrawable(R.drawable.roundcorner));
          test3.setTextColor(Color.parseColor("#404040"));

          // for recording selected answers
          t3Answers[indexString-1] = 1;
        }
        return false;
      }
    });

    test2.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
          button.setEnabled(true);
          //button.setBackgroundColor(Color.parseColor("#7ABC4F"));

          test1.setBackground(getDrawable(R.drawable.roundcorner));
          test1.setTextColor(Color.parseColor("#404040"));
          test2.setBackground(getDrawable(R.drawable.roundcorner_clicked));
          test2.setTextColor(Color.parseColor("#ffffff"));
          test3.setBackground(getDrawable(R.drawable.roundcorner));
          test3.setTextColor(Color.parseColor("#404040"));

          // for recording selected answers
          t3Answers[indexString-1] = 2;

        }
        return false;
      }
    });

    test3.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
          button.setEnabled(true);
          //button.setBackgroundColor(Color.parseColor("#7ABC4F"));

          test1.setBackground(getDrawable(R.drawable.roundcorner));
          test1.setTextColor(Color.parseColor("#404040"));
          test2.setBackground(getDrawable(R.drawable.roundcorner));
          test2.setTextColor(Color.parseColor("#404040"));
          test3.setBackground(getDrawable(R.drawable.roundcorner_clicked));
          test3.setTextColor(Color.parseColor("#ffffff"));

          // for recording selected answers
          t3Answers[indexString-1] = 3;
        }
        return false;
      }
    });
  }
}
