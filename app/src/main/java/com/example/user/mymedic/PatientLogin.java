package com.example.user.mymedic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientLogin extends AppCompatActivity {

    private Button signIn;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        signIn = (Button) findViewById(R.id.patientSignin);
        signUp = (Button) findViewById(R.id.patientSignup);

        signIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientLogin.this, PatientHome.class);
                        startActivity(intent);
                    }
                }
        );

        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientLogin.this, SignUp.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
