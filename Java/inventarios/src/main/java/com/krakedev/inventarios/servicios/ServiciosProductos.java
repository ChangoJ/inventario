package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;

@Path("productos")
public class ServiciosProductos {
	
	@Path("buscar/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena")  int subcadena){
		ProductosBDD productoBDD = new ProductosBDD();
		ArrayList<Producto> productos = null;
		try {
			productos = productoBDD.buscar(subcadena);
			return Response.ok(productos).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Producto producto){
		ProductosBDD productoBDD = new ProductosBDD();
		try {
			productoBDD.crear(producto);
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Producto producto){
		ProductosBDD productoBDD = new ProductosBDD();
		try {
			productoBDD.actualizar(producto);
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("crearCategoria")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearCategoria(Categoria categoria){
		ProductosBDD productoBDD = new ProductosBDD();
		try {
			productoBDD.crearCategoria(categoria);
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("actualizarCategoria")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarCategoria(Categoria categoria){
		ProductosBDD productoBDD = new ProductosBDD();
		try {
			productoBDD.actualizarCategoria(categoria);
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("recuperarCategorias")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarCategorias(){
		ProductosBDD productoBDD = new ProductosBDD();
		ArrayList<Categoria> categorias = null;
		try {
			categorias = productoBDD.recuperarCategorias();
			return Response.ok(categorias).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
