package com.panvdev.apirest_prueba.servicios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.springframework.web.bind.annotation.RequestBody;

import com.panvdev.apirest_prueba.modelos.Notas;

public class creararchivo  {
	NotaServicioImpl notaservicio;
  

    public void creararchivolocal(@RequestBody Notas notas) throws IOException{
        Notas notaPorId = new Notas();
        notaPorId.setNombre(notas.getNombre());
        notaPorId.setCampo1(notas.getCampo1());
        notaPorId.setCampo2(notas.getCampo2());
        
     //   Notas cliente_actualizado = notaservicio.guardar(notaPorId);
       String nombre = "D:\\"+notaPorId.getNombre().replace(':', '_')+".txt";
        FileWriter  archivo = new FileWriter(nombre);


        PrintWriter   escritor = new PrintWriter(archivo);
        try{
escritor.println(notaPorId.getCampo1());
escritor.print("/*"+notaPorId.getCampo2()+"*/");

        }
        catch (Exception e){
System.out.println("Error"+e.getMessage());
        }
        finally{

            archivo.close();

            String command = "java -jar ClaroTest.jar "+nombre; 
Process child = Runtime.getRuntime().exec(command); 

// Get output stream to write from it 
OutputStream out = child.getOutputStream(); 

 
out.flush(); 
 
out.close();



        }
    }
    
}
