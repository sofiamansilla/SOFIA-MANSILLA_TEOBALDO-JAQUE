package com.dentalClinic.dental.service;

import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.DentistUpdateInputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;

import java.util.List;

public interface IDentistService {



    DentistOutputDto registerDentist (PatientInputDto dentist);

    List<DentistOutputDto> listDentists();

    DentistOutputDto searchDentistForId(Long id);

    DentistOutputDto updateDentist (DentistUpdateInputDto dentist);

    void deleteDentist(Long id);


};


