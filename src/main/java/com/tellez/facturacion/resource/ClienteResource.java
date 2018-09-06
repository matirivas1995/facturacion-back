package com.tellez.facturacion.resource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tellez.facturacion.model.*;
import com.tellez.facturacion.dao.*;


@Path("/clientes")
public class ClienteResource {
	
    ClienteDAO dao = new ClienteDAO();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAll() {
 
    	return  dao.seleccionar();

    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Cliente c) {

        Response.ResponseBuilder builder = null;
        
	        try {
	            long id = dao.insertar(c);
	            JsonObject response =  Json.createObjectBuilder().add("id", id).build();
	            builder =	Response.status(Response.Status.CREATED).entity(response);
	            
	        } catch (SQLException e) {
	            // Handle the unique constrain violation
	            Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("bd-error", e.getLocalizedMessage());
	            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
	        } catch (Exception e) {
	            // Handle generic exceptions
	            Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("error", e.getMessage());
	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	        }
        return builder.build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(Cliente c) {

        Response.ResponseBuilder builder = null;

	        try {
	        	
	            dao.actualizar(c);
	            builder = Response.status(200).entity("Cliente actualizado exitosamente");
	            
	        } catch (SQLException e) {
	            // Handle the unique constrain violation
	            Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("bd-error", e.getLocalizedMessage());
	            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
	        } catch (Exception e) {
	            // Handle generic exceptions
	            Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("error", e.getMessage());
	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	        }
        return builder.build();
    }
    
    

    @DELETE
    @Path("/{codigo}")
    public Response borrar(@PathParam("codigo") int codigo)
    {  
    	Response.ResponseBuilder builder = null;

    	 	   try {
    	 		   dao.borrar(codigo);
    			   builder = Response.status(202).entity("Cliente borrado exitosamente.");			      

    	 		   
    	 	   } catch (SQLException e) {
    	            // Handle the unique constrain violation
    	            Map<String, String> responseObj = new HashMap<>();
    	            responseObj.put("bd-error", e.getLocalizedMessage());
    	            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
    	        } catch (Exception e) {
    	            // Handle generic exceptions
    	            Map<String, String> responseObj = new HashMap<>();
    	            responseObj.put("error", e.getMessage());
    	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    	        }
        return builder.build();
    }
    
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getCliente(@PathParam("codigo") int codigo) {

    		return  dao.seleccionarPorId(codigo);

    }
    
}

