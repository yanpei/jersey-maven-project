package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.B_1_1;
import com.thoughtworks.learning.core.B_1_1_Repository;
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

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        b_1_1_repository = session.getMapper(B_1_1_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

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


//    @Test
//    public void should_get_item_b_a_by_id() {
//        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(1);
//        assertThat(b_1_1.getId(), is(1));
//        assertThat((String) b_1_1.getName(), is("P1"));
//    }


    @Test
    public void should_delete_Item(){

        b_1_1_repository.deleteItem(2);
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(2);
        assertNull(b_1_1);
    }

    @Test
    public void should_update_entity(){

        b_1_1_repository.updateEntity(2,"P4");
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(2);
        assertThat((Integer) b_1_1.getId(), is(2));
        assertThat((String) b_1_1.getName(), is("P4"));
    }


    @Test
    public void should_create_a_itemEntity(){

        b_1_1_repository.createEntity(4,"P4");
        B_1_1 b_1_1 = b_1_1_repository.getItem_ById(4);
        assertThat((Integer) b_1_1.getId(), is(4));
        assertThat((String) b_1_1.getName(), is("P4"));

    }




}
