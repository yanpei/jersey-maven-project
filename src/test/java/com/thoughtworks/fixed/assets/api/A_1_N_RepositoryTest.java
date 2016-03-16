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
public class A_1_N_RepositoryTest {


    private SqlSessionFactory sqlSessionFactory;
    private A_1_N_Repository a_1_n_repository;
    private SqlSession session;
    private B_1_N_Repository b_1_n_repository;


    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        a_1_n_repository = session.getMapper(A_1_N_Repository.class);
        b_1_n_repository = session.getMapper(B_1_N_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }


    @Test
    public void should_select_Bs_FromB_1_N() {
        A_1_N a = a_1_n_repository.getItem_ById(1);
        List<B_1_N> item1 = a.getB_s();
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
    public void given_newName_when_update_entity_then_updateName(){
        A_1_N a_old = new A_1_N(3,"Class3");
        A_1_N a_new = new A_1_N(3,"Class5");
        a_1_n_repository.updateEntity(a_old,a_new);
        A_1_N a_1_n = a_1_n_repository.getItem_ById(3);
        assertThat((Integer) a_1_n.getId(), is(3));
        assertThat((String) a_1_n.getName(), is("Class5"));
    }

    //级联更新
    @Test
    public void given_newID_when_update_entity_then_B_update_ID_A_update_B_ID(){
        A_1_N a_old = new A_1_N(1,"Class1");
        A_1_N a_new = new A_1_N(4,"Class5");
        a_1_n_repository.updateEntity(a_old,a_new);
        A_1_N a_1_n = a_1_n_repository.getItem_ById(4);
        assertThat((Integer) a_1_n.getId(), is(4));
        assertThat((String) a_1_n.getName(), is("Class5"));

        B_1_N b1 = b_1_n_repository.getItem_ById(1);
        assertThat((Integer)b1.getA().getId(),is(4));

        B_1_N b2 = b_1_n_repository.getItem_ById(2);
        assertThat((Integer)b2.getA().getId(),is(4));
    }


    @Test
    public void given_A_B_NotExist_when_delete_A_then_delete_A(){
        A_1_N a = new A_1_N(2,"Class2");
        a_1_n_repository.deleteItem(a);
        A_1_N a_1_n = a_1_n_repository.getItem_ById(2);
        assertNull(a_1_n);
    }


//    级联删除
    @Test
    public void given_A_when_delete_A_then_delete_A_delete_B(){
        A_1_N a_del = new A_1_N(1,"Class2");
        a_1_n_repository.deleteItem(a_del);
        A_1_N a = a_1_n_repository.getItem_ById(1);
        assertNull(a);

        B_1_N b1 = b_1_n_repository.getItem_ById(1);
        assertNull(b1);

        B_1_N b2 = b_1_n_repository.getItem_ById(2);
        assertNull(b2);
    }

}
