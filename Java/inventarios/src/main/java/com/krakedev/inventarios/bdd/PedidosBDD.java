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

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.HistorialStock;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	
	public void insertar(Pedido pedido) throws KrakeDevException {
		Connection con = null;
		ResultSet rsClave = null;
		PreparedStatement psDet = null;
		Date fechaActual = new Date();
		int codigoCabecera = 0;
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
		
		
		
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO cabecera_pedidos (proveedor, fecha, estado) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pedido.getProveedor().getIdentificar());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");
			

			ps.executeUpdate();
			
			rsClave = ps.getGeneratedKeys();
			
			if(rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			
			
			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			
			DetallePedido detalle;
			
			for (int i = 0; i < detallesPedido.size(); i++) {
				detalle = detallesPedido.get(i);
				psDet = con.prepareStatement("INSERT INTO detalle_pedidos (cabecera_pedido, producto, cantidad_solicitada, cantidad_recibida,subtotal) VALUES"
						+ "(?, ?, ?, ?,?)");
				
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, detalle.getProducto().getCodigo());
				psDet.setInt(3, detalle.getCantidadSolicitada());
				psDet.setInt(4, detalle.getCantidadRecibida());
				
				BigDecimal pv =  detalle.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(detalle.getCantidadSolicitada());
				BigDecimal subtotal = pv.multiply(cantidad);
				
				psDet.setBigDecimal(5, subtotal);
				
				psDet.executeUpdate();
			}
			
			System.out.println("CodigoGenerado: "+codigoCabecera);
			
			
			
			

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el pedido. Detalle: "+e.getMessage());
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
	
	public void actualizar(Pedido pedido) throws KrakeDevException {
		Connection con = null;
		ResultSet rsClave = null;
		PreparedStatement psDet = null;
		PreparedStatement psDet2 = null;

		HistorialStock historialStock = new HistorialStock();
		Date fechaActual = new Date();
		int codigoCabecera = 0;
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());
		
		
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("UPDATE cabecera_pedidos SET estado = ? where codigo = ?", Statement.RETURN_GENERATED_KEYS);


			ps.setString(1, "R");
			ps.setInt(2, pedido.getCodigo());
			

			ps.executeUpdate();
			
			rsClave = ps.getGeneratedKeys();
			
			if(rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			
			
			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			
			DetallePedido detalle;
			
			for (int i = 0; i < detallesPedido.size(); i++) {
				detalle = detallesPedido.get(i);
				psDet = con
						.prepareStatement("UPDATE detalle_pedidos SET cantidad_recibida=?, subtotal =? WHERE codigo_detalle_pedido = ?");
				

				psDet.setInt(1, detalle.getCantidadRecibida());
				BigDecimal pv =  detalle.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(detalle.getCantidadRecibida());
				BigDecimal subtotal = pv.multiply(cantidad);
				
				psDet.setBigDecimal(2, subtotal);
				psDet.setInt(3, detalle.getCodigo());
				
				psDet.executeUpdate();
				
				psDet2 = con
						.prepareStatement("INSERT INTO historial_stock (fecha, referencia, producto, cantidad) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

				psDet2.setTimestamp(1, fechaHoraActual);
				psDet2.setString(2, "Pedido "+pedido.getCodigo());
				psDet2.setInt(3, detalle.getProducto().getCodigo());
				psDet2.setInt(4, detalle.getCantidadRecibida());
				
				psDet2.executeUpdate();
			}
			
			System.out.println("CodigoGenerado: "+codigoCabecera);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el pedido. Detalle: "+e.getMessage());
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
