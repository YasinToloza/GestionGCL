package com.biblioteca.entidad;



public class SolicitudContrata {
	private int codigo,estado,cod_sol,cod_ccp,codEmp;
	
	
	
	private String descripcion,fecha,nombreEstado,tipocontrato;


	

	public String getTipocontrato() {
		return tipocontrato;
	}



	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}



	public String getNombreEstado() {
		return nombreEstado;
	}



	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}



	public int getCod_sol() {
		return cod_sol;
	}



	public void setCod_sol(int cod_sol) {
		this.cod_sol = cod_sol;
	}



	public int getCod_ccp() {
		return cod_ccp;
	}



	public void setCod_ccp(int cod_ccp) {
		this.cod_ccp = cod_ccp;
	}



	public int getCodEmp() {
		return codEmp;
	}



	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
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
	
	
	
	
	
}
