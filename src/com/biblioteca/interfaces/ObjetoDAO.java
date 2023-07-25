package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.Objeto;
import com.biblioteca.entidad.SolicitudContrata;

public interface ObjetoDAO {
	public int agregar(Objeto agre);
	public int actualiza(Objeto actu);
	public int elimina(int eli);
	public ArrayList<Objeto> listar();
	public ArrayList<Objeto>listartodo();
	public ArrayList<Objeto>listarxcod(String cod);
}
