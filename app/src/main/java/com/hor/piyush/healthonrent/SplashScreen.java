package com.hor.piyush.healthonrent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;



public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(SplashScreen.this, PREF_USER_FIRST_TIME, "true"));

        Intent introIntent = new Intent(SplashScreen.this, MainActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime) {
            startActivity(introIntent);

        } else {
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    startActivity(new Intent(SplashScreen.this, Home.class));
                    finish();

                }
            }, SPLASH_TIME_OUT);
        }
    }





    }


