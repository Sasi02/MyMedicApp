package com.example.user.mymedic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.mymedic.Database.UserDAO;
import com.example.user.mymedic.Model.User;

public class NavMenu extends AppCompatActivity {

    TextView username;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_header);

        UserDAO UserData = new UserDAO(this);
        User user;

        UserData.Open();
        user = UserData.findById(1);
        UserData.close();

        if(user==null){
            return;
        }

        username = (TextView) findViewById(R.id.nav_username);
        email = (TextView) findViewById(R.id.nav_email);

        username.setText(user.getFirstName() + " " + user.getLastName());
        email.setText(user.getEmail());
    }
}
