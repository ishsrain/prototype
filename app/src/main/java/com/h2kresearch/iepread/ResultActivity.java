package com.h2kresearch.iepread;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ResultActivity extends AppCompatActivity
    implements MenuleftFragment.OnFragmentInteractionListener, Contents2Fragment.OnGradeListener,
    MenutopFragment.OnFragmentInteractionListener, Menutop2Fragment.OnFragmentInteractionListener, Menutop3Fragment.OnFragmentInteractionListener,
    ContentsFragment.OnFragmentInteractionListener, Contents2Fragment.OnFragmentInteractionListener, Contents3Fragment.OnFragmentInteractionListener, Contents4Fragment.OnFragmentInteractionListener, ContentsMonthFragment.OnFragmentInteractionListener, ContentsWeekFragment.OnFragmentInteractionListener {



  MenutopFragment menutopFragment;
  Menutop2Fragment menutop2Fragment;
  Menutop3Fragment menutop3Fragment;

  ContentsFragment contentsFragment;
  Contents2Fragment contents2Fragment;
  Contents3Fragment contents3Fragment;
  Contents4Fragment contents4Fragment;
  ContentsMonthFragment contentsMFragment;
  ContentsWeekFragment contentsWFragment;

  int month = 3;

  // 주관식 채점 결과 수신
  int[][] grades;

  // 객관식 학생 선택 답안 수신
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;
  int[] t4Answers;
  int[] t6Answers;
  int[] t7Answers;

  int[] t1RightAnswers = {1, 2, 1, 1, 2, 3, 1, 1, 2, 1};
  int[] t2RightAnswers = {2, 1, 1, 1, 2, 2, 1, 2, 1, 1, 2, 3, 3, 1, 2, 1, 1, 2, 3};
  int[] t3RightAnswers = {1, 1, 2, 1, 1, 2, 2, 1, 3, 1, 2, 1, 3, 1};
  int[] t4RightAnswers = {2, 1, 2, 2, 2, 1, 3, 3, 2, 1, 2};
  int[] t6RightAnswers = {1, 2, 1, 1, 3, 1, 3};
  int[] t7RightAnswers = {2, 2, 1, 1, 2, 3, 1};

  // 객관식 점수
  int t1Score = 0;
  int t2Score = 0;
  int t3Score = 0;
  int t4Score = 0;
  int t6Score = 0;
  int t7Score = 0;

  // 주관식 점수
  int t1Score2 = 0;
  int t2Score2 = 0;
  int t3Score2 = 0;
  int t4Score2 = 0;
  int t5Score2 = 0;
  int t6Score2 = 0;
  int t7Score2 = 0;
  int t8Score2 = 0;

  //FragmentTransaction ft;

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
    contentsMFragment = new ContentsMonthFragment();
    contentsWFragment = new ContentsWeekFragment();

    // 객관식 채점 결과 수신
    Intent pre_intent = getIntent();
    t1Answers = pre_intent.getIntArrayExtra("t1Answers");
    t2Answers = pre_intent.getIntArrayExtra("t2Answers");
    t3Answers = pre_intent.getIntArrayExtra("t3Answers");
    t4Answers = pre_intent.getIntArrayExtra("t4Answers");
    t6Answers = pre_intent.getIntArrayExtra("t6Answers");
    t7Answers = pre_intent.getIntArrayExtra("t7Answers");

    // 객관식 점수 계산
    for(int i=0; i<t1Answers.length; i++){
      if(t1Answers[i] == t1RightAnswers[i]){
        t1Score += 1;
      }
    }

    for(int i=0; i<t2Answers.length; i++){
      if(t2Answers[i] == t2RightAnswers[i]){
        t2Score += 1;
      }
    }

    for(int i=0; i<t3Answers.length; i++){
      if(t3Answers[i] == t3RightAnswers[i]){
        t3Score += 1;
      }
    }

    for(int i=0; i<t4Answers.length; i++){
      if(t4Answers[i] == t4RightAnswers[i]){
        t4Score += 1;
      }
    }

    for(int i=0; i<t6Answers.length; i++){
      if(t6Answers[i] == t6RightAnswers[i]){
        t6Score += 1;
      }
    }

    for(int i=0; i<t7Answers.length; i++){
      if(t7Answers[i] == t7RightAnswers[i]){
        t7Score += 1;
      }
    }

    Log.d("multiple grades sample", "t1score: "+t1Score+" t2score: "+t2Score+"t3score: "+t3Score);
    //ft = getSupportFragmentManager().beginTransaction();
  }

  public void onMenutopFragmentChanged(int index) {
    if(index == 1){ // 주관식 채점하기
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutopFragment).commit();  // 주관식 채점하기 메뉴
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents2Fragment).commit(); // 의미/무의미 단어 읽기 내용
    } else if(index == 2) { // 채점결과 학인하기
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop2Fragment).commit(); // 채점결과 확인하기 메뉴
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents3Fragment).commit(); // 객관식 채점결과 내용
    } else if(index == 3) { // IEP 확인하기
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop3Fragment).commit(); // IEP 확인하기 메뉴
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents4Fragment).commit(); // 학기별 교육내용 내용
    }
  }

  public void onContentsFragmentChanged(int index) {
    if(index == 1){ // 의미/무의미 단어 읽기
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents2Fragment).commit();
    } else if(index == 2) { // 앍기 유창성 검사
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsFragment).commit();
    } else if(index == 3) { // 채점 결과 확인하기

    } else if(index == 4) { // 학기별 교육 내용
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents4Fragment).commit();
    } else if(index == 5) { // 월별 교육 내용
      Bundle bundle = new Bundle();
      bundle.putInt("month", month);
      if (contentsMFragment.getArguments() == null){
        contentsMFragment.setArguments(bundle);
      } else {
        contentsMFragment.getArguments().putAll(bundle);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment).commit();
    } else if(index == 6) { // 주별 교육 내용
      Bundle bundle = new Bundle();
      bundle.putInt("month", month);
      if (contentsWFragment.getArguments() == null){
        contentsWFragment.setArguments(bundle);
      } else {
        contentsWFragment.getArguments().putAll(bundle);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsWFragment).commit();
    }

  }

  @Override
  public void onFragmentInteraction(Uri uri){

  }

  @Override
  public void onGradeSet(int[][] gradeResults){
    // 주관식 채점 결과 수신
    grades = gradeResults;
    t1Score2 = sum(grades[0]);
    t2Score2 = sum(grades[1]);
    t3Score2 = sum(grades[2]);
    t4Score2 = sum(grades[3]);
    t5Score2 = sum(grades[4]);
    t6Score2 = sum(grades[5]);
    t7Score2 = sum(grades[6]);
    t8Score2 = sum(grades[7]);

    Log.d("voice grades sample", "t1score2: "+t1Score2+" t2score2: "+t2Score2+"t3score2: "+t3Score2);
  }

  public int sum(int[] array) {
    int sum = 0;

    for (int i = 0; i < array.length; i++)
      sum += array[i];

    return sum;
  }
}
