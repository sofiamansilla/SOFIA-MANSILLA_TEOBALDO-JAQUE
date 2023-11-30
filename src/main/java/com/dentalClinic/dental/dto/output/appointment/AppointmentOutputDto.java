package com.dentalClinic.dental.dto.output.appointment;

import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentOutputDto {

    private Long id;
    private LocalDateTime dateAndTime;
    private DentistOutputDto dentistOutputDto;
    private PatientOutputDto patientOutputDto;

    public AppointmentOutputDto(Long id) {
        this.id = id;
    }

    public AppointmentOutputDto(LocalDateTime dateAndTime, DentistOutputDto dentistOutputDto, PatientOutputDto patientOutputDto) {
        this.dateAndTime = dateAndTime;
        this.dentistOutputDto = dentistOutputDto;
        this.patientOutputDto = patientOutputDto;
    }

    public AppointmentOutputDto(Long id, LocalDateTime dateAndTime, DentistOutputDto dentistOutputDto, PatientOutputDto patientOutputDto) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.dentistOutputDto = dentistOutputDto;
        this.patientOutputDto = patientOutputDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public DentistOutputDto getDentistOutputDto() {
        return dentistOutputDto;
    }

    public void setDentistOutputDto(DentistOutputDto dentistOutputDto) {
        this.dentistOutputDto = dentistOutputDto;
    }

    public PatientOutputDto getPatientOutputDto() {
        return patientOutputDto;
    }

    public void setPatientOutputDto(PatientOutputDto patientOutputDto) {
        this.patientOutputDto = patientOutputDto;
    }
}
