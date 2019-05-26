package model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
        this.currentTime = Calendar.getInstance();
        this.currentTime.setTimeInMillis(System.currentTimeMillis());
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

    public Calendar getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }

    private void priceCalculate() {
        totalPrice = flight.getPrice() * passengers.size();
        if (flightClass == FlightClass.BUSINESS) {
            totalPrice += 0.65 * flight.getPrice() * passengers.size();
        }
        if (flight.getReturnDate().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            totalPrice += 0.1 * flight.getPrice() * passengers.size();
        }
        if (flight.getDepartureDate().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            totalPrice += 0.1 * flight.getPrice() * passengers.size();
        }
        long month = 1000 * 60L * 60 * 24 * 30;
        long week = 1000 * 60L * 60 * 24 * 7;
        if ((flight.getDepartureDate().getTimeInMillis() - currentTime.getTimeInMillis()) > month) {
            totalPrice -= 0.1 * flight.getPrice() * passengers.size();
        }else if ((flight.getDepartureDate().getTimeInMillis() - currentTime.getTimeInMillis()) < week) {
            totalPrice += 0.2 * flight.getPrice() * passengers.size();
        }
        if(flight.getFreeSeats()<20){
            totalPrice+=0.25 * flight.getPrice() * passengers.size();
        }else if(flight.getFreeSeats()<50){
            totalPrice+=0.1 * flight.getPrice() * passengers.size();
        }

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "flight=" + flight +
                ", flightClass=" + flightClass +
                ", passengers=" + passengers +
                ", totalPrice=" + totalPrice +
                ", currentTime=" + currentTime +
                '}';
    }

    public void updateTotalPrice(){
        this.priceCalculate();
    }

    public void print(String path){
        this.flight.setFreeSeats(flight.getFreeSeats()-passengers.size());
        int[] tab = new int[6];
        for (int i = 0; i < 6; i++) {
            tab[i] = new Random().nextInt(10);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        File file = new File(path);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("******************************************************");
            bufferedWriter.newLine();
            bufferedWriter.write("Identyfikator rezerwacji: " + flight.getSymbolOfDeparture() + flight.getSymbolOfArrival() + tab[0] + tab[1] + tab[2] + tab[3] + tab[4] + tab[5]);
            bufferedWriter.newLine();
            bufferedWriter.write("Miejsce wylotu: " + flight.getPlaceOfDeparture() + " " + flight.getSymbolOfDeparture());
            bufferedWriter.newLine();
            bufferedWriter.write("Miejsce przylotu: " + flight.getPlaceOfArrival() + " " + flight.getSymbolOfArrival());
            bufferedWriter.newLine();
            bufferedWriter.write("Data wylotu: " + dateFormat.format(flight.getDepartureDate().getTime()));
            bufferedWriter.newLine();
            bufferedWriter.write("Data powrotu: " + dateFormat.format(flight.getReturnDate().getTime()));
            bufferedWriter.newLine();
            bufferedWriter.write("Cena: " + totalPrice + " PLN");
            bufferedWriter.newLine();
            bufferedWriter.write("******************************************************");
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
