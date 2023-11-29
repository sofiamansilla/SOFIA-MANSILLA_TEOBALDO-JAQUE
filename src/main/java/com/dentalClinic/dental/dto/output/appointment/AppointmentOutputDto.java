package com.dentalClinic.dental.dto.output.appointment;

import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentOutputDto {

    private Long id;
    private LocalDateTime dateAndTime;
    private DentistInputDto dentistInputDto;
    private PatientInputDto patientInputDto;

    /*Deberia ser el uodate?*/

    public AppointmentOutputDto() {
    }

    public AppointmentOutputDto(Long id, LocalDateTime dateAndTime,
                                DentistInputDto dentistInputDto,
                                PatientInputDto patientInputDto) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.dentistInputDto = dentistInputDto;
        this.patientInputDto = patientInputDto;
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

    public DentistInputDto getDentistInputDto() {
        return dentistInputDto;
    }

    public void setDentistInputDto(DentistInputDto dentistInputDto) {
        this.dentistInputDto = dentistInputDto;
    }

    public PatientInputDto getPatientInputDto() {
        return patientInputDto;
    }

    public void setPatientInputDto(PatientInputDto patientInputDto) {
        this.patientInputDto = patientInputDto;
    }

    public AppointmentOutputDto getPatientOutputDto() {
        return null;
    };

    public AppointmentOutputDto getDentistOutputDto() {
        return null;
    };
}
