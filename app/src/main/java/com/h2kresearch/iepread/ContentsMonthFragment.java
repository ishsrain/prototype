package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentsMonthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentsMonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentsMonthFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  int diagnosisResult = 0; // 0은 모음(1단계)부터 가르쳐야 한다는 진단결과를 의미, 채점 결과를 통해 주어지는 값
  int month = 1; // activity에 표현할 month, 사용자 인풋으로 주어지는 값

  TextView mGoal;
  TextView mContent;
  TextView mMethod;
  TextView mExam;

  TextView month3, month4, month5, month6, month7;

    private static String[][] monthlyGoalString = {//앞 index: diagnosisResult, 뒤 index: month
          {"단모음 음가 파악", "단모음 음가 파악", "자음 음가 파악", "자음 음가 파악", "이중모음 음가 파악"},
          {"자음 음가 파악", "자음 음가 파악", "이중모음 음가 파악", "이중모음 음가 파악", "모음 종합 연습"},
          {"이중모음 음가 파악", "이중모음 음가 파악", "모음 종합 연습", "받침 음가 파악", "받침 음가 파악"},
          {"받침 음가 파악", "받침 음가 파악", "정확한 해독", "정확한 해독", "정확한 해독"},
          {"음운변동 규칙 파악", "음운변동 규칙 파악", "음운변동 규칙 파악", "겹받침 발음 연습", "겹받침 발음 연습"},
          {"유창성 훈련", "유창성 훈련", "유창성 훈련", "유창성 훈련", "유창성 훈련"}
  };

  private static String[][] monthlyContentString = {
          {
                  "1. 단모음 /ㅣ/, /ㅔ/, /ㅏ/, /ㅜ/, /ㅗ/, /ㅐ/, /ㅓ/, /ㅡ/ 소리-입모양-낱자 연결",
                  "1. 단모음 /ㅚ/와 /ㅟ/ 소리-입모양-낱자 연결 \n2. 단모음 10개 발음/분리/변별 연습",
                  "1. 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/, /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/, /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/, /ㅈ/, /ㅉ/, /ㅊ/ 소리-입모양-낱자 연결",
                  "1. 자음 /ㅅ/, /ㅆ/, /ㅎ/, /ㄹ/ 소리-입모양-낱자 연결 \n2. 자음 탐지/분리/변별/발음 연습",
                  "1. 모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 소리-입모양-낱자 연결"
          },
          {
                  "1. 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/, /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/, /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/, /ㅈ/, /ㅉ/, /ㅊ/ 소리-입모양-낱자 연결",
                  "1. 자음 /ㅅ/, /ㅆ/, /ㅎ/, /ㄹ/ 소리-입모양-낱자 연결 \n2. 자음 탐지/분리/변별/발음 연습",
                  "1. 모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 소리-입모양-낱자 연결",
                  "1. 모음 /ㅘ/, /ㅞ/, /ㅙ/, /ㅝ/, /ㅢ/ 소리-입모양-낱자 연결 \n2. 이중 모음 소리-입모양-낱자 연결 종합 연습",
                  "1. 모음 종합 소리-입모양-낱자 연결 \n2. 모음 종합 발음/분리/변별 연습"
          },
          {
                  "1. 모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 소리-입모양-낱자 연결",
                  "1. 모음 /ㅘ/, /ㅞ/, /ㅙ/, /ㅝ/, /ㅢ/ 소리-입모양-낱자 연결 \n2. 이중 모음 소리-입모양-낱자 연결 종합 연습",
                  "1. 모음 종합 소리-입모양-낱자 연결 \n2. 모음 종합 발음/분리/변별 연습",
                  "1. 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/, /ㄱ/, /ㄷ/, /ㅂ/ 소리-입모양-낱자 연결",
                  "1. 받침 분리/변별/발음 연습"
          },
          {
                  "1. 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/, /ㄱ/, /ㄷ/, /ㅂ/ 소리-입모양-낱자 연결",
                  "1. 받침 분리/변별/발음 연습",
                  "1. 의미 단어 읽기 \n2. 끝소리 규칙 /ㄱ/, /ㄲ/, /ㅋ/, /ㄴ/, /ㅇ/ 연습",
                  "1. 끝소리 규칙 /ㄷ/, /ㅅ/, /ㅆ/, /ㅈ/, /ㅊ/, /ㅌ/, /ㅎ/, /ㄹ/, /ㅁ/, /ㅂ/, /ㅍ/ 연습",
                  "1. 의미/ 무의미 단어 읽기 연습"
          },
          {
                  "1. 연음/ 격음화/ 'ㅎ' 탈락 규칙 연습",
                  "1. 구개음화/ 경음화 규칙 연습",
                  "1. 비음화/ 유음화/ 음소첨가/ 사이시옷 규칙 연습",
                  "1. 겹받침의 3가지 발음 방법 연습",
                  "1. 겹받침의 3가지 발음 방법 연습"
          },
          {
                  "1. 지도 하에 소리내서 반복읽기",
                  "1. 신경 각인법 연습",
                  "1. 단어목록 빨리 읽기",
                  "1. 메아리 읽기 연습",
                  "1. 유창성 훈련 종합 복습"
          }
  };

  private static String[][] monthlyMethodString = {
          {
                  "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용 \n2. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결. \n3. 교사의 지도를 따라 학생이 직접 발음해보도록 유도",
                  "1. 발음연습: 단모음 조합을 빠르고 정확하게 발음하는 연습 \n2. 분리연습: 최소대립쌍에 공통적으로 들어있는 모음의 소리를 정확하게 분리하는 연습\n3. 변별연습: 최소대립쌍 소리의 동일/상이 여부를 정확하게 변별하는 연습",
                  "1. 조음 위치가 같은 것끼리 묶어서 학습 \n2. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결 \n3. 교사의 지도를 따라 학생이 직접 발음해보도록 유도 \n4. 자음에 다양한 모음을 붙여 지도하여 모음을 지속적으로 복습",
                  "1. 탐지연습: 소리를 듣고 특정 자음의 음가를 정확하게 탐지하는 연습 \n2. 분리연습: 단어에서 초성의 소리를 정확하게 분리해내는 연습 \n3. 변별연습: 연속해서 불러주는 1음절의 최소대립쌍의 자음 음가의 동일/상이 여부를 정확하게 변별하는 연습 \n4. 발음연습: 서로 다른 자음에 차례로 모음을 붙여서 빠르고 정확하게 발음하는 연습",
                  "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용 \n2. 이중모음은 처음 조음 위치와 나중 조음 위치가 다름을 설명 \n3. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 는 모두 처음 조음 위치가 /ㅣ/ 임을 설명 \n4. 이중모음 /ㅒ/와 /ㅖ/를 변별하는 것은 현실적으로 어려움을 설명"
          },
          {
                  "1. 조음 위치가 같은 것끼리 묶어서 학습 \n2. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결. \n3. 교사의 지도를 따라 학생이 직접 발음해보도록 유도.\n4. 자음에 다양한 모음을 붙여 지도하여 모음을 지속적으로 복습.",
                  "1. 탐지연습: 소리를 듣고 특정 자음의 음가를 정확하게 탐지하는 연습\n2. 분리연습: 단어에서 초성의 소리를 정확하게 분리해내는 연습 \n3. 변별연습: 연속해서 불러주는 1음절의 최소대립쌍의 자음 음가의 동일/상이 여부를 정확하게 변별하는 연습\n4. 발음연습: 서로 다른 자음에 차례로 모음을 붙여서 빠르고 정확하게 발음하는 연습",
                  "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용\n2. 이중모음은 처음 조음 위치와 나중 조음 위치가 다름을 입모양을 활용해 설명\n3. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 는 모두 처음 조음 위치가 /ㅣ/ 임을 설명 \n4. 이중모음 /ㅒ/와 /ㅖ/를 변별하는 것은 현실적으로 어려움을 설명.",
                  "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용\n2. 이중모음은 처음 조음 위치와 나중 조음 위치가 다름을 입모양을 활용해 설명\n3. 이중모음 /ㅘ/는 /ㅗ/로 시작해 /ㅏ/로 끝나는 모음으로 설명\n4. 이중모음 /ㅞ/, /ㅙ/는 /ㅜ/로 시작해 /ㅔ/로 끝나는 모음으로 설명\n5. 이중모음 /ㅝ/는 /ㅜ/로 시작해 /ㅓ/로 끝나는 모음으로 설명\n6. 이중모음 /ㅢ/는 /ㅡ/로 시작해 /ㅣ/로 끝나는 모음으로 설명",
                  "1. 발음연습: 발음이 비슷한 모음을 연속해서 발음하는 연습\n2. 분리연습: 여러 낱말을 듣고, 해당 낱말 속에 공통적으로 들어있는 모음의 소리를 분리하는 연습\n3. 변별연습: 특정 음소만 다른 음절 두 개를 비교하여 차이를 느끼는지 알아보는 연습"
          },
          {
                  "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용\n2. 이중모음은 처음 조음 위치와 나중 조음 위치가 다름을 입모양을 활용해 설명\n3. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 는 모두 처음 조음 위치가 /ㅣ/ 임을 설명 \n4. 이중모음 /ㅒ/와 /ㅖ/를 변별하는 것은 현실적으로 어려움을 설명.",
                  "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용\n2. 이중모음은 처음 조음 위치와 나중 조음 위치가 다름을 입모양을 활용해 설명\n3. 이중모음 /ㅘ/는 /ㅗ/로 시작해 /ㅏ/로 끝나는 모음으로 설명\n4. 이중모음 /ㅞ/, /ㅙ/는 /ㅜ/로 시작해 /ㅔ/로 끝나는 모음으로 설명\n5. 이중모음 /ㅝ/는 /ㅜ/로 시작해 /ㅓ/로 끝나는 모음으로 설명\n6. 이중모음 /ㅢ/는 /ㅡ/로 시작해 /ㅣ/로 끝나는 모음으로 설명",
                  "1. 발음연습: 발음이 비슷한 모음을 연속해서 발음하는 연습\n2. 분리연습: 여러 낱말을 듣고, 해당 낱말 속에 공통적으로 들어있는 모음의 소리를 분리하는 연습\n3. 변별연습: 특정 음소만 다른 음절 두 개를 비교하여 차이를 느끼는지 알아보는 연습",
                  "1. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결. \n2. 받침의 음가 파악을 위해서는 지속적인 반복학습이 필요.",
                  "1. 분리연습: 음절체와 받침을 약간 띄어서 발음하기 연습\n2. 변별연습: 최소대립쌍 소리의 동일/상이 여부를 변별하는 연습\n3. 발음연습: 모음 다음에 받침이 닿으면 어떤 소리가 나는지 발음하는 연습"
          },
          {
                  "1. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결. \n2. 받침의 음가 파악을 위해서는 지속적인 반복학습이 필요.",
                  "1. 분리연습: 음절체와 받침을 약간 띄어서 발음하기 연습\n2. 변별연습: 최소대립쌍 소리의 동일/상이 여부를 변별하는 연습\n3. 발음연습: 모음 다음에 받침이 닿으면 어떤 소리가 나는지 발음하는 연습",
                  "1. 의미 단어 읽기: 특정 모음/자음/받침이 들어간 의미를 가진 단어를 읽기. 처음에는 소리내지 않고 읽다가, 한번에 빠르게 소리내서 읽도록 연습.\n2. 끝소리 규칙 연습: 같은 끝소리 규칙을 가지는 받침끼리 반복적으로 연습.",
                  "1. 끝소리 규칙 연습: 같은 끝소리 규칙을 가지는 받침끼리 반복적으로 연습",
                  "1. 무의미 단어 읽기: 흔히 보는 단어와 비슷하게 생겼지만 조금 다른 무의미 단어를 연습. 추측하여 읽는 습관 교정.\n2. 의미/무의미 단어 읽기: 한눈에 읽어도 되는 단어인지 음소 합성을 이용해 천천히 읽어야 하는 단어인지 해독 전략을 기름"
          },
          {
                  "1. 각 규칙의 원리 설명. 규칙 적용 전과 후의 차이 인식. \n2. 각 규칙을 정확하게 적용할 수 있도록 반복 연습",
                  "1. 각 규칙의 원리 설명. 규칙 적용 전과 후의 차이 인식. \n2. 각 규칙을 정확하게 적용할 수 있도록 반복 연습",
                  "1. 각 규칙의 원리 설명. 규칙 적용 전과 후의 차이 인식. \n2. 각 규칙을 정확하게 적용할 수 있도록 반복 연습",
                  "1. 겹받침의 3가지 발음 규칙의 원리를 설명. \n2. 각 규칙을 정확하게 적용할 수 있도록 반복 연습",
                  "1. 겹받침의 3가지 발음 규칙의 원리를 설명. \n2. 각 규칙을 정확하게 적용할 수 있도록 반복 연습"
          },
          {
                  "1. 한 번에 약 15분 정도로 짧게 자주 시행. \n2. 혼자 읽기에 힘들 정도(현재 수준보다 1년 높은 수준)의 지문이 좋음. \n3. 폰트는 20정도로, 50~200어절 정도의 지문이 좋음. ",
                  "1. 학생과 교사가 빠른 속도로 함께 읽는 연습. \n2. 학생이 함께 읽는 어른의 소리를 들음으로써 배울 수 있다는 이론에 근거한 방법.\n3. 10-15분 안에 가능한 많은 분량을 같이 읽는 것이 목표. 교사는 아이보다 약간 빨리, 크게 읽는 것이 포인트",
                  "1. 단어의 목록을 아주 빠르게 읽도록 연습. \n2. 한 단어 당 1초 이하 즉 1분에 최소 60단어를 읽을 수 있도록 되는 것이 목표.",
                  "1. 교사가 먼저 한 단락을 읽어주면 학생이 똑같이 소리 내어 읽는 방법. \n2. 소리내어 읽기를 격려할 수 있는 다양한 방법을 찾아 시행.",
                  "1. 지도 하에 소리내서 반복읽기 연습. \n2. 신경 각인법 연습.\n3. 단어목록 빨리 읽기 연습.\n4. 메아리 읽기 연습"
          }
  };

  private static String[][] monthlyExamString = {
          {
                  "1. 단모음 /ㅣ/, /ㅔ/, /ㅏ/, /ㅜ/, /ㅗ/, /ㅐ/, /ㅓ/, /ㅡ/ 의 음가를 정확하게 파악하였는가?",
                  "1. 단모음 /ㅚ/와 /ㅟ/ 의 음가를 정확하게 파악하였는가? \n2. 모든 단모음의 음가를 빠르고 정확하게 발음/분리/변별할 수 있는가?",
                  "1. 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/, /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/, /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/, /ㅈ/, /ㅉ/, /ㅊ/의 음가를 정확하게 파악하였는가?",
                  "1. 자음 /ㅅ/, /ㅆ/, /ㅎ/, /ㄹ/ 의 음가를 정확하게 파악하였는가? \n2. 모든 자음의 음가를 빠르고 정확하게 탐지/분리/변별/발음할 수 있는가?",
                  "1. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 의 음가를 정확하게 파악하였는가?"
          },
          {
                  "1. 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/, /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/, /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/, /ㅈ/, /ㅉ/, /ㅊ/의 음가를 정확하게 파악하였는가?",
                  "1. 자음 /ㅅ/, /ㅆ/, /ㅎ/, /ㄹ/ 의 음가를 정확하게 파악하였는가?\n2. 모든 자음의 음가를 빠르고 정확하게 탐지/분리/변별/발음할 수 있는가?",
                  "1. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 의 음가를 정확하게 파악하였는가? ",
                  "1. 이중모음 /ㅘ/, /ㅞ/, /ㅙ/, /ㅝ/, /ㅢ/ 의 음가를 정확하게 파악하였는가? \n2. 모든 이중모음의 음가를 정확하게 파악하였는가?",
                  "1. 모든 모음의 음가를 정확하게 파악하였는가? \n2. 모든 모음의 음가를 빠르고 정확하게 발음/분리/변별할 수 있는가?"
          },
          {
                  "1. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 의 음가를 정확하게 파악하였는가? ",
                  "1. 이중모음 /ㅘ/, /ㅞ/, /ㅙ/, /ㅝ/, /ㅢ/ 의 음가를 정확하게 파악하였는가? \n2. 모든 이중모음의 음가를 정확하게 파악하였는가?",
                  "1. 모든 모음의 음가를 정확하게 파악하였는가? \n2. 모든 모음의 음가를 빠르고 정확하게 발음/분리/변별할 수 있는가?",
                  "1. 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/, /ㄱ/, /ㄷ/, /ㅂ/ 의 음가를 정확하게 파악하였는가?",
                  "1. 모든 받침의 음가를 빠르고 정확하게 분리/변별/발음할 수 있는가?"
          },
          {
                  "1. 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/, /ㄱ/, /ㄷ/, /ㅂ/ 의 음가를 정확하게 파악하였는가?",
                  "1. 모든 받침의 음가를 빠르고 정확하게 분리/변별/발음할 수 있는가?",
                  "1. 의미를 가진 단어를 빠르고 정확하게 읽어낼 수 있는가?\n2. 각 받침별로 끝소리 규칙을 정확하게 적용할 수 있는가?",
                  "1. 각 받침별로 끝소리 규칙을 정확하게 적용할 수 있는가",
                  "1. 의미/무의미 단어를 빠르고 정확하게 읽어낼 수 있는가?"
          },
          {
                  "1. 음운변동 규칙을 정확하게 적용하였는가?",
                  "1. 음운변동 규칙을 정확하게 적용하였는가?",
                  "1. 음운변동 규칙을 정확하게 적용하였는가?",
                  "1. 겹받침 발음 규칙을 정확하게 적용하였는가?",
                  "1. 겹받침 발음 규칙을 정확하게 적용하였는가?"
          },
          {
                  "1. 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                  "1. 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                  "1. 주어진 단어를 빠르고 정확하게 읽어 냈는가?",
                  "1. 교사를 따라 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                  "1. 주어진 지문/ 단어를 빠르고 정확하게 읽어 냈는가?"
          }
  };

    // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  public ContentsMonthFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment Contents4Fragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ContentsMonthFragment newInstance(String param1, String param2) {
    ContentsMonthFragment fragment = new ContentsMonthFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
