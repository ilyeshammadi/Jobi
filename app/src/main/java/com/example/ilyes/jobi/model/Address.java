package com.example.ilyes.jobi.model;

/**
 * Created by ilyes on 28/11/15.
 */
public class Address {
    private String country;
    private String city;
    private String street;

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressAsString() {
        return street + "-" + city + "-" + country;
    }

    public void setAddressFromString(String address) {
        String[] data = address.split("-");
        street = data[0];
        city = data[1];
        country = data[2];
    }

}
