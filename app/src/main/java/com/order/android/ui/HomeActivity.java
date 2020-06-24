package com.order.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.order.android.R;
import com.order.android.base.BaseActivity;
import com.order.android.dialog.TipsDialog;
import com.order.android.utils.IntentUtils;
import com.order.android.utils.StatusBarUtils;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 返回按钮
     */
    private ImageView backIv;
    /**
     * 设置按钮
     */
    private ImageView settingIv;
    /**
     * 人脸扫描
     */
    private ImageView faceScanIv;
    /**
     * 二维码扫描
     */
    private ImageView qrcodeScanIv;
    private int count=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StatusBarUtils.setTransparent(this);
        initView();
    }

    private void initView(){
        backIv=(ImageView)findViewById(R.id.back_iv);
        settingIv=(ImageView)findViewById(R.id.setting_iv);
        faceScanIv=(ImageView)findViewById(R.id.face_scan_iv);
        qrcodeScanIv=(ImageView)findViewById(R.id.qrcode_scan_iv);
        backIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);
        faceScanIv.setOnClickListener(this);
        qrcodeScanIv.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        count=0;
    }

    @Override
    public void onClick(View v) {
        TipsDialog tipsDialog=null;
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.setting_iv:
                count++;
                if (count==5){
                    count=0;
                    IntentUtils.startIntent(this, SettingActivity.class);
                }else {
                    Toast.makeText(this, "再点击"+(5-count)+"可进入设置模式",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.face_scan_iv:
                tipsDialog=new TipsDialog(this);
                tipsDialog.setmUserAgreementOnClick(new TipsDialog.UserAgreementOnClick() {
                    @Override
                    public void userAgreementOnClick(boolean isAgreement) {
                        IntentUtils.startIntent(mContext,FaceScanActivity.class);
                    }
                });
                tipsDialog.show();
                break;
            case R.id.qrcode_scan_iv:
                tipsDialog=new TipsDialog(this);
                tipsDialog.setmUserAgreementOnClick(new TipsDialog.UserAgreementOnClick() {
                    @Override
                    public void userAgreementOnClick(boolean isAgreement) {

                    }
                });
                tipsDialog.show();
                break;
        }
    }
}
