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

import com.tellez.facturacion.model.Pago;



@Stateless
public class PagoDAO {
	
	
	@Inject
    private static Logger log;
   
	EstadoCuentaDAO dao = new EstadoCuentaDAO();
   
   	//TRAER TODOS LOS PAGOS
	public List<Pago> seleccionar() {
		String query = "SELECT * FROM pago";
		
		List<Pago> lista = new ArrayList<Pago>();
		int estadoCuentaId;
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Pago c = new Pago();
        		c.setId(rs.getInt("id"));
        		estadoCuentaId=rs.getInt("estadocuenta");
        		c.setEstadoCuenta(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setMonto(rs.getInt("monto"));
        		c.setPorcentaje(rs.getInt("porcentaje"));
        		c.setFecha(rs.getString("fecha"));
        		c.setEstado(rs.getString("estado"));
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
	
	//TRAER UN SOLO PAGO
    public static Pago getById(int id) throws SQLException {
        String query = "SELECT * FROM pago WHERE id = ?";
        Pago c = null;
        Connection conn = null; 
		int estadoCuentaId;
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		c = new Pago();
        		c.setId(rs.getInt("id"));
        		estadoCuentaId=rs.getInt("estadocuenta");
        		c.setEstadoCuenta(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setMonto(rs.getInt("monto"));
        		c.setPorcentaje(rs.getInt("porcentaje"));
        		c.setFecha(rs.getString("fecha"));
        		c.setEstado(rs.getString("estado"));
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
    
   	//TRAER TODOS LOS PAGOS DE UN ESTADO DE CUENTA
	public static List<Pago> getByEstadoId(String estado , int estadoId) {
		String query = "SELECT * FROM pago WHERE estado = ? AND estadocuenta = ?";
		
		List<Pago> lista = new ArrayList<Pago>();
		int estadoCuentaId;
		Connection conn = null; 
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, estado);
            pstmt.setInt(2, estadoId);
            
        	ResultSet rs = pstmt.executeQuery();
        	


        	while(rs.next()) {
        		Pago c = new Pago();
        		c.setId(rs.getInt("id"));
        		estadoCuentaId=rs.getInt("estadocuenta");
        		c.setEstadoCuenta(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setMonto(rs.getInt("monto"));
        		c.setPorcentaje(rs.getInt("porcentaje"));
        		c.setFecha(rs.getString("fecha"));
        		c.setEstado(rs.getString("estado"));
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
    
    //AGREGAR UN NUEVO PAGO
    public long insertar(Pago c) throws SQLException {

        String SQL = "INSERT INTO pago(estadocuenta, monto, porcentaje, fecha, estado) VALUES (?, ?, ?, ?, ?)";
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,c.getEstadoCuenta().getId());
            pstmt.setInt(2,c.getMonto());
            pstmt.setInt(3, c.getPorcentaje());
            pstmt.setString(4, c.getFecha());
            pstmt.setString(5, c.getEstado());
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
    
    //ACTUALIZAR UN PAGO
    public long actualizar(Pago c) throws SQLException {

        String SQL = "UPDATE pago SET estadocuenta=?, monto=?, porcentaje=?, fecha=?, estado=? WHERE id = ?";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,c.getEstadoCuenta().getId());
            pstmt.setInt(2,c.getMonto());
            pstmt.setInt(3, c.getPorcentaje());
            pstmt.setString(4, c.getFecha());
            pstmt.setString(5, c.getEstado());
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
    
    //ELIMINAR UN PAGO
    public long borrar(int codigo) throws SQLException {

        String SQL = "DELETE FROM pago WHERE id = ? ";
 
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
