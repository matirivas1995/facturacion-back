package com.tellez.facturacion.model;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Cliente implements Serializable {
	
	int id;
	String razonSocial;
	String ruc;
	String direccion;
	String representanteLegal;
	String ci;
	
	public Cliente(int id,String razonSocial, String ruc, String direccion, String representanteLegal, String ci) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.ruc = ruc;
		this.direccion = direccion;
		this.representanteLegal = representanteLegal;
		this.ci = ci;
	}
	
	public Cliente() {}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}

}
