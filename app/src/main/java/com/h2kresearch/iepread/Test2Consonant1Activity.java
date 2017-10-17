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

public class Test2Consonant1Activity extends AppCompatActivity {

  TextView test1;
  TextView test2;
  TextView test3;

  Button button;
  ProgressBar progressBar;

  String[] test1String = {"차", "자", "다", "아", "나", "바", "라", "다", "차", "나", "카", "짜", "자", "빠", "까", "타", "따", "마", "차"};
  String[] test2String = {"가", "카", "하", "다", "사", "하", "아", "싸", "짜", "다", "까", "바", "파", "마", "카", "다", "다", "바", "자"};
  String[] test3String = {"", "", "", "", "", "", "", "", "", "", "다", "파", "마", "다", "나", "하", "타", "파", "짜"}; // 얘는 11번 문제부터 보기가 주어짐

  int[] t2RightAnswers = {2, 1, 1, 1, 2, 2, 1, 2, 1, 1, 2, 3, 3, 1, 2, 1, 1, 2, 3};

  // for playing question audio
  ImageView replayImage;
  MediaPlayer mediaPlayer;
  int resourceNumber;

  private void playInstructionAudio() {
        killMediaPlayer();

        resourceNumber = getResources().getIdentifier("i_t2_1", "raw", getPackageName());

        mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

      mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
          @Override
          public void onCompletion(MediaPlayer mp) {
              resourceNumber = getResources().getIdentifier("t2_1", "raw", getPackageName());
              mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
              mediaPlayer.setLooping(false);
              mediaPlayer.start();
          }
      });

  }

  private void playQuestionAudio(int questionNumber) {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier("t2_"+questionNumber, "raw", getPackageName());

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

  int indexString = 1;
  int threeQuestionStartIndex = 10; // 10 + 1 = 11번부터 세 문제가 시작됨

  // for recording selected answers
  int[] t1Answers;
  int[] t2Answers;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test2consonant1);

    playInstructionAudio();

    progressBar = (ProgressBar) findViewById(R.id.progressBar33);
    progressBar.setMax(test1String.length);
    progressBar.setProgress(indexString);

    test1 = (TextView) findViewById(R.id.option1);
    test2 = (TextView) findViewById(R.id.option2);
    test3 = (TextView) findViewById(R.id.option3);

    // for recording selected answers
    t2Answers = new int[test1String.length];

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
            Intent intent = new Intent(getBaseContext(), Test2Consonant2Activity.class);
            //Intent intent = new Intent(getBaseContext(), ResultActivity.class);

            JSONObject result = new JSONObject(getIntent().getStringExtra("result"));
            JSONObject part = new JSONObject();
            JSONArray answer = new JSONArray();

            for(int i=0; i<t2Answers.length; i++) {
              JSONObject q = new JSONObject();
              q.put("index", i);
              if(t2Answers[i] == t2RightAnswers[i]) {
                q.put("correct", "true");
              } else {
                q.put("correct", "false");
              }
              q.put("student_answer", t2Answers[i]);
              answer.put(q);
            }

            part.put("objective", answer);
            result.put("part2", part);
            Log.d("Result2_1", result.toString());
            intent.putExtra("result", result.toString());

            // for recording selected answers
            Intent pre_intent = getIntent();
            t1Answers = pre_intent.getIntArrayExtra("t1Answers");
            intent.putExtra("t1Answers", t1Answers);
            intent.putExtra("t2Answers", t2Answers);
            intent.putExtra("info", pre_intent.getStringExtra("info"));

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
          t2Answers[indexString-1] = 1;
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
          t2Answers[indexString-1] = 2;

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
          t2Answers[indexString-1] = 3;
        }
        return false;
      }
    });

    TextView testStop = (TextView) findViewById(R.id.textView60);
    testStop.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(Test2Consonant1Activity.this);
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

              for(int i=0; i<t2Answers.length; i++) {
                JSONObject q = new JSONObject();
                q.put("index", i);
                if(t2Answers[i] == t2RightAnswers[i]) {
                  q.put("correct", "true");
                } else {
                  q.put("correct", "false");
                }
                q.put("student_answer", t2Answers[i]);
                answer.put(q);
              }

              part.put("objective", answer);
              result.put("part2", part);
              Log.d("Result2_1", result.toString());
              intent.putExtra("result", result.toString());

              // for recording selected answers
              Intent pre_intent = getIntent();
              t1Answers = pre_intent.getIntArrayExtra("t1Answers");
              intent.putExtra("t1Answers", t1Answers);
              intent.putExtra("t2Answers", t2Answers);
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
