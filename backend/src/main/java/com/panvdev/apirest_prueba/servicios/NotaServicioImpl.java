package com.panvdev.apirest_prueba.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panvdev.apirest_prueba.modelos.Notas;
import com.panvdev.apirest_prueba.repositorios.INotasRepositorio;

@Service
public class NotaServicioImpl implements INotaServicio{
	
	@Autowired
	INotasRepositorio clienterepositorio;

	@Override
	public List<Notas> obtenerTodo() {
		return clienterepositorio.findAll();
	}

	@Override
	public Notas guardar(Notas cliente) {
		
		return clienterepositorio.save(cliente);
	}

	@Override
	public Notas obtenerPorId(long id) {
		return clienterepositorio.findById(id).orElse(null);
	}

	@Override
	public void eliminar(long id) {
		clienterepositorio.deleteById(id);
		
	}

}
