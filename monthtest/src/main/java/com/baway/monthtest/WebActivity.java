package com.baway.monthtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class WebActivity extends AppCompatActivity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //找控件
        web = findViewById(R.id.web);
        //获取传过来的值
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        //WebView展示
        web.loadUrl(key);
        web.setWebViewClient(new WebViewClient());
    }
}
