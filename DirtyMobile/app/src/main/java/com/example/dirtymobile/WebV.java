package com.example.dirtymobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;


public class WebV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_v);
        WebView webview = new WebView(this);
        setContentView(webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.getSettings().setAppCacheEnabled(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url.equals("jobject")) {
            FileUtil fileUtil = new FileUtil();
            webview.addJavascriptInterface(fileUtil, "fileUtil");
            setContentView(webview);
            webview.loadUrl("http://evilsite.local/object.html");
        } else {
            webview.loadUrl(url);
        }
   }

}

class FileUtil {
    @JavascriptInterface
    public String returnString() {
        String flag = AES.decrypt("pvdO628ClJ/N2cse43O11w==", "hQBSxjgLD8nf_TsB");
        return flag;
    }
}

