package com.tellez.facturacion.model;


public class Pago {
	
	int id;
	EstadoCuenta estadoCuenta;
	int monto;
	int porcentaje;
	String fecha;
	String estado;
	
	
	public Pago() {}


	public Pago(int id, EstadoCuenta estadoCuenta, int monto, int porcentaje, String fecha, String estado) {
		super();
		this.id = id;
		this.estadoCuenta = estadoCuenta;
		this.monto = monto;
		this.porcentaje = porcentaje;
		this.fecha = fecha;
		this.estado = estado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}


	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}


	public int getMonto() {
		return monto;
	}


	public void setMonto(int monto) {
		this.monto = monto;
	}


	public int getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	
	
}
