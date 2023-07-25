package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.Buenapro;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.BuenaproDAO;
import com.biblioteca.utils.MySqlConexion;

public class MysqlBuenaproDAO implements BuenaproDAO {

	@Override
	public int save(Buenapro bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="insert into tb_expediente_buenapro values (null,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,bean.getAsunto());
			pstm.setString(2,bean.getReferencia());
			pstm.setInt(3,bean.getCodsol());
			pstm.setInt(4,bean.getCodprov());
			pstm.setInt(5,bean.getCodemp());
			pstm.setString(6,bean.getFecha());
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
	public int update(Buenapro bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_expediente_buenapro set Asunto=?,referencia=?,cod_sol_con=?,cod_prov=?,cod_emp=?,fecha_buneapro=? where cod_expbp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,bean.getAsunto());
			pstm.setString(2,bean.getReferencia());
			pstm.setInt(3,bean.getCodsol());
			pstm.setInt(4,bean.getCodprov());
			pstm.setInt(5,bean.getCodemp());
			pstm.setString(6,bean.getFecha());
			pstm.setInt(7,bean.getCodbuenapro());
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
			String sql="delete from tb_expediente_buenapro where cod_expbp=?";
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
	public ArrayList<Buenapro> listAll() {
		ArrayList<Buenapro> lista=new ArrayList<Buenapro>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {	
			cn=MySqlConexion.getConexion();
			String sql="select * from tb_expediente_buenapro";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				Buenapro bue=new Buenapro();
				bue.setCodbuenapro(rs.getInt(1));
				bue.setAsunto(rs.getString(2));
				bue.setReferencia(rs.getString(3));
				bue.setCodsol(rs.getInt(4));
				bue.setCodprov(rs.getInt(5));
				bue.setCodemp(rs.getInt(6));
				bue.setFecha(rs.getString(7));
				lista.add(bue);
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
	public ArrayList<Buenapro> listartodito() {
		ArrayList<Buenapro> data=new ArrayList<Buenapro>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select b.cod_expbp,b.Asunto,b.referencia,b.cod_sol_con,b.cod_prov,b.cod_emp,b.fecha_buneapro "
					+ "from tb_expediente_buenapro b left join tb_contrato c on c.cod_expbp=b.cod_expbp where c.cod_contrato is null";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				Buenapro bue=new Buenapro();
				bue.setCodbuenapro(rs.getInt(1));
				bue.setAsunto(rs.getString(2));
				bue.setReferencia(rs.getString(3));
				bue.setCodsol(rs.getInt(4));
				bue.setCodprov(rs.getInt(5));
				bue.setCodemp(rs.getInt(6));
				bue.setFecha(rs.getString(7));
            
				data.add(bue);
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
	public ArrayList<Buenapro> listarxcritcontrato(String cod) {
		ArrayList<Buenapro> lista=new ArrayList<Buenapro>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call llamarBPparaContrato(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				Buenapro bue=new Buenapro();
				bue.setCodbuenapro(rs.getInt(1));
				bue.setAsunto(rs.getString(2));
				bue.setReferencia(rs.getString(3));
				bue.setCodsol(rs.getInt(4));
				bue.setCodprov(rs.getInt(5));
				bue.setCodemp(rs.getInt(6));
				bue.setFecha(rs.getString(7));
				
				lista.add(bue);
				
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
