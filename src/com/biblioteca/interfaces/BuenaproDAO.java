package com.biblioteca.interfaces;

import java.util.ArrayList;

import com.biblioteca.entidad.Buenapro;;

public interface BuenaproDAO {

	public int save(Buenapro bean);
    public int update(Buenapro bean);
    public int delete(int cod);
    public ArrayList<Buenapro> listAll();
    public ArrayList<Buenapro>listartodito();
    public ArrayList<Buenapro>listarxcritcontrato(String cod);
}
