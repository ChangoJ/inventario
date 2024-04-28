package com.krakedev.inventarios.entidades;
import com.krakedev.inventarios.entidades.CategoriaUDM;

public class UnidadDeMedida {
	private String nombre;
	private String descripcion;
	private CategoriaUDM categoriaUnidadDeMedida;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public CategoriaUDM getCategoriaUnidadDeMedida() {
		return categoriaUnidadDeMedida;
	}
	
	public void setCategoriaUnidadDeMedida(CategoriaUDM categoriaUnidadDeMedida) {
		this.categoriaUnidadDeMedida = categoriaUnidadDeMedida;
	}
	
	@Override
	public String toString() {
		return "UnidadDeMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", categoriaUnidadDeMedida="
				+ categoriaUnidadDeMedida + "]";
	}
	
	
	
	
}
