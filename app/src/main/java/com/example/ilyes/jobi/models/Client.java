package com.example.ilyes.jobi.models;

import android.os.Parcel;

/**
 * Created by ilyes on 28/11/15.
 */
public class Client  extends User{

    public Client() {

    }

    public Client(long id, String name, String email, String password, String numeroTel, Address address) {
        super(id, name, email, password, numeroTel, address);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected Client(Parcel in) {
        super(in);
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
