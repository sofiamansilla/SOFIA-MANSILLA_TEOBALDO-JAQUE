package com.dentalClinic.dental.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;
    @Test
    void registerAppointment() {

    }

    @Test
    void listAppointments() {
    }

    @Test
    void searchAppointmentForId() {
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void deleteAppointment() {
    }
}