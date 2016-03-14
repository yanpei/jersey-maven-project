package com.thoughtworks.fixed.assets.api;

import com.thoughtworks.learning.core.InputItems;
import com.thoughtworks.learning.core.InputItemsRepository;
import com.thoughtworks.learning.core.User;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by yan on 16-3-11.
 */
public class InputItemsResourceTest extends TestBase{

    private String basePath = "/inputItems";
    private InputItems firstItem = mock(InputItems.class);
    private InputItems secondItem = mock(InputItems.class);
    private InputItems newInputItem= mock(InputItems.class);


    @Override
    public void setUp() throws Exception {
        when(inputItemsRepository.findInputItems()).thenReturn(Arrays.asList(firstItem, secondItem));
        when(firstItem.getId()).thenReturn(1);
        when(firstItem.getBarcode()).thenReturn("ITEM000001");

        when(newInputItem.getId()).thenReturn(3);
        when(newInputItem.getBarcode()).thenReturn("ITEM000003");
        when(inputItemsRepository.newInputItems(anyString())).thenReturn(mock(InputItems.class));

        when(inputItemsRepository.getInputItemsById(1)).thenReturn(firstItem);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ((Map)arguments[0]).put("id", 3);
                return null;
            }
        }).when(usersRepository).createUser(anyMap());
        super.setUp();
    }



    @Test
    public void should_list_all_inputItems(){
        Response response = target(basePath).request().get();

        assertThat(response.getStatus(), is(200));

        List<Map> result = response.readEntity(List.class);

        assertThat(result.size(), is(2));

        Map item = result.get(0);

        assertThat((String) item.get("itemi"), is(basePath+"/"+firstItem.getId()));
        assertThat((String) item.get("barcode"), is(firstItem.getBarcode()));
    }

    @Test
    public void should_create_one_inputItem(){
        Form formData = new Form();

        formData.param("barcode", "ITEM000003");

        Response response = target(basePath).request().post(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(201));

        Map item = response.readEntity(Map.class);

        assertThat((String) item.get("itemi"), is(basePath+"/3"));
        assertThat((String) item.get("barcode"), is("ITEM000003"));

    }

    @Test
    public void should_get_inputItem_by_Id(){
        Response response = target(basePath+"/1").request().get();

        assertThat(response.getStatus(), is(200));

        Map user = response.readEntity(Map.class);

        assertThat((String) user.get("itemi"), is(basePath+"/1"));
        assertThat((String) user.get("barcoe"), is("ITEM000001"));


    }
}
