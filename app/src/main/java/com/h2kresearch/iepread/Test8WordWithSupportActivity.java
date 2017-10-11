package com.h2kresearch.iepread;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test8WordWithSupportActivity extends AppCompatActivity {

  public static final int THREAD_STOP = 0;
  public static final int THREAD_START = 1;

  int recordState = RECORD_READY;
  public static final int RECORD_READY = 1;
  public static final int RECORD_START = 2;
  public static final int RECORD_STOP = 3;
  public static final int PLAY_START = 4;
  public static final int PLAY_STOP = 5;

  TextView test;
  Button next, retry;
  ImageView recordButton;
  ProgressBar progressBar, timeBar;
  Thread thread;

  String[] testString = {"사람", "공간", "위안", "의술", "왜관", "축구", "잠옷", "굶다"};
  int indexString = 1;

  // Record Time (ms)
  int msTime = 1500;

  // Record/Play File
  File sdcard;
  public static String RECORDED_FILE;
  MediaPlayer player;
  MediaRecorder recorder;

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
    setContentView(R.layout.activity_test8wordwithsupport);

    // Record
    sdcard = Environment.getExternalStorageDirectory();

    // ProgressBar
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setMax(testString.length);

    // Retry Button
    retry = (Button) findViewById(R.id.button5);
    retry.setVisibility(View.INVISIBLE);
    retry.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        recordState = RECORD_READY;
        handler.sendEmptyMessage(RECORD_READY);
      }
    });

    // Next Button
    next = (Button) findViewById(R.id.button3);
    next.setVisibility(View.INVISIBLE);
    next.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (indexString < testString.length) {
          test.setText(testString[indexString]);
          indexString++;
          progressBar.setProgress(indexString);
          recordState = RECORD_READY;
          handler.sendEmptyMessage(RECORD_READY);
        } else {

          try {
            Intent intent = new Intent(getBaseContext(), Test9ReadingActivity.class);

            JSONObject result = new JSONObject(getIntent().getStringExtra("result"));
            JSONObject part = new JSONObject();
            JSONArray answer = new JSONArray();

            for(int i=0; i<8; i++) {
              JSONObject q = new JSONObject();
              q.put("index", i);
              q.put("correct", "");
              answer.put(q);
            }

            part.put("voice", answer);
            result.put("part8", part);
            //Log.d("Result", result.toString());
            intent.putExtra("result", result.toString());

            // for recording selected answers
            Intent pre_intent = getIntent();
            t1Answers = pre_intent.getIntArrayExtra("t1Answers");
            t2Answers = pre_intent.getIntArrayExtra("t2Answers");
            t3Answers = pre_intent.getIntArrayExtra("t3Answers");
            t4Answers = pre_intent.getIntArrayExtra("t4Answers");
            t6Answers = pre_intent.getIntArrayExtra("t6Answers");
            t7Answers = pre_intent.getIntArrayExtra("t7Answers");
            intent.putExtra("t1Answers", t1Answers);
            intent.putExtra("t2Answers", t2Answers);
            intent.putExtra("t3Answers", t3Answers);
            intent.putExtra("t4Answers", t4Answers);
            intent.putExtra("t6Answers", t6Answers);
            intent.putExtra("t7Answers", t7Answers);
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
//          t6Answers = pre_intent.getIntArrayExtra("t6Answers");
//          t7Answers = pre_intent.getIntArrayExtra("t7Answers");
//
//          Intent intent = new Intent(getBaseContext(), Test9ReadingActivity.class);
//
//          intent.putExtra("t1Answers", t1Answers);
//          intent.putExtra("t2Answers", t2Answers);
//          intent.putExtra("t3Answers", t3Answers);
//          intent.putExtra("t4Answers", t4Answers);
//          intent.putExtra("t6Answers", t6Answers);
//          intent.putExtra("t7Answers", t7Answers);
//          intent.putExtra("info", pre_intent.getStringExtra("info"));
//
//          startActivity(intent);
        }
      }
    });

    // TextView
    test = (TextView) findViewById(R.id.textView9);
    test.setLetterSpacing(0.3f);

    // TimeBar
    timeBar = (ProgressBar) findViewById(R.id.progressBar6);
    timeBar.setMax(msTime);

    // Record Button
    handler.sendEmptyMessage(RECORD_READY);
    recordButton = (ImageView) findViewById(R.id.imageView10);
    recordButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        if(thread != null) {
          // Thread Stop
          ButtonStateChange(THREAD_STOP);
        }
        else {
          thread = new Thread(new Runnable() {
            @Override
            public void run() {
              try {
                // Progress Bar Working
                for (int i = 0; i < msTime; i++) {
                  timeBar.setProgress(i);
                  Thread.sleep(1);
                }
                // Thread Stop
                ButtonStateChange(THREAD_STOP);
              } catch (InterruptedException e) {}
            }
          });

          // Thread Start
          ButtonStateChange(THREAD_START);
          thread.start();
        }
      }
    });
  }

  private void RecordStart() {
    try {
      if (recorder != null) {
        recorder.stop();
        recorder.release();
        recorder = null;
      }

      // Get Path
      JSONObject info = new JSONObject(getIntent().getStringExtra("info"));
      String RECORDED_DIR = info.getString("filePath");
      RECORDED_FILE = RECORDED_DIR+"/q8_"+ Integer.toString(indexString)+".mp4";
      //Log.d("Recoded File Path", RECORDED_FILE);

      recorder = new MediaRecorder();
      recorder.setOutputFile(RECORDED_FILE);
      recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
      recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
      recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
      recorder.setMaxDuration(3 * 1000);
      recorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
        @Override
        public void onInfo(MediaRecorder mr, int what, int extra) {
          if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
            if (recorder == null) {
              return;
            }
            recorder.stop();
            recorder.release();
            recorder = null;
            Toast.makeText(getApplicationContext(), "3초가 지나 녹음이 중지되었습니다", Toast.LENGTH_LONG).show();
          }
        }
      });
      recorder.prepare();
