package com.example.autobrowsersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    // The target url to surf using web view
    private static final String TAG = "MainActivity";
    private static String SEARCH_URL = "https://accounts.google.com/signin";
    private WebView mWebView;
    private SwipeRefreshLayout refreshLayout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
//        mContext = getApplicationContext();
//        mActivity = MainActivity.this;

        // findViews
        findViews();
        setupWebView();
        setupClickListeners();
    }

    private void setupClickListeners() {
        refreshLayout.setOnRefreshListener(this::refreshWebView);
    }

    private void refreshWebView() {
        mWebView.reload();
        refreshLayout.setRefreshing(false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        /*
        WebView:
        A View that displays web pages. This class is the basis upon which you can roll
        your own web browser or simply display some online content within your Activity.
        It uses the WebKit rendering engine to display web pages and includes methods
        to navigate forward and backward through a history, zoom in and out,
        perform text searches and more.
        */

        // Load the url in web view
        mWebView.loadUrl(SEARCH_URL);

        // Enable java script on web view
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Set a web view client for web view
        mWebView.setWebViewClient(new WebViewClient() {
            /*
                void onPageFinished (WebView view, String url)
                    Notify the host application that a page has finished loading. This method is
                    called only for main frame. When onPageFinished() is called, the rendering
                    picture may not be updated yet. To get the notification for the new Picture,
                    use onNewPicture(WebView, Picture).

                Parameters
                    view WebView : The WebView that is initiating the callback.
                    url String : The url of the page.
            */

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "onPageFinished: url loaded - " + url);

                if (url.equalsIgnoreCase(SEARCH_URL)) {
                    // The java script string to execute in web view after page loaded
                    // First line put a value in input box
                    // Second line submit the form
                    final String js = "javascript:document.getElementsByClassName" +
                            "('whsOnd zHQkBf')[0].value='nikhiljain5195@gmail.com'; " +
                            "document.getElementById('identifierNext').click()";
                    view.loadUrl(js);
//                    view.evaluateJavascript(js, new ValueCallback<String>() {
//                        @Override
//                        public void onReceiveValue(String str) {
//                            Log.e(TAG, "onReceiveValue: " + str);
//                        }
//                    });
                }
                if (url.equalsIgnoreCase("https://accounts.google.com/signin")) {
                    // The java script string to execute in web view after page loaded
                    // First line put a value in input box
                    // Second line submit the form
                    final String js1 = "javascript:document.getElementsByClassName" +
                            "('whsOnd zHQkBf')[0].value='8871152221'; " +
                            "document.getElementById('passwordNext').click()";
                    view.loadUrl(js1);
//                    view.evaluateJavascript(js, new ValueCallback<String>() {
//                        @Override
//                        public void onReceiveValue(String str) {
//                            Log.e(TAG, "onReceiveValue: " + str);
//                        }
//                    });
                }

            }
        });
    }

    private void findViews() {
        mWebView = findViewById(R.id.web_view);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
