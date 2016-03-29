package com.example.shobhit.complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_complaints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);
    }
    public void viewlevelcomplaints(View view)
    {
        Intent intent=new Intent(this,Posts.class );
        Button b = (Button)view;
        String text = b.getText().toString();
        String levelint="";
        if (text.equals("Institute level"))
        {
            levelint="1";
        }
        if (text.equals("Hostel level"))
        {
            levelint="2";
        }
        if (text.equals("Individual"))
        {
            levelint="3";
        }
        intent.putExtra("levelname", levelint);
        startActivity(intent);

        /*
        how to extract
        Intent intent = getIntent();
String temp = intent.getExtras().getString("levelname");
         */
    }
}
