package com.hor.piyush.healthonrent;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(SplashScreen.this, PREF_USER_FIRST_TIME, "true"));

        Intent introIntent = new Intent(SplashScreen.this, MainActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime)
            startActivity(introIntent);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));

        startActivity(new Intent(this,Home.class));
        finish();



    }

}
