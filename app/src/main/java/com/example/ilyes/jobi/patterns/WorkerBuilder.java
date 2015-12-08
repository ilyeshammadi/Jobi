package com.example.ilyes.jobi.patterns;

import com.example.ilyes.jobi.models.Address;
import com.example.ilyes.jobi.models.Worker;

import org.joda.time.DateTime;

public class WorkerBuilder {
    private long id;
    private String name;
    private String email;
    private String password;
    private String numeroTel;
    private Address address;
    private int expYears;
    private DateTime birthDate;
    private String function;

    public WorkerBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public WorkerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WorkerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public WorkerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public WorkerBuilder setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
        return this;
    }

    public WorkerBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public WorkerBuilder setExpYears(int expYears) {
        this.expYears = expYears;
        return this;
    }

    public WorkerBuilder setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public WorkerBuilder setFunction(String function) {
        this.function = function;
        return this;
    }

    public Worker build() {
        return new Worker(id, name, email, password, numeroTel, address, expYears, birthDate, function);
    }
}