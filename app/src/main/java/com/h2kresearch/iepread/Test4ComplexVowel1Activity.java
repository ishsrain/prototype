package com.h2kresearch.iepread;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test4ComplexVowel1Activity extends AppCompatActivity {

  TextView test1;
  TextView test2;
  TextView test3;

  Button button;
  ProgressBar progressBar;

  String[] test1String = {"오", "와", "아", "우", "이", "외", "우", "어", "이", "얘", "우"};
  String[] test2String = {"에", "으", "위", "애", "워", "오", "으", "애", "예", "이", "웨"};
  String[] test3String = {"", "", "", "", "", "아", "의", "왜", "아", "애", "에"}; // 얘는 6번 문제부터 보기가 주어짐
  int[] t4RightAnswers = {2, 1, 2, 2, 2, 1, 3, 3, 2, 1, 2};

  int indexString = 1;
  int threeQuestionStartIndex = 5; // 5 + 1 = 6번부터 세 문제가 시작됨

  // for recording selected answers
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;
  int[] t4Answers;

  // for playing question audio
  ImageView replayImage;
  MediaPlayer mediaPlayer;
  int resourceNumber;

  private void playInstructionAudio() {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("i_t4_1", "raw", getPackageName());

    mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mp) {
        resourceNumber = getResources().getIdentifier("t4_1", "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
      }
    });
  }

  private void playQuestionAudio(int questionNumber) {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("t4_"+questionNumber, "raw", getPackageName());

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
    setContentView(R.layout.activity_test4complexvowel1);

    playInstructionAudio();

    progressBar = (ProgressBar) findViewById(R.id.progressBar33);
    progressBar.setMax(test1String.length);
    progressBar.setProgress(1);

    test1 = (TextView) findViewById(R.id.option1);
    test2 = (TextView) findViewById(R.id.option2);
    test3 = (TextView) findViewById(R.id.option3);

    // for recording selected answers
    t4Answers = new int[test1String.length];

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
            Intent intent = new Intent(getBaseContext(), Test4ComplexVowel2Activity.class);

            JSONObject result = new JSONObject(getIntent().getStringExtra("result"));
            JSONObject part = new JSONObject();
            JSONArray answer = new JSONArray();

            for(int i=0; i<t4Answers.length; i++) {
              JSONObject q = new JSONObject();
              q.put("index", i);
              if(t4Answers[i] == t4RightAnswers[i]) {
                q.put("correct", "true");
              } else {
                q.put("correct", "false");
              }
              q.put("student_answer", t4Answers[i]);
              answer.put(q);
            }

            part.put("objective", answer);
            result.put("part4", part);
            //Log.d("Result", result.toString());
            intent.putExtra("result", result.toString());

            // for recording selected answers
            Intent pre_intent = getIntent();
            t1Answers = pre_intent.getIntArrayExtra("t1Answers");
            t2Answers = pre_intent.getIntArrayExtra("t2Answers");
            t3Answers = pre_intent.getIntArrayExtra("t3Answers");
            intent.putExtra("t1Answers", t1Answers);
            intent.putExtra("t2Answers", t2Answers);
            intent.putExtra("t3Answers", t3Answers);
            intent.putExtra("t4Answers", t4Answers);
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
//
//          Intent intent = new Intent(getBaseContext(), Test4ComplexVowel2Activity.class);
//
//          intent.putExtra("t1Answers", t1Answers);
//          intent.putExtra("t2Answers", t2Answers);
//          intent.putExtra("t3Answers", t3Answers);
//          intent.putExtra("t4Answers", t4Answers);
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
          t4Answers[indexString-1] = 1;
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
          t4Answers[indexString-1] = 2;

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
          t4Answers[indexString-1] = 3;
        }
        return false;
      }
    });

    TextView testStop = (TextView) findViewById(R.id.textView60);
    testStop.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(Test4ComplexVowel1Activity.this);
        alert_confirm.setMessage("진단을 중단하시겠습니까?");
        alert_confirm.setCancelable(false);

        alert_confirm.setPositiveButton("네", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            // 'YES'
            try {
              Intent intent = new Intent(getBaseContext(), TestEndActivity.class);

              // Set JSONObject
              JSONObject result = new JSONObject(getIntent().getStringExtra("result"));
              JSONObject part = new JSONObject();
              JSONArray answer = new JSONArray();

              for(int i=0; i<t4Answers.length; i++) {
                JSONObject q = new JSONObject();
                q.put("index", i);
                if(t4Answers[i] == t4RightAnswers[i]) {
                  q.put("correct", "true");
                } else {
                  q.put("correct", "false");
                }
                q.put("student_answer", t4Answers[i]);
                answer.put(q);
              }

              part.put("objective", answer);
              result.put("part4", part);
              //Log.d("Result", result.toString());
              intent.putExtra("result", result.toString());

              // for recording selected answers
              Intent pre_intent = getIntent();
              t1Answers = pre_intent.getIntArrayExtra("t1Answers");
              t2Answers = pre_intent.getIntArrayExtra("t2Answers");
              t3Answers = pre_intent.getIntArrayExtra("t3Answers");
              intent.putExtra("t1Answers", t1Answers);
              intent.putExtra("t2Answers", t2Answers);
              intent.putExtra("t3Answers", t3Answers);
              intent.putExtra("t4Answers", t4Answers);
              intent.putExtra("info", pre_intent.getStringExtra("info"));

              startActivity(intent);
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        });
        alert_confirm.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {}
        });

        AlertDialog alert = alert_confirm.create();
        alert.show();
      }
    });
  }

  @Override
  public void onBackPressed() {
    //super.onBackPressed();
  }
}
