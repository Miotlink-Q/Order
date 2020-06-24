package com.order.android.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.order.android.R;

public class TipsDialog extends RxDialog{

    private TextView mTvContent;
    private TextView mTvSure;

    private UserAgreementOnClick mUserAgreementOnClick=null;

    public void setmUserAgreementOnClick(UserAgreementOnClick mUserAgreementOnClick) {
        this.mUserAgreementOnClick = mUserAgreementOnClick;
    }

    public TipsDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public TipsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public TipsDialog(Context context) {
        super(context);
        initView();
    }

    public TipsDialog(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
    }



    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sure, null);
        mTvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        mTvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        setContentView(dialogView);
        setCanceledOnTouchOutside(false);
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (mUserAgreementOnClick!=null){
                    mUserAgreementOnClick.userAgreementOnClick(true);
                }
            }
        });
    }



    public interface UserAgreementOnClick{
        public void userAgreementOnClick(boolean isAgreement);
    }

}
