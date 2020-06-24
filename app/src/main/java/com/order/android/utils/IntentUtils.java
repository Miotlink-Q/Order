package com.order.android.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtils {

    public static void startIntent(Context mContext,Class c){
        Intent intent=new Intent(mContext,c);
        mContext.startActivity(intent);
    }

}
