package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.A_1_N;
import com.thoughtworks.learning.core.A_1_N_Repository;
import com.thoughtworks.learning.core.B_1_N;
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
public class A_1_N_RepositoryTest {


    private SqlSessionFactory sqlSessionFactory;
    private A_1_N_Repository a_1_n_repository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        a_1_n_repository = session.getMapper(A_1_N_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }


    @Test
    public void should_select_Bs_FromB_1_N() {
        List<B_1_N> item1 = a_1_n_repository.select_Bs_FromB_1_N(1);
        assertThat(item1.size(), is(2));
        assertThat(item1.get(0).getName(),is("Tom"));
        assertThat(item1.get(1).getName(), is("Jerry"));
    }

    @Test
    public void should_find_all_items() {
        List<A_1_N> item1 = a_1_n_repository.findAll();
        assertThat(item1.size(), is(2));
        assertThat(item1.get(0).getName(),is("Class1"));
        assertThat(item1.get(1).getName(), is("Class2"));
    }

    @Test
    public void should_get_item_by_id() {
        A_1_N a_1_n = a_1_n_repository.getItem_ById(1);
        assertThat(a_1_n.getId(), is(1));
        assertThat((String) a_1_n.getName(), is("Class1"));
    }


    @Test
    public void should_create_a_itemEntity(){
        A_1_N a = new A_1_N(4,"Class4");
        a_1_n_repository.createEntity(a);
        A_1_N a_1_n = a_1_n_repository.getItem_ById(4);
        assertThat((Integer) a_1_n.getId(), is(4));
        assertThat((String) a_1_n.getName(), is("Class4"));
    }

    @Test
    public void should_update_entity(){
        A_1_N a = new A_1_N(2,"Class5");
        a_1_n_repository.updateEntity(a);
        A_1_N a_1_n = a_1_n_repository.getItem_ById(2);
        assertThat((Integer) a_1_n.getId(), is(2));
        assertThat((String) a_1_n.getName(), is("Class5"));
    }


    @Test
    public void should_delete_Item(){
        A_1_N a = new A_1_N(2,"Class2");
        a_1_n_repository.deleteItem(a);
        A_1_N a_1_n = a_1_n_repository.getItem_ById(2);
        assertNull(a_1_n);
    }

}
