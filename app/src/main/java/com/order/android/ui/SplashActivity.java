package com.order.android.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.order.android.MainActivity;
import com.order.android.R;
import com.order.android.base.BaseActivity;
import com.order.android.utils.IntentUtils;
import com.order.android.view.RxRoundProgressBar;
import com.order.android.view.SuperTextView;
import com.zqzn.android.facesearch.YskCallback;
import com.zqzn.android.facesearch.YskFaceEngine;

public class SplashActivity extends BaseActivity implements YskCallback.LoadPerson, YskCallback.SDKInit{
    private static final int REQUEST_PERMISSION_CODE = 1;
    private SuperTextView superTextView=null;
    private RxRoundProgressBar progressBar=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        superTextView=(SuperTextView)findViewById(R.id.super_textview);
        progressBar=(RxRoundProgressBar)findViewById(R.id.loading_progress_bar) ;
        superTextView.setDynamicText(R.string.app_splash_tv);
        superTextView.setOnDynamicListener(new SuperTextView.OnDynamicListener() {
            @Override
            public void onChange(int position) {

            }

            @Override
            public void onCompile() {
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_CODE);
            }
        });

        superTextView.setDynamicStyle(SuperTextView.DynamicStyle.CHANGE_COLOR);
        superTextView.setDuration(50);
        superTextView.setSelectedColorResource(R.color.colorAccent);
        superTextView.start();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "必需权限未授予：" + permissions[i], Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (mContext.checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                    YskFaceEngine.instance().initSDK(mContext, "", this, this);
                }
            }else {
                YskFaceEngine.instance().initSDK(mContext, "", this, this);
            }

        }


    }


    @Override
    public void onChange(int i, int i1) {
        if (progressBar!=null){
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(i);
            progressBar.setProgress(i1);
        }
    }

    @Override
    public void onUpgradePersonFaceFeature(int i, int i1) {

    }

    @Override
    public void initSuccess() {
        IntentUtils.startIntent(mContext, HomeActivity.class);
        finish();
    }

    @Override
    public void needBind(Throwable throwable) {
        throwable.printStackTrace();

    }

    @Override
    public void initFailed(Throwable throwable) {
        throwable.printStackTrace();
        IntentUtils.startIntent(mContext, HomeActivity.class);
        finish();
    }
}
