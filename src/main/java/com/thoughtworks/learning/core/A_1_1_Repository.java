package com.thoughtworks.learning.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-3-12.
 */
public interface A_1_1_Repository {

    B_1_1   select_B_FromB_1_1(int id);

    List<A_1_1> findAll();

    A_1_1 getItem_ById(int id);

//传对象
    void  create(A_1_1 a);

    void  createEntityAndRelationship(A_1_1 a);

    void  update(A_1_1 a);


    void  deleteItem(A_1_1 a);



 //传参数
 //   void  createRelationship_A_B(@Param("id") int id,@Param("b_id") int b_id);

//    void  updateRelationship(@Param("id") int id,@Param("b_id") int b_id);
//
//    void  updateEntity(@Param("id") int id,@Param("name") String name);

//    void  updateEntityAndRelationship(@Param("id") int id,@Param("name") String name,@Param("b_id") int b_id);

//    void  deleteRelationship(int id);



}
