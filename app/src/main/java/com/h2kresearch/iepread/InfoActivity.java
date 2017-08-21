package com.h2kresearch.iepread;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

  EditText teacherName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_info);

    teacherName = (EditText)findViewById(R.id.editText);
    teacherName.addTextChangedListener(nameWatcher);
  }

  TextWatcher nameWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      //Toast.makeText(InfoActivity.this, "abc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){
      if(charSequence.length() == 0) {
        teacherName.setBackgroundColor(Color.rgb(255,255,255));
      } else if(charSequence.length() >= 2 && charSequence.length() < 10) {
        teacherName.setBackgroundColor(Color.rgb(0,255,0));
      } else {
        teacherName.setBackgroundColor(Color.rgb(255,0,0));
      }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
  };
}
