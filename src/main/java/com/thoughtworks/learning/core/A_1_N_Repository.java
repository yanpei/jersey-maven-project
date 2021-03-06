package com.thoughtworks.learning.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yan on 16-3-13.
 */
public interface A_1_N_Repository {

    List<A_1_N> findAll();

    A_1_N getItem_ById(int id);

    void  createEntity(A_1_N a);

    void  updateEntity(@Param("a_old") A_1_N a_old,@Param("a_new") A_1_N a_new);

    void  deleteItem(A_1_N a);



//传参数
//    void  createEntity(@Param("id") int id, @Param("name") String name);

//    void  updateEntity(@Param("id") int id,@Param("name") String name);
//
//    void  deleteItem(int id);


}
