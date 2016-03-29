package com.example.shobhit.complaint;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;


public class login extends AppCompatActivity {

    public EditText etname;
    public EditText etpassword;
    public String username;
    public String password;
    public final String IP_ADDRESS = "http://10.192.38.18:8000/Backend_for_Complaints";
    public final static String IP_ADDRESS1 = "http://10.192.38.18:8000/Backend_for_Complaints";
    public String userid="";
    public String API_LOGIN;
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "session_id_backend_for_complaints";
    private static login _instance;
    private RequestQueue requestQueue;
    private SharedPreferences preferences;

    public static login get() {
        return _instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _instance=this;
        setContentView(R.layout.activity_login);

    }
    public final void checkSessionCookie(Map<String, String> headers) {
        System.out.println("yoyo1");
        System.out.println(headers);
        if (headers.containsKey(SET_COOKIE_KEY)
                && headers.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)) {
            String cookie = headers.get(SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                System.out.println(cookie);
                SharedPreferences.Editor prefEditor = preferences.edit();
                prefEditor.putString(SESSION_COOKIE, cookie);
                prefEditor.commit();
            }
        }
    }

    /**
     * Adds session cookie to headers if exists.
     *
     * @param headers
     */
    public final void addSessionCookie(Map<String, String> headers) {
        System.out.println("yoyo");
        System.out.println(preferences);
        System.out.println(preferences.getAll());
        String sessionId = preferences.getString(SESSION_COOKIE, "");
        System.out.println(headers);
        System.out.println(sessionId);
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (headers.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE_KEY));
            }
            System.out.println("yoyo3");
            System.out.println(builder);
            System.out.println("yoyo4");
            headers.put(COOKIE_KEY, builder.toString());
            System.out.println("yoyo5");
        }
    }

    public static void removeSessionCookie(Map<String,String> headers){
        headers.remove(COOKIE_KEY);
    }

    public void login() {
        System.out.println("yoyo");
        requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        response_login(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        requestQueue.add(stringRequest);
    }
    public void response_login(String json){
        try {
            System.out.println("yop");
            JSONObject jObject = new JSONObject(json);
            System.out.println(jObject);
            JSONObject jo= jObject.getJSONObject("user");
            userid=jo.getString("id");
            String success1 = jObject.getString("success");
            if (success1.equals("true")) {                                                      //TODO generate intent and also something like hello username
                Toast.makeText(login.get(), "login successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, New_complaint.class);
                startActivity(intent);
            }
            else{
                etpassword.setError("wrong password");
            }
        }
        catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }
    public static String ipaddress()
    {
        return IP_ADDRESS1;
    }
    public static String userid()
    {
        return userid();
    }
    public void checkuser(View v)
    {

        _instance = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        etname = (EditText) findViewById(R.id.loginname);
        username = etname.getText().toString();

        etpassword = (EditText) findViewById(R.id.loginpassword);
        password = etpassword.getText().toString();


        API_LOGIN = IP_ADDRESS + "/default/login.json?userid=" + username + "&password=" + password;

        login();


    }


}
