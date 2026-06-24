package com.hospital.gestion_de_citas.repository;

import com.hospital.gestion_de_citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByIdProfesional(Long idProfesional);

    List<Cita> findByIdPaciente(Long idPaciente);

    List<Cita> findByIdPago(Long idPago);

    List<Cita> findByAgendaIdAgenda(Long idAgenda);

    boolean existsByFechaHoraCitaAndIdProfesional(
            LocalDateTime fechaHoraCita,
            Long idProfesional
    );
}