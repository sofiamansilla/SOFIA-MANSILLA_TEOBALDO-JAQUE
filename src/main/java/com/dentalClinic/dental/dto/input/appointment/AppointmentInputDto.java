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
        private DentistOutputDto dentistOutputDto;

        @NotNull(message = "A patient must be specified")
        @NotBlank(message = "A patient must be specified")
        @Valid
        private PatientOutputDto patientOutputDto;


        public AppointmentInputDto(LocalDateTime dateAndTime,
                                   DentistOutputDto dentistOutputDto, PatientOutputDto patientOutputDto) {
            this.dateAndTime = dateAndTime;
            this.dentistOutputDto = dentistOutputDto;
            this.patientOutputDto = patientOutputDto;
        }

        public AppointmentInputDto() {
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
