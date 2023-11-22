package com.dentalClinic.dental.dto.output.patient;

import com.dentalClinic.dental.dto.input.patient.AddressInputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientOutputDto {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private LocalDate dateOfEntry;
    private AddressInputDto addressInputDto;


    public PatientOutputDto() {
    }

    public PatientOutputDto(Long id, String name, String lastName, String dni, LocalDate dateOfEntry, AddressInputDto addressInputDto) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.dateOfEntry = dateOfEntry;
        this.addressInputDto = addressInputDto;
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
