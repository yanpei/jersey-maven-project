package com.thoughtworks.learning.core;

import java.util.List;

/**
 * Created by yan on 16-3-12.
 */
public class A_1_N {
    private int id;
    private String name;
    private List<B_1_N> b_s;

    public A_1_N(){

    }

    public A_1_N(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<B_1_N> getB_1_ns() {
        return b_s;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setB_s(List<B_1_N> b_s) {
        this.b_s = b_s;
    }

}
