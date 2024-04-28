package com.krakedev.inventarios.entidades;

public class TiposDocumento {
	private String codigo;
	private String descripcion;
	
	public TiposDocumento() {
		
	}


	public TiposDocumento(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}



	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "TiposDocumento [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
}
