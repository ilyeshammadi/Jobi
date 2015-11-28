package com.example.ilyes.jobi.pattern;

import com.example.ilyes.jobi.model.Address;
import com.example.ilyes.jobi.model.Client;

public class ClientBuilder {
    private String name;
    private String email;
    private String password;
    private String numeroTel;
    private Address address;

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
        return this;
    }

    public ClientBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Client createClient() {
        return new Client(name, email, password, numeroTel, address);
    }
}