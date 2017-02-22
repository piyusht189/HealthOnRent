package com.hor.piyush.healthonrent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;

public class ContactUs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView mWebview;
    ProgressBar pbar;
    String ur = "http://healthonrent.in/contact-us/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mWebview = ((WebView)findViewById(R.id.mywebview));
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new myWebClient());
        mWebview.loadUrl(this.ur);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public class myWebClient
            extends WebViewClient
    {
        public myWebClient() {}

        public void onPageFinished(WebView paramWebView, String paramString)
        {
            super.onPageFinished(paramWebView, paramString);
          //  injectCSS();
            mWebview.loadUrl("javascript:(function() { document.getElementsByTagName('header')[0].style.display='none';document.getElementsByTagName('footer')[0].style.display='none'; })()");

        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
            super.onPageStarted(paramWebView, paramString, paramBitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
        {

            if (paramString.equals("http://www.healthonrent.com/")) {
                Toast.makeText(ContactUs.this, "Query Submitted Succesfully",Toast.LENGTH_LONG).show();
            }
            paramWebView.loadUrl("http://healthonrent.in/contact-us/");
            return true;
        }
    }
    private void injectCSS() {
        try {
            InputStream inputStream = getAssets().open("style.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            mWebview.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('header').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild('body:{background-color:red;}}')" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    public void cart(View paramView)
    {

        startActivity(new Intent(this, mycart.class));
    }
    public void profile(View paramView)
    {
        startActivity(new Intent(this, MyAccount.class));

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.hospitalbeds) {
            startActivity(new Intent(this, HospitalBeds.class));
            finish();
        } else if (id == R.id.crutches) {
            startActivity(new Intent(this, Crutches.class));
            finish();
        } else if (id == R.id.walkers) {
            startActivity(new Intent(this, Walkers.class));
            finish();
        } else if (id == R.id.wheelchair) {
            startActivity(new Intent(this, WheelChair.class));
            finish();
        } else if (id == R.id.respiratoryrange) {
            startActivity(new Intent(this, RespiratoryRange.class));
            finish();
        } else if (id == R.id.ourtrust) {
            startActivity(new Intent(this, OurTrust.class));
            finish();
        } else{
            startActivity(new Intent(this, ContactUs.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
