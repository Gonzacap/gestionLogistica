package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.servicios.ServiceInsumo;

public class ItemDetalleDaoMysql implements ItemDetalleDao {

	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public Boolean save(ItemDetalle item) throws SQLException {
		String insert = "INSERT INTO itemDetalle(numOrden,insumo,cantidad,precioItem) VALUES(?,?,?,?)";
		try {

			conn = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			pstmt = conn.prepareStatement(insert);

			pstmt.setInt(1, item.getNumOrden());
			pstmt.setInt(2, item.getInsumo().getIdInsumo());
			pstmt.setInt(3, item.getCantidad());
			pstmt.setDouble(4, item.getPrecio());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public Boolean update(ItemDetalle item) throws SQLException {
		String update="UPDATE envioDetalle SET insumo=?,cantidad=?, precioItem= ?  WHERE numOrden= ?";					
		try {

			conn = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			pstmt = conn.prepareStatement(update);

		
			pstmt.setInt(1, item.getInsumo().getIdInsumo());
			pstmt.setInt(2, item.getCantidad());
			pstmt.setDouble(3, item.getPrecio());
			pstmt.setInt(4, item.getNumOrden());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public List<ItemDetalle> buscarNumOrden(Integer numOrden) throws SQLException {
		String buscar = "SELECT * FROM itemdetalle WHERE numOrden = ?";
		List<ItemDetalle> lista = new ArrayList<ItemDetalle>();
		
		ServiceInsumo si = new ServiceInsumo();
		
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1, numOrden);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemDetalle item = new ItemDetalle();
				item.setNumOrden(rs.getInt("numOrden"));
				item.setInsumo(si.buscarPorId(rs.getInt("insumo")));
				item.setCantidad(rs.getInt("cantidad"));
				item.setPrecio(rs.getDouble("precioItem"));
				lista.add(item);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return lista;
	}

	@Override
	public List<ItemDetalle> buscarTodos() throws SQLException {
		String buscar = "SELECT * FROM itemdetalle ";

		ServiceInsumo si = new ServiceInsumo();
		List<ItemDetalle> lista = new ArrayList<ItemDetalle>();
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemDetalle item = new ItemDetalle();
				item.setNumOrden(rs.getInt("numOrden"));
				item.setInsumo(si.buscarPorId(rs.getInt("insumo")));
				item.setCantidad(rs.getInt("cantidad"));
				item.setPrecio(rs.getDouble("precioItem"));
				lista.add(item);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return lista;
	}

}
