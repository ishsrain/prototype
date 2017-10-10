package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
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
 * {@link Contents3Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contents3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contents3Fragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  View v;
  TextView testName;
  TextView testResult;
  int testResultID;
  int iter;

  // 객관식 학생 선택 답안 수신
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;
  int[] t4Answers;
  int[] t6Answers;
  int[] t7Answers;

  int[] t1RightAnswers;
  int[] t2RightAnswers;
  int[] t3RightAnswers;
  int[] t4RightAnswers;
  int[] t6RightAnswers;
  int[] t7RightAnswers;

  // 객관식 점수
  int t1Score = 0;
  int t2Score = 0;
  int t3Score = 0;
  int t4Score = 0;
  int t6Score = 0;
  int t7Score = 0;

  public Contents3Fragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment Contents3Fragment.
   */
  // TODO: Rename and change types and number of parameters
  public static Contents3Fragment newInstance(String param1, String param2) {
    Contents3Fragment fragment = new Contents3Fragment();
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

      t1RightAnswers = getArguments().getIntArray("t1RightAnswers");
      t2RightAnswers = getArguments().getIntArray("t2RightAnswers");
      t3RightAnswers = getArguments().getIntArray("t3RightAnswers");
      t4RightAnswers = getArguments().getIntArray("t4RightAnswers");
      t6RightAnswers = getArguments().getIntArray("t6RightAnswers");
      t7RightAnswers = getArguments().getIntArray("t7RightAnswers");

      t1Answers = getArguments().getIntArray("t1Answers");
      t2Answers = getArguments().getIntArray("t2Answers");
      t3Answers = getArguments().getIntArray("t3Answers");
      t4Answers = getArguments().getIntArray("t4Answers");
      t6Answers = getArguments().getIntArray("t6Answers");
      t7Answers = getArguments().getIntArray("t7Answers");

      t1Score = getArguments().getInt("t1Score");
      t2Score = getArguments().getInt("t2Score");
      t3Score = getArguments().getInt("t3Score");
      t4Score = getArguments().getInt("t4Score");
      t6Score = getArguments().getInt("t6Score");
      t7Score = getArguments().getInt("t7Score");

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_contents3, container, false);

    testName = (TextView) v.findViewById(R.id.testName1);
    testName.setText("1. 쉬운 모음 ("+t1Score+"/"+t1Answers.length+")");
    testName = (TextView) v.findViewById(R.id.testName2);
    testName.setText("2. 자음 ("+t2Score+"/"+t2Answers.length+")");
    testName = (TextView) v.findViewById(R.id.testName3);
    testName.setText("3. 쉬운 음절 ("+t3Score+"/"+t3Answers.length+")");
    testName = (TextView) v.findViewById(R.id.testName4);
    testName.setText("4. 복잡한 모음 ("+t4Score+"/"+t4Answers.length+")");
    testName = (TextView) v.findViewById(R.id.testName6);
    testName.setText("6. 쉬운 받침 ("+t6Score+"/"+t6Answers.length+")");
    testName = (TextView) v.findViewById(R.id.testName7);
    testName.setText("7. 복잡한 받침 ("+t7Score+"/"+t7Answers.length+")");

    for(iter=0; iter<t1Answers.length; iter++){
      testResultID = v.getResources().getIdentifier("testResult1_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResult = (TextView) v.findViewById(testResultID);

      if(t1Answers[iter] == t1RightAnswers[iter]){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    for(iter=0; iter<t2Answers.length; iter++){
      testResultID = v.getResources().getIdentifier("testResult2_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResult = (TextView) v.findViewById(testResultID);

      if(t2Answers[iter] == t2RightAnswers[iter]){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    for(iter=0; iter<t3Answers.length; iter++){
      testResultID = v.getResources().getIdentifier("testResult3_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResult = (TextView) v.findViewById(testResultID);

      if(t3Answers[iter] == t3RightAnswers[iter]){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    for(iter=0; iter<t4Answers.length; iter++){
      testResultID = v.getResources().getIdentifier("testResult4_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResult = (TextView) v.findViewById(testResultID);

      if(t4Answers[iter] == t4RightAnswers[iter]){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    for(iter=0; iter<t6Answers.length; iter++){
      testResultID = v.getResources().getIdentifier("testResult6_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResult = (TextView) v.findViewById(testResultID);

      if(t6Answers[iter] == t6RightAnswers[iter]){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    for(iter=0; iter<t7Answers.length; iter++){
      testResultID = v.getResources().getIdentifier("testResult7_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResult = (TextView) v.findViewById(testResultID);

      if(t7Answers[iter] == t7RightAnswers[iter]){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    return v;
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
