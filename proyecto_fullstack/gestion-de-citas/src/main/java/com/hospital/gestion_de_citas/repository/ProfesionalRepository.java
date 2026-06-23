package com.hospital.gestion_de_citas.repository;

import com.hospital.gestion_de_citas.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Long> {
    boolean existsByRut(int rut);
    boolean existsByNumLicencia(int numLicencia);
}
