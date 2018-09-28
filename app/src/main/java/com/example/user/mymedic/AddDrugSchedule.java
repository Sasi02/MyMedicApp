package com.example.user.mymedic;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Helper.TypeConverter;
import com.example.user.mymedic.Model.Prescription;
import com.example.user.mymedic.Model.User;

import java.util.Calendar;
import java.util.Date;

public class AddDrugSchedule extends AppCompatActivity {

    EditText prescriptionName;
    EditText doctorName;
    EditText disease;
    EditText discription;
    EditText startDate;
    EditText endDate;
    Button saveBtn;
    Button addDrugBtn;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    UserDAO userData;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug_schedule);

        prescriptionName = (EditText)findViewById(R.id.prescriptionNameField);
        doctorName = (EditText)findViewById(R.id.doctorNameField);
        disease = (EditText)findViewById(R.id.diseaseField);
        discription = (EditText)findViewById(R.id.discriptionField);
        startDate = (EditText)findViewById(R.id.startDateField);
        endDate = (EditText)findViewById(R.id.endDateField);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        addDrugBtn = (Button)findViewById(R.id.addDrugBtn);

        addDrugBtn.setClickable(false);

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
                        startDate.setText(year+ "/" +( month + 1 )+ "/" +dayOfMonth);
                    }
                },day, month, year);
                datePickerDialog.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(AddDrugSchedule.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDate.setText(dayOfMonth+ "/" +( month + 1 )+ "/" +year);
                    }
                },day, month, year);
                datePickerDialog.show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String prescriptionNameText = prescriptionName.getText().toString();
                String doctorNameText = doctorName.getText().toString();
                String diseaseText = disease.getText().toString();
                String discriptionText = discription.getText().toString();
                Date startDateText = TypeConverter.toDate(startDate.getText().toString());
                Date endDateText = TypeConverter.toDate(startDate.getText().toString());

                Prescription prescription = new Prescription();


            }
        });

    }
}
