package com.example.user.mymedic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.mymedic.Database.DrugDAO;
import com.example.user.mymedic.Model.Drug;

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
                if(drugManufacturer.getText().toString().equalsIgnoreCase("") &&
                        drugManufacturer.getText().toString().equals(null)){
                    Drug drug = new Drug();
                    drug.setManufacturer(drugManufacturer.getText().toString());
                    drug.setName(drugName.getText().toString());
                    drug.setDosage(Double.parseDouble(dosage.getText().toString()));

                    drugData.Open();
                    drugData.add(drug);
                    drugData.close();
                }
            }
        });
    }
}
