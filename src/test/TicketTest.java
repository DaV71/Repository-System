package test;

import model.*;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @org.junit.jupiter.api.Test
    void updateTotalPrice() {
        Calendar sunday = Calendar.getInstance();
        sunday.set(2019,Calendar.MAY,26);
        Calendar f1r = Calendar.getInstance();
        f1r.set(2019, Calendar.JULY, 19);
        Calendar f2d = Calendar.getInstance();
        f2d.set(2019,Calendar.JULY,4);
        Calendar f2r = Calendar.getInstance();
        f2r.set(2019, Calendar.JULY, 13);

        Calendar date = Calendar.getInstance();
        date.set(2019, Calendar.MAY, 24);


        List<Passenger> passengerList = Arrays.asList(new Passenger(TypeOfPassenger.ADULTS), new Passenger(TypeOfPassenger.KIDS));

        Flight flight1 = new Flight("Warszawa", "WAW", "Kraków", "KRK",sunday,f1r, 1, 100, 150.0 );
        Flight flight2 = new Flight("Gdańsk", "GDA", "Warszawa", "WAW",f2d,f2r, 1, 15, 100.0 );

        Ticket ticket1 = new Ticket(flight1, FlightClass.ECONOMIC, passengerList);
        ticket1.setCurrentTime(date); //wylot w niedzielę, za mniej niż 7 dni, klasa ekonomiczna, wolnych miejsc jest ponad 50, powrót w piątek
        ticket1.updateTotalPrice();
        Assertions.assertEquals(390.0,ticket1.getTotalPrice() , 0.00000001);

        Ticket ticket2 = new Ticket(flight2, FlightClass.BUSINESS, passengerList);
        ticket2.setCurrentTime(date); //wylot w poniedziałek za ponad 30 dni, klasa biznesowa, wolnych miejsc poniżej 20, powrót w sobotę
        ticket2.updateTotalPrice();
        Assertions.assertEquals(360, ticket2.getTotalPrice(), 0.00000001);

    }
}