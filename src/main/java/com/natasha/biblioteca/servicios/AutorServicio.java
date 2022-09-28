/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natasha.biblioteca.servicios;

import com.natasha.biblioteca.entidades.Autor;
import com.natasha.biblioteca.excepciones.MiException;
import com.natasha.biblioteca.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Naniquev
 */
@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    
    @Transactional  //Colocamos esta anotación ya que el método genera modificaciones permanentes en la base de datos
    public void crearAutor(String nombre) throws MiException {

        validar(nombre, nombre);
        
        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }

    
    public List<Autor> listarAutores() {

        List<Autor> autores = autorRepositorio.findAll();

        return autores;

    }
    
    public void modificarAutor(String nombre, String id) throws MiException{
        
        validar(nombre, id);
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        }
        
    }
    
    private void validar(String nombre, String id)throws MiException {
        
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("El nombre no puede estar vacio o ser nulo");
        }
        
        if(id.isEmpty() || id == null){
            throw new MiException("El id no puede estar vacio o ser nulo");
        }
    }

}
