package com.example.ilyes.jobi.patterns;

import com.example.ilyes.jobi.models.Address;
import com.example.ilyes.jobi.models.Client;

public class ClientBuilder {
    private long id;
    private String name;
    private String email;
    private String password;
    private String numeroTel;
    private Address address;

    public ClientBuilder setId(long id) {
        this.id = id;
        return this;
    }

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

    public Client build() {
        return new Client(id, name, email, password, numeroTel, address);
    }
}