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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test6SimpleSupport1Activity extends AppCompatActivity {

  TextView test1;
  TextView test2;
  TextView test3;

  Button button;
  ProgressBar progressBar;

  String[] test1String = {"압", "앙", "악", "앙", "알", "알", "알"};
  String[] test2String = {"알", "앋", "암", "안", "압", "안", "앋"};
  String[] test3String = {"", "", "", "", "암", "악", "안"}; // 얘는 5번 문제부터 보기가 주어짐
  int[] t6RightAnswers = {1, 2, 1, 1, 3, 1, 3};

  int indexString = 1;
  int threeQuestionStartIndex = 4; // 4 + 1 = 5번부터 세 문제가 시작됨

  // for recording selected answers
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;
  int[] t4Answers;
  int[] t6Answers;

  // for playing question audio
  ImageView replayImage;
  MediaPlayer mediaPlayer;
  int resourceNumber;

  private void playInstructionAudio() {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("i_t6_1", "raw", getPackageName());

    mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mp) {
        resourceNumber = getResources().getIdentifier("t6_1", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
      }
    });
  }

  private void playQuestionAudio(int questionNumber) {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("t6_"+questionNumber, "raw", getPackageName());

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
    setContentView(R.layout.activity_test6simplesupport1);

    playInstructionAudio();

    progressBar = (ProgressBar) findViewById(R.id.progressBar33);
    progressBar.setMax(test1String.length);
    progressBar.setProgress(1);

    test1 = (TextView) findViewById(R.id.option1);
    test2 = (TextView) findViewById(R.id.option2);
    test3 = (TextView) findViewById(R.id.option3);

    // for recording selected answers
    t6Answers = new int[test1String.length];

    // for playing question audio
    //playQuestionAudio(indexString);
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

          try {
            Intent intent = new Intent(getBaseContext(), Test6SimpleSupport2Activity.class);
            //Intent intent = new Intent(getBaseContext(), ResultActivity.class);

            JSONObject result = new JSONObject(getIntent().getStringExtra("result"));
            JSONObject part = new JSONObject();
            JSONArray answer = new JSONArray();

            for(int i=0; i<t6Answers.length; i++) {
              JSONObject q = new JSONObject();
              q.put("index", i);
              if(t6Answers[i] == t6RightAnswers[i]) {
                q.put("correct", "true");
              } else {
                q.put("correct", "false");
              }
              q.put("student_answer", t6Answers[i]);
              answer.put(q);
            }

            part.put("objective", answer);
            result.put("part6", part);
            //Log.d("Result", result.toString());
            intent.putExtra("result", result.toString());

            // for recording selected answers
            Intent pre_intent = getIntent();
            t1Answers = pre_intent.getIntArrayExtra("t1Answers");
            t2Answers = pre_intent.getIntArrayExtra("t2Answers");
            t3Answers = pre_intent.getIntArrayExtra("t3Answers");
            t4Answers = pre_intent.getIntArrayExtra("t4Answers");
            intent.putExtra("t1Answers", t1Answers);
            intent.putExtra("t2Answers", t2Answers);
            intent.putExtra("t3Answers", t3Answers);
            intent.putExtra("t4Answers", t4Answers);
            intent.putExtra("t6Answers", t6Answers);
            intent.putExtra("info", pre_intent.getStringExtra("info"));

            startActivity(intent);
          } catch (JSONException e) {
            e.printStackTrace();
          }

//          // for recording selected answers
//          Intent pre_intent = getIntent();
//          t1Answers = pre_intent.getIntArrayExtra("t1Answers");
//          t2Answers = pre_intent.getIntArrayExtra("t2Answers");
//          t3Answers = pre_intent.getIntArrayExtra("t3Answers");
//          t4Answers = pre_intent.getIntArrayExtra("t4Answers");
//
//          Intent intent = new Intent(getBaseContext(), Test6SimpleSupport2Activity.class);
//
//          intent.putExtra("t1Answers", t1Answers);
//          intent.putExtra("t2Answers", t2Answers);
//          intent.putExtra("t3Answers", t3Answers);
//          intent.putExtra("t4Answers", t4Answers);
//          intent.putExtra("t6Answers", t6Answers);
//          intent.putExtra("info", pre_intent.getStringExtra("info"));
//
//          startActivity(intent);
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
          t6Answers[indexString-1] = 1;
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
          t6Answers[indexString-1] = 2;

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
          t6Answers[indexString-1] = 3;
        }
        return false;
      }
    });
  }
}
