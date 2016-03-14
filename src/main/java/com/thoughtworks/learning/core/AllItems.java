package com.thoughtworks.learning.core;

import java.util.List;

/**
 * Created by yan on 16-3-11.
 */
public class AllItems {
    private String barcoe;
    private String name;
    private  String unit;
    private  float price;
    private List<Promotions> promotions;


    public String getBarcoe() {
        return barcoe;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public float getPrice() {
        return price;
    }

    public List<Promotions> getPromotions() {
        return promotions;
    }

}
