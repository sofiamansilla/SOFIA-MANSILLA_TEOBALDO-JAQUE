package com.dentalClinic.dental.dto.update;


import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentUpdateInputDto {

    @NotBlank(message = "The ID of the appointment must be specified")
    private Long id;

    @FutureOrPresent(message = "The date must be on or after today.")
    @NotBlank(message = "The address must be specified")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAndTime;

    @NotNull(message = "A dentist must be specified")
    @Valid
    private Long dentist;

    @NotNull(message = "A patient must be specified")
    @Valid
    private Long patient;

    public AppointmentUpdateInputDto() {
    }

    public AppointmentUpdateInputDto(LocalDate dateAndTime, Long dentist, Long patient) {
        this.dateAndTime = dateAndTime;
        this.dentist = dentist;
        this.patient = patient;
    }

    public AppointmentUpdateInputDto(Long id, LocalDate dateAndTime, Long dentist, Long patient) {
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

    public Long getDentist() {
        return dentist;
    }

    public void setDentist(Long dentist) {
        this.dentist = dentist;
    }

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }
}
