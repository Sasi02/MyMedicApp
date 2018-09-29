package com.example.user.mymedic;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Model.User;

public class PatientHome extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    LinearLayout MyProfile;
    LinearLayout DocView;
    LinearLayout MyDrugs;
    LinearLayout DrugManager;
    NavigationView Menu;
    TextView username;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        firstTime();

        Menu = (NavigationView) findViewById(R.id.home_nav);
        MyProfile = (LinearLayout) findViewById(R.id.myProfile);
        MyDrugs = (LinearLayout) findViewById(R.id.myDrugs);
        DrugManager = (LinearLayout) findViewById(R.id.drugManager);
        DocView = (LinearLayout) findViewById(R.id.docView);

        if(Menu!=null){
            Menu.setNavigationItemSelectedListener(this);
        }

        View header = Menu.getHeaderView(0);

        UserDAO UserData = new UserDAO(this);
        User user;

        UserData.Open();
        user = UserData.findById(1);
        UserData.close();

        if(user==null){
            return;
        }

        username = (TextView) header.findViewById(R.id.nav_username);
        email = (TextView) header.findViewById(R.id.nav_email);

        username.setText(user.getFirstName() + " " + user.getLastName());
        email.setText(user.getEmail());

        MyProfile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientHome.this, PatientMyProfile.class);
                        startActivity(intent);
                    }
                }
        );

        DocView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientHome.this, DoctorView.class);
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
    public boolean onNavigationItemSelected(MenuItem item){
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
            case R.id.exit:
                System.exit(0);
        }
        if(intent!=null){
            startActivity(intent);
            return true;
        }else{
            return false;
        }
    }

    public void buttonClick(View v){
        switch(v.getId()){
            case R.id.notificationButton:
                startActivity(new Intent(this, Notification.class));
        }
    }
}