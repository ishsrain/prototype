package com.h2kresearch.iepread;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test1Vowel1Activity extends AppCompatActivity {

  TextView test1;
  TextView test2;
  TextView test3;

  Button button;
  ProgressBar progressBar;

  String[] test1String = {"아", "우", "우", "오", "오", "이", "유", "으", "아", "여"};
  String[] test2String = {"우", "이", "이", "아", "어", "오", "으", "우", "야", "오"};
  String[] test3String = {"", "", "", "", "", "요", "우", "아", "어", "어"}; // 얘는 6번 문제부터 보기가 주어짐
  int[] t1RightAnswers = {1, 2, 1, 1, 2, 3, 1, 1, 2, 1};

  // for playing question audio
  ImageView replayImage;
  MediaPlayer mediaPlayer;
  int resourceNumber;

  private void playQuestionAudio(int questionNumber) {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("t1_" + questionNumber, "raw", getPackageName());

    mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();
  }

  private void playInstructionAudio() {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("i_t1_1", "raw", getPackageName());

    mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            resourceNumber = getResources().getIdentifier("t1_1", "raw", getPackageName());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        }
    });
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

  int indexString = 1;
  int threeQuestionStartIndex = 5; // 5 + 1 = 6번부터 세 문제가 시작됨

  // for recording selected answers
  int[] t1Answers;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test1vowel1);

    progressBar = (ProgressBar) findViewById(R.id.progressBar3);
    progressBar.setMax(test1String.length);
    progressBar.setProgress(indexString);

    test1 = (TextView) findViewById(R.id.textView);
    test2 = (TextView) findViewById(R.id.textView2);
    test3 = (TextView) findViewById(R.id.textView3);

    // for recording selected answers
    t1Answers = new int[test1String.length];

    // for playing question audio
    playInstructionAudio();
    //playQuestionAudio(indexString);
    replayImage = (ImageView) findViewById(R.id.imageView8);

    replayImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        playQuestionAudio(indexString);
      }
    });

    button = (Button) findViewById(R.id.button2);
    button.setEnabled(false);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (indexString >= test1String.length) {
          // for playing question audio
          killMediaPlayer();

          try {
            Intent intent = new Intent(getBaseContext(), Test1Vowel2Activity.class);

            // Set JSONObject
            JSONObject result = new JSONObject();
            JSONObject info = new JSONObject(getIntent().getStringExtra("info"));
            JSONObject part = new JSONObject();
            JSONArray answer = new JSONArray();

            for(int i=0; i<t1Answers.length; i++) {
              JSONObject q = new JSONObject();
              q.put("index", i);
              if(t1Answers[i] == t1RightAnswers[i]) {
                q.put("correct", "true");
              } else {
                q.put("correct", "false");
              }
              q.put("student_answer", t1Answers[i]);
              answer.put(q);
            }

            part.put("objective", answer);
            result.put("part1", part);
            result.put("info", info);
            Log.d("Result1_1", result.toString());
            intent.putExtra("result", result.toString());

            // for recording selected answers
            intent.putExtra("info", getIntent().getStringExtra("info"));
            intent.putExtra("t1Answers", t1Answers);

            startActivity(intent);
          } catch (JSONException e) {
            e.printStackTrace();
          }
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
          t1Answers[indexString-1] = 1;
          Log.d("t1Answers checking", "index: "+indexString+" t1Answers: "+t1Answers[indexString-1]);
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
          t1Answers[indexString-1] = 2;
          Log.d("t1Answers checking", "index: "+indexString+" t1Answers: "+t1Answers[indexString-1]);

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
          t1Answers[indexString-1] = 3;
          Log.d("t1Answers checking", "index: "+indexString+" t1Answers: "+t1Answers[indexString-1]);

        }
        return false;
      }
    });
  }
}
