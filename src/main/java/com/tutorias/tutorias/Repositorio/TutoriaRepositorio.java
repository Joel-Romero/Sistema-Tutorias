package com.tutorias.tutorias.Repositorio;


import com.tutorias.tutorias.Entidad.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutoriaRepositorio extends JpaRepository<Tutoria, Long> {
    List<Tutoria> findByNombreContainingIgnoreCase(String nombre);
}
