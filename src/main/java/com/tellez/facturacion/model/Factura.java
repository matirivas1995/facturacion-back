package com.tellez.facturacion.model;


public class Factura {
	
	int id;
	int nroFactura;
	Contrato contrato;
	int montoTotal;
	int gravada10;
	int gravada5;
	int iva10;
	int iva5;
	int exenta;
	String descripcion;
	String fecha;
	String estado;
	
	public Factura() {}
	 
	public Factura(int id, int nroFactura, Contrato contrato, int montoTotal, int gravada10, int gravada5, int iva10,
			int iva5, int exenta, String descripcion, String fecha, String estado) {
		super();
		this.id = id;
		this.nroFactura = nroFactura;
		this.contrato = contrato;
		this.montoTotal = montoTotal;
		this.gravada10 = gravada10;
		this.gravada5 = gravada5;
		this.iva10 = iva10;
		this.iva5 = iva5;
		this.exenta = exenta;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public int getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}
	public int getGravada10() {
		return gravada10;
	}
	public void setGravada10(int gravada10) {
		this.gravada10 = gravada10;
	}
	public int getGravada5() {
		return gravada5;
	}
	public void setGravada5(int gravada5) {
		this.gravada5 = gravada5;
	}
	public int getIva10() {
		return iva10;
	}
	public void setIva10(int iva10) {
		this.iva10 = iva10;
	}
	public int getIva5() {
		return iva5;
	}
	public void setIva5(int iva5) {
		this.iva5 = iva5;
	}
	public int getExenta() {
		return exenta;
	}
	public void setExenta(int exenta) {
		this.exenta = exenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
