package com.tutorias.tutorias.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NosotrosControlador {
    @GetMapping("/Nosotros")
    public String holaMundo(){
        return"formulario/nosotros.html";
    }
}