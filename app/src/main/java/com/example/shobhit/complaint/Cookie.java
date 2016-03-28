package com.example.shobhit.complaint;

/**
 * Created by shobhit on 28-03-2016.
 */
import android.app.Application;

import java.net.CookieHandler;
import java.net.CookieManager;
public class Cookie extends Application{




    CookieHandler cookieManage;
    public void onCreate() {
        cookieManage= new CookieManager();
        CookieHandler.setDefault(cookieManage);
        super.onCreate();
    }



}