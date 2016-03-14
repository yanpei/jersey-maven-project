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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by yan on 16-3-11.
 */
public class InputItemsRepositoryTest {

    private SqlSessionFactory sqlSessionFactory;
    private InputItemsRepository inputItemsRepository;
    private SqlSession session;

    @Before
    public void setUp() throws IOException, SQLException {
        String resource = "mybatis-config.xml";

        Reader reader  = Resources.getResourceAsReader(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "test");

        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);   //测试不会向数据库提交数据
        inputItemsRepository = session.getMapper(InputItemsRepository.class);

    }

    @After
    public void tearDown(){
        session.rollback();
        session.close();

    }


    @Test
    public void should_find_all_inputItems() {
        List<InputItems> item1 = inputItemsRepository.findInputItems();
        assertThat(item1.size(), is(2));
        assertThat(item1.get(0).getBarcode(), is("ITEM000001"));
        assertThat(item1.get(1).getBarcode(), is("ITEM000002"));

    }

    @Test
    public void should_create_a_inputItem(){
        Map newInstance = new HashMap();
        newInstance.put("barcode", "ITEM000003");
        newInstance.put("barcode", "ITEM000003");
        inputItemsRepository.createInputItems(newInstance);

        assertThat((String) newInstance.get("barcode"), is("ITEM000003"));
        assertThat((Integer) newInstance.get("id"), is(not(0)));
    }

    @Test
    public void should_get_user_by_id() {
        InputItems theInputItem = inputItemsRepository.getInputItemsById(1);
        assertThat(theInputItem.getId(), is(1));
        assertThat(theInputItem.getBarcode(), is("ITEM000001"));

//        assertThat(contacts.get(0).getUser().getId(), is(1));
    }
}
