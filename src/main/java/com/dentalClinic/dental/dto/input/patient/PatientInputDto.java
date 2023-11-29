package com.dentalClinic.dental.dto.input.patient;


import com.dentalClinic.dental.entity.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientInputDto {


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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfEntry;

    @NotNull
    @Valid
    private AddressInputDto addressInputDto;


    public PatientInputDto() {
    }

    public PatientInputDto(String name, String lastName, String dni,
                           LocalDate dateOfEntry,
                           AddressInputDto addressInputDto) {
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
