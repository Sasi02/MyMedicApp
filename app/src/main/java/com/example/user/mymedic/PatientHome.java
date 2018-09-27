package com.example.user.mymedic;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PatientHome extends AppCompatActivity {

    ImageButton imageButton1;
  //  ImageButton imageButton2;
    //ImageButton imageButton3;
    //ImageButton imageButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        imageButton1 =(ImageButton)findViewById(R.id.imageButton1);
        //imageButton2 =(ImageButton)findViewById(R.id.imageButton2);
        //imageButton3 =(ImageButton)findViewById(R.id.imageButton3);
        //imageButton4 =(ImageButton)findViewById(R.id.imageButton4);

        imageButton1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientHome.this, PatientMyProfile.class);
                        startActivity(intent);
                    }
                }
        );

    }
}