package com.example.ilyes.jobi.others;

import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.models.Worker;

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

    public static int getPictureAsResource(String function) {
        switch (function) {

            case Worker.MACON_BATIMENT:
                return R.drawable.macon_de_batiment;

            case Worker.MACON:
                return R.drawable.macon;

            case Worker.MACON_CHENTIER:
                return R.drawable.macon_de_chentier;

            case Worker.ELECTRICIEN:
                return R.drawable.electricien;

            case Worker.PLOMBIER:
                return R.drawable.plombier;

            case Worker.MENUISIER:
                return R.drawable.menuisier;

            case Worker.MECHANICIEN:
                return R.drawable.mechano;

            case Worker.FORGERON:
                return R.drawable.forgeron;

        }

        return R.drawable.ic_face_black_24dp;
    }

}
