package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contents6Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contents6Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contents6Fragment extends Fragment {

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

  // 최종 점수
  int t1ScoreFinal = 0;
  int t2ScoreFinal = 0;
  int t3ScoreFinal = 0;
  int t4ScoreFinal = 0;
  int t5ScoreFinal = 0;
  int t6ScoreFinal = 0;
  int t7ScoreFinal = 0;
  int t8ScoreFinal = 0;

  ProgressBar progressBar;

  int numTotalWords = 0;
  int numMistakenWords = 0;
  int proficiency = 0;

  public Contents6Fragment() {
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
  public static Contents6Fragment newInstance(String param1, String param2) {
    Contents6Fragment fragment = new Contents6Fragment();
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

      t1ScoreFinal = getArguments().getInt("t1ScoreFinal");
      t2ScoreFinal = getArguments().getInt("t2ScoreFinal");
      t3ScoreFinal = getArguments().getInt("t3ScoreFinal");
      t4ScoreFinal = getArguments().getInt("t4ScoreFinal");
      t5ScoreFinal = getArguments().getInt("t5ScoreFinal");
      t6ScoreFinal = getArguments().getInt("t6ScoreFinal");
      t7ScoreFinal = getArguments().getInt("t7ScoreFinal");
      t8ScoreFinal = getArguments().getInt("t8ScoreFinal");
      numTotalWords = getArguments().getInt("totalWords");
      numMistakenWords = getArguments().getInt("mistakenWords");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_contents6, container, false);

    testName = (TextView) v.findViewById(R.id.testName1);
    testName.setText("1. 쉬운 모음 ("+t1ScoreFinal+"/13)");
    testName = (TextView) v.findViewById(R.id.testName2);
    testName.setText("2. 자음 ("+t2ScoreFinal+"/22)");
    testName = (TextView) v.findViewById(R.id.testName3);
    testName.setText("3. 쉬운 음절 ("+t3ScoreFinal+"/17)");
    testName = (TextView) v.findViewById(R.id.testName4);
    testName.setText("4. 복잡한 모음 ("+t4ScoreFinal+"/14)");
    testName = (TextView) v.findViewById(R.id.testName5);
    testName.setText("5. 쉬운 단어 ("+t5ScoreFinal+"/8)");
    testName = (TextView) v.findViewById(R.id.testName6);
    testName.setText("6. 쉬운 받침 ("+t6ScoreFinal+"/10)");
    testName = (TextView) v.findViewById(R.id.testName7);
    testName.setText("7. 복잡한 받침 ("+t7ScoreFinal+"/10)");
    testName = (TextView) v.findViewById(R.id.testName8);
    testName.setText("8. 복잡한 단어 ("+t8ScoreFinal+"/8)");
    testName = (TextView) v.findViewById(R.id.testName9);
    int numAccurateWords = numTotalWords - numMistakenWords;
    testName.setText("9. 읽기 유창성 ("+ numAccurateWords +"/" + numTotalWords + ")");


    testResult = (TextView) v.findViewById(R.id.textView25);
    testResult.setText("정답률: " + t1ScoreFinal * 100/13 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar4);
    progressBar.setProgress(t1ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView26);
    testResult.setText("정답률: " + t2ScoreFinal * 100/22 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar5);
    progressBar.setProgress(t2ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView27);
    testResult.setText("정답률: " + t3ScoreFinal * 100/17 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar6);
    progressBar.setProgress(t3ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView28);
    testResult.setText("정답률: " + t4ScoreFinal * 100/14 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar7);
    progressBar.setProgress(t4ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView29);
    testResult.setText("정답률: " + t5ScoreFinal * 100/8 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar8);
    progressBar.setProgress(t5ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView30);
    testResult.setText("정답률: " + t6ScoreFinal * 100/10 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar9);
    progressBar.setProgress(t6ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView31);
    testResult.setText("정답률: " + t7ScoreFinal * 100/10 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar10);
    progressBar.setProgress(t7ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView32);
    testResult.setText("정답률: " + t8ScoreFinal * 100/8 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar11);
    progressBar.setProgress(t8ScoreFinal);

    testResult = (TextView) v.findViewById(R.id.textView33);
    testResult.setText("정답률: " + numAccurateWords * 100/177 + "%");
    progressBar = (ProgressBar) v.findViewById(R.id.progressBar12);
    progressBar.setProgress(numAccurateWords);

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
