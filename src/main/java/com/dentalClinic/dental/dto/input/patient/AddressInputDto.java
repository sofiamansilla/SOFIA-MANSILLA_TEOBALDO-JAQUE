package com.dentalClinic.dental.dto.input.patient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInputDto {


    @NotBlank(message = "A street must be specified")
    private String street;

    @Digits(integer = 8, fraction = 0, message = "The street number will only" +
            " be valid if it has less than 8 digits")
    @NotNull(message = "The street number must be specified")
    private int number;


    @NotBlank(message = "The locality must be specified")
    @Size(min = 1, max = 90, message = "The locality will only be valid if it" +
            " has less than 90 characters")
    private String locality;

    @NotBlank(message = "The city must be specified")
    @Size(min = 1, max = 90, message = "The city will only be valid if it" +
            " has less than 90 characters")
    private String city;


    public AddressInputDto() {
    }

    public AddressInputDto(String street, int number, String locality,
                           String city) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
