package com.order.android.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.order.android.R;
import com.order.android.bean.DishesDetails;

public class DishesAdapter extends BaseQuickAdapter<DishesDetails, BaseViewHolder> {
    public DishesAdapter() {
        super(R.layout.item_dishes);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DishesDetails item) {

        helper.setText(R.id.item_serial_number_tv,item.getSerialNumber()+"");
        helper.setText(R.id.item_dishes_name_tv,item.getDishes());
        helper.setText(R.id.item_price_name_tv,item.getPrice());
        helper.setText(R.id.item_amount_name_tv, item.getAmount());
    }
}
