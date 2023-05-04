package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import com.panvdev.apirest_prueba.modelos.Notas;

public interface INotaServicio{

	public List<Notas> obtenerTodo();
	
	public Notas guardar(Notas cliente);
	
	public Notas obtenerPorId(long id);
	
	public void eliminar(long id);
}
