package com.example.user.mymedic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DrugManRepSche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_man_rep_sche);

        Intent getPage = getIntent();
        final int pageNum = getPage.getIntExtra("type",0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pageNum == 1){
                    Intent intent = new Intent(DrugManRepSche.this, AddDrug.class);
                    startActivity(intent);
                }
            }
        });
    }

}
