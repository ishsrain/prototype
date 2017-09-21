package com.h2kresearch.iepread;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity
    implements ContentsFragment.OnFragmentInteractionListener, MenuleftFragment.OnFragmentInteractionListener, MenutopFragment.OnFragmentInteractionListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
  }

  @Override
  public void onFragmentInteraction(Uri uri){

  }
}
