package com.h2kresearch.iepread;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

  EditText teacherName;
  EditText studentName;

  Button imgsel,upload;
  ImageView img;
  String path;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_info);

    teacherName = (EditText)findViewById(R.id.editText);
    studentName = (EditText)findViewById(R.id.editText2);
    teacherName.addTextChangedListener(nameWatcher);
    studentName.addTextChangedListener(nameWatcher);

    img = (ImageView)findViewById(R.id.img);
    Ion.getDefault(this).configure().setLogging("ion-sample", Log.DEBUG);
    imgsel = (Button)findViewById(R.id.imagesel);
    upload =(Button)findViewById(R.id.upload);

    upload.setVisibility(View.INVISIBLE);
    upload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        File f = new File(path);

        Future uploading = Ion.with(InfoActivity.this)
//            .load("http://110.76.77.86:3000/process/androidupload")
            .load("http://192.168.0.124:3000/process/androidupload")
            .setMultipartFile("image", f)
            .asString()
            .withResponse()
            .setCallback(new FutureCallback<Response<String>>() {
              @Override
              public void onCompleted(Exception e, Response<String> result) {
                try {
                  JSONObject jobj = new JSONObject(result.getResult());
                  Toast.makeText(getApplicationContext(), jobj.getString("response"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e1) {
                  e1.printStackTrace();
                }

              }
            });

        Toast.makeText(InfoActivity.this, "abc", Toast.LENGTH_SHORT).show();
      }

    });

    imgsel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent fintent = new Intent(Intent.ACTION_GET_CONTENT);
        fintent.setType("image/jpeg");
        try {
          startActivityForResult(fintent, 100);
        } catch (ActivityNotFoundException e) {

        }
      }
    });
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (data == null)
      return;
    switch (requestCode) {
      case 100:
        if (resultCode == RESULT_OK) {
          path = getPathFromURI(data.getData());
          img.setImageURI(data.getData());
          upload.setVisibility(View.VISIBLE);

          Toast.makeText(InfoActivity.this, path, Toast.LENGTH_SHORT).show();

        }
    }
  }
  private String getPathFromURI(Uri contentUri) {
    String[] proj = { MediaStore.Images.Media.DATA };
    CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
    Cursor cursor = loader.loadInBackground();
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    return cursor.getString(column_index);
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
