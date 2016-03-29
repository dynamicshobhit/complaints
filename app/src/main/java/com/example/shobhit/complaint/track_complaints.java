package com.example.shobhit.complaint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class track_complaints extends AppCompatActivity {

    private ListView mainListView ;
    private RequestQueue requestQueue;
    private SharedPreferences preferences;
    ArrayList<String> post_titles=new ArrayList<>();                                //this store the name of courses
    ArrayList<String> post_body=new ArrayList<>();
    ArrayList<String> post_votes=new ArrayList<>();                                //this store the credit of courses
    ArrayList<String> post_resolved=new ArrayList<>();
    ArrayList<String> post_postedby=new ArrayList<>();
    Context context;
    public final String IP_ADDRESS = login.ipaddress();
    public String API_TRACK_COMPLAINTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_complaints);
        context=this;
        API_TRACK_COMPLAINTS=IP_ADDRESS+"/api/post/by-user/"+login.userid()+".json";
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_TRACK_COMPLAINTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response_posts(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(track_complaints.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);
        mainListView = (ListView) findViewById( R.id.listviewtrackcomplaints);

    }

    public void response_posts(String json){
        try
        {
            JSONObject jobject= new JSONObject(json);
            JSONArray posts=jobject.getJSONArray("content");

            for(int i=0;i<posts.length();i++){
                JSONObject jo = posts.getJSONObject(i);
                post_titles.add(jo.getString("title"));
                post_body.add(jo.getString("body"));
                post_votes.add(jo.getString("votes"));
                post_resolved.add(jo.getString("Resolved"));
                post_postedby.add(jo.getString("posted_by"));


                // Set the ArrayAdapter as the ListView's adapter.
                mainListView.setAdapter( new CustomAdapterTrack(this,post_titles,post_body,post_votes,post_resolved,post_postedby));
            }

        }
        catch (org.json.JSONException e) {
            e.printStackTrace();
        }

    }
}
