package com.thoughtworks.learning.core;

/**
 * Created by yan on 16-3-12.
 */
public class A_1_1 {
    private int id;
    private String name;
    private B_1_1 b;

    public A_1_1(){

    }

    public A_1_1(int id,String name){
        this.id=id;
        this.name = name;
    }

    public A_1_1(int id,String name,B_1_1 b){
        this.id=id;
        this.name = name;
        this.b = b;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public B_1_1 getB() {
        return b;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setB(B_1_1 b) {
        this.b = b;
    }
}
