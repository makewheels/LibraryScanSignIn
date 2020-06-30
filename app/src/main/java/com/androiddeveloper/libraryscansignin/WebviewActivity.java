package com.androiddeveloper.libraryscansignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webview = findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);
    }

}