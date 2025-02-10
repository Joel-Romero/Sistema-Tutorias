package com.tutorias.tutorias.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.tutorias.tutorias.Entidad.Tutor;
import com.tutorias.tutorias.Repositorio.TutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TutorServicio {

    @Autowired
    private TutorRepositorio tutorRepositorio;



    public List<Tutor> mostrarTutor() {
        return tutorRepositorio.findAll();
    }

    // Método para buscar tutorías por el nombre del tutor
    public List<Tutor> buscarTutoria(String buscarTutorNombre) {
        if (buscarTutorNombre == null || buscarTutorNombre.isEmpty()) {
            return tutorRepositorio.findAll();
        } else {
            return tutorRepositorio.findByNombreContainingIgnoreCase(buscarTutorNombre);
        }
    }

    // Método para guardar una nueva tutoría
    public void guardarTutoria(Tutor tutor) {
        tutorRepositorio.save(tutor); // Guarda la tutoría en la base de datos
    }

    // Método para generar el PDF
    public byte[] generarPDF() throws DocumentException, IOException {
        List<Tutor> tutor= tutorRepositorio.findAll();
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Lista de Tutor", FontFactory.getFont("Arial", 14, Font.BOLD)));

        PdfPTable table = new PdfPTable(5); // 5 columnas
        table.setWidthPercentage(100); // Acomoda el ancho de la tabla

        // Añadir encabezados de las columnas
        table.addCell(new PdfPCell(new Phrase("Nombre", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("especialidad", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Correo", FontFactory.getFont("Arial", 12))));


        // Añadir datos de las tutorías
        for (Tutor tutor1 : tutor) {
            table.addCell(new PdfPCell(new Phrase(tutor1.getNombre(), FontFactory.getFont("Arial", 11))));
            table.addCell(new PdfPCell(new Phrase(tutor1.getEspecialidad(), FontFactory.getFont("Arial", 11))));
            table.addCell(new PdfPCell(new Phrase(tutor1.getEspecialidad(), FontFactory.getFont("Arial", 11))));

        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}
