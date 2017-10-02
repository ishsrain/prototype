package com.h2kresearch.iepread;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
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

  int currStateIndex = 0;

  private static String[] monthlyGoalString = {
          "단모음 음가 파악",
          "단모음 음가 파악",
          "자음 음가 파악",
          "자음 음가 파악",
          "이중모음 음가 파악"
  };

  private static String[] monthlyContentString = {
          "1. 단모음 /ㅣ/, /ㅔ/, /ㅏ/, /ㅜ/, /ㅗ/, /ㅐ/, /ㅓ/, /ㅡ/ 소리-입모양-낱자 연결",
          "1. 단모음 /ㅚ/와 /ㅟ/ 소리-입모양-낱자 연결 \n2. 단모음 10개 발음/분리/변별 연습",
          "1. 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/, /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/, /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/, /ㅈ/, /ㅉ/, /ㅊ/ 소리-입모양-낱자 연결",
          "1. 자음 /ㅅ/, /ㅆ/, /ㅎ/, /ㄹ/ 소리-입모양-낱자 연결 \n2. 자음 탐지/분리/변별/발음 연습",
          "1. 모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 소리-입모양-낱자 연결"
  };

  private static String[] monthlyMethodString = {
          "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용 \n2. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결. \n3. 교사의 지도를 따라 학생이 직접 발음해보도록 유도",
          "1. 발음연습: 단모음 조합을 빠르고 정확하게 발음하는 연습 \n2. 분리연습: 최소대립쌍에 공통적으로 들어있는 모음의 소리를 정확하게 분리하는 연습\n3. 변별연습: 최소대립쌍 소리의 동일/상이 여부를 정확하게 변별하는 연습",
          "1. 조음 위치가 같은 것끼리 묶어서 학습 \n2. 소리-입모양을 연결한 후 입모양-낱자를 연결. 마지막으로 소리-낱자 연결 \n3. 교사의 지도를 따라 학생이 직접 발음해보도록 유도 \n4. 자음에 다양한 모음을 붙여 지도하여 모음을 지속적으로 복습",
          "1. 탐지연습: 소리를 듣고 특정 자음의 음가를 정확하게 탐지하는 연습 \n2. 분리연습: 단어에서 초성의 소리를 정확하게 분리해내는 연습 \n3. 변별연습: 연속해서 불러주는 1음절의 최소대립쌍의 자음 음가의 동일/상이 여부를 정확하게 변별하는 연습 \n4. 발음연습: 서로 다른 자음에 차례로 모음을 붙여서 빠르고 정확하게 발음하는 연습",
          "1. 발음 지도시 조음 위치나 방법 등을 설명하기 위해 입모양을 활용 \n2. 이중모음은 처음 조음 위치와 나중 조음 위치가 다름을 설명 \n3. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 는 모두 처음 조음 위치가 /ㅣ/ 임을 설명 \n4. 이중모음 /ㅒ/와 /ㅖ/를 변별하는 것은 현실적으로 어려움을 설명"
    };

  private static String[] monthlyExamString = {
          "1. 단모음 /ㅣ/, /ㅔ/, /ㅏ/, /ㅜ/, /ㅗ/, /ㅐ/, /ㅓ/, /ㅡ/ 의 음가를 정확하게 파악하였는가?",
          "1. 단모음 /ㅚ/와 /ㅟ/ 의 음가를 정확하게 파악하였는가? \n2. 모든 단모음의 음가를 빠르고 정확하게 발음/분리/변별할 수 있는가?",
          "1. 자음 /ㅁ/, /ㅂ/, /ㅃ/, /ㅍ/, /ㄱ/, /ㄲ/, /ㅋ/, /ㅇ/, /ㄷ/, /ㄸ/, /ㅌ/, /ㄴ/, /ㅈ/, /ㅉ/, /ㅊ/의 음가를 정확하게 파악하였는가?",
          "1. 자음 /ㅅ/, /ㅆ/, /ㅎ/, /ㄹ/ 의 음가를 정확하게 파악하였는가? \n2. 모든 자음의 음가를 빠르고 정확하게 탐지/분리/변별/발음할 수 있는가?",
          "1. 이중모음 /ㅑ/, /ㅛ/, /ㅕ/, /ㅠ/, /ㅒ/, /ㅖ/ 의 음가를 정확하게 파악하였는가?"
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
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contents_month, container, false);

    // Set the content of the TextView
    TextView mGoal = (TextView) rootView.findViewById(R.id.textView37);
    TextView mContent = (TextView) rootView.findViewById(R.id.textView38);
      TextView mMethod = (TextView) rootView.findViewById(R.id.textView40);
      TextView mExam = (TextView) rootView.findViewById(R.id.textView42);

    mGoal.setText(monthlyGoalString[currStateIndex]);
    mContent.setText(monthlyContentString[currStateIndex]);
    mMethod.setText(monthlyMethodString[currStateIndex]);
      mExam.setText(monthlyExamString[currStateIndex]);

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
