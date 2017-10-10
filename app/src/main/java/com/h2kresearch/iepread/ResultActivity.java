package com.h2kresearch.iepread;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ResultActivity extends AppCompatActivity
    implements MenuleftFragment.OnFragmentInteractionListener, Contents2Fragment.OnGradeListener, Contents5Fragment.OnFragmentInteractionListener, Contents6Fragment.OnFragmentInteractionListener,
    MenutopFragment.OnFragmentInteractionListener, Menutop2Fragment.OnFragmentInteractionListener, Menutop3Fragment.OnFragmentInteractionListener,
    ContentsFragment.OnFragmentInteractionListener, Contents2Fragment.OnFragmentInteractionListener, Contents3Fragment.OnFragmentInteractionListener, Contents4Fragment.OnFragmentInteractionListener, ContentsMonthFragment.OnFragmentInteractionListener, ContentsWeekFragment.OnFragmentInteractionListener {

  MenutopFragment menutopFragment;
  Menutop2Fragment menutop2Fragment;
  Menutop3Fragment menutop3Fragment;

  ContentsFragment contentsFragment;
  Contents2Fragment contents2Fragment;
  Contents3Fragment contents3Fragment;
  Contents4Fragment contents4Fragment;
  Contents5Fragment contents5Fragment;
  ContentsMonthFragment contentsMFragment;
  ContentsWeekFragment contentsWFragment;
  Contents6Fragment contents6Fragment;

  int month = 3;

  // 진단 결과
  int diagnosisResult = 0;

  // 주관식 채점 여부
  int subjectiveCompleted = 0;

  // 진단 커트라인
  double cutThreshold = 0.8;

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
    contents5Fragment = new Contents5Fragment();
    contents6Fragment = new Contents6Fragment();
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

    // for test purposes
    if(t1Answers == null){
      t1Answers = new int[t1RightAnswers.length];
    }
    if(t2Answers == null){
      t2Answers = new int[t2RightAnswers.length];
    }
    if(t3Answers == null){
      t3Answers = new int[t3RightAnswers.length];
    }
    if(t4Answers == null){
      t4Answers = new int[t4RightAnswers.length];
    }
    if(t6Answers == null){
      t6Answers = new int[t6RightAnswers.length];
    }
    if(t7Answers == null){
      t7Answers = new int[t7RightAnswers.length];
    }

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
    Log.d("answers delivered", "t1Answers: "+t1Answers.length+" t2Answers: "+t2Answers.length);
    //ft = getSupportFragmentManager().beginTransaction();

    //test
    /*t1Score = t1RightAnswers.length;
    t2Score = t2RightAnswers.length;
    t3Score = t3RightAnswers.length;
    t4Score = t4RightAnswers.length - 5;
    t6Score = t6RightAnswers.length;
    t7Score = t7RightAnswers.length;*/
  }

  public void onMenutopFragmentChanged(int index) {
    if(index == 1){ // 주관식 채점하기
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutopFragment).commit();  // 주관식 채점하기 메뉴
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents2Fragment).commit(); // 의미/무의미 단어 읽기 내용
    } else if(index == 2) { // 채점결과 학인하기
      getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop2Fragment).commit(); // 채점결과 확인하기 메뉴

      Bundle bundleWithTestResults = new Bundle();
      bundleWithTestResults.putInt("t1Score", t1Score);
      bundleWithTestResults.putInt("t2Score", t2Score);
      bundleWithTestResults.putInt("t3Score", t3Score);
      bundleWithTestResults.putInt("t4Score", t4Score);
      bundleWithTestResults.putInt("t6Score", t6Score);
      bundleWithTestResults.putInt("t7Score", t7Score);

      bundleWithTestResults.putIntArray("t1Answers", t1Answers);
      bundleWithTestResults.putIntArray("t2Answers", t2Answers);
      bundleWithTestResults.putIntArray("t3Answers", t3Answers);
      bundleWithTestResults.putIntArray("t4Answers", t4Answers);
      bundleWithTestResults.putIntArray("t6Answers", t6Answers);
      bundleWithTestResults.putIntArray("t7Answers", t7Answers);

      bundleWithTestResults.putIntArray("t1RightAnswers", t1RightAnswers);
      bundleWithTestResults.putIntArray("t2RightAnswers", t2RightAnswers);
      bundleWithTestResults.putIntArray("t3RightAnswers", t3RightAnswers);
      bundleWithTestResults.putIntArray("t4RightAnswers", t4RightAnswers);
      bundleWithTestResults.putIntArray("t6RightAnswers", t6RightAnswers);
      bundleWithTestResults.putIntArray("t7RightAnswers", t7RightAnswers);

      if (contents3Fragment.getArguments() == null){
        contents3Fragment.setArguments(bundleWithTestResults);
      } else {
        contents3Fragment.getArguments().putAll(bundleWithTestResults);
      }

      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents3Fragment).commit(); // 객관식 채점결과 내용
    } else if(index == 3) { // IEP 확인하기
        Bundle bundle = new Bundle();
        bundle.putInt("diagnosisResult", diagnosisResult);
        if(contents4Fragment.getArguments() == null){
            contents4Fragment.setArguments(bundle);
        } else {
            contents4Fragment.getArguments().putAll(bundle);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.topMenuContainer, menutop3Fragment).commit(); // IEP 확인하기 메뉴
        getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents4Fragment).commit(); // 학기별 교육내용 내용
      if(subjectiveCompleted != 1) { // 주관식 채점이 안된 상태로 IEP를 확인하려고 할 때
          Log.d("IEP error", "보고 말하기 채점을 완료해주세요.");
      }
    }
  }

  public void onContentsFragmentChanged(int index) {
    if(index == 0){
      Intent intent = new Intent(getBaseContext(), SendActivity.class);
      startActivity(intent);
    }
    if(index == 1){ // 의미/무의미 단어 읽기
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents2Fragment).commit();
    } else if(index == 2) { // 앍기 유창성 검사
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsFragment).commit();
    } else if(index == 3) { // 객관식 채점 결과 확인하기
      Bundle bundleWithTestResults = new Bundle();
      bundleWithTestResults.putInt("t1Score", t1Score);
      bundleWithTestResults.putInt("t2Score", t2Score);
      bundleWithTestResults.putInt("t3Score", t3Score);
      bundleWithTestResults.putInt("t4Score", t4Score);
      bundleWithTestResults.putInt("t6Score", t6Score);
      bundleWithTestResults.putInt("t7Score", t7Score);

      bundleWithTestResults.putIntArray("t1Answers", t1Answers);
      bundleWithTestResults.putIntArray("t2Answers", t2Answers);
      bundleWithTestResults.putIntArray("t3Answers", t3Answers);
      bundleWithTestResults.putIntArray("t4Answers", t4Answers);
      bundleWithTestResults.putIntArray("t6Answers", t6Answers);
      bundleWithTestResults.putIntArray("t7Answers", t7Answers);

      bundleWithTestResults.putIntArray("t1RightAnswers", t1RightAnswers);
      bundleWithTestResults.putIntArray("t2RightAnswers", t2RightAnswers);
      bundleWithTestResults.putIntArray("t3RightAnswers", t3RightAnswers);
      bundleWithTestResults.putIntArray("t4RightAnswers", t4RightAnswers);
      bundleWithTestResults.putIntArray("t6RightAnswers", t6RightAnswers);
      bundleWithTestResults.putIntArray("t7RightAnswers", t7RightAnswers);

      if (contents3Fragment.getArguments() == null){
        contents3Fragment.setArguments(bundleWithTestResults);
      } else {
        contents3Fragment.getArguments().putAll(bundleWithTestResults);
      }

      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents3Fragment).commit();
    } else if(index == 4) { // 학기별 교육 내용
      Bundle bundle = new Bundle();
      bundle.putInt("diagnosisResult", diagnosisResult);
      if(contents4Fragment.getArguments() == null){
        contents4Fragment.setArguments(bundle);
      } else {
        contents4Fragment.getArguments().putAll(bundle);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents4Fragment).commit();
    } else if(index == 5) { // 월별 교육 내용
      Bundle bundle = new Bundle();
      bundle.putInt("diagnosisResult", diagnosisResult);
      if (contentsMFragment.getArguments() == null){
        contentsMFragment.setArguments(bundle);
      } else {
        contentsMFragment.getArguments().putAll(bundle);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsMFragment).commit();
    } else if(index == 6) { // 주별 교육 내용
      Bundle bundle = new Bundle();
      bundle.putInt("diagnosisResult", diagnosisResult);
      if (contentsWFragment.getArguments() == null){
        contentsWFragment.setArguments(bundle);
      } else {
        contentsWFragment.getArguments().putAll(bundle);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contentsWFragment).commit();
    } else if(index == 7) { // 주관식 채점 결과 확인하기
      Bundle bundleWithTestResults = new Bundle();
      bundleWithTestResults.putIntArray("t1Answers", grades[0]);
      bundleWithTestResults.putIntArray("t2Answers", grades[1]);
      bundleWithTestResults.putIntArray("t3Answers", grades[2]);
      bundleWithTestResults.putIntArray("t4Answers", grades[3]);
      bundleWithTestResults.putIntArray("t5Answers", grades[4]);
      bundleWithTestResults.putIntArray("t6Answers", grades[5]);
      bundleWithTestResults.putIntArray("t7Answers", grades[6]);
      bundleWithTestResults.putIntArray("t8Answers", grades[7]);

      bundleWithTestResults.putInt("t1Score", t1Score2);
      bundleWithTestResults.putInt("t2Score", t2Score2);
      bundleWithTestResults.putInt("t3Score", t3Score2);
      bundleWithTestResults.putInt("t4Score", t4Score2);
      bundleWithTestResults.putInt("t5Score", t5Score2);
      bundleWithTestResults.putInt("t6Score", t6Score2);
      bundleWithTestResults.putInt("t7Score", t7Score2);
      bundleWithTestResults.putInt("t8Score", t8Score2);

      if (contents5Fragment.getArguments() == null){
        contents5Fragment.setArguments(bundleWithTestResults);
      } else {
        contents5Fragment.getArguments().putAll(bundleWithTestResults);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents5Fragment).commit();
    } else if(index == 8) { // 최종 결과 확인하기
      Bundle bundleWithTestResults = new Bundle();

      bundleWithTestResults.putInt("t1ScoreFinal", t1Score + t1Score2);
      bundleWithTestResults.putInt("t2ScoreFinal", t2Score + t2Score2);
      bundleWithTestResults.putInt("t3ScoreFinal", t3Score + t3Score2);
      bundleWithTestResults.putInt("t4ScoreFinal", t4Score + t4Score2);
      bundleWithTestResults.putInt("t5ScoreFinal", t5Score2);
      bundleWithTestResults.putInt("t6ScoreFinal", t6Score + t6Score2);
      bundleWithTestResults.putInt("t7ScoreFinal", t7Score + t7Score2);
      bundleWithTestResults.putInt("t8ScoreFinal", t8Score2);

      if (contents6Fragment.getArguments() == null){
        contents6Fragment.setArguments(bundleWithTestResults);
      } else {
        contents6Fragment.getArguments().putAll(bundleWithTestResults);
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.contentsContainer, contents6Fragment).commit();
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

    if (((double) t1Score + t1Score2)/(t1RightAnswers.length + 3) < cutThreshold) { // 쉬운모음
        diagnosisResult = 0; // IEP 1단계
    } else if (((double) t2Score + t2Score2)/(t2RightAnswers.length + 3) < cutThreshold) { // 자음
        diagnosisResult = 1; // IEP 2단계
    } else if (((double) t3Score + t3Score2)/(t3RightAnswers.length + 3) < cutThreshold) { // 쉬운음절
        diagnosisResult = 1; // IEP 2단계
    } else if (((double) t4Score + t4Score2)/(t4RightAnswers.length + 3) < cutThreshold) { // 복잡한모음
        diagnosisResult = 2; // IEP 3단계
    } else if ((double) t5Score2 / 8 < cutThreshold) { // 쉬운단어
        diagnosisResult = 2; // IEP 3단계
    } else if (((double) t6Score + t6Score2)/(t6RightAnswers.length + 3) < cutThreshold) { // 쉬운받침
        diagnosisResult = 3; // IEP 4단계
    } else if (((double) t7Score + t7Score2)/(t7RightAnswers.length + 3) < cutThreshold) { // 복잡한받침
        diagnosisResult = 3; // IEP 4단계
    } else if ((double) t8Score2 / 8 < cutThreshold) { // 복잡한단어
        diagnosisResult = 4; // IEP 5단계
    } else { // 읽기유창성
        diagnosisResult = 5; // IEP 6단계
    }

    subjectiveCompleted = 1; //주관식 채점 완료

    Log.d("voice grades sample", "t1score2: "+t1Score2+" t2score2: "+t2Score2+"t3score2: "+t3Score2);
  }

  public int sum(int[] array) {
    int sum = 0;

    for (int i = 0; i < array.length; i++)
      sum += array[i];

    return sum;
  }
}
