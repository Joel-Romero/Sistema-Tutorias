package com.tutorias.tutorias.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    @GetMapping("")
    public String home() {
        return "/formulario/index.html";
    }
}