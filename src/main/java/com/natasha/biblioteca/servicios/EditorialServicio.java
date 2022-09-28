
package com.natasha.biblioteca.servicios;

import com.natasha.biblioteca.entidades.Editorial;
import com.natasha.biblioteca.excepciones.MiException;
import com.natasha.biblioteca.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Naniquev
 */
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    
    @Transactional  //Colocamos esta anotación ya que el método genera modificaciones permanentes en la base de datos
    public void crearEditorial(String nombre) throws MiException {

        validar(nombre, nombre);
        
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        //Persistimos el objeto editorial
        editorialRepositorio.save(editorial);

    }

    
    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = editorialRepositorio.findAll();

        return editoriales;

    }
    
    public void modificarEditorial(String id, String nombre) throws MiException{
        
        validar(id, nombre);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }
        
    }
    
    private void validar(String id, String nombre) throws MiException{
        
        if(id.isEmpty() || id == null){
            throw new MiException("El id no puede estar vacio o ser nulo");
        }
        
        if(nombre.isEmpty() || nombre == null){
             throw new MiException("El nombre no puede estar vacio o ser nulo");
        }
    }

}
