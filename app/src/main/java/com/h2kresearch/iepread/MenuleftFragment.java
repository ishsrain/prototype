package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuleftFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuleftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuleftFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;

  ResultActivity activity;
  private ImageView menu1, menu2, menu3;
  private TextView menu1Text, menu2Text, menu3Text;
  private LinearLayout menu1Layout, menu2Layout, menu3Layout;

  public MenuleftFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MenuleftFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MenuleftFragment newInstance(String param1, String param2) {
    MenuleftFragment fragment = new MenuleftFragment();
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
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menuleft, container, false);

    activity = (ResultActivity) getActivity();

    menu1 = (ImageView)rootView.findViewById(R.id.imageView13);
    menu2 = (ImageView)rootView.findViewById(R.id.imageView14);
    menu3 = (ImageView)rootView.findViewById(R.id.imageView15);

    menu1Text = (TextView)rootView.findViewById(R.id.textView5);
    menu2Text = (TextView)rootView.findViewById(R.id.textView6);
    menu3Text = (TextView)rootView.findViewById(R.id.textView7);

    menu1Layout = (LinearLayout)rootView.findViewById(R.id.linearLayout13);
    menu2Layout = (LinearLayout)rootView.findViewById(R.id.linearLayout14);
    menu3Layout = (LinearLayout)rootView.findViewById(R.id.linearLayout15);

    menu1Layout.setBackgroundColor(Color.parseColor("#569af0"));
    menu1Text.setTextColor(Color.WHITE);

    menu1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        menu1Layout.setBackgroundColor(Color.parseColor("#569af0"));
        menu2Layout.setBackgroundColor(Color.TRANSPARENT);
        menu3Layout.setBackgroundColor(Color.TRANSPARENT);

        menu1Text.setTextColor(Color.WHITE);
        menu2Text.setTextColor(Color.parseColor("#404040"));
        menu3Text.setTextColor(Color.parseColor("#404040"));

        activity.onMenutopFragmentChanged(1);
      }
    });

    menu2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        menu1Layout.setBackgroundColor(Color.TRANSPARENT);
        menu2Layout.setBackgroundColor(Color.parseColor("#569af0"));
        menu3Layout.setBackgroundColor(Color.TRANSPARENT);

        menu1Text.setTextColor(Color.parseColor("#404040"));
        menu2Text.setTextColor(Color.WHITE);
        menu3Text.setTextColor(Color.parseColor("#404040"));

        activity.onMenutopFragmentChanged(2);
      }
    });

    menu3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        menu1Layout.setBackgroundColor(Color.TRANSPARENT);
        menu2Layout.setBackgroundColor(Color.TRANSPARENT);
        menu3Layout.setBackgroundColor(Color.parseColor("#569af0"));

        menu1Text.setTextColor(Color.parseColor("#404040"));
        menu2Text.setTextColor(Color.parseColor("#404040"));
        menu3Text.setTextColor(Color.WHITE);

        activity.onMenutopFragmentChanged(3);
      }
    });

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
