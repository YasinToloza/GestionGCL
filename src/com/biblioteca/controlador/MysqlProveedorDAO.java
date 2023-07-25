package com.biblioteca.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.biblioteca.entidad.Provedor;
import com.biblioteca.interfaces.ProveedorDAO;
import com.biblioteca.utils.MySqlConexion;


public class MysqlProveedorDAO implements ProveedorDAO {
	
	@Override
	public ArrayList<Provedor> listAll() {
		ArrayList<Provedor> lista=new ArrayList<Provedor>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select p.cod_prov, p.raz_soc, p.ruc,p.dir_prov, p.fono, p.pre_obj,p.cod_obj, e.des_estado_prov "
					+"from  tb_proveedor p join tb_estado_proveedor e on  p.cod_estado_prov = e.cod_estado_prov  ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				Provedor bean=new Provedor();
				bean.setCodPro(rs.getInt(1));
				bean.setRazon(rs.getString(2));
				bean.setRuc(rs.getString(3));
				bean.setDirec(rs.getString(4));
				bean.setCelular(rs.getInt(5));
				bean.setPrecio(rs.getDouble(6));
				bean.setCodObjeto(rs.getInt(7));
				bean.setMonEstado(rs.getString(8));
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
	public int registrar(Provedor bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="insert into tb_Proveedor values (null,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,bean.getRazon());
			pstm.setString(2,bean.getRuc());
			pstm.setString(3, bean.getDirec());
			pstm.setInt(4, bean.getCelular());
			pstm.setDouble(5, bean.getPrecio());
			pstm.setInt(6, bean.getCodObjeto());
			pstm.setInt(7, bean.getEstado());
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
	public int update(Provedor bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_Proveedor set raz_soc=?, ruc=?,dir_prov=?,fono=?,pre_obj=?  where cod_prov=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, bean.getRazon());
			pstm.setString(2, bean.getRuc());
			pstm.setString(3, bean.getDirec());
			pstm.setInt(4, bean.getCelular());
			pstm.setDouble(5, bean.getPrecio());
			pstm.setInt(6, bean.getCodPro());
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
	public ArrayList<Provedor> listarProvedorxCod(String cod) {
	
		ArrayList<Provedor> lista=new ArrayList<Provedor>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="call sp_llamar_proveedor(?);";
			
			cstm=cn.prepareCall(sql);
			cstm.setString(1, cod);
			rs=cstm.executeQuery();
			while(rs.next()){
				Provedor bean=new Provedor();
				bean.setCodPro(rs.getInt(1));
				bean.setRazon(rs.getString(2));
				bean.setRuc(rs.getString(3));
				bean.setDirec(rs.getString(4));
				bean.setCelular(rs.getInt(5));
				bean.setPrecio(rs.getDouble(6));
				lista.add(bean);
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
	public int deleate(int cod) {
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
	public ArrayList<Provedor> listartodo() {
		ArrayList<Provedor> lista=new ArrayList<Provedor>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlConexion.getConexion();
			String sql=" select p.cod_prov, p.raz_soc, p.ruc,p.dir_prov, p.fono, p.pre_obj,p.cod_obj, e.des_estado_prov from  tb_proveedor p join tb_estado_proveedor e on  p.cod_estado_prov = e.cod_estado_prov left join tb_expediente_buenapro b on p.cod_prov=b.cod_prov where b.cod_prov is null";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				Provedor bean=new Provedor();
				bean.setCodPro(rs.getInt(1));
				bean.setRazon(rs.getString(2));
				bean.setRuc(rs.getString(3));
				bean.setDirec(rs.getString(4));
				bean.setCelular(rs.getInt(5));
				bean.setPrecio(rs.getDouble(6));
				bean.setCodObjeto(rs.getInt(7));
				bean.setMonEstado(rs.getString(8));
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
	

}
