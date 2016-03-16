package com.thoughtworks.learning.core;

/**
 * Created by yan on 16-3-12.
 */
public class B_1_1 {
    private int id;
    private String name;
    private A_1_1 a;

    public  B_1_1(){

    }

    public B_1_1(int id, String name){
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

    public A_1_1 getA() {
        return a;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setA(A_1_1 a) {
        this.a = a;
    }
}
