package com.example.Gestion_de_Citas.repository;

import com.example.Gestion_de_Citas.model.AgendaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaMedicaRepository extends JpaRepository<AgendaMedica,Long> {
    List<AgendaMedica> findByProfesionalIdProfesional(Long idProfesional);
}
