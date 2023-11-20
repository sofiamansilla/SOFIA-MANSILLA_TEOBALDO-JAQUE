package com.dentalClinic.dental.dto.update;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressUpdateInputDto {

    @NotNull
    private Long id;

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

    public AddressUpdateInputDto() {
    }

    public AddressUpdateInputDto(Long id, String street, int number, String locality, String city) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
