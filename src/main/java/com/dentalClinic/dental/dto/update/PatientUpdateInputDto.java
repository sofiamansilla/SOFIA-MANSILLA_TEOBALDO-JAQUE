package com.dentalClinic.dental.dto.update;

import com.dentalClinic.dental.entity.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientUpdateInputDto {


    @NotNull(message = "Debe proveerse el id del paciente que se desea modificar")
    private Long id;

    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String name;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String lastName;

    @NotNull(message = "El dni del paciente no puede ser nulo")
    @Size(max = 12, message = "El nombre debe tener hasta 12 digitos")
    private Integer dni;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEntry;

    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @Valid
    private AddressUpdateInputDto addressUpdateInputDto;

    public PatientUpdateInputDto() {
    }

    public PatientUpdateInputDto(Long id, String name, String lastName, Integer dni, LocalDate dateOfEntry, AddressUpdateInputDto addressUpdateInputDto) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.dateOfEntry = dateOfEntry;
        this.addressUpdateInputDto = addressUpdateInputDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public AddressUpdateInputDto getAddressUpdateInputDto() {
        return addressUpdateInputDto;
    }

    public void setAddressUpdateInputDto(AddressUpdateInputDto addressUpdateInputDto) {
        this.addressUpdateInputDto = addressUpdateInputDto;
    }
}
