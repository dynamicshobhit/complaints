package com.example.shobhit.complaint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

public class Comments extends AppCompatActivity {

    private ListView lv;

    private RequestQueue requestQueue;
    private SharedPreferences preferences;
    int counter =0;

    static ArrayList complaint_comments_desc=new ArrayList();
    static ArrayList complaint_comments_userid=new ArrayList();
    static ArrayList complaint_comments_person=new ArrayList();

    public String commentdesc="";
    public final String API_INFO_COMPLAINT=login.ipaddress()+"/api/get_comments.json?id="+CustomAdapterPosts.id;
    public final String API_COMMENT_COMPLAINT=login.ipaddress()+"/api/add_comment.json?id="+CustomAdapterPosts.id+"&body="+commentdesc;
    public final String API_UPVOTE=login.ipaddress()+"/api/up_downvote.json?id="+CustomAdapterPosts.id+"&vote=1";
    public final String API_DOWNVOTE=login.ipaddress()+"/api/up_downvote.json?id="+CustomAdapterPosts.id+"&vote=-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        complaint_comments_desc.clear();
        complaint_comments_userid.clear();
        complaint_comments_person.clear();
        getcomplaintinfo();
        setContentView(R.layout.activity_comments);
        TextView output=(TextView)findViewById(R.id.title);
        output.setText(CustomAdapterPosts.tit);
        output=(TextView)findViewById(R.id.description);
        output.setText(CustomAdapterPosts.des);
        lv = (ListView)findViewById(R.id.commentlist);
        lv.setAdapter(new CustomAdapterComments(this, complaint_comments_desc, complaint_comments_person));

    }

    public void getcomplaintinfo(){
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,API_INFO_COMPLAINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        response_complaintinfo(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Comments.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);
    }

    public static void response_complaintinfo(String json){
        //String threads_title="";
        //String threads_desc="";

        try
        {
            JSONObject jobject= new JSONObject(json);
            //JSONObject thread=jobject.getJSONObject("thread");
            JSONArray comments=jobject.getJSONArray("comments");



            for(int i=0;i<comments.length();i++){
                JSONObject jo = comments.getJSONObject(i);
                complaint_comments_desc.add(jo.getString("description"));
                complaint_comments_person.add(jo.getString("user_name"));

            }

            /*JSONArray comment_users=jobject.getJSONArray("comment_users");
            for(int i=0;i<threads_comments_userid.size();i++)
            {

                for(int j=0;j<comment_users.length();j++)
                {
                    if(comment_users.getJSONObject(j).getString("id").equals(threads_comments_userid.get(i))){
                        threads_comments_person.add(comment_users.getJSONObject(j).getString("first_name"));
                        break;
                    }

                }
            }*/

        }
        catch (org.json.JSONException e) {
            e.printStackTrace();
        }

    }
    public void postcomment(View v){
        EditText etcomment=(EditText)findViewById(R.id.newcomment);
        commentdesc = etcomment.getText().toString().trim().replaceAll(" ", "%20");
        //String API_COMMENT_THREAD=login.ipaddress()+"/threads/post_comment.json?thread_id="+CustomAdapter3.id+"&description="+commentdesc;
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,API_COMMENT_COMPLAINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Comments.this, "comment posted", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Comments.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue.add(stringRequest);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void upvote(View v){
        if(counter==0) {
            requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, API_UPVOTE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Comments.this, "upvoted", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Comments.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            requestQueue.add(stringRequest);
            counter=1;
            Intent intent = getIntent();
            finish();
            startActivity(intent);

        }
    }
    public void downvote(View v){
        if(counter==0) {
            requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, API_DOWNVOTE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Comments.this, "downvoted", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Comments.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            requestQueue.add(stringRequest);
            counter=1;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

}
