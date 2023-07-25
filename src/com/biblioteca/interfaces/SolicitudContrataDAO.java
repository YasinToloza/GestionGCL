package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.SolicitudContrata;

public interface SolicitudContrataDAO {

	public int save(SolicitudContrata bean);
	public int update(SolicitudContrata bean);
	public int delete(int cod);
	public ArrayList<SolicitudContrata> listAll();
	public ArrayList<SolicitudContrata>listartodo();
	public ArrayList<SolicitudContrata>listarxcodsol(String cod);
	public ArrayList<SolicitudContrata>listaparaaprobar();
	public ArrayList<SolicitudContrata>listarxestado1(String cod);
	public int actualizarestado(SolicitudContrata bean);
	public ArrayList<SolicitudContrata>listarxcriterio(int tipo,String filtro);
	public ArrayList<SolicitudContrata>listarparacontrato1();
	public ArrayList<SolicitudContrata>listarporcritparacontrato(String cod);
	public ArrayList<SolicitudContrata>listartodobuenapro();
	public ArrayList<SolicitudContrata>listarxcodsolbuenapro(String cod);
}
