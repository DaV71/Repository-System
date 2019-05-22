package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Flight {
    private String placeOfDeparture;
    private String symbolOfDeparture;
    private String placeOfArrival;
    private String symbolOfArrival;
    private Calendar departureDate;
    private Calendar returnDate;
    private int flightLength;
    private int numberOfPassengers;
    private double price;

    public Flight() {
    }

    public Flight(String placeOfDeparture, String symbolOfDeparture, String placeOfArrival, String symbolOfArrival, Calendar departureDate, Calendar returnDate, int flightLength, int numberOfPassengers, double price) {
        this.placeOfDeparture = placeOfDeparture;
        this.symbolOfDeparture = symbolOfDeparture;
        this.placeOfArrival = placeOfArrival;
        this.symbolOfArrival = symbolOfArrival;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.flightLength = flightLength;
        this.numberOfPassengers = numberOfPassengers;
        this.price = price;
    }

    public String getPlaceOfDeparture() {
        return placeOfDeparture;
    }

    public void setPlaceOfDeparture(String placeOfDeparture) {
        this.placeOfDeparture = placeOfDeparture;
    }

    public String getSymbolOfDeparture() {
        return symbolOfDeparture;
    }

    public void setSymbolOfDeparture(String symbolOfDeparture) {
        this.symbolOfDeparture = symbolOfDeparture;
    }

    public String getPlaceOfArrival() {
        return placeOfArrival;
    }

    public void setPlaceOfArrival(String placeOfArrival) {
        this.placeOfArrival = placeOfArrival;
    }

    public String getSymbolOfArrival() {
        return symbolOfArrival;
    }

    public void setSymbolOfArrival(String symbolOfArrival) {
        this.symbolOfArrival = symbolOfArrival;
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    public int getFlightLength() {
        return flightLength;
    }

    public void setFlightLength(int flightLength) {
        this.flightLength = flightLength;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "placeOfDeparture='" + placeOfDeparture + '\'' +
                ", symbolOfDeparture='" + symbolOfDeparture + '\'' +
                ", placeOfArrival='" + placeOfArrival + '\'' +
                ", symbolOfArrival='" + symbolOfArrival + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", flightLength=" + flightLength +
                ", numberOfPassengers=" + numberOfPassengers +
                ", price=" + price +
                '}';
    }
}
