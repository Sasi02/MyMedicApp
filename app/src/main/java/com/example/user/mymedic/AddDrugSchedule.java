package com.example.user.mymedic;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Model.User;

import java.util.Calendar;
import java.util.Date;

public class AddDrugSchedule extends AppCompatActivity {

    EditText startDate;
    EditText test;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    UserDAO userData;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug_schedule);

        startDate = (EditText)findViewById(R.id.editText7);
        test = (EditText)findViewById(R.id.editText5);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(AddDrugSchedule.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDate.setText(dayOfMonth+ "/" +( month + 1 )+ "/" +year);
                    }
                },day, month, year);
                datePickerDialog.show();

            }
        });

    }
}
