package com.dentalClinic.dental.dto.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistUpdateInputDto {

    @NotNull
    private Long id;



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


    public DentistUpdateInputDto() {
    }

    public DentistUpdateInputDto(Long id, String licenceNumber, String name, String lastName) {
        this.id = id;
        this.licenceNumber = licenceNumber;
        this.name = name;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
