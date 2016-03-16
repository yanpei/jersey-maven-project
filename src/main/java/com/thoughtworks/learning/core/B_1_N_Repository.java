package com.thoughtworks.learning.core;

import java.util.List;

/**
 * Created by yan on 16-3-16.
 */
public interface B_1_N_Repository {
    List<B_1_N> findAll();

    B_1_N getItem_ById(int id);

//    //传对象
//    void  create(B_1_1 b);
//
//    //   void  createEntityAndRelationship(A_1_1 a);
//
//    void  update(B_1_N b);
//
//    void  deleteItem(B_1_N b);
}
