package com.dentalClinic.dental.dto.output.dentist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistOutputDto {


    private Long id;
    private String licenceNumber;
    private String name;
    private String lastName;


    public DentistOutputDto() {
    }

    public DentistOutputDto(Long id, String licenceNumber, String name, String lastName) {
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
