package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.widget.TextView.BufferType.SPANNABLE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentsFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  // 읽기 유창성 검사 결과 기록 및 전송
  ArrayList<String> selectedWords = new ArrayList();
  int totalWords = 0;
  int mistakenWords = 0;

  TextView mainText;

  private OnReadingGradeListener readingGradeListener;
  public interface OnReadingGradeListener{
    void onReadingGradeSet(int totalWords, int mistakenWords, ArrayList selectedWords);
  }

  public ContentsFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ContentsFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ContentsFragment newInstance(String param1, String param2) {
    ContentsFragment fragment = new ContentsFragment();
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

      if(getArguments().getStringArrayList("indSelectedWords") != null){
        selectedWords = getArguments().getStringArrayList("indSelectedWords");
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contents, container, false);

    // Set the content of the TextView
    mainText = (TextView) rootView.findViewById(R.id.textView23);
    SpannableString mainContent = new SpannableString(getResources().getString(R.string.example));
    mainText.setText(mainContent, SPANNABLE);

    // Split the text in the TextView into words
    getEachWord(mainText);
    setEachWordWithHighLight(mainText);

    // Make the TextView clickable
    mainText.setMovementMethod(new LinkMovementMethod());
    return rootView;
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

    if (context instanceof OnReadingGradeListener) {
      readingGradeListener = (OnReadingGradeListener) context;
    } else {
      throw new RuntimeException(context.toString()
              + " must implement OnReadingGradeListener");
    }


    if (getArguments() != null) {
      if(getArguments().getStringArrayList("indSelectedWords") != null){
        //selectedWords = getArguments().getStringArrayList("indSelectedWords");
        //setEachWordWithHighLight(mainText);
      }
    }
    Log.d("SelectedWordsInC", ""+selectedWords.size());
    Log.d("SelectedWordsInC", ""+selectedWords.size());
    Log.d("SelectedWordsInC", ""+selectedWords.size());



  }

  @Override
  public void onDetach() {
    super.onDetach();
    readingGradeListener.onReadingGradeSet(totalWords, mistakenWords, selectedWords);
    readingGradeListener = null;
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

  public class MyClickableSpan extends ClickableSpan {

    @Override
    public void onClick (View widget){
      // We display a Toast. You could do anything you want here.
      TextView tv = (TextView) widget;

      SpannableString ss = (SpannableString) tv.getText();

      if (selectedWords.contains("element"+tv.getSelectionStart())){
        mistakenWords -= 1;
        selectedWords.remove("element"+tv.getSelectionStart());
        ss.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), tv.getSelectionStart(), tv.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      } else {
        mistakenWords += 1;
        selectedWords.add("element"+tv.getSelectionStart());
        ss.setSpan(new BackgroundColorSpan(Color.CYAN), tv.getSelectionStart(), tv.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }

      Log.d("mistakenwords", " "+mistakenWords);
      Log.d("mistakenwords", " "+totalWords);
      Log.d("mistakenwords", " "+mistakenWords);
      Log.d("mistakenwords", " "+totalWords);

      //String s = tv.getText().subSequence(tv.getSelectionStart(), tv.getSelectionEnd()).toString();
      //Toast.makeText(Test9ReadingActivity.this, s, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void updateDrawState (TextPaint ds){
      ds.setColor(Color.BLACK);//set text color
      ds.setUnderlineText(false); // set to false to remove underline
    }
  }

  public void getEachWord(TextView textView){
    Spannable spans = (Spannable)textView.getText();
    Integer[] indices = getIndices(
        textView.getText().toString().replaceAll("(\r\n|\r|\n|\n\r)", " ").trim(), ' ');
    int start = 0;
    int end = 0;
    totalWords = indices.length + 1;
    // to cater last/only word loop will run equal to the length of indices.length
    for (int i = 0; i <= indices.length; i++) {
      ClickableSpan clickSpan = new MyClickableSpan();
      // to cater last/only word
      end = (i <indices.length ? indices[i] : spans.length());

      spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

      start = end + 1;
    }
    //Change the highlight color for selected text
    textView.setHighlightColor(Color.TRANSPARENT);
    Log.d("getEachWord", "called called called");
  }

  public void setEachWordWithHighLight(TextView textView){
    Spannable spans = (Spannable)textView.getText();
    Integer[] indices = getIndices(
            textView.getText().toString().replaceAll("(\r\n|\r|\n|\n\r)", " ").trim(), ' ');
    int start = 0;
    int end = 0;
    // to cater last/only word loop will run equal to the length of indices.length
    for (int i = 0; i <= indices.length; i++) {
      ClickableSpan clickSpan = new MyClickableSpan();
      // to cater last/only word
      end = (i <indices.length ? indices[i] : spans.length());

      if (selectedWords.contains("element"+start)) {
        spans.setSpan(new BackgroundColorSpan(Color.CYAN), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      } else {
        spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      start = end + 1;
    }
    //Change the highlight color for selected text
    //textView.setHighlightColor(Color.TRANSPARENT);
  }
  /*
if (selectedWords.contains("element"+tv.getSelectionStart())){
    mistakenWords -= 1;
    selectedWords.remove("element"+tv.getSelectionStart());
    ss.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), tv.getSelectionStart(), tv.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  } else {
    mistakenWords += 1;
    selectedWords.add("element"+tv.getSelectionStart());
    ss.setSpan(new BackgroundColorSpan(Color.CYAN), tv.getSelectionStart(), tv.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
*/

  public static Integer[] getIndices(String s, char c) {
    int pos = s.indexOf(c, 0);
    List<Integer> indices = new ArrayList<Integer>();
    while (pos != -1) {
      indices.add(pos);
      pos = s.indexOf(c, pos + 1);
    }
    return (Integer[]) indices.toArray(new Integer[0]);
  }

}

