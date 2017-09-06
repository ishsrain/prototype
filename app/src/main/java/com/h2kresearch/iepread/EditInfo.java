package com.h2kresearch.iepread;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Created by ishsrain on 2017. 8. 21..
 */

public class EditInfo extends android.support.v7.widget.AppCompatEditText {

  boolean textSuccess = false;

  public EditInfo(Context context, AttributeSet attrs) {
    super(context, attrs);

    // Add Text Watcher
    addTextChangedListener(infoWatcher);
  }

  // Text Watcher
  TextWatcher infoWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      //Toast.makeText(InfoActivity.this, "abc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){

      textSuccess = false;
      int type = getInputType()&(InputType.TYPE_MASK_VARIATION);

      switch(type) {
        case InputType.TYPE_TEXT_VARIATION_PERSON_NAME:
          if (charSequence.length() == 0) {
            setBackgroundColor(Color.rgb(255, 255, 255));
          } else if (charSequence.length() >= 2 && charSequence.length() < 10) {
            setBackgroundColor(Color.rgb(0, 255, 0));
            textSuccess = true;
          } else {
            setBackgroundColor(Color.rgb(255, 0, 0));
          }
          break;
        case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
          if (charSequence.length() == 0) {
            setBackgroundColor(Color.rgb(255, 255, 255));
          } else if (charSequence.length() > 0) {
            setBackgroundColor(Color.rgb(0, 255, 0));
            textSuccess = true;
          }
          break;
        case InputType.TYPE_CLASS_NUMBER:
          if (charSequence.length() == 0) {
            setBackgroundColor(Color.rgb(255, 255, 255));
          } else if (charSequence.length() > 0) {
            setBackgroundColor(Color.rgb(0, 255, 0));
            textSuccess = true;
          }
          break;
        default:
          if (charSequence.length() == 0) {
            setBackgroundColor(Color.rgb(255, 255, 255));
          } else if (charSequence.length() > 0) {
            setBackgroundColor(Color.rgb(0, 255, 0));
            textSuccess = true;
          }
          break;
      }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
  };

}
