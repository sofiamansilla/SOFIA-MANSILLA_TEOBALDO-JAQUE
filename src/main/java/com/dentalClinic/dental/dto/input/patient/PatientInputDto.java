package com.dentalClinic.dental.dto.input.patient;


import com.dentalClinic.dental.entity.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientInputDto {


    @NotNull(message = "The dentist's licence number can not be null")
    @NotBlank(message = "The dentist's license must be specified")
    @Size(min = 10, message = "The field must have at least 10 characters")
    private String name;


    @NotNull(message = "The dentist's licence number can not be null")
    @NotBlank(message = "The dentist's license must be specified")
    @Size(min = 10, message = "The field must have at least 10 characters")
    private String lastName;


    @NotNull(message = "The dentist's licence number can not be null")
    @NotBlank(message = "The dentist's license must be specified")
    @Size(min = 10, message = "The field must have at least 10 characters")
    private String dni;


    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEntry;


    @NotNull
    @Valid
    private AddressInputDto addressInputDto;


    public PatientInputDto() {
    }

    public PatientInputDto(String name, String lastName, String dni, LocalDate dateOfEntry, AddressInputDto addressInputDto) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.dateOfEntry = dateOfEntry;
        this.addressInputDto = addressInputDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public AddressInputDto getAddressInputDto() {
        return addressInputDto;
    }

    public void setAddressInputDto(AddressInputDto addressInputDto) {
        this.addressInputDto = addressInputDto;
    }
}
