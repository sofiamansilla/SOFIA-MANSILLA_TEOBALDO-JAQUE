package com.dentalClinic.dental.dto.input.appointment;

import com.dentalClinic.dental.dto.output.dentist.DentistOutputDto;
import com.dentalClinic.dental.dto.output.patient.PatientOutputDto;
import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
    public class AppointmentInputDto {

    @FutureOrPresent(message = "The date must be on or after today.")
    @NotNull(message = "The date and time of the appointment are needed, " +
            "please use the format: dd-MM-yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateAndTime;

    @NotNull(message = "A dentist must be specified")
    @NotBlank(message = "A dentist must be specified")
    @Valid
    private Long dentistId;
    @NotNull(message = "A patient must be specified")
    @NotBlank(message = "A patient must be specified")
    @Valid
    private Long patientId;

    public AppointmentInputDto() {
    }

    public AppointmentInputDto(LocalDateTime dateAndTime, Long dentistId, Long patientId) {
        this.dateAndTime = dateAndTime;
        this.dentistId = dentistId;
        this.patientId = patientId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "AppointmentInputDto{" +
                "dateAndTime=" + dateAndTime +
                ", dentistId=" + dentistId +
                ", patientId=" + patientId +
                '}';
    }
}