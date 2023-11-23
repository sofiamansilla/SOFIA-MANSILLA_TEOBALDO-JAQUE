package com.dentalClinic.dental.dto.input.appointment;

import com.dentalClinic.dental.entity.Dentist;
import com.dentalClinic.dental.entity.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;



    @JsonIgnoreProperties(ignoreUnknown = true)
    public class AppointmentInputDto {

        @FutureOrPresent(message = "The date must be on or after today.")
        @NotBlank(message = "The address must be specified")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate dateAndTime;

        @NotNull(message = "A dentist must be specified")
        @Valid
        private Dentist dentist;

        @NotNull(message = "A patient must be specified")
        @Valid
        private Patient patient;


        public AppointmentInputDto() {
        }

        public AppointmentInputDto(LocalDate dateAndTime, Dentist dentist, Patient patient) {
            this.dateAndTime = dateAndTime;
            this.dentist = dentist;
            this.patient = patient;
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
