package com.dentalClinic.dental.dto.output.appointment;

import com.dentalClinic.dental.dto.input.dentist.DentistInputDto;
import com.dentalClinic.dental.dto.input.patient.PatientInputDto;
import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentOutputDto {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateAndTime;
    private PatientAppointmentOutputDto patientAppointmentOutputDto;
    private DentistAppointmentOutputDto dentistAppointmentOutputDto;

    public AppointmentOutputDto() {
    }

    public AppointmentOutputDto(Long id, LocalDateTime dateAndTime, PatientAppointmentOutputDto patientAppointmentOutputDto, DentistAppointmentOutputDto dentistAppointmentOutputDto) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.patientAppointmentOutputDto = patientAppointmentOutputDto;
        this.dentistAppointmentOutputDto = dentistAppointmentOutputDto;
    }

    public AppointmentOutputDto(LocalDateTime dateAndTime, PatientAppointmentOutputDto patientAppointmentOutputDto, DentistAppointmentOutputDto dentistAppointmentOutputDto) {
        this.dateAndTime = dateAndTime;
        this.patientAppointmentOutputDto = patientAppointmentOutputDto;
        this.dentistAppointmentOutputDto = dentistAppointmentOutputDto;
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

    public PatientAppointmentOutputDto getPatientAppointmentOutputDto() {
        return patientAppointmentOutputDto;
    }

    public void setPatientAppointmentOutputDto(PatientAppointmentOutputDto patientAppointmentOutputDto) {
        this.patientAppointmentOutputDto = patientAppointmentOutputDto;
    }

    public DentistAppointmentOutputDto getDentistAppointmentOutputDto() {
        return dentistAppointmentOutputDto;
    }

    public void setDentistAppointmentOutputDto(DentistAppointmentOutputDto dentistAppointmentOutputDto) {
        this.dentistAppointmentOutputDto = dentistAppointmentOutputDto;
    }

    @Override
    public String toString() {
        return "AppointmentOutputDto{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", patientAppointmentOutputDto=" + patientAppointmentOutputDto +
                ", dentistAppointmentOutputDto=" + dentistAppointmentOutputDto +
                '}';
    }
}