package com.hospital.gestion_de_pacientes.repository;

import com.hospital.gestion_de_pacientes.model.ContactoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoEmergenciaRepository extends JpaRepository<ContactoEmergencia,Long> {
}