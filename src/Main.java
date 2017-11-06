import agent.Negociator;
import agent.Provider;
import service.Airport;
import service.PlaneTicket;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ZonedDateTime date1 = ZonedDateTime.parse("2017-05-03T10:10:01+01:00[Europe/Paris]");
        ZonedDateTime date2 = ZonedDateTime.parse("2017-05-03T10:12:01+01:00[Europe/Paris]");
        PlaneTicket p1 = new PlaneTicket(date1, date2, Airport.LYS, Airport.MAD, 200.0);

        date1 = ZonedDateTime.parse("2017-06-03T10:10:01+01:00[Europe/Paris]");
        date2 = ZonedDateTime.parse("2017-06-03T10:12:01+01:00[Europe/Paris]");
        PlaneTicket p2 = new PlaneTicket(date1, date2, Airport.LYS, Airport.MAD, 200.0);

        date1 = ZonedDateTime.parse("2017-07-03T10:10:01+01:00[Europe/Paris]");
        date2 = ZonedDateTime.parse("2017-07-03T10:12:01+01:00[Europe/Paris]");
        PlaneTicket p3 = new PlaneTicket(date1, date2, Airport.LYS, Airport.MAD, 200.0);

        List<PlaneTicket> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Provider f1 = new Provider(list, null);

        Negociator n1 = new Negociator(null);
        List<Provider> providerList = new ArrayList<>();
        providerList.add(f1);
        n1.setProviders(providerList);
    }
}
