package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class Producto {
	private int codigo;
	private String nombre;
	private UnidadDeMedida unidadDeMedida;
	private BigDecimal precioVenta;
	private Boolean tieneIva;
	private BigDecimal coste;
	private Categoria categoria;
	private int stock;

	public Producto() {
		
	}

	public Producto(int codigo, String nombre, UnidadDeMedida unidadDeMedida, BigDecimal precioVenta, Boolean tieneIva,
			BigDecimal coste, Categoria categoria, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidadDeMedida = unidadDeMedida;
		this.precioVenta = precioVenta;
		this.tieneIva = tieneIva;
		this.coste = coste;
		this.categoria = categoria;
		this.stock = stock;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Boolean getTieneIva() {
		return tieneIva;
	}

	public void setTieneIva(Boolean tieneIva) {
		this.tieneIva = tieneIva;
	}

	public BigDecimal getCoste() {
		return coste;
	}

	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", unidadDeMedida=" + unidadDeMedida
				+ ", precioVenta=" + precioVenta + ", tieneIva=" + tieneIva + ", coste=" + coste + ", categoria="
				+ categoria + ", stock=" + stock + "]";
	}

	
	
	
	
	
}
