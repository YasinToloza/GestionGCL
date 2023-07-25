package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.SolicitudContrataDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlSolicitudContrata implements SolicitudContrataDAO{

	@Override
	public int save(SolicitudContrata bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			cn=MySqlConexion.getConexion();
			
			String sql="insert into tb_sol_contrata values (null,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,bean.getDescripcion());
			pstm.setInt(2, bean.getEstado());
			pstm.setInt(3, bean.getCod_sol());
			pstm.setInt(4, bean.getCod_ccp());
			pstm.setString(5, bean.getFecha());
			pstm.setInt(6, bean.getCodEmp());
			pstm.setString(7, bean.getTipocontrato());
			
			salida=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int update(SolicitudContrata bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_sol_contrata set des_sol_con=?,cod_sol=?,cod_ccp=?,fecha_sol_con=?,cod_emp=?,t_contrato=? where cod_sol_con=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setString(1, bean.getDescripcion());
			pstm.setInt(2, bean.getCod_sol());
			pstm.setInt(3, bean.getCod_ccp());
			pstm.setString(4, bean.getFecha());
			pstm.setInt(5, bean.getCodEmp());
			pstm.setString(6, bean.getTipocontrato());
			pstm.setInt(7, bean.getCodigo());
			salida=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int delete(int cod) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from tb_sol_contrata where cod_sol_con=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			salida=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return salida;
	}

	@Override
	public ArrayList<SolicitudContrata> listAll() {
		ArrayList<SolicitudContrata> lista=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			
			cn=MySqlConexion.getConexion();
			
			String sql="Select c.cod_sol_con,c.des_sol_con,e.des_est,c.cod_sol,c.cod_ccp,c.fecha_sol_con,c.cod_emp,c.t_contrato from tb_sol_contrata c join tb_estado e on c.cod_est=e.cod_est";
			
			pstm=cn.prepareStatement(sql);
			
			
			//5.ejecutar
			rs=pstm.executeQuery();
			
			while(rs.next()){
				//7.crear objeto de la clase "Libro"
				SolicitudContrata sol=new SolicitudContrata();
				//8.asignar valores a los atributos del objeto "lib" con la fila actual
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setNombreEstado(rs.getString(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				lista.add(sol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ArrayList<SolicitudContrata> listartodo() {
		ArrayList<SolicitudContrata> data=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato"
					+ "  from tb_sol_contrata b left join tb_bases_admin c on b.cod_sol_con=c.cod_sol_con where c.cod_bases is null and b.cod_est=2;";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				data.add(sol);
            
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public ArrayList<SolicitudContrata> listarxcodsol(String cod) {
		ArrayList<SolicitudContrata> lista=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call llamar_sol_contrata(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				lista.add(sol);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ArrayList<SolicitudContrata> listaparaaprobar() {
		ArrayList<SolicitudContrata> data=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato"
					+ "  from tb_sol_contrata b left join tb_bases_admin c on b.cod_sol_con=c.cod_sol_con where b.cod_est=1;";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				data.add(sol);
            
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public ArrayList<SolicitudContrata> listarxestado1(String cod) {
		ArrayList<SolicitudContrata> lista=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call llamar_sol_contrata_est_1(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				lista.add(sol);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public int actualizarestado(SolicitudContrata bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_sol_contrata set cod_est=? where cod_sol_con=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setInt(1, bean.getEstado());
			pstm.setInt(2, bean.getCodigo());
			salida=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public ArrayList<SolicitudContrata> listarxcriterio(int tipo, String filtro) {
		ArrayList<SolicitudContrata> lista=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			
			String sql="call reporte_sol_contrata(?,?)";
			
			cstm=cn.prepareCall(sql);
			
			cstm.setInt(1, tipo);
			cstm.setString(2, filtro);
			
			rs=cstm.executeQuery();
			
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setNombreEstado(rs.getString(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				lista.add(sol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ArrayList<SolicitudContrata> listartodobuenapro() {
		ArrayList<SolicitudContrata> data=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato from tb_sol_contrata b left join tb_expediente_buenapro e on b.cod_sol_con=e.cod_sol_con where e.cod_sol_con is null and b.cod_est=2";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				data.add(sol);
            
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public ArrayList<SolicitudContrata> listarxcodsolbuenapro(String cod) {
		ArrayList<SolicitudContrata> data=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_llamar_solbuenapro(?)";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				data.add(sol);
            
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
	
	
	@Override
	public ArrayList<SolicitudContrata> listarparacontrato1() {
		ArrayList<SolicitudContrata> data=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato "
					+ "from tb_sol_contrata b left join tb_contrato c on b.cod_sol_con=c.cod_sol_con where c.cod_contrato is null and b.cod_est=2";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				data.add(sol);
            
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public ArrayList<SolicitudContrata> listarporcritparacontrato(String cod) {
		ArrayList<SolicitudContrata> lista=new ArrayList<SolicitudContrata>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call llamarsolconparaContrato(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				SolicitudContrata sol=new SolicitudContrata();
				sol.setCodigo(rs.getInt(1));
				sol.setDescripcion(rs.getString(2));
				sol.setEstado(rs.getInt(3));
				sol.setCod_sol(rs.getInt(4));
				sol.setCod_ccp(rs.getInt(5));
				sol.setFecha(rs.getString(6));
				sol.setCodEmp(rs.getInt(7));
				sol.setTipocontrato(rs.getString(8));
				
				lista.add(sol);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	
	
	
	
	

}
