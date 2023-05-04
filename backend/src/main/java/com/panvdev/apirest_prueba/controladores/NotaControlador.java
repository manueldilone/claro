package com.panvdev.apirest_prueba.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panvdev.apirest_prueba.modelos.Notas;
import com.panvdev.apirest_prueba.servicios.NotaServicioImpl;
import com.panvdev.apirest_prueba.servicios.creararchivo;

@RestController
@RequestMapping("/api/claro")
@CrossOrigin("*")
public class NotaControlador {
	
	@Autowired
	NotaServicioImpl notaservicio;
	
	
	@GetMapping("/notas")
	public List<Notas> obtenerClientes(){
		return notaservicio.obtenerTodo();
	}
	
	
	@PostMapping("/guardar")
	public ResponseEntity<Notas> guardarCliente(@RequestBody Notas notas) throws IOException{
		Notas nueva_nota = notaservicio.guardar(notas);
		creararchivo crear = new creararchivo();
		if(notas!=null){
			crear.creararchivolocal(notas);
		}
		return new ResponseEntity<>(nueva_nota, HttpStatus.CREATED);
	}
	
	@GetMapping("/notas/{id}")
	public ResponseEntity<Notas> obtenerClienteId(@PathVariable long id){
		Notas notaPorId = notaservicio.obtenerPorId(id);
		return ResponseEntity.ok(notaPorId);
	}
	
	@PutMapping("/notas/{id}")
	public ResponseEntity<Notas> actualizarCliente(@PathVariable long id, @RequestBody Notas notas){
		Notas notaPorId = notaservicio.obtenerPorId(id);
		notaPorId.setNombre(notas.getNombre());
		notaPorId.setCampo1(notas.getCampo1());
		notaPorId.setCampo2(notas.getCampo2());
		
		Notas cliente_actualizado = notaservicio.guardar(notaPorId);
		return new ResponseEntity<>(cliente_actualizado, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HashMap<String,Boolean>> eliminarCliente(@PathVariable long id){
		this.notaservicio.eliminar(id);
		
		HashMap<String, Boolean> estadoClienteEliminado = new HashMap<>();
		estadoClienteEliminado.put("eliminado", true);
		return ResponseEntity.ok(estadoClienteEliminado);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
