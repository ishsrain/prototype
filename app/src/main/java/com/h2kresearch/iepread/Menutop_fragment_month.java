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
 * {@link Menutop_fragment_month.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Menutop_fragment_month#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menutop_fragment_month extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  ResultActivity activity;
  private TextView menu1Text, menu2Text, menu3Text;
  private TextView mMarch, mApril, mMay, mJune, mJuly;

  private OnFragmentInteractionListener mListener;

  public Menutop_fragment_month() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MenutopFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static Menutop_fragment_month newInstance(String param1, String param2) {
    Menutop_fragment_month fragment = new Menutop_fragment_month();
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
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menutop_month, container, false);

    activity = (ResultActivity) getActivity();
    menu1Text = (TextView)rootView.findViewById(R.id.textView3);
    menu2Text = (TextView)rootView.findViewById(R.id.textView12);
    menu3Text = (TextView)rootView.findViewById(R.id.textView13);
    mMarch = (TextView)rootView.findViewById(R.id.textView14);
    mApril = (TextView)rootView.findViewById(R.id.textView15);
    mMay = (TextView)rootView.findViewById(R.id.textView16);
    mJune = (TextView)rootView.findViewById(R.id.textView17);
    mJuly = (TextView)rootView.findViewById(R.id.textView18);

    menu1Text.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(6);
      }
    });

    menu2Text.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(7);
      }
    });

    menu3Text.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(8);
      }
    });

    mMarch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(9);
      }
    });

    mApril.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(10);
      }
    });

    mMay.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(11);
      }
    });

    mJune.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(12);
      }
    });

    mJuly.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        activity.onContentsFragmentChanged(13);
      }
    });

    return rootView;
    //return inflater.inflate(R.layout.fragment_menutop3, container, false);
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
