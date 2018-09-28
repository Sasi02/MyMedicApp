package com.example.user.mymedic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Helper.DataValidator;
import com.example.user.mymedic.Helper.TypeConverter;
import com.example.user.mymedic.Model.User;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    private Button Next;
    private EditText FirstName;
    private EditText LastName;
    private EditText DOB;
    private EditText Phone;
    private EditText Email;
    private EditText Gender;
    private EditText Initals;
    DatePickerDialog datePickerDialog;
    UserDAO UserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initialize Attributes
        FirstName = (EditText)findViewById(R.id.firstname);
        LastName = (EditText)findViewById(R.id.lastname);
        Initals = (EditText)findViewById(R.id.initials);
        DOB = (EditText)findViewById(R.id.dob);
        Phone = (EditText)findViewById(R.id.phone);
        Email = (EditText)findViewById(R.id.email);
        Gender = (EditText)findViewById(R.id.gender);
        Next = (Button) findViewById(R.id.signuponenext);
        UserData = new UserDAO(this);

        //Date Of Birth Set

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DOB.setText(dayOfMonth+ "/" +( month + 1 )+ "/" +year);
                    }
                },day, month, year);
                datePickerDialog.show();

            }
        });


        //Next Button Action

        Next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //reset All Fields
                        resetFields();

                        //Create User
                        User user = new User();

                        //Get Attribute Values
                        String firstName = FirstName.getText().toString();
                        String lastName = LastName.getText().toString();
                        String dob = DOB.getText().toString();
                        String phone = Phone.getText().toString();
                        String email = Email.getText().toString();
                        String gender = Gender.getText().toString();
                        String initials = Initals.getText().toString();

                        //Validations
                        boolean empty = false;
                        boolean invalid = false;

                        //Empty Validator
                        if(DataValidator.isEmpty(initials)){
                            Initals.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(DataValidator.isEmpty(firstName)){
                            FirstName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(DataValidator.isEmpty(lastName)){
                            LastName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(DataValidator.isEmpty(dob)){
                            DOB.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(DataValidator.isEmpty(phone)){
                            Phone.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(DataValidator.isEmpty(email)){
                            Email.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(DataValidator.isEmpty(gender)){
                            Gender.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            empty = true;
                        }

                        if(empty){
                            Toast.makeText(getApplicationContext(),"Please Fill All Fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //Validate Fields

                        if(!DataValidator.isWord(firstName)){
                            FirstName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            invalid = true;
                        }
                        if(!DataValidator.isWord(lastName)){
                            LastName.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            invalid = true;
                        }

                        if(!DataValidator.isPhoneNumber(phone)) {
                            Phone.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            invalid = true;
                        }

                        if(!DataValidator.isEmail(email)){
                            Email.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            invalid = true;
                        }

                        if(!DataValidator.isWord(gender)){
                            Gender.setBackgroundColor(getResources().getColor(R.color.warningColor));
                            invalid = true;
                        }

                        if(invalid){
                            Toast.makeText(getApplicationContext(),"Please Re-check All Fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //Assign Values To User
                        user.setFirstName(firstName);
                        user.setDob(TypeConverter.toDate(dob));
                        user.setLastName(lastName);
                        user.setGender(gender);
                        user.setPhone(phone);
                        user.setInitials(initials);
                        user.setEmail(email);

                        //Add User To DB
                        UserData.Open();
                        UserData.add(user);
                        UserData.close();

                        //Check if User Added
                        User newUser = null;
                        UserData.Open();
                        try{newUser = UserData.findById(1);}catch(Exception e){newUser=null;}
                        UserData.close();

                        //Set First Time Done
                        SharedPreferences perfs = getSharedPreferences("prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = perfs.edit();
                        editor.putBoolean("firstStart", false);
                        editor.apply();

                        //Restart App
                        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                }
        );
    }

    private void resetFields(){
        FirstName.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
        LastName.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
    }
}
