package com.order.android.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.order.android.R;
import com.order.android.base.BaseActivity;
import com.order.android.utils.Const;
import com.order.android.utils.StatusBarUtils;
import com.tencent.mmkv.MMKV;

public class SettingActivity extends BaseActivity {

    /**
     * 人脸服务器输入框
     */
    private EditText faceAddressEt;
    /**
     * 业务服务器输入框
     */
    private EditText webAddressEt;
    /**
     * 确认按钮
     */
    private Button saveAddressBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarUtils.setColor(this,mContext.getResources().getColor(R.color.color_5F696F));
        initView();
    }

    private void initView(){
        faceAddressEt=(EditText)findViewById(R.id.face_address_edit);
        webAddressEt=(EditText)findViewById(R.id.web_address_edit);
        faceAddressEt.setText(MMKV.defaultMMKV().getString(Const.FACE_SERVICE,"192.168.1.1"));
        webAddressEt.setText(MMKV.defaultMMKV().getString(Const.WEB_SERVICE,"192.168.1.1"));
        saveAddressBtn=(Button)findViewById(R.id.setting_save_btn);
        saveAddressBtn.setOnClickListener(v->{
            String faceAddress=faceAddressEt.getText().toString();
            String webAddress=webAddressEt.getText().toString();
            if (TextUtils.isEmpty(faceAddress)){
                Toast.makeText(mContext, "请输入人脸服务器地址", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(webAddress)){
                Toast.makeText(mContext, "请输入业务服务器地址", Toast.LENGTH_SHORT).show();
                return;
            }
            MMKV.defaultMMKV().putString(Const.FACE_SERVICE,faceAddress);
            MMKV.defaultMMKV().putString(Const.WEB_SERVICE,webAddress);
            Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();

        });
    }
}
