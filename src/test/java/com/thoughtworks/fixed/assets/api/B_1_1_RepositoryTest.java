package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by yan on 16-3-13.
 */
public class B_1_1_RepositoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private B_1_1_Repository b_1_1_repository;
    private SqlSession session;
    private A_1_1_Repository a_1_1_repository;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        b_1_1_repository = session.getMapper(B_1_1_Repository.class);
        a_1_1_repository = session.getMapper(A_1_1_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }

    @Test
    public void should_select_Bs_FromB_1_N() {
        B_1_1 b = b_1_1_repository.getItem_ById(1);
        A_1_1 item1 = b.getA();
        assertThat(item1.getName(),is("Tom"));
    }

    @Test
    public void should_find_all_items() {
        List<B_1_1> item1 = b_1_1_repository.findAll();
        assertThat(item1.size(), is(3));
        assertThat(item1.get(0).getName(),is("P1"));
        assertThat(item1.get(1).getName(), is("P2"));
        assertThat(item1.get(2).getName(), is("P3"));

    }

    @Test
    public void should_get_item_by_id() {
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(1);
        assertThat(b_1_1.getId(), is(1));
        assertThat((String) b_1_1.getName(), is("P1"));
    }

    @Test
    public void given_new_B_when_create_a_itemEntity_then_insert_B(){
        B_1_1 b = new B_1_1(4,"P4");
        b_1_1_repository.createEntity(b);
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(4);
        assertThat((Integer) b_1_1.getId(), is(4));
        assertThat((String) b_1_1.getName(), is("P4"));

    }

    @Test
    public void given_newName_when_update_entity_then_updateName(){
        B_1_1 b_old = new B_1_1(3,"P3");
        B_1_1 b_new = new B_1_1(3,"P4");
        b_1_1_repository.updateEntity(b_old,b_new);
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(3);
        assertThat((Integer) b_1_1.getId(), is(3));
        assertThat((String) b_1_1.getName(), is("P4"));
    }

//级联更新ID
    @Test
    public void given_newID_when_update_entity_then_B_update_ID_A_update_B_ID(){
        B_1_1 b_old = new B_1_1(2,"P2");
        B_1_1 b_new = new B_1_1(4,"P4");
        b_1_1_repository.updateEntity(b_old,b_new);
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(4);
        assertThat((Integer) b_1_1.getId(), is(4));
        assertThat((String) b_1_1.getName(), is("P4"));

        A_1_1 a = a_1_1_repository.getItem_ById(2);
        assertThat((Integer)a.getB().getId(),is(4));
    }

    public void given_B_A_NotExist_when_delete_B_then_delete_B(){
        B_1_1 b = new B_1_1(2,"P2");
        b_1_1_repository.deleteItem(b);
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(2);
        assertNull(b_1_1);
    }

//级联删除
    @Test
    public void given_B_when_delete_B_then_delete_B_delete_A(){

        B_1_1 b_del = new B_1_1(2,"P2");
        b_1_1_repository.deleteItem(b_del);
        B_1_1 b = b_1_1_repository.getItem_ById(2);
        assertNull(b);
        A_1_1 a = a_1_1_repository.getItem_ById(2);
        assertNull(a);
      //  System.out.print(a.getId());
     //   System.out.print(a.getB());

    }


}
