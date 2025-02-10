package com.tutorias.tutorias.Repositorio;


import com.tutorias.tutorias.Entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByNombreContainingIgnoreCase(String buscarEstudianteNombre);
}
