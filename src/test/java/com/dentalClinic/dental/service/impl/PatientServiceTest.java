package com.dentalClinic.dental.service.impl;

import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    PatientService patientService;
    @Test
    void registerPatient() {
        Patient patientForAdd=new Patient();
    }

    @Test
    void listPatients() {
    }

    @Test
    void searchPatientForId() {
    }

    @Test
    void updatePatient() {
    }

    @Test
    void deletePatient() {
    }
}