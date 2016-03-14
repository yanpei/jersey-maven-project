package com.thoughtworks.learning.core;

/**
 * Created by yan on 16-3-12.
 */
public class B_1_1 {
    private int id;
    private String name;

    public  B_1_1(){

    }

    public B_1_1(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
