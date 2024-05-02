package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.Pedido;
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
			ps = con.prepareStatement(
					"SELECT prov.identificador, prov.tipo_documento, td.descripcion, prov.nombre, prov.telefono, prov.correo, prov.direccion from proveedores prov, tipo_documentos td "
							+ "WHERE prov.tipo_documento = td.codigo AND prov.identificador = ? ");

			ps.setString(1, subCadena);

			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				String codigoTipoDocumento = rs.getString("tipo_documento");
				String descripcionTipoDocumento = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				TiposDocumento tipoDocumento = new TiposDocumento(codigoTipoDocumento, descripcionTipoDocumento);
				proveedor = new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
				proveedores.add(proveedor);
			}

		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw new Exception("Error al consultar. Detalle: " + e.getMessage());
		} finally {
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

			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				tipoDocumento = new TiposDocumento(codigo, descripcion);
				tipoDocumentos.add(tipoDocumento);
			}

		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw new Exception("Error al consultar. Detalle: " + e.getMessage());
		} finally {
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

	public void crear(Proveedor proveedor) throws KrakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO proveedores (identificador, tipo_documento, nombre, telefono, correo, direccion) VALUES (?,?,?,?,?,?)");

			ps.setString(1, proveedor.getIdentificar());
			ps.setString(2, proveedor.getTipoDocumento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el proveedor. Detalle: " + e.getMessage());
		} catch (KrakeDevException e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public ArrayList<DetallePedido> buscarPedidosPorProveedor(String subCadena) throws Exception {

		ArrayList<DetallePedido> detallesPedido = new ArrayList<DetallePedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DetallePedido detallePedido = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"SELECT dp.codigo_detalle_pedido, dp.cabecera_pedido, dp.cantidad_solicitada, dp.cantidad_recibida "
							+ "from proveedores prov, cabecera_pedidos cb, detalle_pedidos dp "
							+ "WHERE prov.identificador = cb.proveedor AND cb.codigo = dp.cabecera_pedido AND prov.identificador = ? ");

			ps.setString(1, subCadena);

			rs = ps.executeQuery();

			while (rs.next()) {
				int codigo_detalle_pedido = rs.getInt("codigo_detalle_pedido");
				int cantidad_solicitada = rs.getInt("cantidad_solicitada");
				int cantidad_recibida = rs.getInt("cantidad_recibida");
				detallePedido = new DetallePedido(codigo_detalle_pedido, null, null, cantidad_solicitada, null,
						cantidad_recibida);
				detallesPedido.add(detallePedido);
			}

		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw new Exception("Error al consultar. Detalle: " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		return detallesPedido;

	}
}
