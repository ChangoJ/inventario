package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios.entidades.Pedido;

@Path("pedidos")
public class ServiciosPedidos {
	
	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Pedido pedido){
		PedidosBDD pedidoBDD = new PedidosBDD();
		try {
			pedidoBDD.insertar(pedido);
			return Response.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("recibir")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Pedido pedido) {

		System.out.println("Actualizar: " + pedido);
		PedidosBDD pedidoBDD = new PedidosBDD();
		try {
			pedidoBDD.actualizar(pedido);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
}
