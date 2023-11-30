package com.dentalClinic.dental.service.impl;

import com.dentalClinic.dental.exceptions.ResourceNotFoundException;
import com.dentalClinic.dental.dto.input.patient.AddressInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.service.impl.PatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    @Order(1)
    void TheServiceShouldRegisterPatientPatricioAndReturnObjectWithId(){
        PatientInputDto patientInputDto = new PatientInputDto("Patricio", "Jara","555009", LocalDate.of(2023,01,01),
                new AddressInputDto("Patricio",12345,"Talca","Talca"));

        PatientOutputDto patientOutputDto =
                patientService.registerPatient(patientInputDto);

        assertNotNull(patientOutputDto.getId());
        assertEquals("Patricio",patientOutputDto.getName());
    }
    @Test
    @Order(2)
    void TheServiceShouldRemoveThePatientWithId1OrLaunchResourceNotFound(){
        try{
            patientService.deletePatient(1L);
        } catch (Exception exception){
            exception.printStackTrace();
        };
        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient(1L));
    }
    @Test
    @Order(3)
    void TheServiceShouldReturnAnEmptyList(){
        List<PatientOutputDto> pacienteSalidaDtoList= patientService.listPatients();
        assertTrue(pacienteSalidaDtoList.isEmpty());
    }

}