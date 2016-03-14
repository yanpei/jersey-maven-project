package com.thoughtworks.learning.api;

import com.thoughtworks.learning.core.InputItems;
import com.thoughtworks.learning.core.InputItemsRepository;
import com.thoughtworks.learning.core.User;
import com.thoughtworks.learning.core.UsersRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-3-11.
 */
@Path("/inputItems")
public class InputItemsResource {

    @Inject
    private InputItemsRepository inputItemsRepository;


    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Map> result = new ArrayList<>();
        List<InputItems> list = inputItemsRepository.findInputItems();
        for(InputItems inputItems : list){
            Map itemBean = new HashMap();
            itemBean.put("itemi","/inputItems/"+inputItems.getId());
            itemBean.put("barcode",inputItems.getBarcode());
            result.add(itemBean);
        }

        return Response.status(200).entity(result).build();
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@FormParam("barcode") String barcode){
        Map result = new HashMap();
        Map newInstanceBean = new HashMap();
        newInstanceBean.put("barcode", barcode);

        inputItemsRepository.createInputItems(newInstanceBean);

    //    result.put("itemi", "/inputItems/"+newInstanceBean.get("id"));
        result.put("itemi", "/inputItems/"+newInstanceBean.get("id"));
        result.put("barcode",newInstanceBean.get("barcode"));



        return Response.status(201).entity(result).build();
    }

    @Path("/{inputItemId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("inputItemId") int inputItemId, @Context SecurityContext context){

        Map result = new HashMap();
        InputItems instance = inputItemsRepository.getInputItemsById(inputItemId);
        result.put("itemi", "/inputItems/"+inputItemId);
        result.put("barcode", instance.getBarcode());
        return Response.status(200).entity(result).build();
    }

}
