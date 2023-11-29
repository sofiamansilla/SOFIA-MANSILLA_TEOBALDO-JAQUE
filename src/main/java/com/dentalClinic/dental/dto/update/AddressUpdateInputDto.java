package com.dentalClinic.dental.dto.update;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressUpdateInputDto {

    @NotBlank(message = "The unique identifier of the address")
    private Long id;

    @NotBlank(message = "A street must be specified")
    private String street;

    @Digits(integer = 8, fraction = 0, message = "The street number will only" +
            " be valid if it has less than 8 digits")
    @NotBlank(message = "The street number must be specified")
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

    public AddressUpdateInputDto() {
    }

    public AddressUpdateInputDto(Long id, String street, int number,
                                 String locality, String city) {
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
