package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.entidad.BasesAdministrativas;
import com.biblioteca.entidad.Buenapro;
import com.biblioteca.entidad.CCP;
import com.biblioteca.entidad.Contrato;
import com.biblioteca.entidad.Provedor;
import com.biblioteca.entidad.SolicitudContrata;
import com.biblioteca.interfaces.ContratoDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlContratoDAO implements ContratoDAO {

	@Override
	public int save(Contrato bean, BasesAdministrativas b, CCP c, Provedor p, SolicitudContrata s, Abastecimiento a) {
		int salida=-1;
		Connection cn=null;
		CallableStatement cstmcontrato=null,cstmbases=null,cstmccp=null,cstmproveedor=null,cstmsolcon=null,cstmabaste=null;
		try {
			
			cn=MySqlConexion.getConexion();
			
			cn.setAutoCommit(false);
			
			String sqlcontrato="call crear_contrato(?,?,?,?,?,?)";
			
			cstmcontrato=cn.prepareCall(sqlcontrato);
			cstmcontrato.setInt(1, bean.getCod_contrato());
			cstmcontrato.setInt(2, bean.getCod_emp());
			cstmcontrato.setInt(3, bean.getCod_sol_con());
			cstmcontrato.setString(4, bean.getFechaContrato());
			cstmcontrato.setInt(5, bean.getCod_bases());
			cstmcontrato.setInt(6, bean.getCod_expBP());
			
			salida=cstmcontrato.executeUpdate();
			
			
			String sqlbases="call sp_actualizaEstado_frmBaseAdmin(?,?)";
			
			//bases update
			cstmbases=cn.prepareCall(sqlbases);
			
			cstmbases.setInt(2, b.getCodigoBases());
			cstmbases.setInt(1, b.getEstado());
				salida+=cstmbases.executeUpdate();
			
			//ccp update
				String sqlccp="call actualizar_estado_ccp(?,?)";
				cstmccp=cn.prepareCall(sqlccp);
			
				cstmccp.setInt(1, c.getCodigoccp());
				cstmccp.setInt(2, c.getEstado());
				
				salida+=cstmccp.executeUpdate();
				
			//update proveedor
				
				String sqlprov="call actualizarestado_proveedor(?,?)";
				cstmproveedor=cn.prepareCall(sqlprov);
				
				cstmproveedor.setInt(1, p.getCodPro());
				cstmproveedor.setInt(2, p.getEstado());
				
			salida+=cstmproveedor.executeUpdate();
			
			
			//update sol contrata
			
			String sqlSolCont="call actualizarestado_sol_contrata(?,?)";
			
			cstmsolcon=cn.prepareCall(sqlSolCont);
				
			cstmsolcon.setInt(1, s.getCodigo());
			cstmsolcon.setInt(2, s.getEstado());
			
			salida+=cstmsolcon.executeUpdate();
			
			//update solicitud de abastecimiento
			
			String sqlSolAbas="call sp_actualizaEstado_frmSolicitud(?,?)";
			
			cstmabaste=cn.prepareCall(sqlSolAbas);
			
			cstmabaste.setInt(2, a.getCodigoSol());
			cstmabaste.setInt(1, a.getCodigoEst());
			
			salida+=cstmabaste.executeUpdate();
				
			cn.commit();
		} catch (SQLException e) {
			try {
				
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				if(cstmcontrato!=null) cstmcontrato.close();
				if(cstmbases!=null) cstmbases.close();
				if(cstmccp!=null) cstmcontrato.close();
				if(cstmproveedor!=null) cstmbases.close();
				if(cstmsolcon!=null) cstmcontrato.close();
				if(cstmabaste!=null) cstmbases.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int numeroContrato() {
		int num=0;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select max(cod_contrato)+1 from tb_contrato";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next()){
				num=rs.getInt(1);
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
		return num;
	}
	}

	
	
	
	

