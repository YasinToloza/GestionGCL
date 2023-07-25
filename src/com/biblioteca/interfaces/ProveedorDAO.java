package com.biblioteca.interfaces;

import com.biblioteca.entidad.Provedor;
import java.util.ArrayList;

public interface ProveedorDAO {
	public ArrayList<Provedor> listAll();
	public int registrar(Provedor bean);
	public int update(Provedor bean);
	public int deleate(int cod);
	public ArrayList<Provedor> listarProvedorxCod(String cod);
	public ArrayList<Provedor>listartodo();

}
