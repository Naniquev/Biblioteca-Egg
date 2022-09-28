
package com.natasha.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")//localhost:8080/autor
public class AutorControlador {
    
    @GetMapping("/registrar")//localhost:8080/autor/registrar
    public String regitrar(){
        
        return "autor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        System.out.println("Nombre: " + nombre);
        
        return "autor_form.html";
    }
        
    }

