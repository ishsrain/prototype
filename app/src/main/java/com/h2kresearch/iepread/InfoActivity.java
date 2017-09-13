package com.h2kresearch.iepread;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.Future;
import org.json.JSONObject;
import org.json.JSONException;

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
        Intent intent = new Intent(getBaseContext(), TestActivity.class);
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