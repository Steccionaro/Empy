package com.example.empy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView web;

    @SuppressLint({"WebViewApiAvailability", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            web.getWebViewClient();
        }
        web.loadUrl("https://www.amazon.it/");

        web.setWebViewClient(new WebViewClient() { //stay on the app when tapping on a link
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //function tu go back with system navigation
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (web.canGoBack()) {
                    web.goBack();
                } else {
                    finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}