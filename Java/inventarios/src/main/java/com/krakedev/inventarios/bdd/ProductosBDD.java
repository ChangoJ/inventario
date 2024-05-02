package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductosBDD {

	public ArrayList<Producto> buscar(int codigo) throws Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto producto = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select prod.codigo_producto, prod.nombre as nombre_producto, udm.codigo_udm as nombre_udm, udm.descripcion as descripcion_udm,  CAST(prod.precio_venta AS decimal(6,2)) AS precio_venta, prod.tiene_iva,  CAST(prod.coste AS  decimal(5,4)) AS coste, prod.categoria, cat.nombre as nombre_categoria, stock "
							+ "from productos prod, unidades_medida udm, categorias cat "
							+ "where prod.UDM = udm.codigo_udm and prod.categoria = cat.codigo_cat and prod.codigo_producto = ?");

			ps.setInt(1, codigo);

			rs = ps.executeQuery();

			while (rs.next()) {
				int codigoProducto = rs.getInt("codigo_producto");
				String nombreProducto = rs.getString("nombre_producto");
				String nombreUnidadMedida = rs.getString("nombre_udm");
				String descripcionUnidadMedida = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();
				udm.setNombre(nombreUnidadMedida);
				udm.setDescripcion(descripcionUnidadMedida);

				Categoria categoria = new Categoria();
				categoria.setCodigo(codigoCategoria);
				categoria.setNombre(nombreCategoria);

				producto = new Producto();
				producto.setCodigo(codigoProducto);
				producto.setNombre(nombreProducto);
				producto.setUnidadDeMedida(udm);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);

				productos.add(producto);
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

		return productos;

	}
	
	public void crear(Producto producto) throws KrakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO productos (nombre, UDM, precio_venta, tiene_iva, coste, categoria, stock) VALUES (?,?,?,?,?,?,?)");

			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUnidadDeMedida().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.getTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getStock());
			

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el producto. Detalle: "+e.getMessage());
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
	
	public void actualizar(Producto producto) throws KrakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("UPDATE productos SET nombre=?, UDM =?, precio_venta = ?,tiene_iva = ?, coste = ?, categoria = ? WHERE codigo_producto = ?");

			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUnidadDeMedida().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.getTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getCodigo());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar el producto. Detalle: "+e.getMessage());
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
	
	
	
	public void crearCategoria(Categoria categoria) throws KrakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO categorias(nombre, categoria_padre) VALUES (?,?)");

			ps.setString(1, categoria.getNombre());
			if(categoria.getCategoriaPadre() != null) {

				ps.setInt(2,categoria.getCategoriaPadre().getCodigo());
			}else {
				ps.setNull(2, 0);
			}
			

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar la categoria. Detalle: "+e.getMessage());
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
	
	public void actualizarCategoria(Categoria categoria) throws KrakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("UPDATE categorias SET nombre = ?, categoria_padre = ? WHERE codigo_cat = ?");

			ps.setString(1, categoria.getNombre());
			if(categoria.getCategoriaPadre() != null) {

				ps.setInt(2,categoria.getCategoriaPadre().getCodigo());
			}else {
				ps.setNull(2, 0);
			}
			ps.setInt(3,categoria.getCodigo());
			

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualiar la categoria. Detalle: "+e.getMessage());
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
	
	private Categoria buscarCategoriaPorCodigo(int codigo) throws Exception {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Categoria categoria = null;
	    try {
	        con = ConexionBDD.obtenerConexion();
	        ps = con.prepareStatement("SELECT * FROM categorias WHERE codigo_cat = ?");
	        ps.setInt(1, codigo);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int codigoCategoria = rs.getInt("codigo_cat");
	            String nombreCategoria = rs.getString("nombre");
	            Categoria categoriaPadre = new Categoria(codigoCategoria, nombreCategoria, null);
	            return categoriaPadre;
	        }
	        return null; 
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
	}
	
	public ArrayList<Categoria> recuperarCategorias() throws Exception {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria categoria = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("SELECT * from categorias");
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int codigo = rs.getInt("codigo_cat");
				String nombre = rs.getString("nombre");
				int codigoPadre = rs.getInt("categoria_padre");
				System.out.println(codigoPadre);
				
				Categoria categoriaPadre = buscarCategoriaPorCodigo(codigoPadre);
				categoria = new Categoria(codigo,nombre, categoriaPadre);
				categorias.add(categoria);
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

		return categorias;

	}
	
	
}
