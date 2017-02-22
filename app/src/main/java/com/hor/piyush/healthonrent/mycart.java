package com.hor.piyush.healthonrent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class mycart extends AppCompatActivity {
    private WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mWebview = ((WebView)findViewById(R.id.mywebview));
        this.mWebview.getSettings().setJavaScriptEnabled(true);
        this.mWebview.setWebViewClient(new myWebClient());
        this.mWebview.loadUrl("http://www.healthonrent.in/cart");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mycart.this.startActivity(new Intent(mycart.this, Home.class));
                mycart.this.finish();
            }
        });
    }
    public class myWebClient
            extends WebViewClient
    {
        public myWebClient() {}

        public void onPageFinished(WebView paramWebView, String paramString)
        {
            super.onPageFinished(paramWebView, paramString);
            mycart.this.mWebview.loadUrl("javascript:(function() { document.getElementsByTagName('header')[0].style.display='none';document.getElementsByTagName('footer')[0].style.display='none'; })()");
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
        {
            super.onPageStarted(paramWebView, paramString, paramBitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
        {
            Toast.makeText(mycart.this, paramString, Toast.LENGTH_LONG).show();
            if (paramString.equals("http://healthonrent.in/product-categories/"))
            {
                mycart.this.startActivity(new Intent(mycart.this, Home.class));
                mycart.this.finish();
            }
            paramWebView.loadUrl(paramString);
            return true;
        }
    }
}
