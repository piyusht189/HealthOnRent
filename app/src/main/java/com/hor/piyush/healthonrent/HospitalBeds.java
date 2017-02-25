package com.hor.piyush.healthonrent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

public class HospitalBeds extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView mWebview;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_beds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mWebview = ((WebView)findViewById(R.id.mywebview));
        this.mWebview.getSettings().setJavaScriptEnabled(true);
        this.mWebview.setWebViewClient(new myWebClient());
        this.mWebview.loadUrl("http://healthonrent.in/product-category/hospital-beds/");
        mWebview.setInitialScale(200);
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

        public void onLoadResource(WebView paramWebView, String paramString)
        {
            if (paramString.equals("http://healthonrent.in/product/fowler-bed/")) {
                Toast.makeText(HospitalBeds.this, "Yea", Toast.LENGTH_LONG).show();
            }
        }

        public void onPageFinished(WebView paramWebView, String paramString)
        {
            super.onPageFinished(paramWebView, paramString);
            HospitalBeds.this.mWebview.loadUrl("javascript:(function() { document.getElementsByTagName('header')[0].style.display='none';document.getElementsByTagName('footer')[0].style.display='none'; })()");
            mWebview.setVisibility(View.VISIBLE);
            progress.dismiss();
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
            progress=new ProgressDialog(HospitalBeds.this);
            progress.setTitle("HealthOnRent");
            progress.setIcon(R.drawable.loader);
            progress.setMessage("Loading");
            progress.show();
            mWebview.setVisibility(View.INVISIBLE);
            super.onPageStarted(paramWebView, paramString, paramBitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
        {
            if(paramString.equals("http://healthonrent.in/cart/")){
                startActivity(new Intent(getApplicationContext(),mycart.class));
                finish();
            }
            if(paramString.substring(0,34).equals("http://healthonrent.in/wp-content/upload")){
                Toast.makeText(HospitalBeds.this, "hello", Toast.LENGTH_SHORT).show();
            }
            paramWebView.loadUrl(paramString);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mWebview.canGoBack()) {
            mWebview.goBack();
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
