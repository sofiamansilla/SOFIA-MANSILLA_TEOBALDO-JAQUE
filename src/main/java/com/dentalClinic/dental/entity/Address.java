package com.dentalClinic.dental.entity;


import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String street;

    @Column(length = 8)
    private int number;
    @Column(length = 50)
    private String locality;
    @Column(length = 50)
    private String city;

    public Address() {
    }

    public Address(String street, int number, String locality, String city) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.city = city;
    }

    public Address(Long id, String street, int number, String locality, String city) {
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


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", locality='" + locality + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
