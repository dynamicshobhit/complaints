package com.example.shobhit.complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class landingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);
    }
    public void userlogin(View view)
    {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
    public void usersignup(View view)
    {
        Intent intent2 = new Intent(this,signup.class);
        startActivity(intent2);
    }
}
