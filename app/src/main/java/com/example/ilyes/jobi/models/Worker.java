package com.example.ilyes.jobi.models;

import android.os.Parcel;

import org.joda.time.DateTime;

/**
 * Created by ilyes on 28/11/15.
 */
public class Worker extends User {

    private int expYears;
    private DateTime birthDate;
    private String function;


    public static final String MECHANICIEN = "Mechanicien";
    public static final String PLOMBIER = "Plombier";
    public static final String ELECTRICIEN = "Electricien";
    public static final String MACON = "Macon";
    public static final String MACON_CHENTIER = "Macon de chentier";
    public static final String MACON_BATIMENT = "Macon de batiment";
    public static final String MENUISIER = "Menuisier";
    public static final String FORGERON = "Forgeron";

    private static String[] workerFunctions = {
            ELECTRICIEN,
            MECHANICIEN,
            PLOMBIER,
            MACON,
            MACON_CHENTIER,
            MACON_BATIMENT,
            MENUISIER,
            FORGERON
    };


    public Worker() {

    }

    public Worker(long id, String name, String email, String password, String numeroTel, Address address, int expYears, DateTime birthDate, String function) {
        super(id, name, email, password, numeroTel, address);
        this.expYears = expYears;
        this.birthDate = birthDate;
        this.function = function;
    }

    public static String[] getWorkerFunctions() {
        return workerFunctions;
    }

    public int getExpYears() {
        return expYears;
    }

    public void setExpYears(int expYears) {
        this.expYears = expYears;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public String getBirthDateAsString() {
        return birthDate.getYear() + "-" + birthDate.getMonthOfYear() + "-" + birthDate.getDayOfMonth();
    }
    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setDateTimeFromString(String date) {
        String[] data = date.split("-");
        int year = Integer.parseInt(data[0]);
        int mount = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);

        birthDate = new DateTime(year, mount, day, 0, 0, 0);
    }




    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return super.toString() +
                "expYears=" + expYears +
                ", birthDate=" + birthDate +
                ", function='" + function + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.expYears);
        dest.writeSerializable(this.birthDate);
        dest.writeString(this.function);
    }

    protected Worker(Parcel in) {
        super(in);
        this.expYears = in.readInt();
        this.birthDate = (DateTime) in.readSerializable();
        this.function = in.readString();
    }

    public static final Creator<Worker> CREATOR = new Creator<Worker>() {
        public Worker createFromParcel(Parcel source) {
            return new Worker(source);
        }

        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };
}


