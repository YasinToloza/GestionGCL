package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.CCP;
import com.biblioteca.interfaces.CCPDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlCCPDAO implements CCPDAO{

	@Override
	public int save(CCP bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="insert into tb_ccp values (null,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,bean.getDesccp());
			pstm.setDouble(2, bean.getMontoccp());
			pstm.setInt(3, bean.getEmpleado());
			pstm.setInt(4, bean.getEstado());
			pstm.setString(5, bean.getFecha());
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
	public int update(CCP bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_ccp set desc_ccp=?,monto_ccp=?,cod_emp=?,fecha_ccp=? where cod_ccp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,bean.getDesccp());
			pstm.setDouble(2, bean.getMontoccp());
			pstm.setInt(3, bean.getEmpleado());
			pstm.setString(4, bean.getFecha());
			pstm.setInt(5, bean.getCodigoccp());
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
			String sql="delete from tb_ccp where cod_ccp=?";
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
	public ArrayList<CCP> listAll() {
		ArrayList<CCP> lista=new ArrayList<CCP>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select  c.cod_ccp, c.desc_ccp , c.monto_ccp ,c.cod_emp, e.des_est, c.fecha_ccp from tb_ccp c join tb_estado e on  c.cod_est = e.cod_est";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				CCP bean=new CCP();
				bean.setCodigoccp(rs.getInt(1));
				bean.setDesccp(rs.getString(2));
				bean.setMontoccp(rs.getDouble(3));
				bean.setEmpleado(rs.getInt(4));
				bean.setNombreEstado(rs.getString(5));
				bean.setFecha(rs.getString(6));
				lista.add(bean);
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
	public ArrayList<CCP> listarxcodsol(String cod) {
		ArrayList<CCP> lista=new ArrayList<CCP>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call llamar_ccp(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				CCP ccp=new CCP();
				ccp.setCodigoccp(rs.getInt(1));
				ccp.setDesccp(rs.getString(2));
				ccp.setMontoccp(rs.getDouble(3));
				ccp.setEmpleado(rs.getInt(4));
				ccp.setEstado(rs.getInt(5));
				ccp.setFecha(rs.getString(6));
				
				lista.add(ccp);
				
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
	public ArrayList<CCP> listartodo() {
		ArrayList<CCP> data=new ArrayList<CCP>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select c.cod_ccp,c.desc_ccp,c.monto_ccp,c.cod_emp,c.cod_est,c.fecha_ccp from tb_ccp c left join"
					+ " tb_sol_contrata x on c.cod_ccp=x.cod_ccp where c.cod_est=1 and x.cod_sol_con is null;";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				CCP ccp=new CCP();
				ccp.setCodigoccp(rs.getInt(1));
				ccp.setDesccp(rs.getString(2));
				ccp.setMontoccp(rs.getDouble(3));
				ccp.setEmpleado(rs.getInt(4));
				ccp.setEstado(rs.getInt(5));
				ccp.setFecha(rs.getString(6));
				
				data.add(ccp);
            
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

}
