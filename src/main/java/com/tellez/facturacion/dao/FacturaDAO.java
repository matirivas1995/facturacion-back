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

import com.tellez.facturacion.model.Factura;

@Stateless
public class FacturaDAO {
	
	@Inject
    private static Logger log;
   
	EstadoCuentaDAO dao = new EstadoCuentaDAO();
   
   	//TRAER TODOS LAS FACTURAS
	public List<Factura> seleccionar() {
		String query = "SELECT * FROM factura";
		
		List<Factura> lista = new ArrayList<Factura>();
		int contratoId;
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Factura c = new Factura();
        		c.setId(rs.getInt("id"));
        		c.setNroFactura(rs.getInt("nrofactura"));
        		contratoId = rs.getInt("contrato");
        		c.setContrato(ContratoDAO.getById(contratoId));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setGravada10(rs.getInt("gravada10"));
        		c.setGravada5(rs.getInt("gravada5"));
        		c.setIva10(rs.getInt("iva10"));
        		c.setIva5(rs.getInt("iva5"));
        		c.setExenta(rs.getInt("exenta"));
        		c.setDescripcion(rs.getString("descripcion"));
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
	
	//TRAER UNA SOLA FACTURA
    public static Factura getById(int id) throws SQLException {
        String query = "SELECT * FROM factura WHERE id = ?";
        Factura c = null;
        Connection conn = null; 
		int contratoId;
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		c = new Factura();
        		c.setId(rs.getInt("id"));
        		c.setNroFactura(rs.getInt("nrofactura"));
        		contratoId = rs.getInt("contrato");
        		c.setContrato(ContratoDAO.getById(contratoId));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setGravada10(rs.getInt("gravada10"));
        		c.setGravada5(rs.getInt("gravada5"));
        		c.setIva10(rs.getInt("iva10"));
        		c.setIva5(rs.getInt("iva5"));
        		c.setExenta(rs.getInt("exenta"));
        		c.setDescripcion(rs.getString("descripcion"));
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
    
    
    //AGREGAR UNA NUEVA FACTURA
    public long insertar(Factura c) throws SQLException {

        String SQL = "INSERT INTO factura(nrofactura, contrato, montototal, gravada10, gravada5, iva10, iva5, exenta, descripcion, fecha, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,c.getNroFactura());
            pstmt.setInt(2,c.getContrato().getId());
            pstmt.setInt(3, c.getMontoTotal());
            pstmt.setInt(4, c.getGravada10());
            pstmt.setInt(5, c.getGravada5());
            pstmt.setInt(6, c.getIva10());
            pstmt.setInt(7, c.getIva5());
            pstmt.setInt(8, c.getExenta());
            pstmt.setString(9, c.getDescripcion());
            pstmt.setString(10, c.getFecha());
            pstmt.setString(11, c.getEstado());
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
    
    //ACTUALIZAR UNA FACTURA
    public long actualizar(Factura c) throws SQLException {

        String SQL = "UPDATE factura SET nrofactura=?, contrato=?, montototal=?, gravada10=?, gravada5=?, iva10=?, iva5=?, exenta=?, descripcion=?, fecha=?, estado=? WHERE id = ?";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,c.getNroFactura());
            pstmt.setInt(2,c.getContrato().getId());
            pstmt.setInt(3, c.getMontoTotal());
            pstmt.setInt(4, c.getGravada10());
            pstmt.setInt(5, c.getGravada5());
            pstmt.setInt(6, c.getIva10());
            pstmt.setInt(7, c.getIva5());
            pstmt.setInt(8, c.getExenta());
            pstmt.setString(9, c.getDescripcion());
            pstmt.setString(10, c.getFecha());
            pstmt.setString(11, c.getEstado());
            pstmt.setInt(12, c.getId());
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

        String SQL = "DELETE FROM factura WHERE id = ? ";
 
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
