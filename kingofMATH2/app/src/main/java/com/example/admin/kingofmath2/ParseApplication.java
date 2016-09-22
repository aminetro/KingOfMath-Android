package com.example.admin.kingofmath2;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by ADMIN on 31/12/2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "HxQ3okETLaYRaP8uJSFiBdMgMkiQlKshpZe6TYa3", "luZcvzmphwNTVml9Mf0iPcRxaunxODz8R8nKpIUh");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}

