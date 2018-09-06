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

import com.tellez.facturacion.model.Contrato;

@Stateless
public class ContratoDAO {

	@Inject
    private static Logger log;
   
	EstadoCuentaDAO dao = new EstadoCuentaDAO();
   
   	//TRAER TODOS LOS CONTRATOS
	public List<Contrato> seleccionar() {
		String query = "SELECT * FROM contrato";
		
		List<Contrato> lista = new ArrayList<Contrato>();
		int estadoCuentaId;
		int clienteId;
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Contrato c = new Contrato();
        		c.setId(rs.getInt("id"));
        		clienteId=rs.getInt("cliente");
        		c.setCliente(ClienteDAO.getById(clienteId));
        		c.setPeriodo(rs.getInt("periodo"));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setCuotas(rs.getInt("cuotas"));
        		estadoCuentaId=rs.getInt("estado");
        		c.setEstado(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setFecha(rs.getString("fecha"));
        		c.setLink(rs.getString("link"));
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
	
	//TRAER UN SOLO CONTRATO
    public static Contrato getById(int id) throws SQLException {
        String query = "SELECT * FROM pago WHERE id = ?";
        Contrato c = null;
		int estadoCuentaId;
		int clienteId;
        Connection conn = null; 
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		c = new Contrato();
        		c.setId(rs.getInt("id"));
        		clienteId=rs.getInt("estado");
        		c.setCliente(ClienteDAO.getById(clienteId));
        		c.setPeriodo(rs.getInt("periodo"));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setCuotas(rs.getInt("cuotas"));
        		estadoCuentaId=rs.getInt("estado");
        		c.setEstado(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setFecha(rs.getString("fecha"));
        		c.setLink(rs.getString("link"));
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
    
  //TRAER CONTRATO DE UN CLIENTE
    public static Contrato getByClienteId(int clienteId , int periodo) throws SQLException {
        String query = "SELECT * FROM contrato WHERE cliente = ? AND periodo = ?";
		int estadoCuentaId;
		int clienteID;
        Connection conn = null; 
		Contrato c = new Contrato();
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, periodo);
            
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		c.setId(rs.getInt("id"));
        		clienteID=rs.getInt("cliente");
        		c.setCliente(ClienteDAO.getById(clienteID));
        		c.setPeriodo(rs.getInt("periodo"));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setCuotas(rs.getInt("cuotas"));
        		estadoCuentaId=rs.getInt("estado");
        		c.setEstado(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setFecha(rs.getString("fecha"));
        		c.setLink(rs.getString("link"));
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
    
  //TRAER CONTRATO DE UN CLIENTE
    public static List<Contrato> getByYear(int periodo) throws SQLException {

        String query = "SELECT * FROM contrato WHERE periodo = ?";
        
        List<Contrato> listo = new ArrayList<Contrato>();
		int estadoCuentaId;
		int clienteID;
        Connection conn = null; 
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, periodo);
            
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		Contrato c = new Contrato();
        		c.setId(rs.getInt("id"));
        		clienteID=rs.getInt("cliente");
        		c.setCliente(ClienteDAO.getById(clienteID));
        		c.setPeriodo(rs.getInt("periodo"));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setCuotas(rs.getInt("cuotas"));
        		estadoCuentaId=rs.getInt("estado");
        		c.setEstado(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setFecha(rs.getString("fecha"));
        		c.setLink(rs.getString("link"));
        		listo.add(c);
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
        return listo;
    }
    
    
    //TRAER CONTRATO DE UN ESTADO DE CUENTA
    public static Contrato getByEstadoId(int estadoId , int periodo) throws SQLException {
        String query = "SELECT * FROM contrato WHERE estado = ? AND periodo = ?";
		int estadoCuentaId;
		int clienteID;
        Connection conn = null; 
		Contrato c = new Contrato();
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, estadoId);
            pstmt.setInt(2, periodo);
            
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		c.setId(rs.getInt("id"));
        		clienteID=rs.getInt("cliente");
        		c.setCliente(ClienteDAO.getById(clienteID));
        		c.setPeriodo(rs.getInt("periodo"));
        		c.setMontoTotal(rs.getInt("montototal"));
        		c.setCuotas(rs.getInt("cuotas"));
        		estadoCuentaId=rs.getInt("estado");
        		c.setEstado(EstadoCuentaDAO.getById(estadoCuentaId));
        		c.setFecha(rs.getString("fecha"));
        		c.setLink(rs.getString("link"));
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
    
    
    //AGREGAR UN NUEVO CONTRATO
    public long insertar(Contrato c) throws SQLException {

        String SQL = "INSERT INTO contrato(cliente, periodo, montototal, cuotas, estado, fecha, contacto,link) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,c.getCliente().getId());
            pstmt.setInt(2, c.getPeriodo());
            pstmt.setInt(3,c.getMontoTotal());
            pstmt.setInt(4, c.getCuotas());
            pstmt.setInt(5, c.getEstado().getId());
            pstmt.setString(6, c.getFecha());
            pstmt.setString(7, c.getContacto());
            pstmt.setString(8, c.getLink());
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
 
    //ACTUALIZAR UN CONTRATO
    public long actualizar(Contrato c) throws SQLException {

        String SQL = "UPDATE contrato SET cliente=?, periodo=?, montototal=?, cuotas=?, estado=?, fecha=?, contacto=?, link=? WHERE id = ?";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,c.getCliente().getId());
            pstmt.setInt(2, c.getPeriodo());
            pstmt.setInt(3,c.getMontoTotal());
            pstmt.setInt(4, c.getCuotas());
            pstmt.setInt(5, c.getEstado().getId());
            pstmt.setString(6, c.getFecha());
            pstmt.setString(7, c.getContacto());
            pstmt.setString(8, c.getLink());
            pstmt.setInt(9, c.getId());
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
    
    //ELIMINAR UN CONTRATO
    public long borrar(int codigo) throws SQLException {

        String SQL = "DELETE FROM contrato WHERE id = ? ";
 
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
