package com.dentalClinic.dental.dto.input.dentist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistInputDto {


    @NotBlank(message = "The dentist's license number must be specified")
    @Size(min = 8, max = 20, message = "The licence number is incorrect, " +
            "this one must have at least 8 characters")
    private String licenceNumber;


    @Size(min = 2, max = 50, message = "Please make sure your name is at " +
            "least 2 characters and no more than 50" +
            "characters long")
    @NotBlank(message = "The dentist's name must be specified")
    private String name;

    @Size(max = 50, message = "Please make sure your lastname is at least 2 " +
            "characters and no more than 50")
    @NotBlank(message = "The dentist's last name must be specified")
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
