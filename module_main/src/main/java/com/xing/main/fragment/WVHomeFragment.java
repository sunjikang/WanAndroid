package com.xing.main.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.xing.commonbase.base.BaseFragment;
import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.util.SharedPreferenceUtil;
import com.xing.commonbase.widget.ProgressWebView;
import com.xing.main.R;

import java.util.HashMap;
import java.util.Map;

public class WVHomeFragment extends BaseFragment {

    private ProgressWebView webView;
    private String url;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_web_home;
    }

    @Override
    protected void initView(View rootView) {
        final String token = SharedPreferenceUtil.read(Constants.FILE_TOKEN, Constants.ACCESS_TOKEN, "");
        webView = rootView.findViewById(R.id.wv_web);
        webView.setWebViewCallback(new ProgressWebView.WebViewCallback() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:setToken('" + token + "')");
                    }
                });
            }

            @Override
            public void onLoadResource(WebView view, String url) {

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }

            @Override
            public void onPageLoadComplete() {

            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                return false;
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {

            }
        });
    }

    @Override
    protected void initData() {
        url = "http://10.2.8.154:9000/app_home";
        webView.loadUrl(url);
    }
}