//    String sMonth = this.getArguments().getString("month");
//    month = Integer.parseInt(sMonth);
//    System.out.println(month);

    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contents_month, container, false);

    // Set the content of the TextView
    mGoal = (TextView) rootView.findViewById(R.id.textView37);
    mContent = (TextView) rootView.findViewById(R.id.textView38);
    mMethod = (TextView) rootView.findViewById(R.id.textView40);
    mExam = (TextView) rootView.findViewById(R.id.textView42);

    mGoal.setText(monthlyGoalString[diagnosisResult][month]); //첫 화면은 3월 IEP
    mContent.setText(monthlyContentString[diagnosisResult][month]);
    mMethod.setText(monthlyMethodString[diagnosisResult][month]);
    mExam.setText(monthlyExamString[diagnosisResult][month]);

    month3 = (TextView)rootView.findViewById(R.id.textView14);
    month4 = (TextView)rootView.findViewById(R.id.textView15);
    month5 = (TextView)rootView.findViewById(R.id.textView16);
    month6 = (TextView)rootView.findViewById(R.id.textView17);
    month7 = (TextView)rootView.findViewById(R.id.textView18);

    month3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month3.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.BLACK);
        month5.setTextColor(Color.BLACK);
        month6.setTextColor(Color.BLACK);
        month7.setTextColor(Color.BLACK);
        SetIEP(3);
      }
    });

    month4.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month4.setTextColor(Color.parseColor("#4a83c7"));
        month3.setTextColor(Color.BLACK);
        month5.setTextColor(Color.BLACK);
        month6.setTextColor(Color.BLACK);
        month7.setTextColor(Color.BLACK);
        SetIEP(4);
      }
    });

    month5.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month5.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.BLACK);
        month3.setTextColor(Color.BLACK);
        month6.setTextColor(Color.BLACK);
        month7.setTextColor(Color.BLACK);
        SetIEP(5);
      }
    });

    month6.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month6.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.BLACK);
        month5.setTextColor(Color.BLACK);
        month3.setTextColor(Color.BLACK);
        month7.setTextColor(Color.BLACK);
        SetIEP(6);
      }
    });

    month7.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month7.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.BLACK);
        month5.setTextColor(Color.BLACK);
        month6.setTextColor(Color.BLACK);
        month3.setTextColor(Color.BLACK);
        SetIEP(7);
      }
    });

    return rootView;

    /*
    TextView currState = (TextView) rootView.findViewById(R.id.textView37);
    SpannableString mainContent = new SpannableString(getResources().getString(R.string.example));
    currState.setText(mainContent, SPANNABLE);

    // Split the text in the TextView into words
    // Make the TextView clickable

    currState.setMovementMethod(new LinkMovementMethod());
    */

//    return inflater.inflate(R.layout.fragment_contents_month, container, false);
  }

  public void SetIEP(int month){
    mGoal.setText(monthlyGoalString[diagnosisResult][month-3]);
    mContent.setText(monthlyContentString[diagnosisResult][month-3]);
    mMethod.setText(monthlyMethodString[diagnosisResult][month-3]);
    mExam.setText(monthlyExamString[diagnosisResult][month-3]);
  }

  public void onMonthChanged() {
      String sMonth = this.getArguments().getString("month");
      month = Integer.parseInt(sMonth);
      System.out.println(month);

      mGoal.setText(monthlyGoalString[diagnosisResult][month]); //첫 화면은 3월 IEP
      mContent.setText(monthlyContentString[diagnosisResult][month]);
      mMethod.setText(monthlyMethodString[diagnosisResult][month]);
      mExam.setText(monthlyExamString[diagnosisResult][month]);
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
      System.out.println("clicked");
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
