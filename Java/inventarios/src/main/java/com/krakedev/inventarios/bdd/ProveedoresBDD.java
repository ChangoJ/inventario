package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TiposDocumento;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	
	public ArrayList<Proveedor> buscar(String subCadena) throws Exception {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("SELECT identificador, tipo_documento, nombre, telefono, correo, direccion from proveedores "
					+ "WHERE upper(nombre) like ? ");
			
			ps.setString(1, "%"+subCadena.toUpperCase()+"%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String identificador = rs.getString("identificador");
				String tipoDocumento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				proveedor = new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
				proveedores.add(proveedor);
			}
			
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw new Exception("Error al consultar. Detalle: "+e.getMessage());
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		return proveedores;

	}
	
	public ArrayList<TiposDocumento> recuperar() throws Exception {
		ArrayList<TiposDocumento> tipoDocumentos = new ArrayList<TiposDocumento>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TiposDocumento tipoDocumento = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("SELECT * from tipo_documentos");
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				tipoDocumento = new TiposDocumento(codigo, descripcion);
				tipoDocumentos.add(tipoDocumento);
			}
			
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw new Exception("Error al consultar. Detalle: "+e.getMessage());
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		return tipoDocumentos;

	}
	
}
