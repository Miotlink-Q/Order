package com.order.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.order.android.MainActivity;
import com.order.android.R;
import com.order.android.base.BaseActivity;
import com.order.android.utils.IntentUtils;
import com.zqzn.android.facesearch.YskCallback;
import com.zqzn.android.facesearch.YskFaceEngine;
import com.zqzn.android.facesearch.YskPerson;
import com.zqzn.android.facesearch.bean.FaceTrackInfo;
import com.zqzn.android.facesearch.camera.CameraTextureView;
import com.zqzn.android.facesearch.exception.ParameterException;

import java.util.List;

public class FaceScanActivity extends BaseActivity implements YskCallback.FaceDetect {

    private static String TAG=FaceScanActivity.class.getName();
    private FrameLayout mFrCamera;
    private ImageView imageView=null;
    private int mCameraWidth;
    private int mCameraHeight;
    /**
     * 人脸框缩放因子:实际View宽度(高度)和摄像头预览宽度(高度)的比例系数，以便在预览View上正确绘制人脸框区域
     */
    private float mCoordinateScaleFactor = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        mFrCamera = findViewById(R.id.fr_camera);
//        try {
//            YskFaceEngine.instance().syncPersonData(this, cloudBind);
//        } catch (ParameterException e) {
//            Log.e(TAG, "sync===" + e.getMessage());
//        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        startRecognize();
        setCameraPreviewWindow();
    }

    private void startRecognize(){
        try {
            YskFaceEngine.instance().startRecognize(mContext, 1,this);
        } catch (ParameterException e) {
            Log.e(TAG, "recognize_start===" + e.getMessage());
        }
    }

    private void setCameraPreviewWindow() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mFrCamera.getLayoutParams();
        //预览区域的实际宽高,(根据项目实际UI设计设置：指定宽或高中的一个，另一个根据示例代码计算)
        int viewWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        //这里指定了宽的长度，高的长度根据代码计算
        int viewHeight = 0;
        //获取摄像头可视化对象
        CameraTextureView cameraTextureView= YskFaceEngine.instance().getCameraTextureView();
        //获取摄像头的宽高互换结果，true为没有互换，false为已互换。互换后，getWidth获取的就是原摄像头的高度信息，getHeight获取的是原摄像头的宽度信息
        if (cameraTextureView.getFaceCamera().getCameraParams().isPreviewWHChange()) {
            mCameraHeight = cameraTextureView.getFaceCamera().getCameraParams().getPreviewSize().getWidth();
            mCameraWidth = cameraTextureView.getFaceCamera().getCameraParams().getPreviewSize().getHeight();
        } else {
            mCameraHeight = cameraTextureView.getFaceCamera().getCameraParams().getPreviewSize().getHeight();
            mCameraWidth = cameraTextureView.getFaceCamera().getCameraParams().getPreviewSize().getWidth();
        }
        //计算实际View的高度
        viewHeight = viewWidth * mCameraHeight / mCameraWidth;
        //缩放因子：实际View宽度(高度)和摄像头预览宽度(高度)的比例系数，以便在预览View上正确绘制人脸框区域
        mCoordinateScaleFactor = 1.0f * viewWidth / mCameraWidth;
        //动态设置预览View的宽高
        layoutParams.width = viewWidth;
        layoutParams.height = viewHeight;
        mFrCamera.setLayoutParams(layoutParams);
        mFrCamera.addView(cameraTextureView);
        Log.i("FaceScanActivity","摄像头==="+mCameraWidth + "===" + mCameraHeight + "===" + viewWidth + "===" + viewHeight);
    }

    @Override
    public void onFaceDetected(List<FaceTrackInfo> list) {

    }

    @Override
    public void onNoFaceDetected() {

    }

    @Override
    public void onFaceFind(String s, YskPerson yskPerson, byte[] bytes, String s1, List<YskPerson> list) {

    }

    @Override
    public void onFaceNotFind(String s) {

    }

    @Override
    public void onAttack(String s, YskPerson yskPerson, byte[] bytes, String s1) {

    }

    @Override
    public void faceToSmall(String s) {

    }

    @Override
    public void onLowQuality(int i) {

    }
    YskCallback.CloudBind cloudBind= new YskCallback.CloudBind() {
        @Override
        public void onUnbind() {

        }

        @Override
        public void onSnDisabled() {

        }
    };


}
