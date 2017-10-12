package com.h2kresearch.iepread;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class SendActivity extends AppCompatActivity {

  ProgressBar progressBar;
  JSONObject result;
  String filePath, teacherEmail, studentNumber;
  int progress = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send);

    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setProgress(progress);

    int resourceNumber = getResources().getIdentifier("thanksfor", "raw", getPackageName());
    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();

//    filePath = "/storage/emulated/0/Download/IEPRead/김선생/홍창기/20171012_09242819";
//    teacherEmail = "id@naver.com";
//    studentNumber = "1234";

    try {
      result = new JSONObject(getIntent().getStringExtra("result"));
      Log.d("Final Result", result.toString());
      JSONObject info = result.getJSONObject("info");
      filePath = info.getString("filePath");
      teacherEmail = info.getString("teacherEmail");
      studentNumber = info.getString("studentNumber");

      File resultFile = new File(filePath + "/result.txt");
      FileOutputStream fos = new FileOutputStream(filePath + "/result.txt");
      fos.write(result.toString().getBytes());
      fos.flush();
      fos.close();

      startLoading();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

  public void startLoading() {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
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
              //String filename = "RECORDED_FILE";
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

              DataOutputStream wr = new DataOutputStream(httpUrlConnection.getOutputStream());

              // Result File
              String filetag = teacherEmail + studentNumber + "_";
              String filename = "result.txt";
              String filepath = filePath + "/" + filename;
              String uploadFilename = filetag + filename;

              // Start content wrapper
              wr.writeBytes(twoHyphens + boundary + crlf);
              wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                  + "\";filename=\"" + uploadFilename + "\"" + crlf);
              wr.writeBytes(crlf);

              // Read from FileInputStream and write to OutputStream
              if (filepath != null) {
                FileInputStream fileInputStream = new FileInputStream(filepath);
                int res = 1;
                byte[] buffer = new byte[10000];
                while (0 < (res = fileInputStream.read(buffer))) {
                  wr.write(buffer, 0, res);
                }
              }
              wr.writeBytes(crlf);

              // Test 1
              for (int i = 0; i < 3; i++) {

                filename = "q1_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 2
              for (int i = 0; i < 3; i++) {

                filename = "q2_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 3
              for (int i = 0; i < 3; i++) {

                filename = "q3_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 4
              for (int i = 0; i < 3; i++) {

                filename = "q4_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 5
              for (int i = 0; i < 8; i++) {

                filename = "q5_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 6
              for (int i = 0; i < 3; i++) {

                filename = "q6_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 7
              for (int i = 0; i < 3; i++) {

                filename = "q7_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 8
              for (int i = 0; i < 8; i++) {

                filename = "q8_" + (i + 1) + ".mp4";
                filepath = filePath + "/" + filename;
                uploadFilename = filetag + filename;

                wr.writeBytes(twoHyphens + boundary + crlf);
                wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                    + "\";filename=\"" + uploadFilename + "\"" + crlf);
                wr.writeBytes(crlf);

                // Read from FileInputStream and write to OutputStream
                if (filepath != null) {
                  FileInputStream fileInputStream = new FileInputStream(filepath);
                  int res = 1;
                  byte[] buffer = new byte[10000];
                  while (0 < (res = fileInputStream.read(buffer))) {
                    wr.write(buffer, 0, res);
                  }
                }
                wr.writeBytes(crlf);
              }

              // Test 9

              filename = "q9_1.mp4";
              filepath = filePath + "/" + filename;
              uploadFilename = filetag + filename;

              wr.writeBytes(twoHyphens + boundary + crlf);
              wr.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName
                  + "\";filename=\"" + uploadFilename + "\"" + crlf);
              wr.writeBytes(crlf);

              // Read from FileInputStream and write to OutputStream
              if (filepath != null) {
                FileInputStream fileInputStream = new FileInputStream(filepath);
                int res = 1;
                byte[] buffer = new byte[10000];
                while (0 < (res = fileInputStream.read(buffer))) {
                  wr.write(buffer, 0, res);
                }
              }
              wr.writeBytes(crlf);

              // Finish content wrapper
              wr.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
              wr.flush();
              wr.close();

//              // String Sending
//              OutputStream outputStream = httpUrlConnection.getOutputStream();
//              BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//              bufferedWriter.write("kimwoohyun");
//              bufferedWriter.flush();
//              bufferedWriter.close();
//              outputStream.close();
//
//              httpUrlConnection.connect();

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
      }
    }, 3000);
  }
}
