package com.example.user.mymedic;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PatientHome extends AppCompatActivity {

    LinearLayout MyProfile;
    LinearLayout DocView;
    LinearLayout MyDrugs;
    LinearLayout DrugManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        firstTime();

        MyProfile = (LinearLayout) findViewById(R.id.myProfile);
        MyDrugs = (LinearLayout) findViewById(R.id.myDrugs);
        DrugManager = (LinearLayout) findViewById(R.id.drugManager);
        DocView = (LinearLayout) findViewById(R.id.docView);

        MyProfile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientHome.this, PatientMyProfile.class);
                        startActivity(intent);
                    }
                }
        );

        MyDrugs.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientHome.this, DrugManRepSche.class);
                        intent.putExtra("type",1);
                        startActivity(intent);
                    }
                }
        );

        DrugManager.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientHome.this, DrugManRepSche.class);
                        intent.putExtra("type",2);
                        startActivity(intent);
                    }
                }
        );

    }

    private void firstTime(){
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if(firstStart){
            startActivity(new Intent(PatientHome.this, SignUp.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.profile:
                intent = new Intent(PatientHome.this, PatientMyProfile.class);
                break;
            case R.id.drugs:
                intent = new Intent(PatientHome.this, DrugManRepSche.class);
                intent.putExtra("type",1);
                break;
            case R.id.dman:
                intent = new Intent(PatientHome.this, DrugManRepSche.class);
                intent.putExtra("type",2);
                break;
            case R.id.docview:
                intent = new Intent(PatientHome.this, PatientHome.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
            return true;
        }else{
            return false;
        }
    }
}