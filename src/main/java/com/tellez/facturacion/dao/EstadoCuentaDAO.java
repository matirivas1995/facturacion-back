package com.tellez.facturacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.tellez.facturacion.model.EstadoCuenta;

@Stateless
public class EstadoCuentaDAO {
	
	   @Inject
	    private static Logger log;
	   
	   
	   	//TRAER TODOS LOS ESTADOS DE CUENTA
		public List<EstadoCuenta> seleccionar() {
			String query = "SELECT * FROM estadocuenta";
			
			List<EstadoCuenta> lista = new ArrayList<EstadoCuenta>();
			
			Connection conn = null; 
	        try 
	        {
	        	conn = Bd.connect();
	        	ResultSet rs = conn.createStatement().executeQuery(query);

	        	while(rs.next()) {
	        		EstadoCuenta c = new EstadoCuenta();
	        		c.setId(rs.getInt("id"));
	        		c.setCuotasTotales(rs.getInt("cuotastotales"));
	        		c.setCuotasPagadas(rs.getInt("cuotaspagadas"));
	        		c.setMontoTotal(rs.getInt("montototal"));
	        		c.setPagado(rs.getInt("pagado"));
	        		c.setSaldo(rs.getInt("saldo"));
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
		
		//TRAER UN SOLO ESTADO DE CUENTA
	    public static EstadoCuenta getById(int id) throws SQLException {
	        String query = "SELECT * FROM estadocuenta WHERE id = ?";
	        EstadoCuenta c = null;
	        Connection conn = null; 
	        
	        try 
	        {
	            conn = Bd.connect();
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, id);
	            
	        	ResultSet rs = pstmt.executeQuery();

	        	while(rs.next()) {
	        		c = new EstadoCuenta();
	        		c.setId(rs.getInt("id"));
	        		c.setCuotasTotales(rs.getInt("cuotastotales"));
	        		c.setCuotasPagadas(rs.getInt("cuotaspagadas"));
	        		c.setMontoTotal(rs.getInt("montototal"));
	        		c.setPagado(rs.getInt("pagado"));
	        		c.setSaldo(rs.getInt("saldo"));
	        	}

	        } catch (SQLException ex) {
	            log.severe("Error en la seleccion: " + ex.getMessage());
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
	        return c;
	    }
	    
	    
	    //AGREGAR UN NUEVO ESTADO DE CUENTAS
	    public long insertar(EstadoCuenta c) throws SQLException {

	        String SQL = "INSERT INTO estadocuenta(cuotastotales, cuotaspagadas, montototal, pagado, saldo) VALUES (?, ?, ?, ?, ?)";
	 
	        long id = 0;
	        Connection conn = null;
	        
	        try 
	        {
	        	conn = Bd.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	            pstmt.setInt(1, c.getCuotasTotales());
	            pstmt.setInt(2, c.getCuotasPagadas());
	            pstmt.setInt(3, c.getMontoTotal());
	            pstmt.setInt(4, c.getPagado());
	            pstmt.setInt(5, c.getSaldo());
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
	    
	    //ACTUALIZAR UN ESTADO DE CUENTA
	    public long actualizar(EstadoCuenta c) throws SQLException {

	        String SQL = "UPDATE estadocuenta SET cuotastotales=?, cuotaspagadas=?, montototal=?, pagado=?, saldo=? WHERE id = ?";
	 
	        long id = 0;
	        Connection conn = null;
	        
	        try 
	        {
	            conn = Bd.connect();
	            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	            pstmt.setInt(1, c.getCuotasTotales());
	            pstmt.setInt(2, c.getCuotasPagadas());
	            pstmt.setInt(3, c.getMontoTotal());
	            pstmt.setInt(4, c.getPagado());
	            pstmt.setInt(5, c.getSaldo());
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
	    
	    //ELIMINAR UN ESTADO DE CUENTA
	    public long borrar(int codigo) throws SQLException {

	        String SQL = "DELETE FROM estadocuenta WHERE id = ? ";
	 
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
		

	
	
	
	
}
