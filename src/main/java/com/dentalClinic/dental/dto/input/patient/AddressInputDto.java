package com.dentalClinic.dental.dto.input.patient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInputDto {

    @NotNull(message = "sss")
    @NotBlank(message = "ss")


    private String street;

    @NotNull(message = "ss")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    @NotBlank(message = "ss")
    private int number;

    @NotNull(message = "sss")
    @NotBlank(message = "ss")

    private String locality;

    @NotNull(message = "sss")
    @NotBlank(message = "ss")

    private String city;


    public AddressInputDto() {
    }

    public AddressInputDto(String street, int number, String locality, String city) {
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
