package com.h2kresearch.iepread;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity
    implements MenuleftFragment.OnFragmentInteractionListener,
    MenutopFragment.OnFragmentInteractionListener, Menutop2Fragment.OnFragmentInteractionListener, Menutop3Fragment.OnFragmentInteractionListener,
    ContentsFragment.OnFragmentInteractionListener, Contents2Fragment.OnFragmentInteractionListener, Contents3Fragment.OnFragmentInteractionListener, Contents4Fragment.OnFragmentInteractionListener{

  MenutopFragment menutopFragment;
  Menutop2Fragment menutop2Fragment;
  Menutop3Fragment menutop3Fragment;

  ContentsFragment contentsFragment;
  Contents2Fragment contents2Fragment;
  Contents3Fragment contents3Fragment;
  Contents4Fragment contents4Fragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    menutopFragment = (MenutopFragment)getSupportFragmentManager().findFragmentById(R.id.fragment9);
    menutop2Fragment = new Menutop2Fragment();
    menutop3Fragment = new Menutop3Fragment();

    contents2Fragment = (Contents2Fragment)getSupportFragmentManager().findFragmentById(R.id.fragment10);
    contentsFragment = new ContentsFragment();
    contents3Fragment = new Contents3Fragment();
    contents4Fragment = new Contents4Fragment();

  }

  public void onMenutopFragmentChanged(int index) {
    if(index == 1){
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutopFragment).commit();
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents2Fragment).commit();
    } else if(index == 2) {
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop2Fragment).commit();
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents3Fragment).commit();
    } else if(index == 3) {
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop3Fragment).commit();
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents4Fragment).commit();
    }
  }

  public void onContentsFragmentChanged(int index) {
    if(index == 1){
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents2Fragment).commit();
    } else if(index == 2) {
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsFragment).commit();
    }
  }

  @Override
  public void onFragmentInteraction(Uri uri){

  }
}
