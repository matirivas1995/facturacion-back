package com.tellez.facturacion.model;


public class Contrato {

	int id;
	Cliente cliente;
	int periodo;
	int montoTotal;
	int cuotas;
	EstadoCuenta estado;
	String fecha;
	String Contacto;
	String link;
	
	public Contrato() {}
	
	
	public Contrato(int id, Cliente cliente, int periodo, int montoTotal, int cuotas, EstadoCuenta estado, String fecha,
			String contacto, String link) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.periodo = periodo;
		this.montoTotal = montoTotal;
		this.cuotas = cuotas;
		this.estado = estado;
		this.fecha = fecha;
		this.Contacto = contacto;
		this.link = link;
	}
	
	
	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public int getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public EstadoCuenta getEstado() {
		return estado;
	}
	public void setEstado(EstadoCuenta estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getContacto() {
		return Contacto;
	}
	public void setContacto(String contacto) {
		Contacto = contacto;
	}
	
	
	
	
}
