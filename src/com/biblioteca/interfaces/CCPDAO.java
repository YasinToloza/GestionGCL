package com.biblioteca.interfaces;

import java.util.ArrayList;


import com.biblioteca.entidad.CCP;

public interface CCPDAO {
	public int save(CCP bean);
    public int update(CCP bean);
    public int delete(int cod);
    public ArrayList<CCP> listAll();
    public ArrayList<CCP> listarxcodsol(String cod);
    public ArrayList<CCP>listartodo();
}
