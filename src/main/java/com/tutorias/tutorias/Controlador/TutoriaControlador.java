package com.tutorias.tutorias.Controlador;

import com.tutorias.tutorias.Entidad.Tutoria;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TutoriaControlador {

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        Tutoria tutoria = new Tutoria();
        model.addAttribute("tutoria", tutoria);
        return "formulario/formulario";
    }

    @PostMapping("/enviar")  // Ruta cambiada para evitar conflicto
    public String enviarFormulario(@Valid @ModelAttribute Tutoria tutoria, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "formulario/formulario";
        } else {
            return "formulario/registroExitoso";
        }
    }
}
