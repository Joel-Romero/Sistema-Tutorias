package com.tutorias.tutorias.Controlador;

import com.tutorias.tutorias.Entidad.Tutor;
import com.tutorias.tutorias.Repositorio.TutorRepositorio;
import com.tutorias.tutorias.Servicio.TutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TutorControlador {

    @Autowired
    private TutorRepositorio tutorRepositorio;
    @Autowired
    private TutorServicio tutorServicio;

    // Página de formulario de tutoría
    @GetMapping("/formularioTutor")
    public String formularioTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "formulario/tutor/formularioTutor"; // Nombre de la vista HTML
    }

    // Enviar formulario de tutoría
    @PostMapping("/enviar")
    public String registrarTutor(@ModelAttribute Tutor tutor) {
        // Guardar la tutoría en la base de datos
        tutorRepositorio.save(tutor);


        return "redirect:/formulario/tutor/listaTutor"; // Ruta completa para listaTutoria
    }

    // Mostrar lista de tutorías
    @GetMapping("/formulario/tutor/listaTutor")
    public String listaTutor(Model model) {
        model.addAttribute("tutor", tutorRepositorio.findAll());
        return "formulario/tutor/listaTutor"; // Nombre de la vista de lista
    }

    // Ver detalles de tutoría
    @GetMapping("/detallesTutor/{id}")
    public String detallesTutor(@PathVariable Long id, Model model) {
        Tutor tutor= tutorRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tutor Id:" + id));
        model.addAttribute("tutor", tutor);
        return "formulario/tutor/detallesTutor"; // Vista de detalles de tutoría
    }

    // Método para actualizar tutoría (opcional)
    @GetMapping("/actualizarFormularioTutor/{id}")
    public String actualizarFormularioTutor(@PathVariable Long id, Model model) {
        Tutor tutor = tutorRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tutor Id:" + id));
        model.addAttribute("tutor", tutor);
        return "formulario/tutor/formularioTutor"; // Vista del formulario para editar tutoría
    }

    // Método para eliminar tutoría
    @GetMapping("/eliminarFormularioTutor/{id}")
    public String eliminarTutor(@PathVariable Long id) {
        Tutor tutor = tutorRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tutor Id:" + id));
        tutorRepositorio.delete(tutor);
        return "redirect:/formulario/tutor/listaTutor"; // Redirige después de eliminar
    }
    @GetMapping("/tutor/pdf")
    public ResponseEntity<byte[]> decargarpdf() throws Exception{
        byte[] pdf = tutorServicio.generarPDF();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "autores.pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
    @GetMapping("/tutor")
    public String listarTutor(Model model) {
        List<Tutor> listaTutor = tutorServicio.mostrarTutor();
        model.addAttribute("tutor", listaTutor);
        return "formulario/tutor/listaTutor";
    }


}





