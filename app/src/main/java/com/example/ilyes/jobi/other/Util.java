package com.example.ilyes.jobi.other;

import org.joda.time.DateTime;

/**
 * Created by ilyes on 28/11/15.
 */
public class Util {
    public static final String LOG_TAG = "TOTO";
    public static final String ID_FLAG = "id";
    public static final String USER_TYPE_FLAG = "user";


    public static int calculateAge(DateTime userBirthDate) {
        DateTime acctualDate = DateTime.now();

        return acctualDate.getYear() - userBirthDate.getYear();

    }

}
