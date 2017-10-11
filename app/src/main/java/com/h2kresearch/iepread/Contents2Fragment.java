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
import org.json.JSONObject;


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

      if(getArguments().getString("filepath") != null){
        RECORDED_FILE = getArguments().getString("filepath");
        Log.d("Recorded File Path", RECORDED_FILE);
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_contents2, container, false);

    //RECORDED_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/IEPRead/김선생/홍창기/20171011_09242611";

    ImageView bt1_1 = (ImageView) v.findViewById(R.id.play1_1);
    bt1_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q1_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt1_2 = (ImageView) v.findViewById(R.id.play1_2);
    bt1_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q1_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt1_3 = (ImageView) v.findViewById(R.id.play1_3);
    bt1_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q1_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt2_1 = (ImageView) v.findViewById(R.id.play2_1);
    bt2_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q2_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt2_2 = (ImageView) v.findViewById(R.id.play2_2);
    bt2_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q2_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt2_3 = (ImageView) v.findViewById(R.id.play2_3);
    bt2_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q2_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt3_1 = (ImageView) v.findViewById(R.id.play3_1);
    bt3_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q3_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt3_2 = (ImageView) v.findViewById(R.id.play3_2);
    bt3_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q3_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt3_3 = (ImageView) v.findViewById(R.id.play3_3);
    bt3_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q3_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt4_1 = (ImageView) v.findViewById(R.id.play4_1);
    bt4_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q4_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt4_2 = (ImageView) v.findViewById(R.id.play4_2);
    bt4_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q4_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt4_3 = (ImageView) v.findViewById(R.id.play4_3);
    bt4_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q4_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt6_1 = (ImageView) v.findViewById(R.id.play6_1);
    bt6_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q6_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt6_2 = (ImageView) v.findViewById(R.id.play6_2);
    bt6_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q6_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt6_3 = (ImageView) v.findViewById(R.id.play6_3);
    bt6_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q6_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt7_1 = (ImageView) v.findViewById(R.id.play7_1);
    bt7_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q7_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt7_2 = (ImageView) v.findViewById(R.id.play7_2);
    bt7_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q7_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt7_3 = (ImageView) v.findViewById(R.id.play7_3);
    bt7_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q7_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_1 = (ImageView) v.findViewById(R.id.play5_1);
    bt5_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_2 = (ImageView) v.findViewById(R.id.play5_2);
    bt5_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_3 = (ImageView) v.findViewById(R.id.play5_3);
    bt5_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_4 = (ImageView) v.findViewById(R.id.play5_4);
    bt5_4.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_4.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_5 = (ImageView) v.findViewById(R.id.play5_5);
    bt5_5.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_5.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_6 = (ImageView) v.findViewById(R.id.play5_6);
    bt5_6.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_6.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_7 = (ImageView) v.findViewById(R.id.play5_7);
    bt5_7.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_7.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt5_8 = (ImageView) v.findViewById(R.id.play5_8);
    bt5_8.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q5_8.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_1 = (ImageView) v.findViewById(R.id.play8_1);
    bt8_1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_1.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_2 = (ImageView) v.findViewById(R.id.play8_2);
    bt8_2.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_2.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_3 = (ImageView) v.findViewById(R.id.play8_3);
    bt8_3.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_3.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_4 = (ImageView) v.findViewById(R.id.play8_4);
    bt8_4.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_4.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_5 = (ImageView) v.findViewById(R.id.play8_5);
    bt8_5.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_5.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_6 = (ImageView) v.findViewById(R.id.play8_6);
    bt8_6.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_6.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_7 = (ImageView) v.findViewById(R.id.play8_7);
    bt8_7.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_7.mp4");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    ImageView bt8_8 = (ImageView) v.findViewById(R.id.play8_8);
    bt8_8.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        try {
          playRecoredAudio(RECORDED_FILE+"/q8_8.mp4");
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
