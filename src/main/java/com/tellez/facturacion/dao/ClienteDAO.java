package com.tellez.facturacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.*;
import javax.inject.Inject;

import com.tellez.facturacion.model.*;

@Stateless
public class ClienteDAO {
	
   @Inject
    private static Logger log;
   
   
   	//TRAER TODOS LOS CLIENTES
	public List<Cliente> seleccionar() {
		String query = "SELECT * FROM cliente ";
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Cliente c = new Cliente();
        		c.setId(rs.getInt("id"));
        		c.setRazonSocial(rs.getString("razonsocial"));
        		c.setRuc(rs.getString("ruc"));
        		c.setDireccion(rs.getString("dureccion"));
        		c.setRepresentanteLegal(rs.getString("representanteLegal"));
        		c.setCi(rs.getString("ci"));
        		lista.add(c);
        	}
        	
        } catch (SQLException ex) {
            log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;

	}

	//TRAER UN SOLO CLIENTE
    public static Cliente getById(int id) throws SQLException {
		
		String SQL = "SELECT * FROM cliente WHERE id = ? ";
		Cliente user = null;
		Connection conn = null; 
		
		try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setInt(1, id);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		user = new Cliente();
        		user.setId(rs.getInt("id"));
        		user.setRazonSocial(rs.getString("razonsocial"));
        		user.setRuc(rs.getString("ruc"));
        		user.setDireccion(rs.getString("dureccion"));
        		user.setRepresentanteLegal(rs.getString("representanteLegal"));
        		user.setCi(rs.getString("ci"));
        	}
        	
        } catch (SQLException ex) {
        	log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return user;

    }
	
    
    //AGREGAR UN NUEVO CLIENTE
    public long insertar(Cliente c) throws SQLException {

        String SQL = "INSERT INTO cliente(razonsocial, ruc, dureccion, representantelegal, ci) VALUES (?, ?, ?, ?, ?);";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, c.getRazonSocial());
            pstmt.setString(2, c.getRuc());
            pstmt.setString(3, c.getDireccion());
            pstmt.setString(4, c.getRepresentanteLegal());
            pstmt.setString(5, c.getCi());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                	throw ex;
                }
            }
        } catch (SQLException ex) {
        	throw ex;
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        	
        return id;
    	
    }

    public long actualizar(Cliente c) throws SQLException {

        String SQL = "UPDATE cliente SET razonsocial=?, ruc=?, dureccion=?, representantelegal=?, ci=? WHERE id = ?";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, c.getRazonSocial());
            pstmt.setString(2, c.getRuc());
            pstmt.setString(3, c.getDireccion());
            pstmt.setString(4, c.getRepresentanteLegal());
            pstmt.setString(5, c.getCi());
            pstmt.setInt(6, c.getId());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        } catch (SQLException ex) {
            throw ex;
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
            
        return id;
        
    }
    
    public long borrar(int codigo) throws SQLException {

        String SQL = "DELETE FROM cliente WHERE id = ? ";
 
        int id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, codigo);
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                	log.severe("Error en la eliminación: " + ex.getMessage());
                	throw ex;
                }
            }
        } catch (SQLException ex) {
        	log.severe("Error en la eliminación: " + ex.getMessage());
        	throw ex;
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        		throw ef;
        	}
        }
        return id;
    }
    
    public Cliente seleccionarPorId(int id) {
		
		String SQL = "SELECT * FROM cliente WHERE id = ? ";
		Cliente user = null;
		Connection conn = null; 
		
		try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setInt(1, id);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		user = new Cliente();
        		user.setId(rs.getInt("id"));
        		user.setRazonSocial(rs.getString("razonsocial"));
        		user.setRuc(rs.getString("ruc"));
        		user.setDireccion(rs.getString("dureccion"));
        		user.setRepresentanteLegal(rs.getString("representanteLegal"));
        		user.setCi(rs.getString("ci"));
        	}
        	
        } catch (SQLException ex) {
        	log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return user;

	}
    
    

}
