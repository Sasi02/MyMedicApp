package com.example.user.mymedic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Helper.DataValidator;
import com.example.user.mymedic.Helper.TypeConverter;
import com.example.user.mymedic.Model.User;

public class PatientMyProfile extends AppCompatActivity {

    private TextView Intials;
    private TextView FirstName;
    private TextView LastName;
    private TextView DateOfBirth;
    private TextView Phone;
    private TextView Email;
    private ImageView InitialsEditButton;
    private ImageView FirstNameEditButton;
    private ImageView LastNameEditButton;
    private ImageView DateOfBirthEditButton;
    private ImageView PhoneEditButton;
    private ImageView EmailEditButton;
    private Button OkayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_my_profile);

        Intials = (TextView) findViewById(R.id.profileInitials);
        FirstName = (TextView) findViewById(R.id.profileFirstName);
        LastName = (TextView) findViewById(R.id.profileLastName);
        DateOfBirth = (TextView) findViewById(R.id.profileDOB);
        Phone = (TextView) findViewById(R.id.profilePhone);
        Email = (TextView) findViewById(R.id.profileEmail);
        InitialsEditButton = (ImageView) findViewById(R.id.profileInitialsEdit);
        FirstNameEditButton = (ImageView) findViewById(R.id.profileFirstNameEdit);
        LastNameEditButton = (ImageView) findViewById(R.id.profileLastNameEdit);
        DateOfBirthEditButton = (ImageView) findViewById(R.id.profileDOBEdit);
        PhoneEditButton = (ImageView) findViewById(R.id.profilePhoneEdit);
        EmailEditButton = (ImageView) findViewById(R.id.profileEmailEdit);
        OkayButton = (Button) findViewById(R.id.profileOkButton);

        Intials.setFocusable(false);
        FirstName.setFocusable(false);
        LastName.setFocusable(false);
        DateOfBirth.setFocusable(false);
        Phone.setFocusable(false);
        Email.setFocusable(false);

        User user;
        UserDAO userData = new UserDAO(this);
        userData.Open();
        user = userData.findById(1);
        userData.close();

        if(user == null){
            //Set First Time Done
            SharedPreferences perfs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = perfs.edit();
            editor.putBoolean("firstStart", true);
            editor.apply();

            //Restart App
            Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        AddText(FirstName, user.getFirstName());
        AddText(LastName, user.getLastName());
        AddText(DateOfBirth, TypeConverter.toString(user.getDob()));
        AddText(Phone, user.getPhone());
        AddText(Email, user.getEmail());
        AddText(Intials, user.getInitials());

        FirstNameEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirstName.setCursorVisible(true);
                        FirstName.setFocusableInTouchMode(true);
                        FirstName.setInputType(InputType.TYPE_CLASS_TEXT);
                        FirstName.requestFocus(); //to trigger the soft input
                    }
                }
        );
        InitialsEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intials.setCursorVisible(true);
                        Intials.setFocusableInTouchMode(true);
                        Intials.setInputType(InputType.TYPE_CLASS_TEXT);
                        Intials.requestFocus(); //to trigger the soft input
                    }
                }
        );
        LastNameEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LastName.setCursorVisible(true);
                        LastName.setFocusableInTouchMode(true);
                        LastName.setInputType(InputType.TYPE_CLASS_TEXT);
                        LastName.requestFocus(); //to trigger the soft input
                    }
                }
        );
        DateOfBirthEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DateOfBirth.setCursorVisible(true);
                        DateOfBirth.setFocusableInTouchMode(true);
                        DateOfBirth.setInputType(InputType.TYPE_CLASS_TEXT);
                        DateOfBirth.requestFocus(); //to trigger the soft input
                    }
                }
        );
        PhoneEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Phone.setCursorVisible(true);
                        Phone.setFocusableInTouchMode(true);
                        Phone.setInputType(InputType.TYPE_CLASS_TEXT);
                        Phone.requestFocus(); //to trigger the soft input
                    }
                }
        );
        EmailEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Email.setCursorVisible(true);
                        Email.setFocusableInTouchMode(true);
                        Email.setInputType(InputType.TYPE_CLASS_TEXT);
                        Email.requestFocus(); //to trigger the soft input
                    }
                }
        );

        OnButtonClick(user);
     }

    private void OnButtonClick(final User user) {
        OkayButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Get Text
                        String firstNameText = FirstName.getText().toString();
                        String lastNameText = LastName.getText().toString();
                        String initialsText = Intials.getText().toString();
                        String dobText = DateOfBirth.getText().toString();
                        String phoneText = Phone.getText().toString();
                        String emailText = Email.getText().toString();

                        User newuser = new User();

                        //Validate and Add
                        if(!DataValidator.isEmpty(firstNameText) && DataValidator.isWord(firstNameText))
                            newuser.setFirstName(firstNameText);
                        if(!DataValidator.isEmpty(lastNameText) && DataValidator.isWord(lastNameText))
                            newuser.setLastName(lastNameText);
                        if(!DataValidator.isEmpty(initialsText) && DataValidator.isText(initialsText))
                            newuser.setInitials(initialsText);
                        if(!DataValidator.isEmpty(phoneText) && DataValidator.isPhoneNumber(phoneText))
                            newuser.setPhone(phoneText);
                        if(!DataValidator.isEmpty(emailText) && DataValidator.isEmail(emailText))
                            newuser.setEmail(emailText);
                        if(!DataValidator.isEmpty(dobText))
                            newuser.setDob(TypeConverter.toDate(dobText));

                        if(!user.equals(newuser)){
                            newuser.setId(user.getId());

                            UserDAO userData = new UserDAO(PatientMyProfile.this);
                            userData.Open();
                            userData.updateUser(newuser);
                            userData.close();
                        }

                        startActivity(new Intent(PatientMyProfile.this, PatientHome.class));
                    }
                }
        );
    }

    private void AddText(TextView t, String str){
        t.setText(str);
    }

    private void Edit(ImageView i, final TextView t){
        i.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        t.setFocusable(true);
                    }
                }
        );
    }
}
