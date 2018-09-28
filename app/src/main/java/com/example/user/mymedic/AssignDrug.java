package com.example.user.mymedic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class AssignDrug extends AppCompatActivity {

    private Spinner drugSpinner;
    private Spinner methodSpinner;
    private Spinner qtySpinner;
    private EditText hourDif;
    private CheckBox breakfast;
    private CheckBox lunch;
    private CheckBox Dinner;
    private Spinner beforeOrAfter;
    private Button saveBtn;

    private String[] methods = {"Hour Based", "Meal Based"};
    private int[] quantity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private String[] beforeAfter = {"Before", "After"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_drug);

        Spinner drugSpinner = (Spinner)findViewById(R.id.drugSpinner);
        Spinner methodSpinner = (Spinner)findViewById(R.id.methodSpinner);
        Spinner qtySpinner = (Spinner)findViewById(R.id.qtySpinner);
        EditText hourDif = (EditText) findViewById(R.id.hourDifference);
        CheckBox breakfast = (CheckBox) findViewById(R.id.breakfast);
        CheckBox lunch = (CheckBox) findViewById(R.id.lunch);
        CheckBox Dinner = (CheckBox) findViewById(R.id.dinner);
        Spinner beforeOrAfter = (Spinner)findViewById(R.id.beforeOrAfter);
        Button saveBtn = (Button) findViewById(R.id.addDrugBtn);




    }
}
