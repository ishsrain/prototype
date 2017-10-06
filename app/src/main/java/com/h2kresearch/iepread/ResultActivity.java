package com.h2kresearch.iepread;

import android.app.Fragment;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity
    implements MenuleftFragment.OnFragmentInteractionListener,
    MenutopFragment.OnFragmentInteractionListener, Menutop2Fragment.OnFragmentInteractionListener, Menutop3Fragment.OnFragmentInteractionListener, Menutop_fragment_month.OnFragmentInteractionListener,
    ContentsFragment.OnFragmentInteractionListener, Contents2Fragment.OnFragmentInteractionListener, Contents3Fragment.OnFragmentInteractionListener, Contents4Fragment.OnFragmentInteractionListener, ContentsMonthFragment.OnFragmentInteractionListener, ContentsWeekFragment.OnFragmentInteractionListener {

  MenutopFragment menutopFragment;
  Menutop2Fragment menutop2Fragment;
  Menutop3Fragment menutop3Fragment;
  Menutop_fragment_month menutop3MonthFragment;

  ContentsFragment contentsFragment;
  Contents2Fragment contents2Fragment;
  Contents3Fragment contents3Fragment;
  Contents4Fragment contents4Fragment;
  ContentsMonthFragment contentsMFragment;
  ContentsWeekFragment contentsWFragment;

  //FragmentTransaction ft;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    menutopFragment = (MenutopFragment)getSupportFragmentManager().findFragmentById(R.id.fragment9);
    menutop2Fragment = new Menutop2Fragment();
    menutop3Fragment = new Menutop3Fragment();
    menutop3MonthFragment = new Menutop_fragment_month();

    contents2Fragment = (Contents2Fragment)getSupportFragmentManager().findFragmentById(R.id.fragment10);
    contentsFragment = new ContentsFragment();
    contents3Fragment = new Contents3Fragment();
    contents4Fragment = new Contents4Fragment();
    contentsMFragment = new ContentsMonthFragment();
    contentsWFragment = new ContentsWeekFragment();

    //ft = getSupportFragmentManager().beginTransaction();
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
    } else if(index == 6) {
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop3Fragment).commit();
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents4Fragment).commit();
    } else if(index == 7) {
        getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop3MonthFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putString("month", "0");
        if (contentsMFragment.getArguments() == null){
            contentsMFragment.setArguments(bundle);
        } else {
            contentsMFragment.getArguments().putAll(bundle);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment).commit();
    } else if(index == 8) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsWFragment).commit();
    } else if(index == 9) {
        //3월 IEP fragment 출력
        Bundle bundle = new Bundle();
        bundle.putString("month", "0");
        if (contentsMFragment.getArguments() == null){
            contentsMFragment.setArguments(bundle);
        } else {
            contentsMFragment.getArguments().putAll(bundle);
        }
        contentsMFragment.onMonthChanged();
    }  else if(index == 10) {
        //4월 IEP fragment 출력
        Bundle bundle = new Bundle();
        bundle.putString("month", "1");
        if (contentsMFragment.getArguments() == null){
            contentsMFragment.setArguments(bundle);
        } else {
            contentsMFragment.getArguments().putAll(bundle);
        }
        contentsMFragment.onMonthChanged();
        //getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment, "TAG_MONTH").commit();
    } else if(index == 11) {
        //5월 IEP fragment 출력
        Bundle bundle = new Bundle();
        bundle.putString("month", "2");
        if (contentsMFragment.getArguments() == null){
            contentsMFragment.setArguments(bundle);
        } else {
            contentsMFragment.getArguments().putAll(bundle);
        }
        contentsMFragment.onMonthChanged();
        //getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment, "TAG_MONTH").commit();
    } else if(index == 12) {
        //6월 IEP fragment 출력
        Bundle bundle = new Bundle();
        bundle.putString("month", "3");
        if (contentsMFragment.getArguments() == null){
            contentsMFragment.setArguments(bundle);
        } else {
            contentsMFragment.getArguments().putAll(bundle);
        }
        contentsMFragment.onMonthChanged();
        //getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment, "TAG_MONTH").commit();
    } else if(index == 13) {
        //7월 IEP fragment 출력
        Bundle bundle = new Bundle();
        bundle.putString("month", "4");
        if (contentsMFragment.getArguments() == null){
            contentsMFragment.setArguments(bundle);
        } else {
            contentsMFragment.getArguments().putAll(bundle);
        }
        contentsMFragment.onMonthChanged();
        //getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment, "TAG_MONTH").commit();
    }

  }

  @Override
  public void onFragmentInteraction(Uri uri){

  }
}
