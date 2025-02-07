package com.itsqmet.app_bibloteca.servicio;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itsqmet.app_bibloteca.entidad.Autor;
import com.itsqmet.app_bibloteca.repositorio.AutorRepositorio;
import com.tutorias.tutorias.Repositorio.TutoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    TutoriaRepositorio tutoriaRepositorio;

    public List<Tutoria> mostrarAutores(){
        return tutoriaRepositorio.findAll();
    }

    public List<Tutoria> buscarAutorNombre(String buscarAutor){
        if(buscarAutor == null || buscarAutor.isEmpty()){
            return tutoriaRepositorio.findAll();
        }else{
            return tutoriaRepositorio.findByNombreContainingIgnoreCase(buscarAutor);
        }
    }

    public void guardarAutor(Tutoria tutoria){
        tutoriaRepositorio.save(autor);
    }

    public void eliminarAutor(Long id){
        tutoriaRepositorio.deleteById(id);
    }

    public Optional<Autor> buscarAutorId(Long id){
        return tutoriaRepositorio.findById(id);
    }

    public byte[] generarpdf() throws DocumentException, IOException{
        List<Autor> autores = tutoriaRepositorio.findAll();
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Lista de Autores", FontFactory.getFont("Arial", 14, Font.BOLD)));
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("Codigo", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Nombre", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Nacionalidad", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Genero", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Fecha Nacimiento", FontFactory.getFont("Arial", 12))));
        for(Autor autor: autores){
            table.addCell(new PdfPCell(new Phrase(String.valueOf(autor.getId()), FontFactory.getFont("Arial"))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(autor.getNombre()), FontFactory.getFont("Arial"))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(autor.getNacionaliad()), FontFactory.getFont("Arial"))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(autor.getFechaNacimiento()), FontFactory.getFont("Arial"))));
        }
        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}
