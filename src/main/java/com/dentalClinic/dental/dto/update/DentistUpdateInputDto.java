package com.dentalClinic.dental.dto.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistUpdateInputDto {

    @NotBlank(message = "The ID of the dentist must be specified")
    private Long id;

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
