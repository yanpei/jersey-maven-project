package com.thoughtworks.learning.core;

import java.util.List;

/**
 * Created by yan on 16-3-12.
 */
public class A_N_N {
    private int id;
    private String name;
    private List<B_N_N> bs;

    public  A_N_N(){

    }

    public  A_N_N(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<B_N_N> getBs() {
        return bs;
    }

    public void setName(String name) {
        this.name = name;
    }

}


