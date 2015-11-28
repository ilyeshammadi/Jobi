package com.example.ilyes.jobi.model;

/**
 * Created by ilyes on 28/11/15.
 */
public class Client  extends User{


    public Client(long id, String name, String email, String password, String numeroTel, Address address) {
        super(id, name, email, password, numeroTel, address);
    }
}
