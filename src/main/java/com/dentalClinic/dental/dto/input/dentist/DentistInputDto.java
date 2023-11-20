package com.dentalClinic.dental.dto.input.dentist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistInputDto {


    @NotNull(message = "The dentist's licence number can not be null")
    @NotBlank(message = "The dentist's license must be specified")
    @Size(min = 10, message = "The field must have at least 10 characters")
    private String licenceNumber;

    @Size(max = 50, message = "The dentist's name must be up to 50 characters long")
    @NotNull(message = "El nombre de odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    private String name;

    @Size(max = 50, message = "El apellido de odontólogo debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido de odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    private String lastName;


    public DentistInputDto() {
    }

    public DentistInputDto(String licenceNumber, String name, String lastName) {
        this.licenceNumber = licenceNumber;
        this.name = name;
        this.lastName = lastName;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
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

    @Override
    public String toString() {
        return "dentistInputDto{" +
                "licenceNumber='" + licenceNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
