package com.example.ilyes.jobi.others;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.ilyes.jobi.R;
import com.example.ilyes.jobi.models.User;
import com.gc.materialdesign.views.ButtonIcon;

/**
 * Created by ilyes on 11/12/15.
 */
public class DialogBox {


    public static void showContactDialog(final Context context, final User user) {

        // Setup the dialog box
        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Contact")
                .customView(R.layout.contact, false)
                .build();


        dialog.show();

        final View conactView = dialog.getCustomView();

        if (conactView != null) {

            ButtonIcon phoneIconBtn = (ButtonIcon) conactView.findViewById(R.id.contact_phone);
            ButtonIcon mailIconBtn = (ButtonIcon) conactView.findViewById(R.id.contact_mail);

            // Click on conact using phone number
            phoneIconBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Call phone to : " + user.getNumeroTel(), Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + user.getNumeroTel()));

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    context.startActivity(in);
                }
            });

            // Click on contact using mail
            mailIconBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Send Mail to : " + user.getEmail(), Toast.LENGTH_SHORT).show();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{
                            user.getEmail()
                    });

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    context.startActivity(Intent.createChooser(email, "Choose an Email client :"));


                }
            });

        }
    }


}
