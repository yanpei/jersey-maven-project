package com.thoughtworks.learning.core;

import java.util.List;

/**
 * Created by yan on 16-3-12.
 */
public class B_N_N {
    private int id;
    private String name;
    private List<A_N_N> a;

    public B_N_N(){

    }

    public B_N_N(int id,String name){
        this.id =id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<A_N_N> getA() {
        return a;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setA(List<A_N_N> a) {
        this.a = a;
    }
}
