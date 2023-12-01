package com.dentalClinic.dental.service.impl;
import com.dentalClinic.dental.dto.input.appointment.AppointmentInputDto;
import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.input.patient.AddressInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.appointment.AppointmentOutputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DentistService dentistService;
    @Autowired
    private PatientService patientService;
    PatientInputDto patient1;
    PatientOutputDto patientOutput;
    DentistInputDto dentist1;
    DentistInputDto dentist2;
    DentistOutputDto dentistOutput;

    /*
        @BeforeEach
        void setUp() {
            Patient1 = new PatientEntradaDto("Claudio", "Caicedo", 123, LocalDate.of(2024, 01, 01),
                    new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago"));
            PatientSalida = PatientService.registrarPatient(Patient1);

            Dentist1 = new DentistEntradaDto(2020, "Jhon", "Perezeition");
            DentistSalida = DentistService.registrarDentist(Dentist1);
        }


        @Test// resulta si no se hace el otro primero
        void deberiaCrearUnAppointmentConDentistId1(){
            AppointmentEntradaDto AppointmentEntrada =new AppointmentEntradaDto(LocalDateTime.of(2024,1,1,11,1,1),DentistSalida,PatientSalida);
            AppointmentSalidaDto Appointment = AppointmentService.registrarAppointment(AppointmentEntrada);

                assertEquals(1,Appointment.getDentistSalidaDto().getId());
                };
    */
    @BeforeEach
    void setUp() {
        patient1 = new PatientInputDto("Testeo", "Caicedo", "ASDFGHJKCG", LocalDate.of(2024, 01, 01),
                new AddressInputDto("calle", 12345, "Santiago", "Santiago"));
        patientOutput = patientService.registerPatient(patient1);

        dentist1 = new DentistInputDto("WERTYUIOIUYTR", "Jhon", "Perezeition");
        dentistOutput = dentistService.registerDentist(dentist1);

        dentist2 = new DentistInputDto("FDEDFGHJHGF", "Ana", "Lopez");
        dentistOutput = dentistService.registerDentist(dentist2);
    }


    @Test
    void shouldCreateAnAppointmentWithId1() throws BadRequestException {
        AppointmentInputDto appointmentInput =new AppointmentInputDto(LocalDateTime.of(2024,1,1,11,1,1),dentistOutput.getId(),patientOutput.getId());
        AppointmentOutputDto appointment = appointmentService.registerAppointment(appointmentInput);

        assertTrue(appointment.getId() == 1L);
    }};