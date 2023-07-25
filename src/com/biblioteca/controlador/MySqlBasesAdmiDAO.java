package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.biblioteca.entidad.BasesAdministrativas;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.BasesAdministrativasDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlBasesAdmiDAO implements BasesAdministrativasDAO{

	@Override
	public int save(BasesAdministrativas bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			cn=MySqlConexion.getConexion();
			
			String sql="insert into tb_bases_admin values (null,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getDes_bases());
			pstm.setString(2, bean.getPlazo());
			pstm.setInt(3, bean.getCodEmp());
			pstm.setString(4, bean.getFecha());
			pstm.setInt(5, bean.getEstado());
			pstm.setInt(6, bean.getCod_sol());
			
			
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
	public int update(BasesAdministrativas bean) {
		int salida=-1;
		Connection cn=null;
		CallableStatement cstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call update_bases(?,?,?,?,?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, bean.getDes_bases());
			cstm.setString(2, bean.getPlazo());
			cstm.setInt(3, bean.getCodEmp());
			cstm.setString(4, bean.getFecha());
			cstm.setInt(5, bean.getCod_sol());
			cstm.setInt(6, bean.getCodigoBases());
			salida=cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(cstm!=null) cstm.close();
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
			String sql="delete from tb_bases_admin where cod_bases=?";
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
	public ArrayList<BasesAdministrativas> listAll() {
		ArrayList<BasesAdministrativas> data=new  ArrayList<BasesAdministrativas>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call listar_basesadmin()";
			cstm=cn.prepareCall(sql);
			rs=cstm.executeQuery();
			while(rs.next()) {
				BasesAdministrativas bean=new BasesAdministrativas();
				bean.setCodigoBases(rs.getInt(1));
				bean.setDes_bases(rs.getString(2));
				bean.setPlazo(rs.getString(3));
				bean.setCodEmp(rs.getInt(4));
				bean.setFecha(rs.getString(5));
				bean.setNombrestado(rs.getString(6));
				bean.setCod_sol(rs.getInt(7));
				data.add(bean);
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
		return data;
	}

	@Override
	public ArrayList<BasesAdministrativas> listarTodoXConsulta(){
		ArrayList<BasesAdministrativas> Ab=new ArrayList<BasesAdministrativas>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select BA.cod_bases, BA.Des_bases, BA.plazo, BA.cod_emp, BA.fecha_bases, E.des_est, BA.cod_sol_con\r\n"
					+ "					from tb_bases_admin BA join tb_estado E\r\n"
					+ "					on BA.cod_est = E.cod_est\r\n"
					+ "					where BA.cod_est=1";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				BasesAdministrativas bean=new BasesAdministrativas();
				bean.setCodigoBases(rs.getInt(1));
				bean.setDes_bases(rs.getString(2));
				bean.setPlazo(rs.getString(3));
				bean.setCodEmp(rs.getInt(4));
				bean.setFecha(rs.getString(5));
				bean.setNombrestado(rs.getString(6));
				bean.setCod_sol(rs.getInt(7));
				Ab.add(bean);
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
	public ArrayList<BasesAdministrativas> listaFiltrada(int Filtro) {
		ArrayList<BasesAdministrativas> listar=new ArrayList<BasesAdministrativas>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_Filtar_cod_frmConsultaDbaseAdmin(?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, Filtro);
			rs=cstm.executeQuery();
			while(rs.next()){
				BasesAdministrativas bean=new BasesAdministrativas();
				bean.setCodigoBases(rs.getInt(1));
				bean.setDes_bases(rs.getString(2));
				bean.setPlazo(rs.getString(3));
				bean.setCodEmp(rs.getInt(4));
				bean.setFecha(rs.getString(5));
				bean.setNombrestado(rs.getString(6));
				bean.setCod_sol(rs.getInt(7));
				listar.add(bean);
			}
		}
			catch (SQLException e) {
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
			return listar;
		}

	@Override
	public int ActualizarEstado(BasesAdministrativas bean) {
		int salida=-1;
		Connection cn=null;
		CallableStatement cstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_actualizaEstado_frmBaseAdmin(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, bean.getEstado());
			cstm.setInt(2, bean.getCod_sol());
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
	public ArrayList<BasesAdministrativas> listarSolicitudesConsulta(int tipo, String Estado) {
		ArrayList<BasesAdministrativas> lista=new ArrayList<BasesAdministrativas>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_consulta_solicitudesBasesAdmin(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, tipo);
			cstm.setString(2, Estado);
			rs=cstm.executeQuery();
			while(rs.next()) {
				BasesAdministrativas bean=new BasesAdministrativas();
				bean.setCodigoBases(rs.getInt(1));
				bean.setDes_bases(rs.getString(2));
				bean.setPlazo(rs.getString(3));
				bean.setCodEmp(rs.getInt(4));
				bean.setFecha(rs.getString(5));
				bean.setNombrestado(rs.getString(6));
				bean.setCod_sol(rs.getInt(7));
				lista.add(bean);
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

	@Override
	public ArrayList<BasesAdministrativas> listarparaContrato() {
		ArrayList<BasesAdministrativas> data=new ArrayList<BasesAdministrativas>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select b.cod_bases,b.des_bases,b.plazo,b.cod_emp,b.fecha_bases,b.cod_est,b.cod_sol_con "
					+ "from tb_bases_admin b left join tb_contrato c on c.cod_bases=b.cod_bases where b.cod_est=2";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				BasesAdministrativas bas=new BasesAdministrativas();
				
				bas.setCodigoBases(rs.getInt(1));
				bas.setDes_bases(rs.getString(2));
				bas.setPlazo(rs.getString(3));
				bas.setCodEmp(rs.getInt(4));
				bas.setFecha(rs.getString(5));
				bas.setEstado(rs.getInt(6));
				bas.setCod_sol(rs.getInt(7));
				
				
				data.add(bas);
            
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
	public ArrayList<BasesAdministrativas> listarparaContratoxcrit(String codigo) {
		ArrayList<BasesAdministrativas> lista=new ArrayList<BasesAdministrativas>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call llamarBAparacontrato(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, codigo);
			rs=cstm.executeQuery();
			while(rs.next()){
				BasesAdministrativas bas=new BasesAdministrativas();
				bas.setCodigoBases(rs.getInt(1));
				bas.setDes_bases(rs.getString(2));
				bas.setPlazo(rs.getString(3));
				bas.setCodEmp(rs.getInt(4));
				bas.setFecha(rs.getString(5));
				bas.setEstado(rs.getInt(6));
				bas.setCod_sol(rs.getInt(7));
				
				lista.add(bas);
				
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




