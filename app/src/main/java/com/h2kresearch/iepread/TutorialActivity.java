package com.h2kresearch.iepread;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.StrictMode.ThreadPolicy;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TutorialActivity extends AppCompatActivity {

  ImageView imageView;

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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  MediaPlayer mediaPlayer;
  int resourceNumber;

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
