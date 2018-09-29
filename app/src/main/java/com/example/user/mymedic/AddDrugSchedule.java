package com.example.user.mymedic;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.user.mymedic.Database.PrescriptionDAO;
import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Helper.DataValidator;
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
    PrescriptionDAO prescriptionData;

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
        prescriptionData = new PrescriptionDAO(this);

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
                        endDate.setText(year+ "/" +( month + 1 )+ "/" +dayOfMonth);
                    }
                },day, month, year);
                datePickerDialog.show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetFields();

                String prescriptionNameText = prescriptionName.getText().toString();
                String doctorNameText = doctorName.getText().toString();
                String diseaseText = disease.getText().toString();
                String discriptionText = discription.getText().toString();
                Date startDateText = null;
                Date endDateText = null;

                if(!startDate.getText().toString().isEmpty()){
                    startDateText = TypeConverter.toDate(startDate.getText().toString());
                }
                if(!endDate.getText().toString().isEmpty()){
                    endDateText = TypeConverter.toDate(startDate.getText().toString());
                }

                Prescription prescription = new Prescription();

                boolean empty = false;
                boolean invalid = false;

                if(DataValidator.isEmpty(prescriptionNameText)){
                    prescriptionName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(DataValidator.isEmpty(doctorNameText)){
                    doctorName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(DataValidator.isEmpty(diseaseText)){
                    disease.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(DataValidator.isEmpty(discriptionText)){
                    discription.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(startDate.getText().toString().isEmpty()){
                    startDate.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(endDate.getText().toString().isEmpty()){
                    endDate.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(empty){
                    Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!DataValidator.isText(prescriptionNameText)){
                    prescriptionName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(!DataValidator.isText(doctorNameText)){
                    doctorName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(!DataValidator.isText(diseaseText)){
                    disease.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(invalid){
                    Toast.makeText(getApplicationContext(),"Please Re-check All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                prescription.setName(prescriptionNameText);
                prescription.setDocName(doctorNameText);
                prescription.setDisease(diseaseText);
                prescription.setDiscription(discriptionText);
                prescription.setStartDate(startDateText);
                prescription.setEndDate(endDateText);

                prescriptionData.Open();
                try {
                    prescriptionData.add(prescription);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                prescriptionData.close();
                addDrugBtn.setClickable(true);

            }
        });

    }

    private void resetFields() {
        prescriptionName.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        doctorName.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        disease.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        discription.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        startDate.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        endDate.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
    }
}
