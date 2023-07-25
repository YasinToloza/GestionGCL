package com.biblioteca.interfaces;

import com.biblioteca.entidad.Abastecimiento;
import com.biblioteca.entidad.BasesAdministrativas;
import com.biblioteca.entidad.Buenapro;
import com.biblioteca.entidad.CCP;
import com.biblioteca.entidad.Contrato;
import com.biblioteca.entidad.Provedor;
import com.biblioteca.entidad.SolicitudContrata;

public interface ContratoDAO {
	public int save(Contrato bean,BasesAdministrativas b,CCP c,Provedor p,SolicitudContrata s,Abastecimiento a);
	public int numeroContrato();
}
