package com.dentalClinic.dental.repository;

import com.dentalClinic.dental.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository <Dentist, Long> {
    Object findByLicenceNumber(String licenceNumber);
}
