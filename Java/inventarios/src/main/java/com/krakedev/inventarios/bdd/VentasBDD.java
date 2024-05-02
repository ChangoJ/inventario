package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {
	
	public void guardar(Venta venta) throws KrakeDevException {
		Connection con = null;
		ResultSet rsClave = null;
		PreparedStatement psDet = null;
		Date fechaActual = new Date();
		int codigoCabecera = 0;
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO cabecera_ventas (fecha, total_sin_iva, iva, total) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setDate(1, fechaSQL);
			ps.setBigDecimal(2, BigDecimal.ZERO);
			ps.setBigDecimal(3, BigDecimal.ZERO);
			ps.setBigDecimal(4, BigDecimal.ZERO);

			ps.executeUpdate();

			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			System.out.println("Codsigo: "+ codigoCabecera);

			ArrayList<DetalleVenta> detallesVenta = venta.getDetalles();

			DetalleVenta detalle;
			System.out.println(venta);
			for (int i = 0; i < detallesVenta.size(); i++) {
				detalle = detallesVenta.get(i);
				psDet = con.prepareStatement(
						"INSERT INTO detalle_ventas (cabecera_ventas, producto, cantidad, precio_venta, subtotal, subtotal_con_iva) VALUES"
								+ "(?, ?, ?, ?, ?, ?)");

				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, detalle.getProducto().getCodigo());
				psDet.setInt(3, detalle.getCantidad());
				psDet.setBigDecimal(4, detalle.getProducto().getPrecioVenta());

				BigDecimal pv = detalle.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(detalle.getCantidad());
				BigDecimal subtotal = pv.multiply(cantidad);

				psDet.setBigDecimal(5, subtotal);

				BigDecimal iva = new BigDecimal(1.12);
				if (detalle.getProducto().getTieneIva()) {
					psDet.setBigDecimal(6, subtotal.multiply(iva));
				} else {
					psDet.setBigDecimal(7, subtotal);

				}

				psDet.executeUpdate();
			}

		

			actualizar(venta, codigoCabecera);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el pedido. Detalle: " + e.getMessage());
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

	public void actualizar(Venta venta, int codigoCabecera) throws KrakeDevException {
		Connection con = null;
		PreparedStatement psDet = null;

		Date fechaActual = new Date();

		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());
		ArrayList<DetalleVenta> detallesVenta = venta.getDetalles();

		DetalleVenta detalle;
		BigDecimal totalSinIva = BigDecimal.ZERO;
		BigDecimal totalConIva = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		try {
			for (int i = 0; i < detallesVenta.size(); i++) {
				detalle = detallesVenta.get(i);

				BigDecimal pv = detalle.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(detalle.getCantidad());
				BigDecimal subtotal = pv.multiply(cantidad);

				BigDecimal iva = new BigDecimal(1.12);
				if (detalle.getProducto().getTieneIva()) {
					totalConIva = totalConIva.add(subtotal.multiply(iva));
				} 
				
				totalSinIva = totalSinIva.add(subtotal);

			

				total = total.add(subtotal);

				con = ConexionBDD.obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"UPDATE cabecera_ventas SET total_sin_iva = ?, iva = ?, total = ?  where codigo_cabecera_ventas = ?");

				ps.setBigDecimal(1, totalConIva);

				ps.setBigDecimal(2, totalSinIva);

				ps.setBigDecimal(3, total);
				ps.setInt(4, codigoCabecera);

				ps.executeUpdate();

				psDet = con.prepareStatement(
						"INSERT INTO historial_stock (fecha, referencia, producto, cantidad) VALUES (?,?,?,?)");

				psDet.setTimestamp(1, fechaHoraActual);
				psDet.setString(2, "VENTA " + codigoCabecera);
				psDet.setInt(3, detalle.getProducto().getCodigo());
				psDet.setInt(4, detalle.getCantidad() * -1);

				psDet.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar la venta. Detalle: " + e.getMessage());
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

}
