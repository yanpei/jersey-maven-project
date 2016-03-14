package com.thoughtworks.learning.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yan on 16-3-13.
 */
public interface B_1_1_Repository {
    List<B_1_1> findAll();

    B_1_1 getItem_ById(int id);

    void  createEntity(@Param("id") int id, @Param("name") String name);

    void  updateEntity(@Param("id") int id,@Param("name") String name);

    void  deleteItem(int id);


//    void  createEntity(@Param("id") int id, @Param("name") String name);
//
//    void  updateEntity(@Param("id") int id,@Param("name") String name);
//
//    void  deleteItem(int id);



   }
