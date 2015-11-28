package com.example.ilyes.jobi.model;

import org.joda.time.DateTime;

/**
 * Created by ilyes on 28/11/15.
 */
public class Worker extends User {

    private int expYears;
    private DateTime birthDate;
    private String function;

    public Worker() {

    }

    public Worker(long id, String name, String email, String password, String numeroTel, Address address, int expYears, DateTime birthDate, String function) {
        super(id, name, email, password, numeroTel, address);
        this.expYears = expYears;
        this.birthDate = birthDate;
        this.function = function;
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
}


