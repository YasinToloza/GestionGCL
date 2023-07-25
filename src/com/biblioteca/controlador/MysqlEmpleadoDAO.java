package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.entidad.empleado;
import com.biblioteca.interfaces.EmpleadoDAO;
import com.biblioteca.utils.MySqlConexion;


public class MysqlEmpleadoDAO implements EmpleadoDAO{

	@Override
	public empleado iniciosesion(String login, String clave) {
		
		empleado bean=null;
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call inicio_sesion(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1, login);
			cstm.setString(2, clave);
			rs=cstm.executeQuery();
			if(rs.next()) {
				bean=new empleado();
				bean.setCodigo(rs.getInt(1));
				bean.setNombres(rs.getString(2));
				bean.setApellidos(rs.getString(3));
				bean.setCodcar(rs.getInt(4));
				bean.setCodDivi(rs.getInt(5));
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

		return bean;
		
	}

}
