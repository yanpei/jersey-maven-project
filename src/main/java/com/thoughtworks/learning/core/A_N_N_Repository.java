package com.thoughtworks.learning.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yan on 16-3-13.
 */
public interface A_N_N_Repository {

    List<B_N_N>   select_Bs_FromB_N_N(int id);

    List<A_N_N> findAll();

    A_N_N getItem_ById(int id);

//传对象
    void  createEntity(A_N_N a);

    void  createRelationship_A_B(@Param("a") A_N_N a,@Param("b") B_N_N b);
//
//    void  createEntityAndRelationship(@Param("id") int id,@Param("name") String name,@Param("b_id") int b_id);

    void  updateEntity(A_N_N a);

    void  updateRelationship(@Param("a") A_N_N a,@Param("b") B_N_N b);

     void  deleteRelationship(@Param("a") A_N_N a,@Param("b") B_N_N b);

    void  deleteItem(A_N_N a);



//传参数
//    void  createRelationship_A_B(@Param("a_id") int a_id, @Param("b_id") int b_id);
//
////    void  createEntityAndRelationship(@Param("id") int id,@Param("name") String name,@Param("b_id") int b_id);
//
//    void  createEntity(@Param("id") int id, @Param("name") String name);
//
//    void  updateRelationship(@Param("a_id") int id,@Param("b_id_old") int b_id_old,@Param("b_id_new") int b_id_new);
//
//    void  updateEntity(@Param("id") int id,@Param("name") String name);
//
//    void  deleteRelationship(@Param("a_id")int a_id,@Param("b_id")int b_id);
//
//    void  deleteItem(int id);




}
