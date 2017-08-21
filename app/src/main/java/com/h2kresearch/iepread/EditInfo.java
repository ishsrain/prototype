package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ishsrain on 2017. 8. 21..
 */

public class EditInfo extends android.support.v7.widget.AppCompatEditText {

  public EditInfo(Context context, AttributeSet attrs) {
    super(context, attrs);

    setOnFocusChangeListener(new OnFocusChangeListener() {

      @Override
      public void onFocusChange(View view, boolean b) {
        if(b){
          setBackgroundColor(Color.rgb(255,0,0));
        } else {
          setBackgroundColor(Color.rgb(0,255,0));
        }
      }
    });
  }

}
