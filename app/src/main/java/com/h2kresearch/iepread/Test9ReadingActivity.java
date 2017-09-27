package com.h2kresearch.iepread;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;

public class Test9ReadingActivity extends AppCompatActivity {

  TextView test;
  ProgressBar progressBar;
  Button nextButton;

  Thread thread;

  // Record Time (ms)
  int msTime = 15000;

  // Record/Play File
  public static String RECORDED_FILE;

  MediaPlayer player = null;
  MediaRecorder recorder = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test9reading);

    // Record
    File sdcard = Environment.getExternalStorageDirectory();
    File file = new File(sdcard, "recorded.mp4");
    RECORDED_FILE = file.getAbsolutePath();

    // TextView
    test = (TextView) findViewById(R.id.textView10);
    test.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

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

          }
        });

        thread.start();
        //recordFunction();
      }
    });

    // ProgressBar
    progressBar = (ProgressBar) findViewById(R.id.progressBar2);
    progressBar.setMax(msTime);

    // Next Button
    nextButton = (Button) findViewById(R.id.button4);
    nextButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), TestEndActivity.class);
        startActivity(intent);
      }
    });
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
}
