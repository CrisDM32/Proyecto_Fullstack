package com.hospital.Gestion_de_Pacientes.repository;

import com.hospital.Gestion_de_Pacientes.model.ContactoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoEmergenciaRepository extends JpaRepository<ContactoEmergencia,Long> {
}