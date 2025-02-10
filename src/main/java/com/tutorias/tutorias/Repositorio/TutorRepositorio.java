package com.tutorias.tutorias.Repositorio;

import com.tutorias.tutorias.Entidad.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepositorio extends JpaRepository<Tutor, Long> {
    List<Tutor> findByNombreContainingIgnoreCase(String nombre);
    // Aquí puedes agregar consultas personalizadas si es necesario.
}
