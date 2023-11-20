package com.dentalClinic.dental.dto.update;


import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentUpdateInputDto {

    @NotNull(message = "Debe proveerse el id del paciente que se desea " +
            "modificar")
    private Long id;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAndTime;

    @NotNull(message = "sss")
    @Valid

    private Dentist dentist;


    @NotNull(message = "sss")
    @Valid
    private Patient patient;

    public AppointmentUpdateInputDto() {
    }

    public AppointmentUpdateInputDto(Long id, LocalDate dateAndTime, Dentist dentist, Patient patient) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.dentist = dentist;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDate dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
