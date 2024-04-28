package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.TiposDocumento;

@Path("tiposdocumento")
public class ServiciosTiposDocumentos {
	
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperar(){
		ProveedoresBDD proveedoreBDD = new ProveedoresBDD();
		ArrayList<TiposDocumento> tiposDocumentos = null;
		try {
			tiposDocumentos = proveedoreBDD.recuperar();
			return Response.ok(tiposDocumentos).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
