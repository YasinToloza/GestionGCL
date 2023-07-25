package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.interfaces.AbastecimientoDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlAbastecimientoDAO implements AbastecimientoDAO{

	@Override
	public int save(Abastecimiento bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="insert into tb_Solicitud values(null,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getDescripSoli());
			pstm.setString(2, bean.getFecha());
			pstm.setInt(3, bean.getCodigoEst());
			pstm.setInt(4, bean.getCodigoTipSol());
			pstm.setInt(5, bean.getCodemp());
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
	public int update(Abastecimiento bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_Solicitud set des_sol=?, fec_sol=?,cod_t_sol=?,cod_emp=? where cod_sol=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getDescripSoli());
			pstm.setString(2, bean.getFecha());
			pstm.setInt(3, bean.getCodigoTipSol());
			pstm.setInt(4, bean.getCodemp());
			pstm.setInt(5, bean.getCodigoSol());
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
	public ArrayList<Abastecimiento> listAll() {
		ArrayList<Abastecimiento> Ab=new ArrayList<Abastecimiento>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="Select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, x.cod_emp \r\n"
					+ "From tb_estado E join tb_solicitud S\r\n"
					+ "On E.cod_est = S.cod_est join tb_tipo_solicitud TS\r\n"
					+ "On S.cod_t_sol = TS.cod_t_sol join tb_empleado x on x.cod_emp=S.cod_emp";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
			Abastecimiento abas=new Abastecimiento();
            abas.setCodigoSol(rs.getInt(1));
            abas.setDescripSoli(rs.getString(2));
            abas.setFecha(rs.getString(3));
            abas.setNombreTipo(rs.getString(4));
            abas.setNombreSolicitud(rs.getString(5));
            abas.setCodemp(rs.getInt(6));
            Ab.add(abas);
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
		return Ab;
	}

	@Override
	public int delete(int cod) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from tb_Solicitud where cod_sol=?";
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
	public ArrayList<Abastecimiento> listarxcodsol(String cod) {
		ArrayList<Abastecimiento> lista=new ArrayList<Abastecimiento>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_llamar_sol_abastecimiento(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				Abastecimiento abas=new Abastecimiento();
	            abas.setCodigoSol(rs.getInt(1));
	            abas.setDescripSoli(rs.getString(2));
	            abas.setFecha(rs.getString(3));
	            abas.setCodigoEst(rs.getInt(4));
	            abas.setCodigoTipSol(rs.getInt(5));
	            abas.setCodemp(rs.getInt(6));
				lista.add(abas);
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
	public ArrayList<Abastecimiento> listartodo() {
		ArrayList<Abastecimiento> Ab=new ArrayList<Abastecimiento>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select s.cod_sol,s.des_sol,s.fec_sol,s.cod_est,s.cod_t_sol,s.cod_emp from tb_solicitud s left join"+
			" tb_sol_contrata c on s.cod_sol=c.cod_sol where c.cod_sol_con is null and s.cod_est=2";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
			Abastecimiento abas=new Abastecimiento();
            abas.setCodigoSol(rs.getInt(1));
            abas.setDescripSoli(rs.getString(2));
            abas.setFecha(rs.getString(3));
            abas.setCodigoEst(rs.getInt(4));
            abas.setCodigoTipSol(rs.getInt(5));	
            abas.setCodemp(rs.getInt(6));
            Ab.add(abas);
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
		return Ab;
	}

	@Override
	public ArrayList<Abastecimiento> listarForConsulta(int Filtro) {
		ArrayList<Abastecimiento> listar=new ArrayList<Abastecimiento>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_listar_solicitudes_frmSolicitud(?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, Filtro);
			rs=cstm.executeQuery();
			while(rs.next()) {
			Abastecimiento abas=new Abastecimiento();
			abas.setCodigoSol(rs.getInt(1));
			abas.setDescripSoli(rs.getString(2));
			abas.setFecha(rs.getString(3));
			abas.setNombreTipo(rs.getString(4));
			abas.setNombreSolicitud(rs.getString(5));
			abas.setCodemp(rs.getInt(6));
			listar.add(abas);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs!=null) rs.close();
				if (cstm!=null) cstm.close();
				if (cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return listar;
	 
}

	@Override
	public ArrayList<Abastecimiento> listarTodoConsultaSol() {
		ArrayList<Abastecimiento> Ab=new ArrayList<Abastecimiento>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select s.cod_sol,s.des_sol,s.fec_sol,E.des_est,TS.tip_sol,s.cod_emp\r\n"
					+ "					from tb_estado E join tb_solicitud s \r\n"
					+ "					on E.cod_est = s.cod_est join tb_tipo_solicitud TS\r\n"
					+ "                    on S.cod_t_sol = TS.cod_t_sol\r\n"
					+ "					where s.cod_est=1";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
			Abastecimiento abas=new Abastecimiento();
            abas.setCodigoSol(rs.getInt(1));
            abas.setDescripSoli(rs.getString(2));
            abas.setFecha(rs.getString(3));
            abas.setNombreTipo(rs.getString(4));
            abas.setNombreSolicitud(rs.getString(5));
            abas.setCodemp(rs.getInt(6));
            Ab.add(abas);
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
		return Ab;
	}

	@Override
	public int actualizaEstado(Abastecimiento bean) {
		int salida=-1;
		Connection cn=null;
		CallableStatement cstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_actualizaEstado_frmSolicitud(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, bean.getCodigoEst());
			cstm.setInt(2, bean.getCodigoSol());
			salida=cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cstm!=null) cstm.close();
				if (cn !=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return salida;
	}

	@Override
	public ArrayList<Abastecimiento> listarSolicitudesConsulta(int tipo, String Estado) {
		ArrayList<Abastecimiento> lista=new ArrayList<Abastecimiento>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_consulta_solicitudesAbas(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, tipo);
			cstm.setString(2, Estado);
			rs=cstm.executeQuery();
			while(rs.next()) {
				Abastecimiento abas=new Abastecimiento();
	            abas.setCodigoSol(rs.getInt(1));
	            abas.setDescripSoli(rs.getString(2));
	            abas.setFecha(rs.getString(3));
	            abas.setNombreTipo(rs.getString(4));
	            abas.setNombreSolicitud(rs.getString(5));
	            abas.setCodemp(rs.getInt(6));
	            lista.add(abas);
			}
		} catch (Exception e) {
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
