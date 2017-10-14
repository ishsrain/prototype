package com.h2kresearch.iepread;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.StrictMode.ThreadPolicy;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TutorialActivity extends AppCompatActivity {

  ImageView imageView;
  MediaPlayer mediaPlayer;
  int resourceNumber;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tutorial);

    imageView = (ImageView) findViewById(R.id.imageView);

    int tutorial = getIntent().getIntExtra("tutorial", 1);
    switch (tutorial) {
      case 1:
        playTutorial1();
        break;
      case 2:
        playTutorial2();
        break;
    }
  }

  public void playTutorial1() {
    try {
      playInstructionAudio("i_t1_1");
      imageView.setImageResource(R.drawable.tutorial1);

      AnimationDrawable animation = (AnimationDrawable) imageView.getDrawable();
      animation.setOneShot(true);
      animation.start();

      AlertDialog.Builder alert_confirm = new AlertDialog.Builder(TutorialActivity.this);
      alert_confirm.setMessage("튜토리얼을 계속하시겠습니까?").setCancelable(false).setPositiveButton("네",
          new DialogInterface.OnClickListener() {
          @Override
              public void onClick(DialogInterface dialog, int which) {
                  // 'YES'
          }
        }).setNegativeButton("아니오",
          new DialogInterface.OnClickListener() {
          @Override
              public void onClick(DialogInterface dialog, int which) {
                  // 'No'
                  finish();
                  killMediaPlayer();
              }
        });
      AlertDialog alert = alert_confirm.create();
      alert.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void playTutorial2() {
    try {
      playInstructionAudio("i_t1_2");
      imageView.setImageResource(R.drawable.tutorial2);

      AnimationDrawable animation = (AnimationDrawable) imageView.getDrawable();
      animation.setOneShot(true);
      animation.start();
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(TutorialActivity.this);
        alert_confirm.setMessage("튜토리얼을 계속하시겠습니까?").setCancelable(false).setPositiveButton("네",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'YES'
                    }
                }).setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'No'
                        finish();
                        killMediaPlayer();
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
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

  private void playInstructionAudio(String filename) {
    killMediaPlayer();

    resourceNumber = getResources().getIdentifier(filename, "raw", getPackageName());

    mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceNumber);
    mediaPlayer.setLooping(false);
    mediaPlayer.start();

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mp) {
        // Complete
        finish();
      }
    });
  }
}
