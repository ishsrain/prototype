package com.h2kresearch.iepread;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class InfoActivity extends AppCompatActivity {

  EditInfo teacherName;
  EditInfo teacherEmail;
  EditInfo studentName;
  EditInfo studentNumber;
  Button buttonStart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_info);

    // Info EditText
    teacherName = (EditInfo) findViewById(R.id.editText);
    teacherEmail = (EditInfo) findViewById(R.id.editText2);
    studentName = (EditInfo) findViewById(R.id.editText3);
    studentNumber = (EditInfo) findViewById(R.id.editText4);

    // Set Focus
    teacherName.setNextFocusDownId(R.id.editText2);
    teacherEmail.setNextFocusDownId(R.id.editText3);
    studentName.setNextFocusDownId(R.id.editText4);

    // Add Text Watcher
    teacherName.addTextChangedListener(buttonWatcher);
    teacherEmail.addTextChangedListener(buttonWatcher);
    studentName.addTextChangedListener(buttonWatcher);
    studentNumber.addTextChangedListener(buttonWatcher);

    // Start Button
    buttonStart = (Button) findViewById(R.id.button);
    buttonStart.setEnabled(false);

    // Set Button Click Listener
    buttonStart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // Current Time
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd_HH24mmss");
        String time = sdfNow.format(new Date(System.currentTimeMillis()));

        // Make Folder
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            +"/IEPRead/"+teacherName.getText()+"/"+studentName.getText()+"/"+time;
        File dir = new File(filePath);
        dir.mkdirs();

        // Set JSONObject
        JSONObject info;
        info = new JSONObject();
        try {
          info.put("teacherName", teacherName.getText());
          info.put("teacherEmail", teacherEmail.getText());
          info.put("studentName", studentName.getText());
          info.put("studentNumber", studentNumber.getText());
          info.put("filePath",filePath);
        } catch (JSONException e) {
          e.printStackTrace();
        }
        //System.out.println("info:"+info);

        Intent intent = new Intent(getBaseContext(), TestStartActivity.class);
        //Intent intent = new Intent(getBaseContext(), Test8WordWithSupportActivity.class);

        intent.putExtra("info", info.toString());
        startActivity(intent);
        //finish();
      }
    });
  }

  // Text Watcher
  TextWatcher buttonWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      //Toast.makeText(InfoActivity.this, "abc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      if (teacherName.textSuccess == true && teacherEmail.textSuccess == true
          && studentName.textSuccess == true && studentNumber.textSuccess == true) {
        buttonStart.setEnabled(true);
      } else {
        buttonStart.setEnabled(false);
      }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
  };
}