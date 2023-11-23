package com.dentalClinic.dental.service;

import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.DentistUpdateInputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;

import java.util.List;

public interface IDentistService {



    DentistOutputDto registerDentist (DentistInputDto dentist);

    List<DentistOutputDto> listDentists();

    DentistOutputDto searchDentistForId(Long id);

    DentistOutputDto updateDentist (DentistUpdateInputDto dentistUpdateInputDto);

    void deleteDentist(Long id);

    DentistOutputDto searchDentistForLicenceNumber(String licenceNumber);

};


