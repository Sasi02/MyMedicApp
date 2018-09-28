package com.example.user.mymedic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.mymedic.Model.User;

public class PatientSignUp extends AppCompatActivity {

    private Button signUp;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);

        signUp = (Button) findViewById(R.id.patientSignup);

        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientSignUp.this, SignUp.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
