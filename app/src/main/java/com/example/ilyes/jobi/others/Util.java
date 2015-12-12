package com.example.ilyes.jobi.others;

import org.joda.time.DateTime;

/**
 * Created by ilyes on 28/11/15.
 */
public class Util {
    public static final String LOG_TAG = "TOTO";
    public static final String ID_FLAG = "id";
    public static final String USER_TYPE_FLAG = "user";

    public static final String WORKER = "worker";
    public static final String CLIENT = "client";

    public static final String IS_APP_FIRST_RUN = "is_app_first_run";

    public static int calculateAge(DateTime userBirthDate) {
        DateTime acctualDate = DateTime.now();

        return acctualDate.getYear() - userBirthDate.getYear();

    }

}
