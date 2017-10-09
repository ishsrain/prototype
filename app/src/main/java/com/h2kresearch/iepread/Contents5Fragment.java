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
 * {@link Contents5Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contents5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contents5Fragment extends Fragment {

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

  int[] testResultID = new int[6];
  int iter;

  // 객관식 학생 선택 답안 수신
  int[] t1Answers;
  int[] t2Answers;
  int[] t3Answers;
  int[] t4Answers;
  int[] t5Answers;
  int[] t6Answers;
  int[] t7Answers;
  int[] t8Answers;

  // 객관식 점수
  int t1Score = 0;
  int t2Score = 0;
  int t3Score = 0;
  int t4Score = 0;
  int t5Score = 0;
  int t6Score = 0;
  int t7Score = 0;
  int t8Score = 0;

  public Contents5Fragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment Contents5Fragment.
   */
  // TODO: Rename and change types and number of parameters
  public static Contents5Fragment newInstance(String param1, String param2) {
    Contents5Fragment fragment = new Contents5Fragment();
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

      t1Answers = getArguments().getIntArray("t1Answers");
      t2Answers = getArguments().getIntArray("t2Answers");
      t3Answers = getArguments().getIntArray("t3Answers");
      t4Answers = getArguments().getIntArray("t4Answers");
      t5Answers = getArguments().getIntArray("t5Answers");
      t6Answers = getArguments().getIntArray("t6Answers");
      t7Answers = getArguments().getIntArray("t7Answers");
      t8Answers = getArguments().getIntArray("t8Answers");

      t1Score = getArguments().getInt("t1Score");
      t2Score = getArguments().getInt("t2Score");
      t3Score = getArguments().getInt("t3Score");
      t4Score = getArguments().getInt("t4Score");
      t5Score = getArguments().getInt("t5Score");
      t6Score = getArguments().getInt("t6Score");
      t7Score = getArguments().getInt("t7Score");
      t8Score = getArguments().getInt("t8Score");

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_contents5, container, false);

    testName = (TextView) v.findViewById(R.id.testName1);
    testName.setText("쉬운 모음 ("+t1Score+"/3)");
    testName = (TextView) v.findViewById(R.id.testName2);
    testName.setText("자음 ("+t2Score+"/3)");
    testName = (TextView) v.findViewById(R.id.testName3);
    testName.setText("쉬운 음절 ("+t3Score+"/3)");
    testName = (TextView) v.findViewById(R.id.testName4);
    testName.setText("복잡한 모음 ("+t4Score+"/3)");
    testName = (TextView) v.findViewById(R.id.testName5);
    testName.setText("쉬운 단어 ("+t5Score+"/8)");
    testName = (TextView) v.findViewById(R.id.testName6);
    testName.setText("쉬운 받침 ("+t6Score+"/3)");
    testName = (TextView) v.findViewById(R.id.testName7);
    testName.setText("복잡한 받침 ("+t7Score+"/3)");
    testName = (TextView) v.findViewById(R.id.testName8);
    testName.setText("복잡한 받침 ("+t8Score+"/8)");

    for(iter=0; iter<3; iter++){
      testResultID[0] = v.getResources().getIdentifier("testResult1_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResultID[1] = v.getResources().getIdentifier("testResult2_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResultID[2] = v.getResources().getIdentifier("testResult3_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResultID[3] = v.getResources().getIdentifier("testResult4_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResultID[4] = v.getResources().getIdentifier("testResult6_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResultID[5] = v.getResources().getIdentifier("testResult7_"+Integer.toString(iter+1), "id", getActivity().getPackageName());

      testResult = (TextView) v.findViewById(testResultID[0]);
      if(t1Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }

      testResult = (TextView) v.findViewById(testResultID[1]);
      if(t2Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }

      testResult = (TextView) v.findViewById(testResultID[2]);
      if(t3Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }

      testResult = (TextView) v.findViewById(testResultID[3]);
      if(t4Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }

      testResult = (TextView) v.findViewById(testResultID[4]);
      if(t6Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }

      testResult = (TextView) v.findViewById(testResultID[5]);
      if(t7Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }
    }

    for(iter=0; iter<8; iter++){
      testResultID[0] = v.getResources().getIdentifier("testResult5_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
      testResultID[1] = v.getResources().getIdentifier("testResult8_"+Integer.toString(iter+1), "id", getActivity().getPackageName());

      testResult = (TextView) v.findViewById(testResultID[0]);
      if(t5Answers[iter] == 1){
        testResult.setTextColor(Color.BLACK);
      }else{
        testResult.setTextColor(Color.RED);
      }

      testResult = (TextView) v.findViewById(testResultID[1]);
      if(t8Answers[iter] == 1){
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
