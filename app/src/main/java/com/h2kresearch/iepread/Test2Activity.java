package com.h2kresearch.iepread;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;

public class Test2Activity extends AppCompatActivity {

  TextView test;
  ProgressBar progressBar;
  Thread thread;

  String[] testString = {"소나문", "소나무", "자전거", "자동차", "가지", "가재"};
  int indexString = 1;

  // Record Time (ms)
  int msTime = 1500;

  // Record/Play File
  public static String RECORDED_FILE;

  MediaPlayer player = null;
  MediaRecorder recorder = null;

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
        handler.sendEmptyMessage(0);
      }
    });

    // Record
    File sdcard = Environment.getExternalStorageDirectory();
    File file = new File(sdcard, "recorded.mp4");
    RECORDED_FILE = file.getAbsolutePath();

    // TextView
    test = (TextView) findViewById(R.id.textView9);
    test.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        test.setClickable(false);
        thread.start();
        recordFunction();
      }
    });

    // ProgressBar
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setMax(msTime);
  }

  private void recordFunction() {

    // Recording
    if (recorder != null) {
      recorder.stop();
      recorder.release();
      recorder = null;
    }

    recorder = new MediaRecorder();

    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
    recorder.setMaxDuration(3 * 1000);
    recorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
      @Override
      public void onInfo(MediaRecorder mr, int what, int extra) {
        if(what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED){
          if (recorder == null)
            return;

          recorder.stop();
          recorder.release();
          recorder = null;
          Toast.makeText(getApplicationContext(), "3초가 지나 녹음이 중지되었습니다", Toast.LENGTH_LONG).show();

          // Playing
          if (player != null) {
            player.stop();
            player.release();
            player = null;
          }

//          Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다.", Toast.LENGTH_LONG).show();
          try {
            player = new MediaPlayer();

            player.setDataSource(RECORDED_FILE);
            player.prepare();
            player.start();
          } catch (Exception e) {
            Log.e("SampleAudioRecorder", "Audio play failed.", e);
          }
        }
      }
    });

    recorder.setOutputFile(RECORDED_FILE);

    try {
//      Toast.makeText(getApplicationContext(), "녹음을 시작합니다.", Toast.LENGTH_LONG).show();

      recorder.prepare();
      recorder.start();
    } catch (Exception ex) {
      Log.e("SampleAudioRecorder", "Exception : ", ex);
    }
  }

  // Handler
  Handler handler = new Handler() {
    public void handleMessage(Message msg) {
      if (indexString < testString.length) {
        test.setText(testString[indexString]);
        test.setClickable(true);
        indexString++;
      } else {
        Intent intent = new Intent(getBaseContext(), Test3Activity.class);
        startActivity(intent);
      }
    }
  };
}
