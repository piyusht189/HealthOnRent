package com.hor.piyush.healthonrent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MyAccount extends AppCompatActivity {
    private WebView mWebview;
    private ProgressDialog progress;
    FrameLayout mContainer;
    private WebView mWebviewPop;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        mContainer = (FrameLayout) findViewById(R.id.content_my_account);
        this.mWebview = ((WebView)findViewById(R.id.mywebview));
        this.mWebview.getSettings().setJavaScriptEnabled(true);
        this.mWebview.setWebViewClient(new myWebClient());


        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
       // mWebview.setWebChromeClient(new UriChromeClient());
        this.mWebview.loadUrl("http://healthonrent.in/my-account/");

        mContext=this.getApplicationContext();



    }
    public class myWebClient
            extends WebViewClient
    {
        public myWebClient() {}

        public void onPageFinished(WebView paramWebView, String paramString)
        {
            super.onPageFinished(paramWebView, paramString);
            MyAccount.this.mWebview.loadUrl("javascript:(function() { document.getElementsByTagName('header')[0].style.display='none';document.getElementsByTagName('footer')[0].style.display='none'; })()");
            mWebview.setVisibility(View.VISIBLE);
            progress.dismiss();
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
            progress=new ProgressDialog(MyAccount.this);
            progress.setTitle("HealthOnRent");
            progress.setIcon(R.drawable.loader);
            progress.setMessage("Loading");
            progress.show();
            mWebview.setVisibility(View.INVISIBLE);
            super.onPageStarted(paramWebView, paramString, paramBitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
        {

          if(paramString.equals("http://healthonrent.in/wp-login.php?ywsl_social=facebook&redirect=http%3A%2F%2Fhealthonrent.in%2Fmy-account%2F")){
              Toast.makeText(mContext, "yo", Toast.LENGTH_SHORT).show();
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

}
