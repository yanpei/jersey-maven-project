package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.A_1_N;
import com.thoughtworks.learning.core.B_1_N;
import com.thoughtworks.learning.core.B_1_N_Repository;
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
import static org.junit.Assert.assertThat;

/**
 * Created by yan on 16-3-16.
 */
public class B_1_N_RepostitoryTest {
    private SqlSessionFactory sqlSessionFactory;
    private B_1_N_Repository b_1_n_repository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        b_1_n_repository = session.getMapper(B_1_N_Repository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }

    @Test
    public void should_select_As_FromA_1_N() {
        B_1_N b = b_1_n_repository.getItem_ById(1);
        A_1_N item1 = b.getA();
        assertThat(item1.getId(),is(1));
        assertThat(item1.getName(),is("Class1"));
    }


    @Test
    public void should_find_all_items() {
        List<B_1_N> item1 = b_1_n_repository.findAll();
        assertThat(item1.size(), is(4));
        assertThat(item1.get(0).getId(),is(1));
        assertThat(item1.get(1).getId(), is(2));
        assertThat(item1.get(2).getId(), is(3));

    }

    @Test
    public void should_get_item_by_id() {
        B_1_N b = b_1_n_repository.getItem_ById(1);
        assertThat(b.getId(), is(1));
        assertThat((String) b.getName(), is("Tom"));
//        assertThat(b.getA().getId(), is(1));
    }
}
