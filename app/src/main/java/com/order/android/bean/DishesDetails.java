package com.order.android.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DishesDetails implements Serializable {

    private int serialNumber=0;

    private String dishes="";

    private String price="";

    private String amount="";

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public DishesDetails(int serialNumber, String dishes, String price, String amount) {
        this.serialNumber = serialNumber;
        this.dishes = dishes;
        this.price = price;
        this.amount = amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static List<DishesDetails>  getDishesDetailsList(){
        List<DishesDetails> list=new ArrayList<>();
        list.add(new DishesDetails(2, "豆腐", "1.0元","1" ));
        list.add(new DishesDetails(12, "红烧鱼", "11.0元","1" ));
        list.add(new DishesDetails(15, "东坡肉", "5.0元","1" ));
        list.add(new DishesDetails(2, "豆腐", "1.0元","1" ));
        return list;
    }
}
