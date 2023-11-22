package com.dentalClinic.dental.dto.output.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressOutputDto {

    private Long id;
    private String street;
    private int number;
    private String locality;
    private String city;

    public AddressOutputDto() {
    }

    public AddressOutputDto(Long id, String street, int number, String locality, String city) {
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
