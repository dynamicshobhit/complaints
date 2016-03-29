package com.example.shobhit.complaint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.interfaces.DSAPublicKey;
import java.util.ArrayList;
import java.util.List;

public class New_complaint extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public final String IP_ADDRESS = login.ipaddress();
    public String API_NEW_COMPLAINT;
    EditText edittitle;
    EditText editbody;
    String title;
    String body;
    String category="";
    String complaintlevel="";
    public RequestQueue requestQueue;
    public SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_level);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_category);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Institute Level");
        categories.add("Hostel Level");
        categories.add("Individual Level");

        List<String> categories1 = new ArrayList<String>();
        categories1.add("Dean");
        categories1.add("BSW");
        categories1.add("Institute Security");
        categories1.add("CSC");
        categories1.add("Hostel Mess Secy.");
        categories1.add("Hostel Maintainence Secy.");
        categories1.add("Hostel Cultural Secy.");
        categories1.add("Hostel Sports Secy.");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);
        spinner2.setAdapter(dataAdapter2);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        switch(parent.getId()){
            case R.id.spinner_level:
                String item = parent.getItemAtPosition(position).toString();
                switch(item){
                    case "Institute Level":
                        category+="1";
                        complaintlevel="1";
                    case "Hostel Level":
                        category+="2";
                        complaintlevel="2";
                    case "Individual Level":
                        category+="3";
                        complaintlevel="3";
                    break;
                }
                break;
            case R.id.spinner_category:
                item = parent.getItemAtPosition(position).toString();
                switch(item){
                    case "Dean":
                        category+="1";
                    case "BSW":
                        category+="2";
                    case "Institute Security":
                        category+="3";
                    case "CSC":
                        category+="4";
                    case "Hostel Mess Secy.":
                        category+="5";
                    case "Hostel Maintenance Secy.":
                        category+="6";
                    case "Hostel Cultural Secy.":
                        category+="7";
                    case "Hostel Sports Secy.":
                        category+="8";

                        break;
                }
                    break;
        }
        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void register_complaint(View v){

        edittitle = (EditText) findViewById(R.id.ettitle);
        title = edittitle.getText().toString().trim().replaceAll(" ", "%20");
        editbody = (EditText) findViewById(R.id.etname);
        body = editbody.getText().toString().trim().replaceAll(" ", "%20");
        API_NEW_COMPLAINT=IP_ADDRESS+"/api/add_complaint.json?catagoryy="+category+"&complaint_level="+complaintlevel+"&title="+title+"&resolved=false&body="+body;

        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,API_NEW_COMPLAINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(API_NEW_COMPLAINT);
                        //Toast.makeText(MainActivity.this, "comment posted"+thread(response), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(New_complaint.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);

    }
    public void gotoregister(View view)
    {
        Intent a=new Intent(this,New_complaint.class);
        startActivity(a);
    }
    public void gototrack(View view)
    {
        Intent a=new Intent(this,track_complaints.class);
        startActivity(a);
    }
    public void gotoview(View view)
    {
        Intent a=new Intent(this,New_complaint.class);
        startActivity(a);
    }
    public void gotoprofile(View view)
    {
        Intent a=new Intent(this,New_complaint.class);
        startActivity(a);
    }
}
