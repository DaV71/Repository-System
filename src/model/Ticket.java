package model;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Ticket {
    private Flight flight;
    private FlightClass flightClass;
    private List<Passenger> passengers;
    private double totalPrice;
    private Calendar currentTime;

    public Ticket() {
    }

    public Ticket(Flight flight, FlightClass flightClass, List<Passenger> passengers) {
        this.flight = flight;
        this.flightClass = flightClass;
        this.passengers = passengers;
        this.totalPrice = flight.getPrice() * passengers.size();
        this.currentTime = Calendar.getInstance();
        this.currentTime.setTimeInMillis(System.currentTimeMillis());
        this.flight.setNumberOfPassengers(passengers.size());

    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void priceCalculate() {
        if (flightClass == FlightClass.BUSINESS) {
            totalPrice += 0.65 * flight.getPrice() * passengers.size();
        }
        if (flight.getReturnDate().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            totalPrice += 0.1 * flight.getPrice() * passengers.size();
        }
        if (flight.getDepartureDate().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            totalPrice += 0.1 * flight.getPrice() * passengers.size();
        }
        if ((flight.getDepartureDate().getTimeInMillis() - currentTime.getTimeInMillis()) > (1000 * 60 * 60 * 24 * 30)) {
            totalPrice -= 0.1 * flight.getPrice() * passengers.size();
        }
        if ((flight.getDepartureDate().getTimeInMillis() - currentTime.getTimeInMillis()) < (1000 * 60 * 60 * 24 * 7)) {
            totalPrice += 0.2 * flight.getPrice() * passengers.size();
        }

    }

    @Override
    public String toString() {
        priceCalculate();
        int[] tab = new int[6];
        for (int i = 0; i < 6; i++) {
            tab[i] = new Random().nextInt(10);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "******************************************************" +
                "\nIdentyfikator rezerwacji: " + flight.getSymbolOfDeparture() + flight.getSymbolOfArrival() + tab[0] + tab[1] + tab[2] + tab[3] + tab[4] + tab[5] +
                "\nMiejsce wylotu: " + flight.getPlaceOfDeparture() + " " + flight.getSymbolOfDeparture() +
                "\n Miejsce przylotu: " + flight.getPlaceOfArrival() + " " + flight.getSymbolOfArrival() +
                "\n Data wylotu: " + dateFormat.format(flight.getDepartureDate().getTime()) +
                "\n Data powrotu: " + dateFormat.format(flight.getReturnDate().getTime()) +
                "\n Cena: " + totalPrice +
                "\n******************************************************";
    }
}
