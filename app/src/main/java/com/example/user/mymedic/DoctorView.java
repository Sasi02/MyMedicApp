package com.example.user.mymedic;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Helper.DataValidator;
import com.example.user.mymedic.Helper.TypeConverter;
import com.example.user.mymedic.Model.User;

/**
 * Created by Sachith Rukshan on 9/29/2018.
 */

public class DoctorView extends Activity {
    private TextView disease;
    private TextView allegies;
    private TextView operations;
    private Button diseaseAddBtn;
    private Button diseaseEditBtn;
    private Button allegiesAddBtn;
    private Button allegiesEditBtn;
    private Button operationsAddBtn;
    private Button operationsEditBtn;
    private Button applyBtn;
    private String diseaseTemp;
    private String allegiesTemp;
    private String operationsTemp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);

        User user = getUser();

        // Initialise Fields
        disease = (TextView) findViewById(R.id.doctorViewDiseasesText);
        allegies = (TextView) findViewById(R.id.doctorViewAllegiesText);
        operations = (TextView) findViewById(R.id.doctorViewOperationsText);
        diseaseAddBtn = (Button) findViewById(R.id.doctorViewDiseasesAddBtn);
        diseaseEditBtn = (Button) findViewById(R.id.doctorViewDiseasesEditBtn);
        allegiesAddBtn = (Button) findViewById(R.id.doctorViewAllegiesAddBtn);
        allegiesEditBtn = (Button) findViewById(R.id.doctorViewAllegiesEditBtn);
        operationsAddBtn = (Button) findViewById(R.id.doctorViewOperationsAddBtn);
        operationsEditBtn = (Button) findViewById(R.id.doctorViewOperationsEditBtn);
        applyBtn = (Button) findViewById(R.id.doctorViewApplyBtn);

        disease.setText(user.getGenDiseases());
        allegies.setText(user.getAllergies());
        operations.setText(user.getOperations());

        diseaseEditBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        disease.setCursorVisible(true);
                        disease.setFocusableInTouchMode(true);
                        disease.setInputType(InputType.TYPE_CLASS_TEXT);
                        disease.requestFocus(); //to trigger the soft input
                    }
                }
        );

        allegiesEditBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        allegies.setCursorVisible(true);
                        allegies.setFocusableInTouchMode(true);
                        allegies.setInputType(InputType.TYPE_CLASS_TEXT);
                        allegies.requestFocus(); //to trigger the soft input
                    }
                }
        );

        operationsEditBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        operations.setCursorVisible(true);
                        operations.setFocusableInTouchMode(true);
                        operations.setInputType(InputType.TYPE_CLASS_TEXT);
                        operations.requestFocus(); //to trigger the soft input
                    }
                }
        );

        operationsAddBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorView.this);
                        builder.setTitle("Add Operation");

// Set up the input
                        final EditText input = new EditText(DoctorView.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

// Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringBuilder stringBuilder = new StringBuilder(operations.getText().toString());
                                stringBuilder.append("[" + input.getText().toString() + "]");
                                operations.setText(stringBuilder.toString());
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                }
        );

        diseaseAddBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorView.this);
                        builder.setTitle("Add Disease");

// Set up the input
                        final EditText input = new EditText(DoctorView.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

// Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringBuilder stringBuilder = new StringBuilder(disease.getText().toString());
                                stringBuilder.append("[" + input.getText().toString() + "]");
                                disease.setText(stringBuilder.toString());
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                }
        );

        allegiesAddBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorView.this);
                        builder.setTitle("Add Allegies");

// Set up the input
                        final EditText input = new EditText(DoctorView.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

// Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StringBuilder stringBuilder = new StringBuilder(allegies.getText().toString());
                                stringBuilder.append("[" + input.getText().toString() + "]");
                                allegies.setText(stringBuilder.toString());
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                }
        );

        OnButtonClick(user);
    }

    private User getUser() {
        User user;
        UserDAO userDAO = new UserDAO(this);
        userDAO.Open();
        user = userDAO.findById(1);
        userDAO.close();
        return user;
    }

    private void OnButtonClick(final User user) {
        applyBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Get Text
                        String diseaseText = disease.getText().toString();
                        String allegiesText = allegies.getText().toString();
                        String operationsText = operations.getText().toString();

                        User newuser = new User();

                        //Validate and Add
                        if(!DataValidator.isEmpty(diseaseText))
                            newuser.setGenDiseases(diseaseText);
                        if(!DataValidator.isEmpty(allegiesText))
                            newuser.setAllergies(allegiesText);
                        if(!DataValidator.isEmpty(operationsText))
                            newuser.setOperations(operationsText);

                        if(!user.equals(newuser)){
                            user.setOperations(newuser.getOperations()==""?user.getOperations():newuser.getOperations());
                            user.setAllergies(newuser.getAllergies()==""?user.getAllergies():newuser.getAllergies());
                            user.setGenDiseases(newuser.getGenDiseases()==""?user.getGenDiseases():newuser.getGenDiseases());

                            UserDAO userData = new UserDAO(DoctorView.this);
                            userData.Open();
                            userData.updateUser(user);
                            userData.close();
                        }

                        startActivity(new Intent(DoctorView.this, PatientHome.class));
                    }
                }
        );
    }

    public void buttonClick(View v){
        switch(v.getId()){
            case R.id.notificationButton:
                startActivity(new Intent(this, Notification.class));
        }
    }
}
