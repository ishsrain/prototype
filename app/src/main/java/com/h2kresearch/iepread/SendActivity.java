package com.h2kresearch.iepread;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SendActivity extends AppCompatActivity {

  ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send);

    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setProgress(0);

    int resourceNumber = getResources().getIdentifier("thanksfor", "raw", getPackageName());
    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();

    //startLoading();
  }

  private void startLoading() {
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
              String filename = "RECORDED_FILE";
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
      }
    }, 3000);
  }
}
