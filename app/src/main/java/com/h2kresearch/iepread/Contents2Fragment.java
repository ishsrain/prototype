package com.h2kresearch.iepread;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contents2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contents2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contents2Fragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  // 주관식 채점 결과 전송
  View v;
  RadioGroup rg;
  RadioButton rb;
  int rg_id;
  int rb_id;
  int numQuestion;
  int numTest;
  int iter;
  int[][] results = new int[8][8];

  // 녹음 파일 재생하기
  String RECORDED_FILE;
  MediaPlayer mediaPlayer;
  ImageView playButton;

  private void playRecoredAudio(String path) throws IOException {
    killMediaPlayer();

    mediaPlayer = new MediaPlayer();
    mediaPlayer.setDataSource(path);
    //mediaPlayer.setLooping(false);
    mediaPlayer.prepare();
    mediaPlayer.start();
  }

  private void killMediaPlayer() {
    if (mediaPlayer != null) {
      try {
        mediaPlayer.release();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private OnGradeListener gradeAllListener;
  public interface OnGradeListener{
    void onGradeSet(int[][] gradeResults);
  }

  public Contents2Fragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment Contents2Fragment.
   */
  // TODO: Rename and change types and number of parameters
  public static Contents2Fragment newInstance(String param1, String param2) {
    Contents2Fragment fragment = new Contents2Fragment();
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
    v = inflater.inflate(R.layout.fragment_contents2, container, false);

    playButton = (ImageView) v.findViewById(R.id.play1_1);
    playButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        File sdcard = Environment.getExternalStorageDirectory();
        Log.d("recorded file path", sdcard+"/recorded.mp4");
        try {
          playRecoredAudio(sdcard+"/recorded.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

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

    if (context instanceof OnGradeListener) {
      gradeAllListener = (OnGradeListener) context;
    } else {
      throw new RuntimeException(context.toString()
              + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    setGradeResult();
    gradeAllListener.onGradeSet(results);
    super.onDetach();
    mListener = null;
    gradeAllListener = null;
  }

  public void setGradeResult(){

    for(numTest=1; numTest<9; numTest++){
      if(numTest == 5 || numTest == 8){
        numQuestion = 8;
      } else{
        numQuestion = 3;
      }

      for(iter=0; iter<numQuestion; iter++){
        rg_id = v.getResources().getIdentifier("q"+Integer.toString(numTest)+"_"+Integer.toString(iter+1), "id", getActivity().getPackageName());
        rg = (RadioGroup) v.findViewById(rg_id);
        rb_id = rg.getCheckedRadioButtonId();
        rb = (RadioButton) v.findViewById(rb_id);
        if (rb != null){
          results[numTest-1][iter] = Integer.parseInt(rb.getText().toString());
        }
        //Log.d("success rate1", "numTest: "+numTest+" numQuestion: "+(iter+1));
        //Log.d("success rate2", "sample[0][0]: "+results[0][0]);
      }
    }


    //rg = (RadioGroup) v.findViewById(R.id.q1_1);
    //id = rg.getCheckedRadioButtonId();
    //rb = (RadioButton) v.findViewById(id);
    //q1results[0] = Integer.parseInt(rb.getText().toString());
    //Log.d("results", "sample[1][1]: "+results[0][0]+" sample[2][1]: "+results[1][0]);

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
