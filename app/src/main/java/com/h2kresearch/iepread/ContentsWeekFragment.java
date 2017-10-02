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

  int currStateIndex = 0;

  private static String[][][] weeklyContent = {
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
          }
  };

  private static String[] weeklyExam = {
          "평가: 단모음 /ㅣ/와 /ㅔ/ 의 음가를 정확히 파악하였는가?",
          "평가: 단모음 /ㅏ/와 /ㅜ/ 의 음가를 정확히 파악하였는가?",
          "평가: 단모음 /ㅗ/와 /ㅐ/ 의 음가를 정확히 파악하였는가?",
          "평가: 단모음 /ㅓ/와 /ㅡ/ 의 음가를 정확히 파악하였는가?"
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

    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contents_week, container, false);

    // Set the content of the TextView
    TextView week1 = (TextView) rootView.findViewById(R.id.textView37);
    TextView week2 = (TextView) rootView.findViewById(R.id.textView38);
    TextView week3 = (TextView) rootView.findViewById(R.id.textView40);
    TextView week4 = (TextView) rootView.findViewById(R.id.textView42);

    week1.setText(weeklyContent[0][0][0] + "\n" + weeklyExam[0]);
    week2.setText(weeklyContent[0][0][1] + "\n" + weeklyExam[1]);
    week3.setText(weeklyContent[0][0][2] + "\n" + weeklyExam[2]);
    week4.setText(weeklyContent[0][0][3] + "\n" + weeklyExam[3]);

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
