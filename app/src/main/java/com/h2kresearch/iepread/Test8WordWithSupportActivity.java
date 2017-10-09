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

public class Test8WordWithSupportActivity extends AppCompatActivity {

  TextView test;
  ProgressBar progressBar;
  Thread thread;

  String[] testString = {"사람", "공간", "위안", "의술", "왜관", "축구", "잠옷", "굶다"};
  int indexString = 1;

  // Record Time (ms)
  int msTime = 1500;

  // Record/Play File
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
    File sdcard = Environment.getExternalStorageDirectory();
    File file = new File(sdcard, "recorded.mp4");
    RECORDED_FILE = file.getAbsolutePath();

    // TextView
    test = (TextView) findViewById(R.id.textView9);
    test.setLetterSpacing(0.3f);
    test.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        test.setClickable(false);

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

        thread.start();
        recordFunction();
      }
    });

    // ProgressBar
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setMax(msTime);
  }

  private void recordFunction() {

    try {
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
          if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
            if (recorder == null) {
              return;
            }

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

              // Server Test
              new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                  super.onPreExecute();
                }

                @Override
                protected Void doInBackground(Void... voids) {
                  try {
                    // Variables
                    String filename = RECORDED_FILE;
                    String stringUrl = "http://110.76.77.86:3000/android";
                    String attachmentName = "data";
                    String crlf = "\r\n";
                    String twoHyphens = "--";
                    String boundary = "*****";

                    //Setup the request
                    HttpURLConnection httpUrlConnection = null;
                    URL url = new URL(stringUrl);
                    httpUrlConnection = (HttpURLConnection) url.openConnection();
                    httpUrlConnection.setUseCaches(false);
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setDoInput(true);

                    httpUrlConnection.setRequestMethod("POST");
                    httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
                    httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
                    httpUrlConnection.setRequestProperty("Content-Type",
                        "multipart/form-data;boundary=" + boundary);

                    // Start content wrapper
                    DataOutputStream wr = new DataOutputStream(httpUrlConnection.getOutputStream());
                    wr.writeBytes(twoHyphens + boundary + crlf);
                    wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                        + "\";filename=\"" + "test1.mp4" + "\"" + crlf);
                    wr.writeBytes(crlf);

                    // Read from FileInputStream and write to OutputStream
                    if (filename != null) {
                      FileInputStream fileInputStream = new FileInputStream(filename);
                      int res = 1;
                      byte[] buffer = new byte[1000000];
                      while (0 < (res = fileInputStream.read(buffer))) {
                        //                      OutputStream os = httpUrlConnection.getOutputStream();
                        //                      os.write(buffer, 0, res);
                        //                      os.flush();
                        //                      os.close();
                        wr.write(buffer, 0, res);
                      }
                    }
                    wr.writeBytes(crlf);

                    wr.writeBytes(twoHyphens + boundary + crlf);
                    wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                        + "\";filename=\"" + "test2.mp4" + "\"" + crlf);
                    wr.writeBytes(crlf);

                    // Read from FileInputStream and write to OutputStream
                    if (filename != null) {
                      FileInputStream fileInputStream = new FileInputStream(filename);
                      int res = 1;
                      byte[] buffer = new byte[1000000];
                      while (0 < (res = fileInputStream.read(buffer))) {
                        //                      OutputStream os = httpUrlConnection.getOutputStream();
                        //                      os.write(buffer, 0, res);
                        //                      os.flush();
                        //                      os.close();
                        wr.write(buffer, 0, res);
                      }
                    }
                    wr.writeBytes(crlf);

                    // Finish content wrapper
                    wr.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
                    wr.flush();
                    wr.close();

                    // Response
                    InputStream responseStream = new BufferedInputStream(
                        httpUrlConnection.getInputStream());
                    BufferedReader responseStreamReader = new BufferedReader(
                        new InputStreamReader(responseStream));
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = responseStreamReader.readLine()) != null) {
                      stringBuilder.append(line).append("\n");
                    }
                    responseStreamReader.close();
                    String response = stringBuilder.toString();
                    int returnCode = httpUrlConnection.getResponseCode();

                    // Response Print
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    System.out.println(response);

                    // Disconnection
                    httpUrlConnection.disconnect();
                  } catch (MalformedURLException | ProtocolException exception) {
                    exception.printStackTrace();
                  } catch (IOException io) {
                    io.printStackTrace();
                  }
                  return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                  super.onPostExecute(aVoid);
                }
              }.execute();

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
        // for recording selected answers
        Intent pre_intent = getIntent();
        t1Answers = pre_intent.getIntArrayExtra("t1Answers");
        t2Answers = pre_intent.getIntArrayExtra("t2Answers");
        t3Answers = pre_intent.getIntArrayExtra("t3Answers");
        t4Answers = pre_intent.getIntArrayExtra("t4Answers");
        t6Answers = pre_intent.getIntArrayExtra("t6Answers");
        t7Answers = pre_intent.getIntArrayExtra("t7Answers");

        Intent intent = new Intent(getBaseContext(), Test9ReadingActivity.class);

        intent.putExtra("t1Answers", t1Answers);
        intent.putExtra("t2Answers", t2Answers);
        intent.putExtra("t3Answers", t3Answers);
        intent.putExtra("t4Answers", t4Answers);
        intent.putExtra("t6Answers", t6Answers);
        intent.putExtra("t7Answers", t7Answers);

        startActivity(intent);
      }
    }
  };
}
