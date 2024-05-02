package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Proveedor;


@Path("proveedores")
public class ServiciosProveedores {
	
	
	@Path("buscar/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena")  String subcadena){
		ProveedoresBDD proveedoreBDD = new ProveedoresBDD();
		ArrayList<Proveedor> proveedores = null;
		try {
			proveedores = proveedoreBDD.buscar(subcadena);
			return Response.ok(proveedores).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Proveedor proveedor){
		ProveedoresBDD proveedoreBDD = new ProveedoresBDD();
		try {
			proveedoreBDD.crear(proveedor);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	@Path("buscarPedidosPorProveedor/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPedidosPorProveedor(@PathParam("subcadena")  String subcadena){
		ProveedoresBDD proveedoreBDD = new ProveedoresBDD();
		ArrayList<DetallePedido> detallePedidos = null;
		try {
			detallePedidos = proveedoreBDD.buscarPedidosPorProveedor(subcadena);
			return Response.ok(detallePedidos).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
