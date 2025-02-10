package com.tutorias.tutorias.Servicio;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.tutorias.tutorias.Entidad.Estudiante;
import com.tutorias.tutorias.Repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class EstudianteServicio {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    // Otros métodos existentes...

    public List<Estudiante> mostrarEstudiantes() {
        return estudianteRepositorio.findAll();
    }

    // Método para buscar tutorías por el nombre del tutor
    public List<Estudiante> buscarEstudiante(String buscarEstudianteNombre) {
        if (buscarEstudianteNombre == null || buscarEstudianteNombre.isEmpty()) {
            return estudianteRepositorio.findAll();
        } else {
            return estudianteRepositorio.findByNombreContainingIgnoreCase(buscarEstudianteNombre);
        }
    }

    public void guardarEstudiante (Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }


    // Método para generar el PDF
    public byte[] generarPDF() throws IOException {
        // Crear documento PDF
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Crear escritor de PDF
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Título o cabecera del PDF
            document.add(new Paragraph("Lista de Estudiantes"));

            // Separador
            document.add(new Paragraph("\n----------------------------------\n"));

            // Obtener lista de estudiantes
            List<Estudiante> estudiantes = estudianteRepositorio.findAll();
            for (Estudiante estudiante : estudiantes) {
                // Agregar información de cada estudiante al PDF
                document.add(new Paragraph("Nombre: " + estudiante.getNombre()));
                document.add(new Paragraph("Cédula: " + estudiante.getCedula()));
                document.add(new Paragraph("Email: " + estudiante.getEmail()));
                document.add(new Paragraph("Teléfono: " + estudiante.getTelefono()));
                document.add(new Paragraph("Fecha de Nacimiento: " + estudiante.getFechaNacimiento()));
                document.add(new Paragraph("Carrera: " + estudiante.getCarrera()));

                // Separador entre estudiantes
                document.add(new Paragraph("\n----------------------------------\n"));
            }

        } catch (Exception e) {
            e.printStackTrace();  // Es recomendable registrar el error o devolver una respuesta adecuada
        } finally {
            document.close();  // Asegurarse de cerrar el documento para evitar problemas de memoria
        }

        // Retornar el PDF como byte array
        return outputStream.toByteArray();
    }
}
