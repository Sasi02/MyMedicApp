package com.example.user.mymedic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.user.mymedic.Database.DrugDAO;
import com.example.user.mymedic.Database.DrugScheduleHourDAO;
import com.example.user.mymedic.Database.DrugScheduleMealDAO;
import com.example.user.mymedic.Helper.DataValidator;
import com.example.user.mymedic.Helper.DrugCustomAdapter;
import com.example.user.mymedic.Helper.DrugSpinner;
import com.example.user.mymedic.Model.Drug;
import com.example.user.mymedic.Model.DrugScheduleHour;
import com.example.user.mymedic.Model.DrugScheduleMeal;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AssignDrug extends AppCompatActivity {

    private Spinner drugSpinner;
    private Spinner methodSpinner;
    private Spinner qtySpinner;
    private EditText hourDif;
    private CheckBox breakfast;
    private CheckBox lunch;
    private CheckBox dinner;
    private Spinner beforeOrAfter;
    private Button saveBtn;
    private LinearLayout hourBased;
    private LinearLayout mealBased;
    private DrugDAO drugData;
    private ArrayList<Drug> drugList;
    private DrugScheduleHourDAO drugHourData;
    private DrugScheduleMealDAO drugMealData;
    private DrugScheduleMeal drugScheduleMeal;
    private DrugScheduleHour drugScheduleHour;

    private String[] methods = {"Hour Based", "Meal Based"};
    private Integer[] quantity = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private String[] beforeAfter = {"Before", "After"};

    private ArrayAdapter<String> adapterMethods;
    private ArrayAdapter<Integer> adapterQuantity;
    private ArrayAdapter<String> adapterBeforeAfter;
    private DrugSpinner adapterDrugs;

    private String selectedMethod;
    private int selectedQty;
    private String selectedBA;
    private Drug selectedDrug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_drug);



        hourBased = (LinearLayout)findViewById(R.id.hourBasedLayout);
        mealBased = (LinearLayout)findViewById(R.id.mealBasedLayout);
        hourBased.setVisibility(View.INVISIBLE);
        mealBased.setVisibility(View.INVISIBLE);

        final Spinner drugSpinner = (Spinner)findViewById(R.id.drugSpinner);
        final Spinner methodSpinner = (Spinner)findViewById(R.id.methodSpinner);
        final Spinner qtySpinner = (Spinner)findViewById(R.id.qtySpinner);
        final EditText hourDif = (EditText) findViewById(R.id.hourDifference);
        final CheckBox breakfast = (CheckBox) findViewById(R.id.breakfast);
        final CheckBox lunch = (CheckBox) findViewById(R.id.lunch);
        final CheckBox dinner = (CheckBox) findViewById(R.id.dinner);
        final Spinner beforeOrAfter = (Spinner)findViewById(R.id.beforeOrAfter);
        Button saveBtn = (Button) findViewById(R.id.saveAssignedDrugBtn);
        drugData = new DrugDAO(this);
        drugHourData = new DrugScheduleHourDAO(this);
        drugMealData = new DrugScheduleMealDAO(this);

        Drug perasi = new Drug(0, "SPC", "Peracitamol", 25.0);
        drugData.Open();
        drugData.add(perasi);
        drugList = drugData.GetAll();
        drugData.close();

        adapterMethods = new ArrayAdapter<String>(AssignDrug.this, android.R.layout.simple_spinner_item, methods);
        methodSpinner.setAdapter(adapterMethods);

        adapterQuantity = new ArrayAdapter<Integer>(AssignDrug.this, android.R.layout.simple_spinner_item, quantity);
        qtySpinner.setAdapter(adapterQuantity);

        adapterBeforeAfter = new ArrayAdapter<String>(AssignDrug.this, android.R.layout.simple_spinner_item, beforeAfter);
        beforeOrAfter.setAdapter(adapterBeforeAfter);

        adapterDrugs = new DrugSpinner(drugList, AssignDrug.this);
        drugSpinner.setAdapter(adapterDrugs);

        methodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedMethod = String.valueOf(parent.getItemAtPosition(position));
                if(selectedMethod == "Hour Based"){
                    ViewGroup.LayoutParams layout = hourBased.getLayoutParams();
                    layout.height = WRAP_CONTENT;
                    hourBased.setLayoutParams(layout);
                    hourBased.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layout2 = mealBased.getLayoutParams();
                    layout2.height = 0;
                    mealBased.setLayoutParams(layout2);
                }

                if(selectedMethod == "Meal Based"){
                    ViewGroup.LayoutParams layout = mealBased.getLayoutParams();
                    layout.height = WRAP_CONTENT;
                    mealBased.setLayoutParams(layout);
                    mealBased.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layout2 = hourBased.getLayoutParams();
                    layout2.height = 0;
                    hourBased.setLayoutParams(layout2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        qtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedQty = Integer.valueOf((Integer) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        beforeOrAfter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBA = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        drugSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDrug = (Drug) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double hourDifValue;
                boolean breakfastChecked = breakfast.isChecked();
                boolean lunchChecked = lunch.isChecked();
                boolean dinnerChecked = dinner.isChecked();
                String beforeAfterText = (String)beforeOrAfter.getSelectedItem();

                if(hourDif.getText().toString().isEmpty()) {
                    hourDifValue = 0.0;
                }else {
                    hourDifValue = Double.parseDouble(hourDif.getText().toString());
                }

                boolean empty = false;
                boolean invalid = false;

                if(selectedDrug == null){
                    drugSpinner.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(selectedMethod.isEmpty()){
                    methodSpinner.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(selectedQty == 0){
                    qtySpinner.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    empty = true;
                }

                if(selectedMethod == "Hour Based"){
                    if(DataValidator.isEmpty(hourDifValue)){
                        hourDif.setBackgroundColor(getResources().getColor(R.color.warningColor));
                        empty = true;
                    }
                }

                if(selectedMethod == "Meal Based"){
                    if(breakfastChecked == false || lunchChecked == false || dinnerChecked == false){
                        breakfast.setBackgroundColor(getResources().getColor(R.color.warningColor));
                        dinner.setBackgroundColor(getResources().getColor(R.color.warningColor));
                        lunch.setBackgroundColor(getResources().getColor(R.color.warningColor));
                        empty = true;
                    }
                    if(selectedBA.isEmpty()){
                        beforeOrAfter.setBackgroundColor(getResources().getColor(R.color.warningColor));
                        empty = true;
                    }
                }

                if(empty){
                    Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(hourDifValue > 24.0){
                    hourDif.setBackgroundColor(getResources().getColor(R.color.warningColor));
                    invalid = true;
                }

                if(invalid){
                    Toast.makeText(getApplicationContext(),"Please Re-check All Fields", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(selectedMethod == "Hour Based"){
                    drugScheduleHour.setMethod(selectedMethod);
                    drugScheduleHour.setDuration(hourDifValue);
                    drugScheduleHour.setNoOfPills(selectedQty);
                    drugScheduleHour.setDrugId(selectedDrug.getId());

                    drugHourData.Open();
                    try {
                        drugHourData.add(drugScheduleHour);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                    drugHourData.close();
                }

                if(selectedMethod == "Meal Based"){
                    drugScheduleMeal.setMethod(selectedMethod);
                    drugScheduleMeal.setNoOfPills(selectedQty);
                    //drugScheduleMeal.setSelectedMeal();
                    drugScheduleMeal.setBeforeOrAfter(selectedBA);
                    drugScheduleMeal.setDrugId(selectedDrug.getId());

                    drugHourData.Open();
                    try {
                        drugHourData.add(drugScheduleHour);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                    drugHourData.close();
                }
            }
        });




    }
}
