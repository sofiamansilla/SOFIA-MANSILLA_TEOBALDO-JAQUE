package com.dentalClinic.dental.service;

import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.dto.update.PatientUpdateInputDto;
import com.dentalClinic.dental.exceptions.ResourceNotFoundException;

import java.lang.annotation.Documented;
import java.util.List;

public interface IPatientService {

    PatientOutputDto registerPatient(PatientInputDto patient);

    List<PatientOutputDto> listPatients();

    PatientOutputDto searchPatientForId(Long id);

    PatientOutputDto updatePatient(PatientUpdateInputDto patient);

    void deletePatient(Long id) throws ResourceNotFoundException;

//    PatientOutputDto searchPatientForDni(int dni);

}
