package com.thoughtworks.learning.core;

import java.util.List;

/**
 * Created by yan on 16-3-14.
 */
public class Promotions {
    private int id;
    private String name;
    private List<AllItems> promotionsItems;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AllItems> getPromotionsItems() {
        return promotionsItems;
    }
}
