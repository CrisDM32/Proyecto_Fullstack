package com.hospital.Gestion_de_Citas.repository;

import com.hospital.Gestion_de_Citas.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Long> {
    boolean existsByRut(int rut);
    boolean existsByNumLicencia(int numLicencia);
}
