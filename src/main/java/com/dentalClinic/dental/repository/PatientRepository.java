package com.dentalClinic.dental.repository;

import com.dentalClinic.dental.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository <Patient, Long> {

    Object findByDni(int dni);

}
