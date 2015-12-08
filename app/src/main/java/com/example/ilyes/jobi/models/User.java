package com.example.ilyes.jobi.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ilyes on 28/11/15.
 */
public class User implements Parcelable {

    private long id;
    private String name;
    private String email;
    private String password;
    private String numeroTel;
    private Address address;

    public User() {

    }

    public User(long id, String name, String email, String password, String numeroTel, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.numeroTel = numeroTel;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAddressFromString(String address) {
        String[] data = address.split("-");
        String street = data[0];
        String city = data[1];
        String country = data[2];
        this.address = new Address(country, city, street);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", numeroTel='" + numeroTel + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.numeroTel);
        dest.writeParcelable(this.address, 0);
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.numeroTel = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

}
