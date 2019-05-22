package controller;

import model.*;

import java.time.LocalDate;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();
        c1.set(2019,Calendar.MAY,26);
        Calendar c2 = Calendar.getInstance();
        c2.set(2019, Calendar.OCTOBER, 19);
        Flight flight1 = new Flight("Warszawa", "WAW", "Kraków", "KRK",c1,c2, 1, 100, 150.0 );
        Passenger passenger1 = new Passenger(TypeOfPassenger.ADULTS);
        /*Passenger passenger2 = new Passenger(TypeOfPassenger.YOUTH);
        Passenger passenger3 = new Passenger(TypeOfPassenger.KIDS);
        Passenger passenger4 = new Passenger(TypeOfPassenger.BABIES);*/
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger1);
        /*passengers.add(passenger2);
        passengers.add(passenger3);
        passengers.add(passenger4);*/
        Ticket ticket = new Ticket(flight1, FlightClass.BUSINESS, passengers);

        System.out.println(ticket);
    }


}