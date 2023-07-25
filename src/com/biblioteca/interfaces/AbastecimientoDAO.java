package com.biblioteca.interfaces;

import java.util.ArrayList;
import com.biblioteca.entidad.Abastecimiento;

public interface AbastecimientoDAO {
	public int save(Abastecimiento bean);
    public int update(Abastecimiento bean);
    public int delete(int cod);
    public ArrayList<Abastecimiento> listAll();
    public ArrayList<Abastecimiento> listarxcodsol(String cod);
    public ArrayList<Abastecimiento>listartodo();
    public ArrayList<Abastecimiento> listarForConsulta(int Filtro); 
    public ArrayList<Abastecimiento> listarTodoConsultaSol();
    public int actualizaEstado(Abastecimiento bean);
    public ArrayList<Abastecimiento> listarSolicitudesConsulta(int tipo,String Estado);
}
