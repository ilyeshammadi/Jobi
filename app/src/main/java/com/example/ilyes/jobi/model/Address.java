package com.example.ilyes.jobi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ilyes on 28/11/15.
 */
public class Address implements Parcelable {
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


    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country);
        dest.writeString(this.city);
        dest.writeString(this.street);
    }

    protected Address(Parcel in) {
        this.country = in.readString();
        this.city = in.readString();
        this.street = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
