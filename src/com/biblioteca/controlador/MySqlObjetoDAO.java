package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biblioteca.entidad.Objeto;
import com.biblioteca.interfaces.ObjetoDAO;
import com.biblioteca.utils.MySqlConexion;

public class MySqlObjetoDAO implements ObjetoDAO{

	@Override
	public int agregar(Objeto agre) {
	    int salida=-1;
	    Connection cn=null;
	    CallableStatement cstm=null;
	    try {
	    	cn=MySqlConexion.getConexion();
	    	String sql="call sp_añadir_objeto_frmobjeto(?,?)";
	    	cstm=cn.prepareCall(sql);
	    	cstm.setString(1, agre.getNomObj());
	    	cstm.setString(2, agre.getDescObj());
			salida=cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    finally {
			try {
				if (cstm!=null) cstm.close();
				if (cn!=null) cn.close();			
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	    
		return salida;
	}

	@Override
	public int actualiza(Objeto actu) {
		int salida =-1;
		Connection cn=null;
		CallableStatement cstm=null;	
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_actualizar_objeto_frmobjeto (?,?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, actu.getCodigoObj());
			cstm.setString(2, actu.getNomObj());
   			cstm.setString(3, actu.getDescObj());
			salida=cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cn!=null) cn.close();
				if (cstm!=null) cstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int elimina(int eli) {
		int salida =-1;
		Connection cn=null;
		CallableStatement cstm=null;	
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_eliminar_objeto_frmobjeto(?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1, eli);
			salida=cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cn!=null) cn.close();
				if (cstm!=null) cstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public ArrayList<Objeto> listar() {
		ArrayList<Objeto> lista=new ArrayList<>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_mostrar_objeto_frmobjeto";
			cstm=cn.prepareCall(sql);
			rs=cstm.executeQuery();
			while(rs.next()) {
				Objeto bin=new Objeto();
				bin.setCodigoObj(rs.getInt(1));
				bin.setNomObj(rs.getString(2));
				bin.setDescObj(rs.getString(3));
				lista.add(bin);
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
		
		return lista;
	}

	@Override
	public ArrayList<Objeto> listartodo() {
		ArrayList<Objeto> listartodo=new ArrayList<>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql=" select o.cod_obj,o.nom_objeto,o.des_obj from tb_objeto o left join  tb_proveedor p on o.cod_obj=p.cod_obj where p.cod_obj is null; ";
			cstm=cn.prepareCall(sql);
			rs=cstm.executeQuery();
			while(rs.next()) {
				Objeto bin=new Objeto();
				bin.setCodigoObj(rs.getInt(1));
				bin.setNomObj(rs.getString(2));
				bin.setDescObj(rs.getString(3));
				listartodo.add(bin);
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
		return listartodo;
	}

	@Override
	public ArrayList<Objeto> listarxcod(String cod) {
		ArrayList<Objeto> listaxcod=new ArrayList<>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql=" call sp_llamar_objeto(?)";
			cstm=cn.prepareCall(sql);
			rs=cstm.executeQuery();
			while(rs.next()) {
				Objeto bin=new Objeto();
				bin.setCodigoObj(rs.getInt(1));
				bin.setNomObj(rs.getString(2));
				bin.setDescObj(rs.getString(3));
				listaxcod.add(bin);
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
		return listaxcod;
	}
	

}
