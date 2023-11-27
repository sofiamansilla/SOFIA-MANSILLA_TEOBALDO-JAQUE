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



    @JsonIgnoreProperties(ignoreUnknown = true)
    public class AppointmentInputDto {

        @FutureOrPresent(message = "The date must be on or after today.")
        @NotBlank(message = "The address must be specified")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate dateAndTime;

        @NotNull(message = "A dentist must be specified")
        @Valid
        private DentistOutputDto dentistOutputDto;

        @NotNull(message = "A patient must be specified")
        @Valid
        private PatientOutputDto patientOutputDto;


        public AppointmentInputDto(LocalDate dateAndTime, DentistOutputDto dentistOutputDto, PatientOutputDto patientOutputDto) {
            this.dateAndTime = dateAndTime;
            this.dentistOutputDto = dentistOutputDto;
            this.patientOutputDto = patientOutputDto;
        }

        public AppointmentInputDto() {
        }

        public LocalDate getDateAndTime() {
            return dateAndTime;
        }

        public void setDateAndTime(LocalDate dateAndTime) {
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
