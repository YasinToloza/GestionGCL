package com.biblioteca.interfaces;

import com.biblioteca.entidad.empleado;

public interface EmpleadoDAO {

	public empleado iniciosesion(String login,String clave);
}
