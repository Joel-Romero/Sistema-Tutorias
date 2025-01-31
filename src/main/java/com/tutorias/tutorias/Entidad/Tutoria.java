package com.tutorias.tutorias.Entidad;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Tutoria {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres")
    private String apellidos;

    @NotBlank(message = "La cédula no puede estar vacía")
    @Pattern(regexp = "\\d{10}", message = "La cédula debe contener exactamente 10 dígitos numéricos")
    private String cedula;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe contener exactamente 10 dígitos numéricos")
    private String telefono;

    @NotBlank(message = "El género no puede estar vacío")
    @Pattern(regexp = "Hombre|Mujer", message = "El género debe ser 'Hombre' o 'Mujer'")
    private String genero;

    @NotNull(message = "Debe seleccionar al menos una materia")
    @Size(min = 1, message = "Debe seleccionar al menos una materia")
    private List<String> materias;


    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }
}

