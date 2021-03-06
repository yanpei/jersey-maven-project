package com.thoughtworks.learning.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yan on 16-3-13.
 */
public interface B_1_1_Repository {
    List<B_1_1> findAll();

    B_1_1 getItem_ById(int id);

    void  createEntity(B_1_1 b);

    void  updateEntity(@Param("b_old") B_1_1 b_old,@Param("b_new") B_1_1 b_new);

    void  deleteItem(B_1_1 b);

//传参数
//    void  createEntity(@Param("id") int id, @Param("name") String name);
//
//    void  updateEntity(@Param("id") int id,@Param("name") String name);
//
//    void  deleteItem(int id);



   }
