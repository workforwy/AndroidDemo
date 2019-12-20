package com.ceshi.ha.activity;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ceshi.ha.R;

/**
 * Created by Administrator on 2016/8/10.
 */
public class RefreshActivity extends Activity {

    public WebView mWebView;
    public SwipeRefreshLayout refresh;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_refresh);
        mWebView = findViewById(R.id.content_web_view);
        setSettings();
        mWebView.setWebViewClient(new ContentWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

        refresh = findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });
        mWebView.loadUrl("https://www.jianshu.com/p/8ef6340dc166");
    }

    private class ContentWebViewClient extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

    }

    private void setSettings() {
        WebSettings s = mWebView.getSettings();
        s.setBuiltInZoomControls(false);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);//支持js
        s.setDefaultTextEncodingName("utf-8");
        s.setGeolocationEnabled(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
    }
}