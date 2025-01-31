package com.tutorias.tutorias.Controlador;

import com.tutorias.tutorias.Entidad.Contacto;
import com.tutorias.tutorias.Entidad.Tutoria;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactoControlador {

    @GetMapping("/formulario2")
    public String mostrarFormulario2(Model model) {
        Contacto contacto = new Contacto();
        model.addAttribute("contacto", contacto);
        return "formulario/formulario2";
    }

    @PostMapping("/enviarr")  // Ruta cambiada para evitar conflicto
    public String enviarFormulario2(@Valid @ModelAttribute Contacto contacto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "formulario2/formulario";
        } else {
            return "formulario/registroExitoso2";
        }
    }
}
