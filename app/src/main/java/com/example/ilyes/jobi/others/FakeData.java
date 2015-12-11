package com.example.ilyes.jobi.others;

import android.content.Context;

import com.example.ilyes.jobi.database.ClientDataSource;
import com.example.ilyes.jobi.database.PostDataSource;
import com.example.ilyes.jobi.database.WorkerDataSource;
import com.example.ilyes.jobi.models.Address;
import com.example.ilyes.jobi.models.Client;
import com.example.ilyes.jobi.models.Worker;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyes on 11/12/15.
 */
public class FakeData {


    private static WorkerDataSource workerDataSource;
    private static ClientDataSource clientDataSource;
    private static PostDataSource postDataSource;

    public static void generate(Context context) {


        // Generate Workers
        List<Worker> workers = new ArrayList<>();

        workers.add(new Worker(-1, "Reagan Patel", "Etiam.bibendum.fermentum@molestietellusAenean.net", "a123456", "0659990912", new Address("Guatemala", "Oromocto", "New Brunswick"), 3, new DateTime(1990, 1, 1, 0, 0), Worker.ELECTRICIEN));
        workers.add(new Worker(-1, "Kessie Davenport", "fermentum.vel.mauris@IncondimentumDonec.edu", "a123456", "0566319021", new Address("Greece", "Whitchurch", "Shropshire"), 3, new DateTime(1980, 1, 1, 0, 0), Worker.PLOMBIER));
        workers.add(new Worker(-1, "Jena Roman", "Aliquam.ornare@aliquameuaccumsan.co.uk", "a123456", "0977290865", new Address("Hong Kong", "Sabadell", "CA"), 3, new DateTime(1990, 1, 1, 0, 0), Worker.MECHANICIEN));
        workers.add(new Worker(-1, "Quail York", "mi@utquam.net", "a123456", "04 31 33 06 91", new Address("Qatar", "Berlin", "BE"), 3, new DateTime(1970, 1, 1, 0, 0), Worker.ELECTRICIEN));
        workers.add(new Worker(-1, "Desiree Schneider", "id@gravida.org", "a123456", "0750766491", new Address("New Caledonia", "Broken Arrow", "Oklahoma"), 3, new DateTime(1984, 1, 1, 0, 0), Worker.ELECTRICIEN));
        workers.add(new Worker(-1, "Riley Alexander", "tincidunt@nectempus.edu", "a123456", "0320891030", new Address("Djibouti", "Logan City", "Queensland"), 3, new DateTime(1974, 1, 1, 0, 0), Worker.ELECTRICIEN));
        workers.add(new Worker(-1, "Naomi Hogan", "felis@utpharetra.ca", "a123456", "0458767716", new Address("Wallis and Futuna", "Richmond Hill", "Ontario"), 3, new DateTime(1969, 1, 1, 0, 0), Worker.ELECTRICIEN));
        workers.add(new Worker(-1, "Noel Buck", "ac.feugiat@dolordolor.net", "a123456", "0866119373", new Address("Austria", "Bamberg", "Bavaria"), 3, new DateTime(1990, 1, 1, 0, 0), Worker.ELECTRICIEN));
        workers.add(new Worker(-1, "Miloud Ben", "miloud.ben@gmail.com", "a123456", "0659940912", new Address("Algerie", "Oran", "Senia"), 3, new DateTime(1967, 1, 1, 0, 0), Worker.ELECTRICIEN));

        // My worker account
        workers.add(new Worker(-1, "ilyes", "ilyes@ilyes.com", "a123456", "079989803", new Address("Algerie", "Oran", "Gdyel"), 2, new DateTime(1994, 8, 19, 0, 0), Worker.ELECTRICIEN));


        // Insert the data into the database
        workerDataSource = new WorkerDataSource(context);
        workerDataSource.open();

        for (Worker w : workers) {
            workerDataSource.create(w);
        }

        workerDataSource.close();


        // Generate Clients
        List<Client> clients = new ArrayList<>();

        clients.add(new Client(-1, "Echo Lamb", "ante.dictum@mus.net", "a123456", "0193204265", new Address("Guinea", "Jerez de la Frontera", "North Island")));
        clients.add(new Client(-1, "Rebekah Golden", "hendrerit.a@maurissit.org", "a123456", "0719846801", new Address("United States Minor Outlying Islands", "Boston", "Massachusetts")));
        clients.add(new Client(-1, "Elizabeth Hardin", "metus.facilisis.lorem@lectus.net", "a123456", "0949466615", new Address("Pitcairn Islands", "Wuppertal", "NW")));
        clients.add(new Client(-1, "Harriet Lawrence", "urna@mollis.net", "a123456", "0163287204", new Address("Antarctica", "Tauranga", "NI")));

        // My client account
        clients.add(new Client(-1, "Ilyes Hammadi", "ilyes1@ilyes.com", "a123456", "07564821", new Address("Algerie", "Oran", "Gdyel")));


        // Insert data into the database
        clientDataSource = new ClientDataSource(context);
        clientDataSource.open();

        for (Client c : clients) {
            clientDataSource.create(c);
        }

        clientDataSource.close();


    }


}
