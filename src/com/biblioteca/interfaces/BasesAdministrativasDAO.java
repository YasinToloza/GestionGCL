package com.biblioteca.interfaces;

import java.util.ArrayList;
import com.biblioteca.entidad.BasesAdministrativas;


public interface BasesAdministrativasDAO {
	
	
	public int save(BasesAdministrativas bean);
    public int update(BasesAdministrativas bean);
    public int delete(int cod);
    public ArrayList<BasesAdministrativas> listAll();
    public ArrayList<BasesAdministrativas> listarTodoXConsulta();
    public ArrayList<BasesAdministrativas> listaFiltrada(int Filtro);
    public int ActualizarEstado (BasesAdministrativas bean);
    public ArrayList<BasesAdministrativas> listarSolicitudesConsulta(int tipo,String Estado);
    public ArrayList<BasesAdministrativas> listarparaContrato();
    public ArrayList<BasesAdministrativas> listarparaContratoxcrit(String codigo);
    
}
