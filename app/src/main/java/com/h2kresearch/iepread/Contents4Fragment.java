package com.h2kresearch.iepread;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.widget.TextView.BufferType.SPANNABLE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contents4Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contents4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contents4Fragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  //int currStateIndex = 1;

  int diagnosisResult = 0;

  private static String[] currStateString = {
          "1. 자음 모음의 소리값을 정확하게 파악하지 못함. \n2. 받침이 있는 글자에 특히 어려움을 겪음. \n3. 현재 읽기 수준은 1학년 평균 수준에 심각하게 미도달.",
          "1. 모음 음가에 비해 자음 음가 성취도가 부족함. \n2. 받침이 있는 글자에 특히 어려움을 겪음. \n3. 현재 읽기 수준은 1학년 평균 수준에 미도달.",
          "1. 단모음에 비해 이중모음의 성취도가 부족함. \n2. 자음의 음가는 정확히 알고 있음. \n3. 받침이 있는 글자에 특히 어려움을 겪음. \n4. 현재 읽기 수준은 1학년 평균 수준에 미도달.",
          "1. 모음(이중모음 포함)과 자음(받침 포함)의 음가를 정확하게 알고 있음. \n2. 하지만 받침이 있는 글자 읽기를 어려워 함. \n3. 낱자-소리 대응이 빠르거나 자동화되지 못함. \n4. 현재 읽기 수준은 1학년 수준.",
          "1. 모음과 자음의 음가를 정확하게 알고 있음. \n2. 간단한 받침이 있는 글자는 정확하게 읽어냄. \n3. 하지만 곁받침, 음운 변동 규칙을 어려워 함. \n4. 현재 읽기 수준은 1학년 수준.",
          "1. 모음과 자음의 음가를 정확하게 알고 있음. \n2. 음운 변동 규칙도 정확히 알고 있지만 유창하게 읽지는 못함. \n3. 현재 읽기 수준은 2학년 평균 미도달."
  };

  private static String[] currGoalString = {
          "1. 단모음의 음가를 정확하게 파악. \n2. 자음의 음가를 정확하게 파악. \n3. 이중모음의 음가를 파악.",
          "1. 자음의 음가를 정확하게 파악. \n2. 이중모음의 음가를 정확하게 파악.",
          "1. 이중모음의 음가를 정확하게 파악. \n2. 받침의 음가를 정확하게 파악.",
          "1. 받침의 음가를 정확하게 파악. \n2. 정확한 해독.",
          "1. 음운변동 규칙을 정확하게 파악. \n2. 겹받침의 발음을 정확하게 파악.",
          "1. 유창성 훈련."
  };


  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  public Contents4Fragment() {
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
  public static Contents4Fragment newInstance(String param1, String param2) {
    Contents4Fragment fragment = new Contents4Fragment();
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
    //String inDiagnosisResult = this.getArguments().getString("diagnosisResult");
    //diagnosisResult = Integer.parseInt(inDiagnosisResult);
    diagnosisResult = this.getArguments().getInt("diagnosisResult");

    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contents4, container, false);

    // Set the content of the TextView
    TextView currState = (TextView) rootView.findViewById(R.id.textView37);
    TextView currGoal = (TextView) rootView.findViewById(R.id.textView38);

    //currState.setText(currStateString[currStateIndex]);
    //currGoal.setText(currGoalString[currStateIndex]);
    currState.setText(currStateString[diagnosisResult]);
    currGoal.setText(currGoalString[diagnosisResult]);

    /*
    TextView currState = (TextView) rootView.findViewById(R.id.textView37);
    SpannableString mainContent = new SpannableString(getResources().getString(R.string.example));
    currState.setText(mainContent, SPANNABLE);

    // Split the text in the TextView into words
    // Make the TextView clickable

    currState.setMovementMethod(new LinkMovementMethod());
    */
    return rootView;

    //return inflater.inflate(R.layout.fragment_contents4, container, false);
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
