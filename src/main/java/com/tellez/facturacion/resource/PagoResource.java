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

import com.tellez.facturacion.dao.EstadoCuentaDAO;
import com.tellez.facturacion.dao.PagoDAO;
import com.tellez.facturacion.model.EstadoCuenta;
import com.tellez.facturacion.model.Pago;

@Path("/pagos")
public class PagoResource {

    PagoDAO dao = new PagoDAO();
    EstadoCuentaDAO estadodao = new EstadoCuentaDAO();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> getAll() {
 
    	return  dao.seleccionar();

    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Pago c) {

        Response.ResponseBuilder builder = null;
	
	        try {
	            dao.insertar(c);
	            builder = Response.status(201).entity("Pago creado exitosamente");
	            
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
    public Response actualizar(Pago c) {

        Response.ResponseBuilder builder = null;
        EstadoCuenta es = new EstadoCuenta();
	        try {
	        	
	            dao.actualizar(c);
	            es = c.getEstadoCuenta();
	            es.setCuotasPagadas(es.getCuotasPagadas()+1);
	            es.setPagado(es.getPagado()+c.getMonto());
	            es.setSaldo(es.getMontoTotal()-es.getPagado());
	            estadodao.actualizar(es);
	            builder = Response.status(200).entity("Pago actualizado exitosamente");
	            
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
    			   builder = Response.status(202).entity("Pago borrado exitosamente.");			      

    	 		   
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
    public Pago getPago(@PathParam("codigo") int codigo) throws SQLException {

    		return  PagoDAO.getById(codigo);

    }
    
    @GET
    @Path("/{estado}/{estadoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> getPagosByEstado(@PathParam("estado") String estado , @PathParam("estadoId") int estadoId) throws SQLException {

    		return  PagoDAO.getByEstadoId(estado,estadoId);

    }
	
	
}