//      Toast.makeText(getApplicationContext(), "녹음을 시작합니다.", Toast.LENGTH_LONG).show();
      recorder.start();
    } catch (Exception e) {
      Log.e("SampleAudioRecorder", "Exception : ", e);
    }
  }

  private void RecordStop() {
    try {
      if (recorder != null) {
        recorder.stop();
        recorder.release();
        recorder = null;
      }
    } catch (Exception e) {
      Log.e("SampleAudioRecorder", "Exception : ", e);
    }
  }

  private void PlayStart() {
    try {
      if (player != null) {
        player.stop();
        player.release();
        player = null;
      }
      player = new MediaPlayer();
      player.setDataSource(RECORDED_FILE);
      player.prepare();
//      Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다.", Toast.LENGTH_LONG).show();
      player.start();
    } catch (Exception e) {
      Log.e("SampleAudioRecorder", "Exception : ", e);
    }
  }

  private void PlayStop() {
    try {
      if (player != null) {
        player.stop();
        player.release();
        player = null;
      }
    } catch (Exception e) {
      Log.e("SampleAudioRecorder", "Exception : ", e);
    }
  }

  public void ButtonStateChange(int input){
    if(input == THREAD_START) {
      if(recordState == RECORD_READY) {
        // Record START
        recordState = RECORD_START;
        handler.sendEmptyMessage(RECORD_STOP);
        RecordStart();
      } else if(recordState == RECORD_STOP){
        // Play START
        recordState = PLAY_START;
        handler.sendEmptyMessage(RECORD_STOP);
        PlayStart();
      } else if(recordState == PLAY_STOP) {
        // Play START
        recordState = PLAY_START;
        handler.sendEmptyMessage(RECORD_STOP);
        PlayStart();
      }
    } else if(input == THREAD_STOP) {
      // Kill Thread
      thread.interrupt();
      thread = null;

      if (recordState == RECORD_START) {
        // Record Stop
        recordState = RECORD_STOP;
        handler.sendEmptyMessage(PLAY_START);
        RecordStop();
      } else if (recordState == PLAY_START) {
        // Play Stop
        recordState = PLAY_STOP;
        handler.sendEmptyMessage(PLAY_START);
        PlayStop();
      }
    }
  }

  // Handler
  Handler handler = new Handler() {
    public void handleMessage(Message msg) {
      try {
        if (msg.what == RECORD_READY || msg.what == RECORD_START) {
          timeBar.setProgress(0);
          retry.setVisibility(View.INVISIBLE);
          next.setVisibility(View.INVISIBLE);
          recordButton.setImageResource(R.drawable.record);
          recordButton.setEnabled(false);
          Thread.sleep(100);
          recordButton.setEnabled(true);
        } else if (msg.what == RECORD_STOP || msg.what == PLAY_STOP) {
          timeBar.setProgress(0);
          recordButton.setImageResource(R.drawable.stop);
          recordButton.setEnabled(false);
          Thread.sleep(100);
          recordButton.setEnabled(true);
        } else if (msg.what == PLAY_START) {
          timeBar.setProgress(0);
          retry.setVisibility(View.VISIBLE);
          next.setVisibility(View.VISIBLE);
          recordButton.setImageResource(R.drawable.replay);
          recordButton.setEnabled(false);
          Thread.sleep(100);
          recordButton.setEnabled(true);
        }
      } catch (InterruptedException e) {}
    }
  };
}
