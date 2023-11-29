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


    @NotBlank(message = "The ID of the patient must be specified")
    private Long id;

    @Size(min = 2, max = 50, message = "Please make sure your name is at " +
            "least 2 characters and no more than 50" +
            "characters long")
    @NotBlank(message = "The patient's name must be specified")
    private String name;

    @Size(max = 50, message = "Please make sure your lastname is at least 2 " +
            "characters and no more than 50")
    @NotBlank(message = "The patient's last name must be specified")
    private String lastName;

    @NotBlank(message = "The National Identity Document (DNI) must be " +
            "specified")
    @Size(min = 8, max = 13, message = "The DNI is invalid. It must be " +
            "between 8 and 13 digits long")
    private String dni;

    @FutureOrPresent(message = "The date must be on or after today")
    @NotNull(message = "The date of entry must be specified")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEntry;

    @NotNull(message = "An address must be specified")
    @Valid
    private AddressUpdateInputDto addressUpdateInputDto;

    public PatientUpdateInputDto() {
    }

    public PatientUpdateInputDto(Long id, String name, String lastName,
                                 String dni, LocalDate dateOfEntry,
                                 AddressUpdateInputDto addressUpdateInputDto) {
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

    public AddressUpdateInputDto getAddressUpdateInputDto() {
        return addressUpdateInputDto;
    }

    public void setAddressUpdateInputDto(AddressUpdateInputDto addressUpdateInputDto) {
        this.addressUpdateInputDto = addressUpdateInputDto;
    }
}
