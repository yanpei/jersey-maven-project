package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by yan on 16-3-12.
 */
public class A_1_1_RepostitoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private A_1_1_Repository a_1_1_repository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        a_1_1_repository = session.getMapper(A_1_1_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }

    @Test
    public void should_select_Bs_FromB_1_N() {
        B_1_1 item1 = a_1_1_repository.select_B_FromB_1_1(1);
        assertThat(item1.getId(),is(1));
        assertThat(item1.getName(),is("P1"));
    }


    @Test
    public void should_find_all_items() {
        List<A_1_1> item1 = a_1_1_repository.findAll();
        assertThat(item1.size(), is(3));
        assertThat(item1.get(0).getId(),is(1));
        assertThat(item1.get(1).getId(), is(2));
        assertThat(item1.get(2).getId(), is(3));

    }

    @Test
    public void should_get_item_by_id() {
        A_1_1 a_1_1 = a_1_1_repository.getItem_ById(1);
        assertThat(a_1_1.getId(), is(1));
        assertThat((String) a_1_1.getName(), is("Tom"));
      //  a_1_1.setB(a_1_1_repository.select_B_FromB_1_1(1));
        assertThat(a_1_1.getB().getId(), is(1));
    }

    @Test
    public void should_create_a_itemEntity(){

        A_1_1 a = new A_1_1(4,"Jack");

        a_1_1_repository.create(a);
        A_1_1 a_1_1 = a_1_1_repository.getItem_ById(4);
        assertThat((Integer) a_1_1.getId(), is(4));
        assertThat((String) a_1_1.getName(), is("Jack"));

    }


    @Test
    public void should_create_a_itemEntity_andd_realtionship(){
        B_1_1 b = new B_1_1(3,"P3");
        A_1_1 a = new A_1_1(4,"Jack",b);
        a_1_1_repository.create(a);
        A_1_1 a1= a_1_1_repository.getItem_ById(4);
        assertThat((Integer) a1.getId(), is(4));
        assertThat((String) a1.getName(), is("Jack"));
        assertThat((Integer) a1.getB().getId(), is(3));
    }

    @Test
    public void should_update_entity(){
        A_1_1 a = new A_1_1(2,"Jack");

        a_1_1_repository.update(a);
        A_1_1 a1 = a_1_1_repository.getItem_ById(2);
        assertThat((Integer) a1.getId(), is(2));
        assertThat((String) a1.getName(), is("Jack"));
    }

    @Test
    public void should_create_a_relationship_A_B(){

        B_1_1 b = new B_1_1(3,"P3");

        A_1_1 a = new A_1_1(3,"Anna",b);

        a_1_1_repository.update(a);
        A_1_1 a1 = a_1_1_repository.getItem_ById(3);

        assertThat(a1.getB().getId(),is(3));
    }

    @Test
    public void should_update_relationship(){
        B_1_1 b = new B_1_1(3,"P3");
        A_1_1 a = new A_1_1(2,"Jerry",b);
        System.out.print(a.getB().getId());
        a_1_1_repository.update(a);
        A_1_1 a_1_1 = a_1_1_repository.getItem_ById(2);
        assertThat((Integer) a_1_1.getId(), is(2));
        assertThat((String) a_1_1.getName(), is("Jerry"));
       System.out.print(a_1_1.getB().getId());
        assertThat((Integer) a_1_1.getB().getId(), is(3));
    }


    @Test
    public void should_update_entity_and_Relationship(){
        B_1_1 b = new B_1_1(3,"P3");
        A_1_1 a = new A_1_1(2,"Jack",b);
        a_1_1_repository.update(a);
        A_1_1 a_1_1 = a_1_1_repository.getItem_ById(2);
        assertThat((Integer) a_1_1.getId(), is(2));
        assertThat((String) a_1_1.getName(), is("Jack"));
        assertThat((Integer) a_1_1.getB().getId(), is(3));
    }

    @Test
    public void should_delete_Relationship(){
        A_1_1 a = new A_1_1(2,"Jerry");
        a_1_1_repository.update(a);
        A_1_1 a_1_1 = a_1_1_repository.getItem_ById(2);
        assertThat((Integer) a_1_1.getId(), is(2));
        assertNull(a_1_1.getB());
    }

    @Test
    public void should_delete_Item(){
        B_1_1 b = new B_1_1(3,"P3");

        A_1_1 a = new A_1_1(3,"Anna",b);

        a_1_1_repository.deleteItem(a);
        A_1_1 a1 = a_1_1_repository.getItem_ById(3);
        assertNull(a1);
    }

}
