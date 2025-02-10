package com.tutorias.tutorias.Controlador;

import com.tutorias.tutorias.Entidad.Estudiante;
import com.tutorias.tutorias.Repositorio.EstudianteRepositorio;
import com.tutorias.tutorias.Servicio.EstudianteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EstudianteControlador {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Autowired
    private EstudianteServicio estudianteServicio;

    // Mostrar el formulario para registrar estudiante
    @GetMapping("/formulario/estudiante/formularioEstudiante")
    public String showFormularioEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());  // Crear un objeto Estudiante vacío para el formulario
        return "formulario/estudiante/formularioEstudiante";  // Nombre de la plantilla Thymeleaf
    }


    // Enviar formulario de tutoría
    @PostMapping("/enviarEstudiante")
    public String registrarEstudiante(@ModelAttribute Estudiante estudiante) {
        // Guardar la tutoría en la base de datos
        estudianteRepositorio.save(estudiante);


        return "redirect:/formulario/estudiante/listaEstudiante"; // Ruta completa para listaTutoria
    }

    // Mostrar lista de tutorías
    @GetMapping("/formulario/estudiante/listaEstudiante")
    public String listaEstudiante(Model model) {
        model.addAttribute("estudiante", estudianteRepositorio.findAll());
        return "formulario/estudiante/listaEstudiante"; // Nombre de la vista de lista
    }



    // Ver detalles de tutoría
    @GetMapping("/detallesEstudiante/{id}")
    public String detallesEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tutoria Id:" + id));
        model.addAttribute("estudiante", estudiante);
        return "formulario/estudiante/detallesEstudiante"; // Vista de detalles de tutoría
    }

    // Método para actualizar tutoría (opcional)
    @GetMapping("/actualizarFormularioEstudiante/{id}")
    public String actualizarFormularioEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante= estudianteRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid estudiante Id:" + id));
        model.addAttribute("estudiante", estudiante);
        return "formulario/estudiante/formularioEstudiante"; // Vista del formulario para editar tutoría
    }

    // Método para eliminar tutoría
    @GetMapping("/eliminarFormularioEstudiante/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        Estudiante estudiante = estudianteRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tutoria Id:" + id));
        estudianteRepositorio.delete(estudiante);
        return "redirect:/formulario/estudiante/listaEstudiante"; // Redirige después de eliminar
    }
    @GetMapping("/estudiante/pdf")
    public ResponseEntity<byte[]> decargarpdf() throws Exception{
        byte[] pdf = estudianteServicio.generarPDF();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "autores.pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
    @GetMapping("/estudiante")
    public String listarEstudiante(Model model) {
        List<Estudiante> listaEstudiante = estudianteServicio.mostrarEstudiantes();
        model.addAttribute("tutorias", listaEstudiante);
        return "formulario/estudiante/listaEstudiante";
    }


}

