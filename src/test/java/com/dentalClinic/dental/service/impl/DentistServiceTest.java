package com.dentalClinic.dental.service.impl;


import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.update.DentistUpdateInputDto;
import com.dentalClinic.dental.service.impl.DentistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DentistServiceTest {
    @Autowired
    private DentistService dentistService;

    @Test
    @Order(1)
    void shouldCreateDentistWithRegistrationAAABBBTTT() {
        DentistInputDto dentistInputDto = new DentistInputDto("AAABBBTTT",
                "Pietro", "Narvaez");
        DentistOutputDto dentistOutputDto =
                dentistService.registerDentist(dentistInputDto);
        Assertions.assertEquals("AAABBBTTT", dentistOutputDto.getLicenceNumber());
    }

    ;

    @Test
    @Order(2)
    void shouldSearchDentistForId() {
        DentistOutputDto dentistOutputDto =
                dentistService.searchDentistForId(1L);
        Assertions.assertNotNull(dentistOutputDto.getId());
    }

    @Test
    @Order(3)
    void TheServiceShouldUpdateTheNameOfDentistId1Pietro() {
        DentistUpdateInputDto dentistUpdate = new DentistUpdateInputDto(1L,
                "QWERTYUIERTL", "Pietro", "Narvaez");
        DentistOutputDto dentistOutputDto = null;
        try {
            dentistOutputDto = dentistService.updateDentist(dentistUpdate);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ;
        Assertions.assertEquals("Pietro", dentistOutputDto.getName());
        Assertions.assertNotEquals("Albert", dentistOutputDto.getName());
    }
}