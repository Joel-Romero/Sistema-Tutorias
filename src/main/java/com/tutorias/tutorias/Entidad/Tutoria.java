package com.itsqmet.app_bibloteca.entidad;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Tutoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nacionaliad;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionaliad() {
        return nacionaliad;
    }

    public void setNacionaliad(String nacionaliad) {
        this.nacionaliad = nacionaliad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
