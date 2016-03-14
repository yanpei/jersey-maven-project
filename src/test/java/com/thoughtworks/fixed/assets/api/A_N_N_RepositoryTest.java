package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.A_N_N;
import com.thoughtworks.learning.core.A_N_N_Repository;
import com.thoughtworks.learning.core.B_N_N;
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
public class A_N_N_RepositoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private A_N_N_Repository a_n_n_repository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        a_n_n_repository = session.getMapper(A_N_N_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }


    @Test
    public void should_find_all_items() {
        List<A_N_N> item1 = a_n_n_repository.findAll();
        assertThat(item1.size(), is(3));
        assertThat(item1.get(0).getName(),is("Tom"));
        assertThat(item1.get(1).getName(), is("Jerry"));
        assertThat(item1.get(2).getName(), is("Anna"));

    }


    @Test
    public void should_get_item_by_id() {
        A_N_N a_n_n = a_n_n_repository.getItem_ById(1);
        assertThat(a_n_n.getId(), is(1));
        assertThat((String) a_n_n.getName(), is("Tom"));
    }

    @Test
    public void should_select_Bs_FromB_N_N() {
        List<B_N_N> item1 = a_n_n_repository.select_Bs_FromB_N_N(1);
        assertThat(item1.size(), is(2));
        assertThat(item1.get(0).getName(),is("Math"));
        assertThat(item1.get(1).getName(), is("English"));
    }


    @Test
    public void should_create_a_itemEntity(){
        A_N_N a = new A_N_N(4,"Jack");
        a_n_n_repository.createEntity(a);
        A_N_N a_n_n = a_n_n_repository.getItem_ById(4);
        assertThat((Integer) a_n_n.getId(), is(4));
        assertThat((String) a_n_n.getName(), is("Jack"));

    }

//    @Test
//    public void should_create_a_relationship_A_B(){
//        A_N_N a = new A_N_N(2,"Jack");
//        B_N_N b = new B_N_N(4,"Java");
//        a_n_n_repository.createRelationship_A_B(a,b);
//        List<B_N_N> b_n_n = a_n_n_repository.select_Bs_FromB_N_N(2);
//        System.out.println(a.getId());
//        boolean isSuccess = false;
//        for(int i=0;i<b_n_n.size();i++){
//            if(b_n_n.get(i).getId()==4){
//                isSuccess = true;
//            }
//        }
//        assertThat(isSuccess, is(true));
//    }


    @Test
    public void should_update_entity(){
        A_N_N a = new A_N_N(2,"Jack");
        a_n_n_repository.updateEntity(a);
        A_N_N a_n_n = a_n_n_repository.getItem_ById(2);
        assertThat((Integer) a_n_n.getId(), is(2));
        assertThat((String) a_n_n.getName(), is("Jack"));
    }

//    @Test
//    public void should_update_relationship(){
//
//        a_n_n_repository.updateRelationship(1,2,3);
//        List<B_N_N> b_n_n = a_n_n_repository.select_Bs_FromB_N_N(1);
//        boolean isSuccess = false;
//        for(int i=0;i<b_n_n.size();i++){
//            if(b_n_n.get(i).getId()==3){
//                isSuccess = true;
//            }
//        }
//        assertThat(isSuccess, is(true));
//    }

//
//    @Test
//    public void should_delete_Relationship(){
//
//        a_n_n_repository.deleteRelationship(2,2);
//        List<B_N_N> b_n_n = a_n_n_repository.select_Bs_FromB_N_N(2);
//        boolean isSuccess = false;
//        for(int i=0;i<b_n_n.size();i++){
//            if(b_n_n.get(i).getId()==2){
//                isSuccess = true;
//            }
//        }
//        assertThat(isSuccess, is(false));
//    }

    @Test
    public void should_delete_Item(){
        A_N_N a = new A_N_N(2,"Jerry");
        a_n_n_repository.deleteItem(a);
        A_N_N a_n_n = a_n_n_repository.getItem_ById(2);
        assertNull(a_n_n);
    }





}
