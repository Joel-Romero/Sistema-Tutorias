package com.tutorias.tutorias.Controlador;
import com.itsqmet.app_bibloteca.entidad.Autor;
import com.itsqmet.app_bibloteca.servicio.AutorServicio;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AutorController {

    @Autowired
    AutorServicio autorServicio;

    //Leer los autores
    @GetMapping("/tutorias")
    public String listarAutores(@RequestParam(name="buscarAutor", required = false, defaultValue = "") String buscarAutor, Model model){
        List<Tutoria> autores = autorServicio.buscarAutorNombre(buscarAutor);
        model.addAttribute("buscarAutor", buscarAutor);
        model.addAttribute("tutorias", tutorias);
        return "autor/listaAutor";
    }
    @GetMapping("/formularioAutor")
    public String mostrarFormurario(Model model){
        model.addAttribute("autor", new Autor());
        return "autor/formulario";
    }

    @PostMapping("/registrar")
    public String insertarAutor(Autor autor){
        autorServicio.guardarAutor(autor);
        return "redirect:/autores";
    }

    @GetMapping("/actualizar/{id}")
    public String editarLibro(@PathVariable Long id, Model model){
        Optional<Autor> autor=autorServicio.buscarAutorId(id);
        model.addAttribute("autor", autor);
        return "autor/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id){
        autorServicio.eliminarAutor(id);
        return "redirect:/autores";
    }

    @GetMapping("/autores/pdf")
    public ResponseEntity<byte[]> decargarpdf() throws Exception{
        byte[] pdf = autorServicio.generarpdf();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "autores.pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


}