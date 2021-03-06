package sistGestionLogistica.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;

import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.StockInsumo;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.servicios.ServicePlanta;

public class StockInsumoDaoMysql implements StockInsumoDao {
	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public Boolean save(StockInsumo s) throws SQLException {
		String insertStock = "INSERT INTO stockinsumo (planta,insumo,cantidad,puntoReposicion) VALUES (?,?,?,?)";

		try {

			conn = DB.getConexion();
			System.out.println("EJECUTA INSERT stock");
			pstmt = conn.prepareStatement(insertStock);

			pstmt.setInt(1, s.getPlanta().getId());
			pstmt.setInt(2, s.getInsumo().getIdInsumo());
			pstmt.setInt(3, s.getCantidad());
			pstmt.setInt(4, s.getPuntoReposicion());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("el problema 1");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				System.out.println("el problema 2");
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public Boolean update(StockInsumo stock) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeStock(Integer idPlanta, Integer idInsumo) throws SQLException {
		String buscar = "SELECT * FROM stockinsumo WHERE planta = ? AND insumo = ?";
		Boolean res = true;
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1, idPlanta);
			pstmt.setInt(2, idInsumo);
			rs = pstmt.executeQuery();
			res = rs.next();

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

		return res;
	}

	@Override
	public Integer stockTotal(Integer idInsumo) {
		String buscar = "SELECT SUM(cantidad) FROM stockinsumo WHERE insumo=?";
		Integer resultado = 0;

		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1, idInsumo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultado = rs.getInt(1);
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

		return resultado;
	}

	@Override
	public List<StockInsumo> faltantes() {
		String select_faltantes = "SELECT * FROM stockinsumo WHERE cantidad < puntoReposicion";
		List<StockInsumo> lista = new ArrayList<StockInsumo>();
		ServiceInsumo si = new ServiceInsumo();
		ServicePlanta sp = new ServicePlanta();
		conn = DB.getConexion();

		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(select_faltantes);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer idStockInsumo = rs.getInt("idStockInsumo");
				Integer cantidad = rs.getInt("cantidad");
				Integer puntoReposicion = rs.getInt("puntoReposicion");

				Planta planta = sp.buscarPorId(rs.getInt("planta"));

				Insumo insumo = si.buscarPorId(rs.getInt("insumo"));

				// creamos el stockinsumo y lo agregamos a la lista
				lista.add(new StockInsumo(idStockInsumo, planta, insumo, cantidad, puntoReposicion));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Resultado " + lista);
		return lista;
	}

	public List<StockInsumo> buscarStockPlanta(Integer id) {
		ArrayList<StockInsumo> lista = new ArrayList<StockInsumo>();
		String buscarTodos = "SELECT * FROM stockinsumo where planta =?";
		PlantaDao pd = new PlantaDaoMysql();
		InsumoDao idao = new InsumoDaoMysql();
		Planta p = new Planta();
		Insumo i = null;

		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscarTodos);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				p = pd.buscarPorId(rs.getInt("planta"));
				i = idao.buscarPorId(rs.getInt("insumo"));

				StockInsumo s = new StockInsumo(rs.getInt("idStockInsumo"), p, i, rs.getInt("cantidad"),
						rs.getInt("puntoReposicion"));

				lista.add(s);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Resultado " + lista);
		return lista;

	}

	@Override
	public List<Planta> plantasConInsumo(Integer idInsumo, Integer cantidad) {
		ArrayList<Planta> lista = new ArrayList<Planta>();
		String buscarPlantas = "SELECT planta FROM stockinsumo where insumo = ? AND cantidad > ?";
		PlantaDao pd = new PlantaDaoMysql();
		Planta p = new Planta();

		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscarPlantas);
			pstmt.setInt(1, idInsumo);
			pstmt.setInt(2, cantidad);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				p = pd.buscarPorId(rs.getInt("planta"));

				lista.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
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

	public void actualizarStock(Integer idPlanta, Integer cantidad) {
		String actualizar = "UPDATE stockInsumo SET cantidad= ? WHERE Planta = ?";
		try {

			conn = DB.getConexion();
			System.out.println("EJECUTA UPDATE PLANTA");
			pstmt = conn.prepareStatement(actualizar);
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, idPlanta);

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

	}

	@Override
	public Integer buscarCantidad(Integer idPlanta) {
		String buscar = "SELECT cantidad FROM stockinsumo WHERE planta=?";
		Integer cantidad = 0;
		try {

			conn = DB.getConexion();
			System.out.println("buscando cantidad");
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1, idPlanta);
			rs = pstmt.executeQuery();
			rs.next();
			cantidad = rs.getInt("cantidad");

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
		return cantidad;
	}
}
