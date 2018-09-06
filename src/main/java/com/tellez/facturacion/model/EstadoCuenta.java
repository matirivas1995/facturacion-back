package com.tellez.facturacion.model;

public class EstadoCuenta {
	
	int id;
	int cuotasTotales;
	int cuotasPagadas;
	int montoTotal;
	int pagado;
	int saldo;
	
	public EstadoCuenta() {}

	public EstadoCuenta(int id, int cuotasTotales, int cuotasPagadas, int montoTotal, int pagado, int saldo) {
		super();
		this.id = id;
		this.cuotasTotales = cuotasTotales;
		this.cuotasPagadas = cuotasPagadas;
		this.montoTotal = montoTotal;
		this.pagado = pagado;
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCuotasTotales() {
		return cuotasTotales;
	}

	public void setCuotasTotales(int cuotasTotales) {
		this.cuotasTotales = cuotasTotales;
	}

	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	public int getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	
	
	
	
}
