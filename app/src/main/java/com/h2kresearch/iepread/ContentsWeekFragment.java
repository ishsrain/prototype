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
 * {@link ContentsWeekFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentsWeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentsWeekFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  int diagnosisResult = 0; // 0은 모음(1단계)부터 가르쳐야 한다는 진단결과를 의미, 채점 결과를 통해 주어지는 값
  int inputMonth = 0; // activity에 표현할 month, 사용자 인풋으로 주어지는 값

  TextView month3, month4, month5, month6, month7; // Menu
  TextView week1, week2, week3, week4; // Content

  private static String[][][] weeklyContent = { //1st index: diagnosisResult, 2nd index: month, 3rd index: week
          {
                  {
                          "교육내용: 단모음 /ㅣ/와 /ㅔ/ 소리-입모양-낱자 연결",
                          "교육내용: 단모음 /ㅏ/와 /ㅜ/ 소리-입모양-낱자 연결",
                          "교육내용: 단모음 /ㅗ/와 /ㅐ/ 소리-입모양-낱자 연결",
                          "교육내용: 단모음 /ㅓ/와 /ㅡ/ 소리-입모양-낱자 연결"
                  },
                  {
                          "교육내용: 단모음 /ㅚ/와 /ㅟ/ 소리-입모양-낱자 연결",
                          "교육내용: 단모음 10개 발음연습",
                          "교육내용: 단모음 10개 분리연습",
                          "교육내용: 단모음 10개 변별연습"
                  },
                  {
                          "교육내용: 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㅈ/, /ㅉ/, /ㅊ/ 소리-입모양-낱자 연결"
                  },
                  {
                          "교육내용: 자음 /ㅅ/와 /ㅆ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㅎ/와 /ㄹ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 탐지/분리 연습",
                          "교육내용: 자음 변별/발음 연습"
                  },
                  {
                          "교육내용: 모음 /ㅑ/와 /ㅛ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅕ/와 /ㅠ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅒ/와 /ㅖ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 종합 복습"
                  }
          },
          {
                  {
                          "교육내용: 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㅈ/, /ㅉ/, /ㅊ/ 소리-입모양-낱자 연결"
                  },
                  {
                          "교육내용: 자음 /ㅅ/와 /ㅆ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 /ㅎ/와 /ㄹ/ 소리-입모양-낱자 연결",
                          "교육내용: 자음 탐지/분리 연습",
                          "교육내용: 자음 변별/발음 연습"
                  },
                  {
                          "교육내용: 모음 /ㅑ/와 /ㅛ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅕ/와 /ㅠ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅒ/와 /ㅖ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 종합 복습"
                  },
                  {
                          "교육내용: 모음 /ㅘ/, /ㅞ/, /ㅙ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅝ/ 와 /ㅢ/ 소리-입모양-낱자 연결",
                          "교육내용: 이중 모음 소리-입모양-낱자 연결 종합 연습",
                          "교육내용: 이중 모음 소리-입모양-낱자 연결 종합 연습"
                  },
                  {
                          "교육내용: 모음 소리-입모양-낱자 연결",
                          "교육내용: 모음 종합 발음 연습",
                          "교육내용: 모음 종합 분리 연습",
                          "교육내용: 모음 종합 변별 연습"
                  }
          },
          {
                  {
                          "교육내용: 모음 /ㅑ/와 /ㅛ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅕ/와 /ㅠ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅒ/와 /ㅖ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 종합 복습"
                  },
                  {
                          "교육내용: 모음 /ㅘ/, /ㅞ/, /ㅙ/ 소리-입모양-낱자 연결",
                          "교육내용: 모음 /ㅝ/ 와 /ㅢ/ 소리-입모양-낱자 연결",
                          "교육내용: 이중 모음 소리-입모양-낱자 연결 종합 연습",
                          "교육내용: 이중 모음 소리-입모양-낱자 연결 종합 연습"
                  },
                  {
                          "교육내용: 모음 소리-입모양-낱자 연결",
                          "교육내용: 모음 종합 발음 연습",
                          "교육내용: 모음 종합 분리 연습",
                          "교육내용: 모음 종합 변별 연습"
                  },
                  {
                          "교육내용: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/ 소리-입모양-낱자 연결",
                          "교육내용: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/ 소리-입모양-낱자 연결",
                          "교육내용: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 소리-입모양-낱자 연결",
                          "교육내용: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 소리-입모양-낱자 연결"
                  },
                  {
                          "교육내용: 받침 분리연습",
                          "교육내용: 받침 변별연습",
                          "교육내용: 받침 발음연습",
                          "교육내용: 받침 분리/변별/발음 연습"
                  }
          },
          {
                  {
                          "교육내용: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/ 소리-입모양-낱자 연결",
                          "교육내용: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/ 소리-입모양-낱자 연결",
                          "교육내용: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 소리-입모양-낱자 연결",
                          "교육내용: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 소리-입모양-낱자 연결"
                  },
                  {
                          "교육내용: 받침 분리연습",
                          "교육내용: 받침 변별연습",
                          "교육내용: 받침 발음연습",
                          "교육내용: 받침 분리/변별/발음 연습"
                  },
                  {
                          "교육내용: 의미 단어 읽기",
                          "교육내용: 끝소리 규칙 /ㄱ/, /ㄲ/, /ㅋ/ -> /ㄱ/ 연습",
                          "교육내용: 끝소리 규칙 /ㄴ/ -> /ㄴ/ 연습",
                          "교육내용: 끝소리 규칙 /ㅇ/ -> /ㅇ/ 연습"
                  },
                  {
                          "교육내용: 끝소리 규칙 /ㄷ/, /ㅅ/, /ㅆ/, /ㅈ/, /ㅊ/, /ㅌ/, /ㅎ/ -> /ㄷ/ 연습",
                          "교육내용: 끝소리 규칙 /ㄹ/ -> /ㄹ/ 연습",
                          "교육내용: 끝소리 규칙 /ㅁ/ -> /ㅁ/ 연습",
                          "교육내용: 끝소리 규칙 /ㅂ, ㅍ/ -> /ㅂ/ 연습"
                  },
                  {
                          "교육내용: 무의미 단어 읽기",
                          "교육내용: 무의미 단어 읽기",
                          "교육내용: 의미/무의미 단어 읽기",
                          "교육내용: 의미/무의미 단어 읽기"
                  }
          },
          {
                  {
                          "교육내용: 연음규칙 연습",
                          "교육내용: 격음화('ㅎ' 뒤에서 거센소리로 소리 나는 경우) 규칙 연습",
                          "교육내용: 격음화(뒤 음절의 첫소리 'ㅎ'이 거센소리로 소리 나는 경우) 규칙 연습",
                          "교육내용: 'ㅎ' 탈락 규칙 연습"
                  },
                  {
                          "교육내용: 구개음화 규칙 연습",
                          "교육내용: 경음화(앞 음절의 받침소리가 'ㄱ, ㄷ, ㅂ' 일 때) 규칙 연습",
                          "교육내용: 경음화(앞 음절의 받침소리가 'ㄴ, ㄹ, ㅁ, ㅇ'일 때) 규칙 연습",
                          "교육내용: 경음화(앞 음절의 마지막 소리가 모음일 때) 규칙 연습"
                  },
                  {
                          "교육내용: 비음화 규칙 연습",
                          "교육내용: 유음화 규칙 연습",
                          "교육내용: 음소첨가 규칙 연습",
                          "교육내용: 사이시옷 규칙 연습"
                  },
                  {
                          "교육내용: 앞에 있는 자음으로 소리 나는 겹받침 발음 연습",
                          "교육내용: 앞에 있는 자음으로 소리 나는 겹받침 발음 연습",
                          "교육내용: 뒤에 있는 자음으로 소리 나는 겹받침 발음 연습",
                          "교육내용: 뒤에 있는 자음으로 소리 나는 겹받침 발음 연습"
                  },
                  {
                          "교육내용: 'ㅎ'이 탈락되는 겹받침",
                          "교육내용: 'ㅎ'이 탈락되는 겹받침",
                          "교육내용: 겹받침 발음 종합 연습",
                          "교육내용: 겹받침 발음 종합 연습"
                  }
          },
          {
                  {
                          "교육내용: 지도 하에 소내내서 반복 읽기 연습",
                          "교육내용: 지도 하에 소내내서 반복 읽기 연습",
                          "교육내용: 지도 하에 소내내서 반복 읽기 연습",
                          "교육내용: 지도 하에 소내내서 반복 읽기 연습"
                  },
                  {
                          "교육내용: 신경각인법 연습",
                          "교육내용: 신경각인법 연습",
                          "교육내용: 신경각인법 연습",
                          "교육내용: 신경각인법 연습"
                  },
                  {
                          "교육내용: 단어목록 빨리 읽기 연습",
                          "교육내용: 단어목록 빨리 읽기 연습",
                          "교육내용: 단어목록 빨리 읽기 연습",
                          "교육내용: 단어목록 빨리 읽기 연습"
                  },
                  {
                          "교육내용: 메아리 읽기 연습",
                          "교육내용: 메아리 읽기 연습",
                          "교육내용: 메아리 읽기 연습",
                          "교육내용: 메아리 읽기 연습"
                  },
                  {
                          "교육내용: 지도 하에 소리내서 반복읽기 연습",
                          "교육내용: 신경 각인법 연습",
                          "교육내용: 단어목록 빨리 읽기 연습",
                          "교육내용: 메아리 읽기 연습"
                  }
          }
  };

  private static String[][][] weeklyExam = {
          {
                  {
                          "평가: 단모음 /ㅣ/와 /ㅔ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 단모음 /ㅏ/와 /ㅜ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 단모음 /ㅗ/와 /ㅐ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 단모음 /ㅓ/와 /ㅡ/ 의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 단모음 /ㅚ/와 /ㅟ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 주어진 단모음 조합을 빠르고 정확히 발음하였는가?",
                          "평가: 최소대립쌍에 공통적으로 들어있는 단모음의 소리를 정확히 분리하였는가?",
                          "평가: 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가?"
                  },
                  {
                          "평가: 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㅈ/, /ㅉ/, /ㅊ/ 의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 자음 /ㅅ/와 /ㅆ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㅎ/와 /ㄹ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 주어진 단어에서 특정 자음의 음가 혹은 초성의 소리를 정확히 탐지 및 분리하였는가?",
                          "평가: 1. 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가? \n2. 주어진 자음 조합(모음은 동일)을 빠르고 정확히 발음하였는가?"
                  },
                  {
                          "평가: 모음 /ㅑ/와 /ㅛ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅕ/와 /ㅠ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅒ/와 /ㅖ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음의 음가를 정확히 파악하였는가?"
                  }
          },
          {
                  {
                          "평가: 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㅈ/, /ㅉ/, /ㅊ/ 의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 자음 /ㅅ/와 /ㅆ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 자음 /ㅎ/와 /ㄹ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 주어진 단어에서 특정 자음의 음가 혹은 초성의 소리를 정확히 탐지 및 분리하였는가?",
                          "평가: 1. 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가? \n2. 주어진 자음 조합(모음은 동일)을 빠르고 정확히 발음하였는가?"
                  },
                  {
                          "평가: 모음 /ㅑ/와 /ㅛ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅕ/와 /ㅠ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅒ/와 /ㅖ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 모음 /ㅘ/, /ㅞ/, /ㅙ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅝ/ 와 /ㅢ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모든 이중모음의 음가를 정확히 파악하였는가?",
                          "평가: 모든 이중모음의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 모든 모음의 음가를 정확히 파악하였는가?",
                          "평가: 모든 모음을 빠르고 정확히 발음하였는가?",
                          "평가: 낱말에 공통적으로 들어있는 모음의 소리를 정확히 분리하였는가?",
                          "평가: 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가?"
                  }
          },
          {
                  {
                          "평가: 모음 /ㅑ/와 /ㅛ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅕ/와 /ㅠ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅒ/와 /ㅖ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 모음 /ㅘ/, /ㅞ/, /ㅙ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모음 /ㅝ/ 와 /ㅢ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 모든 이중모음의 음가를 정확히 파악하였는가?",
                          "평가: 모든 이중모음의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 모든 모음의 음가를 정확히 파악하였는가?",
                          "평가: 모든 모음을 빠르고 정확히 발음하였는가?",
                          "평가: 낱말에 공통적으로 들어있는 모음의 소리를 정확히 분리하였는가?",
                          "평가: 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가?"
                  },
                  {
                          "평가: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/의 음가를 정확히 파악하였는가?",
                          "평가: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/의 음가를 정확히 파악하였는가?",
                          "평가: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 낱말에 공통적으로 들어있는 받침의 소리를 정확히 분리하였는가?",
                          "평가: 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가?",
                          "평가: 모음에 받침이 닿을 때의 소리를 빠르고 정확히 발음하였는가?",
                          "평가: 모든 받침의 음가를 빠르고 정확하게 분리/변별/발음할 수 있는가?"
                  }
          },
          {
                  {
                          "평가: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/의 음가를 정확히 파악하였는가?",
                          "평가: 받침 /ㄴ/, /ㅁ/, /ㅇ/, /ㄹ/의 음가를 정확히 파악하였는가?",
                          "평가: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 의 음가를 정확히 파악하였는가?",
                          "평가: 받침 /ㄱ/, /ㄷ/, /ㅂ/ 의 음가를 정확히 파악하였는가?"
                  },
                  {
                          "평가: 낱말에 공통적으로 들어있는 받침의 소리를 정확히 분리하였는가?",
                          "평가: 최소대립쌍 소리의 동일/상이 여부를 정확히 변별하였는가?",
                          "평가: 모음에 받침이 닿을 때의 소리를 빠르고 정확히 발음하였는가?",
                          "평가: 모든 받침의 음가를 빠르고 정확하게 분리/변별/발음할 수 있는가?"
                  },
                  {
                          "평가: 의미를 가진 단어를 빠르고 정확하게 읽어냈는가?",
                          "평가: /ㄱ/, /ㄲ/, /ㅋ/ 받침은 /ㄱ/ 의 음가로 변하는 규칙을 정확히 적용하고 있는가?",
                          "평가: /ㄴ/ 받침은 /ㄴ/ 의 음가 그대로 발음하는 규칙을 정확히 적용하고 있는가?",
                          "평가: /ㅇ/ 받침은 /ㅇ/ 의 음가 그대로 발음하는 규칙을 정확히 적용하고 있는가?"
                  },
                  {
                          "평가: /ㄷ/, /ㅅ/, /ㅆ/, /ㅈ/, /ㅊ/, /ㅌ/, /ㅎ/ 받침은 /ㄷ/ 의 음가로 변하는 규칙을 정확히 적용하고 있는가?",
                          "평가: /ㄹ/ 받침은 /ㄹ/의 음가 그대로 발음하는 규칙을 정확히 적용하고 있는가?",
                          "평가: /ㅁ/ 받침은 /ㅁ/의 음가 그대로 발음하는 규칙을 정확히 적용하고 있는가?",
                          "평가: /ㅂ/, /ㅍ/ 받침은 /ㅂ/의 음가로 변하는 규칙을 정확히 적용하고 있는가?"
                  },
                  {
                          "평가: 무의미 단어를 빠르고 정확히 읽어냈는가?",
                          "평가: 무의미 단어를 빠르고 정확히 읽어냈는가?",
                          "평가: 의미/무의미 단어를 빠르고 정확히 읽어냈는가?",
                          "평가: 의미/무의미 단어를 빠르고 정확히 읽어냈는가?"
                  }
          },
          {
                  {
                          "평가: 연음 규칙을 정확하게 적용하였는가?",
                          "평가: 격음화 규칙을 정확하게 적용하였는가?",
                          "평가: 격음화 규칙을 정확하게 적용하였는가?",
                          "평가:  'ㅎ' 탈락 규칙을 정확하게 적용하였는가?"
                  },
                  {
                          "평가: 구개음화 규칙을 정확하게 적용하였는가?",
                          "평가: 경음화 규칙을 정확하게 적용하였는가?",
                          "평가: 경음화 규칙을 정확하게 적용하였는가?",
                          "평가: 경음화 규칙을 정확하게 적용하였는가?"
                  },
                  {
                          "평가: 비음화 규칙을 정확하게 적용하였는가?",
                          "평가: 유음화 규칙을 정확하게 적용하였는가?",
                          "평가: 음소첨가 규칙을 정확하게 적용하였는가?",
                          "평가: 사이시옷 규칙을 정확하게 적용하였는가?"
                  },
                  {
                          "평가: 겹받침 발음 규칙을 정확하게 적용하였는가?",
                          "평가: 겹받침 발음 규칙을 정확하게 적용하였는가?",
                          "평가: 겹받침 발음 규칙을 정확하게 적용하였는가?",
                          "평가: 겹받침 발음 규칙을 정확하게 적용하였는가?"
                  }
          },
          {
                  {
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?"
                  },
                  {
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문을 빠르고 정확하게 읽어 냈는가?"
                  },
                  {
                          "평가: 주어진 단어를 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 단어를 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 단어를 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 단어를 빠르고 정확하게 읽어 냈는가?"
                  },
                  {
                          "평가: 교사를 따라 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 교사를 따라 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 교사를 따라 주어진 지문을 빠르고 정확하게 읽어 냈는가?",
                          "평가: 교사를 따라 주어진 지문을 빠르고 정확하게 읽어 냈는가?"
                  },
                  {
                          "평가: 주어진 지문/ 단어를 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문/ 단어를 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문/ 단어를 빠르고 정확하게 읽어 냈는가?",
                          "평가: 주어진 지문/ 단어를 빠르고 정확하게 읽어 냈는가?"
                  }
          }
  };

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  public ContentsWeekFragment() {
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
  public static ContentsWeekFragment newInstance(String param1, String param2) {
    ContentsWeekFragment fragment = new ContentsWeekFragment();
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
    diagnosisResult = this.getArguments().getInt("diagnosisResult");

    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contents_week, container, false);

    // Set the content of the TextView
    week1 = (TextView) rootView.findViewById(R.id.textView37);
    week2 = (TextView) rootView.findViewById(R.id.textView38);
    week3 = (TextView) rootView.findViewById(R.id.textView40);
    week4 = (TextView) rootView.findViewById(R.id.textView42);

    week1.setText(weeklyContent[diagnosisResult][inputMonth][0] + "\n" + weeklyExam[diagnosisResult][inputMonth][0]); // 첫화면은 3월 IEP 1주차
    week2.setText(weeklyContent[diagnosisResult][inputMonth][1] + "\n" + weeklyExam[diagnosisResult][inputMonth][1]); // 2주차
    week3.setText(weeklyContent[diagnosisResult][inputMonth][2] + "\n" + weeklyExam[diagnosisResult][inputMonth][2]); // 3주차
    week4.setText(weeklyContent[diagnosisResult][inputMonth][3] + "\n" + weeklyExam[diagnosisResult][inputMonth][3]); // 4주차

    month3 = (TextView)rootView.findViewById(R.id.textView14);
    month4 = (TextView)rootView.findViewById(R.id.textView15);
    month5 = (TextView)rootView.findViewById(R.id.textView16);
    month6 = (TextView)rootView.findViewById(R.id.textView17);
    month7 = (TextView)rootView.findViewById(R.id.textView18);
    month3.setTextColor(Color.parseColor("#4a83c7"));

    month3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month3.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.parseColor("#404040"));
        month5.setTextColor(Color.parseColor("#404040"));
        month6.setTextColor(Color.parseColor("#404040"));
        month7.setTextColor(Color.parseColor("#404040"));
        SetIEP(3);
      }
    });

    month4.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month4.setTextColor(Color.parseColor("#4a83c7"));
        month3.setTextColor(Color.parseColor("#404040"));
        month5.setTextColor(Color.parseColor("#404040"));
        month6.setTextColor(Color.parseColor("#404040"));
        month7.setTextColor(Color.parseColor("#404040"));
        SetIEP(4);
      }
    });

    month5.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month5.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.parseColor("#404040"));
        month3.setTextColor(Color.parseColor("#404040"));
        month6.setTextColor(Color.parseColor("#404040"));
        month7.setTextColor(Color.parseColor("#404040"));
        SetIEP(5);
      }
    });

    month6.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month6.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.parseColor("#404040"));
        month5.setTextColor(Color.parseColor("#404040"));
        month3.setTextColor(Color.parseColor("#404040"));
        month7.setTextColor(Color.parseColor("#404040"));
        SetIEP(6);
      }
    });

    month7.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        month7.setTextColor(Color.parseColor("#4a83c7"));
        month4.setTextColor(Color.parseColor("#404040"));
        month5.setTextColor(Color.parseColor("#404040"));
        month6.setTextColor(Color.parseColor("#404040"));
        month3.setTextColor(Color.parseColor("#404040"));
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

    //return inflater.inflate(R.layout.fragment_contents_week, container, false);
  }

  public void SetIEP(int month){
    week1.setText(weeklyContent[diagnosisResult][month-3][0] + "\n" + weeklyExam[diagnosisResult][month-3][0]); // 첫화면은 3월 IEP 1주차
    week2.setText(weeklyContent[diagnosisResult][month-3][1] + "\n" + weeklyExam[diagnosisResult][month-3][1]); // 2주차
    week3.setText(weeklyContent[diagnosisResult][month-3][2] + "\n" + weeklyExam[diagnosisResult][month-3][2]); // 3주차
    week4.setText(weeklyContent[diagnosisResult][month-3][3] + "\n" + weeklyExam[diagnosisResult][month-3][3]); // 4주차
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
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
