package com.order.android.task;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import com.mlink.launch.starter.task.ITask;
import com.mlink.launch.starter.task.Task;
import com.order.android.ui.SplashActivity;
import com.zqzn.android.facesearch.YskCallback;
import com.zqzn.android.facesearch.YskFaceEngine;

public class FaceTask extends Task implements YskCallback.LoadPerson, YskCallback.SDKInit {
    @Override
    public void run() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (mContext.checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
//                YskFaceEngine.instance().initSDK(mContext, "", this, this);
//            }
        }
    }

    @Override
    public void onChange(int i, int i1) {

    }

    @Override
    public void onUpgradePersonFaceFeature(int i, int i1) {

    }

    @Override
    public void initSuccess() {

    }

    @Override
    public void needBind(Throwable throwable) {

    }

    @Override
    public void initFailed(Throwable throwable) {

    }
}
