package main;

import model.*;
import view.Homepage;

import java.util.*;

public class main {
    public static void main(String[] args) {

        Calendar f1d = Calendar.getInstance();
        f1d.set(2019,Calendar.MAY,30);
        Calendar f1r = Calendar.getInstance();
        f1r.set(2019, Calendar.JULY, 19);
        Calendar f2d = Calendar.getInstance();
        f2d.set(2019,Calendar.JULY,1);
        Calendar f2r = Calendar.getInstance();
        f2r.set(2019, Calendar.JULY, 14);
        Calendar f3d = Calendar.getInstance();
        f3d.set(2019,Calendar.JULY,25);
        Calendar f3r = Calendar.getInstance();
        f3r.set(2019, Calendar.AUGUST, 14);
        Calendar f4d = Calendar.getInstance();
        f4d.set(2019,Calendar.JUNE,2);
        Calendar f4r = Calendar.getInstance();
        f4r.set(2019, Calendar.JUNE, 14);
        Calendar f5d = Calendar.getInstance();
        f5d.set(2019,Calendar.AUGUST,1);
        Calendar f5r = Calendar.getInstance();
        f5r.set(2019, Calendar.AUGUST, 14);
        Calendar f6d = Calendar.getInstance();
        f6d.set(2019,Calendar.JUNE,19);
        Calendar f6r = Calendar.getInstance();
        f6r.set(2019, Calendar.JULY, 3);
        Calendar f7d = Calendar.getInstance();
        f7d.set(2019,Calendar.JULY,7);
        Calendar f7r = Calendar.getInstance();
        f2r.set(2019, Calendar.JULY, 14);
        Calendar f8d = Calendar.getInstance();
        f8d.set(2019,Calendar.JULY,14);
        Calendar f8r = Calendar.getInstance();
        f8r.set(2019, Calendar.JULY, 28);
        Calendar f9d = Calendar.getInstance();
        f9d.set(2019,Calendar.JULY,17);
        Calendar f9r = Calendar.getInstance();
        f9r.set(2019, Calendar.JULY, 21);
        Calendar f10d = Calendar.getInstance();
        f10d.set(2019,Calendar.AUGUST,29);
        Calendar f10r = Calendar.getInstance();
        f10r.set(2019, Calendar.SEPTEMBER, 14);

        List<Flight> flightList = Arrays.asList(
        new Flight("Warszawa", "WAW", "Kraków", "KRK",f1d,f1r, 1, 100, 150.0 ),
        new Flight("Gdańsk", "GDA", "Warszawa", "WAW",f2d,f2r, 1, 100, 100.0 ),
        new Flight("Warszawa", "WAW", "Lublin", "LBN",f3d,f3r, 1, 100, 75.0 ),
        new Flight("Warszawa", "WAW", "Pekin", "PEK",f4d,f4r, 10, 100, 500.0 ),
        new Flight("Kraków", "KRK", "Pekin", "PEK",f5d,f5r, 11, 100, 550.0 ),
        new Flight("Tokio", "HND", "Warszawa", "WAW",f6d,f6r, 9, 100, 450.0 ),
        new Flight("Dallas", "DFW", "Kraków", "KRK",f7d,f7r, 14, 100, 700.0 ),
        new Flight("Warszawa", "WAW", "Frankfurt", "FRA",f8d,f8r, 2, 100, 200.0 ),
        new Flight("Londyn", "LHR", "Lublin", "LBN",f9d,f9r, 4, 100, 350.0 ),
        new Flight("Paryż", "CDG", "Kraków", "KRK",f10d,f10r, 2, 100, 250.0 )
    );


        new Homepage(flightList).setVisible(true);


    }


}