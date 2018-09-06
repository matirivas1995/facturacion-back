package com.tellez.facturacion.resource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.tellez.facturacion.dao.ContratoDAO;
import com.tellez.facturacion.model.Contrato;

@Path("/contratos")
public class ContratoResource {
	
    ContratoDAO dao = new ContratoDAO();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contrato> getAll() {
 
    	return  dao.seleccionar();

    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Contrato c) {

        Response.ResponseBuilder builder = null;
	
	        try {
	            dao.insertar(c);
	            builder = Response.status(201).entity("Contrato creado exitosamente");
	            
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
    public Response actualizar(Contrato c) {

        Response.ResponseBuilder builder = null;

	        try {
	        	
	            dao.actualizar(c);
	            builder = Response.status(200).entity("Contrato actualizado exitosamente");
	            
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
    			   builder = Response.status(202).entity("Contrato borrado exitosamente.");			      

    	 		   
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
    public Contrato getContrato(@PathParam("codigo") int codigo) throws SQLException {

    		return  ContratoDAO.getById(codigo);

    }
    
    @GET
    @Path("/{periodo}/{cliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contrato getContratoByCliente (@PathParam("cliente") int cliente ,@PathParam("periodo") int periodo ) throws SQLException {

    		return  ContratoDAO.getByClienteId( cliente, periodo);

    }
    
    @GET
    @Path("/estados/{periodo}/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contrato getContratoByEstado (@PathParam("estado") int estado ,@PathParam("periodo") int periodo ) throws SQLException {

    		return  ContratoDAO.getByEstadoId(estado, periodo);

    }
	
    @GET
    @Path("/periodo/{periodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contrato> getContratoByEstado (@PathParam("periodo") int periodo ) throws SQLException {

    		return  ContratoDAO.getByYear(periodo);

    }

}
