package com.example.user.mymedic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.user.mymedic.Database.DrugDAO;
import com.example.user.mymedic.Helper.DataValidator;
import com.example.user.mymedic.Model.Drug;
import java.util.ArrayList;

public class AddDrug extends AppCompatActivity {

    EditText drugManufacturer;
    EditText drugName;
    EditText dosage;
    Button saveBtn;
    DrugDAO drugData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        drugManufacturer = (EditText)findViewById(R.id.drugManufacturerEditText);
        drugName = (EditText)findViewById(R.id.drugNameEditText);
        dosage = (EditText)findViewById(R.id.dosageEditText);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        drugData = new DrugDAO(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetFields();

                Drug drug = new Drug();

                String drugManufacturerText = drugManufacturer.getText().toString();
                String drugNameText = drugName.getText().toString();
                Double dosageValue;

                if(dosage.getText().toString().isEmpty()) {
                    dosageValue = 0.0;
                }else {
                    dosageValue = Double.parseDouble(dosage.getText().toString());
                }

                boolean empty = false;
                boolean invalid = false;

                if(DataValidator.isEmpty(drugManufacturerText)){
                    drugManufacturer.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(DataValidator.isEmpty(drugNameText)){
                    drugName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(DataValidator.isEmpty(dosageValue)){
                    dosage.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(empty){
                    Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!DataValidator.isText(drugManufacturerText)){
                    drugManufacturer.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(!DataValidator.isText(drugNameText)){
                    drugName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(dosageValue > 100){
                    dosage.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(invalid){
                    Toast.makeText(getApplicationContext(),"Please Re-check All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                drug.setManufacturer(drugManufacturerText);
                drug.setName(drugNameText);
                drug.setDosage(dosageValue);

                drugData.Open();
                drugData.add(drug);
                ArrayList drugs = drugData.GetAll();
                drugData.close();

            }
        });

        //this.finish();
    }

    private void resetFields() {
        drugManufacturer.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        drugName.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        dosage.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
    }
}
