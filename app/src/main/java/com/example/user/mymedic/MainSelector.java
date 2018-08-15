package com.example.user.mymedic;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainSelector extends AppCompatActivity {

    private ImageButton patientSelect;
    private ImageButton phamacistSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selector);

        patientSelect = (ImageButton) findViewById(R.id.patientButton);
        phamacistSelect = (ImageButton) findViewById(R.id.phamacistButton);

        patientSelect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainSelector.this, SignUp.class);
                        startActivity(intent);
                    }
                }
        );

        phamacistSelect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainSelector.this, SignUp1.class);
                        startActivity(intent);
                    }
                }
        );
        
        
    }
}
