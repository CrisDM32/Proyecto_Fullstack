package com.hospital.gestion_de_citas.repository;

import com.hospital.gestion_de_citas.model.AgendaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaMedicaRepository extends JpaRepository<AgendaMedica,Long> {
    List<AgendaMedica> findByProfesionalIdProfesional(Long idProfesional);
}
