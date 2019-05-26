package view;

import exception.OverbookingException;
import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class ReservationPage {
    private JFrame frame;
    private Flight flight;
    private JButton reservateButton;
    private JButton calculateButton;
    private JTextField adultsTextField;
    private JTextField youthTextField;
    private JTextField kidsTextField;
    private JTextField babiesTextField;
    private JButton closeButton;
    private JLabel departurePlaceLabel;
    private JLabel departureDateLabel;
    private JLabel arrivalPlaceLabel;
    private JLabel returnlDateLabel;
    private JLabel flightLenghtLabel;
    private JLabel priceLabel;
    private JLabel adultsLabel;
    private JLabel youthLabel;
    private JLabel kidsLabel;
    private JLabel babiesLabel;
    private JLabel numberOfSeats;
    private JRadioButton businessRadioButton;
    private JRadioButton economicRadioButton;
    private ButtonGroup buttonGroup;
    private Ticket ticket;
    private List<Passenger> passengerList = new LinkedList<>();


    public ReservationPage(Flight flight) {
        frame = new JFrame();
        this.flight = flight;

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 175, 500, 350);
        frame.setTitle("Zamów lot");
        frame.setResizable(false);
        frame.setVisible(true);


        ticket = new Ticket(flight, FlightClass.ECONOMIC, passengerList);

        Action close = new AddAction("Zamknij");
        Action reservate = new AddAction("Zamów");
        Action calculate = new AddAction("Oblicz cenę");

        closeButton = new JButton(close);
        reservateButton = new JButton(reservate);
        calculateButton = new JButton(calculate);
        businessRadioButton = new JRadioButton("Klasa biznesowa", false);
        economicRadioButton = new JRadioButton("Klasa ekonomiczna", true);

        buttonGroup = new ButtonGroup();

        buttonGroup.add(businessRadioButton);
        buttonGroup.add(economicRadioButton);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        adultsTextField = new JTextField("0");
        adultsTextField.setMaximumSize(new Dimension(30, 20));
        youthTextField = new JTextField("0");
        youthTextField.setMaximumSize(new Dimension(30, 20));
        kidsTextField = new JTextField("0");
        kidsTextField.setMaximumSize(new Dimension(30, 20));
        babiesTextField = new JTextField("0");
        babiesTextField.setMaximumSize(new Dimension(30, 20));

        departurePlaceLabel = new JLabel("Miejsce wylotu: " + flight.getPlaceOfDeparture() + " (" + flight.getSymbolOfDeparture() + ")");
        arrivalPlaceLabel = new JLabel("Miejsce przylotu: " + flight.getPlaceOfArrival() + " (" + flight.getSymbolOfArrival() + ")");
        departureDateLabel = new JLabel("Data wylotu: " + dateFormat.format(flight.getDepartureDate().getTime()));
        returnlDateLabel = new JLabel("Data powrotu: " + dateFormat.format(flight.getReturnDate().getTime()));
        flightLenghtLabel = new JLabel("Długość lotu: " + flight.getFlightLength() + "h");
        priceLabel = new JLabel("Cena: " + ticket.getTotalPrice());
        adultsLabel = new JLabel("Dorośli (18+):");
        youthLabel = new JLabel("Młodzież (12-18):");
        kidsLabel = new JLabel("Dzieci (2-12):");
        babiesLabel = new JLabel("Niemowlęta (0-2):");
        numberOfSeats = new JLabel("Wybierz ilość miejsc");


        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup()
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(departurePlaceLabel)
                                        .addComponent(arrivalPlaceLabel)
                                        .addComponent(departureDateLabel)
                                        .addComponent(returnlDateLabel)
                                        .addComponent(flightLenghtLabel)
                                        .addComponent(priceLabel)
                                )
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(numberOfSeats)
                                        .addComponent(adultsLabel)
                                        .addComponent(adultsTextField)
                                        .addComponent(youthLabel)
                                        .addComponent(youthTextField)
                                        .addComponent(kidsLabel)
                                        .addComponent(kidsTextField)
                                        .addComponent(babiesLabel)
                                        .addComponent(babiesTextField)
                                        .addComponent(economicRadioButton)
                                        .addComponent(businessRadioButton)
                                )
                        )
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(calculateButton)
                                .addComponent(reservateButton)
                                .addComponent(closeButton))
        );

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup()
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup()
                                        .addComponent(departurePlaceLabel)
                                        .addComponent(arrivalPlaceLabel)
                                        .addComponent(departureDateLabel)
                                        .addComponent(returnlDateLabel)
                                        .addComponent(flightLenghtLabel)
                                        .addComponent(priceLabel)
                                )
                                .addGap(20, 50, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup()
                                        .addComponent(numberOfSeats)
                                        .addComponent(adultsLabel)
                                        .addComponent(adultsTextField)
                                        .addComponent(youthLabel)
                                        .addComponent(youthTextField)
                                        .addComponent(kidsLabel)
                                        .addComponent(kidsTextField)
                                        .addComponent(babiesLabel)
                                        .addComponent(babiesTextField)
                                        .addComponent(economicRadioButton)
                                        .addComponent(businessRadioButton)
                                )
                        )

                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(calculateButton)
                                .addComponent(reservateButton)
                                .addGap(20, 50, Short.MAX_VALUE)
                                .addComponent(closeButton))
        );

        frame.getContentPane().setLayout(groupLayout);

    }

    private void calculatePrice() {
        passengerList.clear();
        if (businessRadioButton.isSelected()) {
            ticket.setFlightClass(FlightClass.BUSINESS);
        } else {
            ticket.setFlightClass(FlightClass.ECONOMIC);
        }
        try {
            int adults = Integer.parseInt(adultsTextField.getText());
            for (int i = 0; i < adults; i++) {
                passengerList.add(new Passenger(TypeOfPassenger.ADULTS));
            }
            int youth = Integer.parseInt(youthTextField.getText());
            for (int i = 0; i < youth; i++) {
                passengerList.add(new Passenger(TypeOfPassenger.YOUTH));
            }
            int kids = Integer.parseInt(kidsTextField.getText());
            for (int i = 0; i < kids; i++) {
                passengerList.add(new Passenger(TypeOfPassenger.KIDS));
            }
            int babies = Integer.parseInt(babiesTextField.getText());
            for (int i = 0; i < babies; i++) {
                passengerList.add(new Passenger(TypeOfPassenger.BABIES));
            }
            ticket.updateTotalPrice();
            priceLabel.setText("Cena: " + ticket.getTotalPrice());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("Wpisano niepoprawną wartość");
        }
    }

    private void overbookingHandler() throws OverbookingException {
        if (passengerList.size() > 110 || flight.getFreeSeats() - passengerList.size() < -10) {
            throw new OverbookingException();
        } else if (passengerList.size() > flight.getFreeSeats()) {
            Object[] options = {"Tak, poproszę", "Nie, dziękuje"};
            int n = JOptionPane.showOptionDialog(frame, "Wolne miejsca się skończyły. Czy chcesz lecieć jako steward/stewardesa otrzymując 20% zniżki?",
                    "Brak wolnch miejsc", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if (n == JOptionPane.YES_OPTION) {
                ticket.setTotalPrice(ticket.getTotalPrice() * 0.8);
            } else if (n == JOptionPane.NO_OPTION) {
                int m = JOptionPane.showOptionDialog(frame, "Czy chcesz lecieć na skrzydle samolotu za 50% ceny?",
                        "Brak wolnych miejsc", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                if (m == JOptionPane.YES_OPTION) {
                    ticket.setTotalPrice(ticket.getTotalPrice() * 0.5);
                }
            }
        }
    }

    private void chargesHandler() {
        JOptionPane.showMessageDialog(frame, "Do ceny zostaje doliczona opłata serwisowa w wysokości 50zl");
        ticket.setTotalPrice(ticket.getTotalPrice() + 50.0);
        Object[] options = {"Tak, poproszę", "Nie, dziękuje"};
        int n = JOptionPane.showOptionDialog(frame, "Czy chcesz wykupić możliwość powiększenia bagażu do 10kg za 120 od osoby?",
                "Powiększenie bagażu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (n == JOptionPane.YES_OPTION) {
            ticket.setTotalPrice(ticket.getTotalPrice() + passengerList.size() * 120.0);
        }
        int m = JOptionPane.showOptionDialog(frame, "Czy chcesz wykupić możliwość rezerwacji posiłku na pokładzie za 40zł za posiłek?",
                "Rezerwacja posiłku na pokładzie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (m == JOptionPane.YES_OPTION) {
            ticket.setTotalPrice(ticket.getTotalPrice() + passengerList.size() * 40.0);
        }
        if (ticket.getFlightClass() == FlightClass.BUSINESS) {
            int k = JOptionPane.showOptionDialog(frame, "Czy chcesz wykupić możliwość pierwszeństwa wsiadania za 25zł od osoby?",
                    "Pierwszeństwo wsiadania", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if (k == JOptionPane.YES_OPTION) {
                ticket.setTotalPrice(ticket.getTotalPrice() + passengerList.size() * 25.0);
            }
        } else {
            int k = JOptionPane.showOptionDialog(frame, "Czy chcesz wykupić możliwość pierwszeństwa wsiadania za 15zł od osoby?",
                    "Pierwszeństwo wsiadania", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if (k == JOptionPane.YES_OPTION) {
                ticket.setTotalPrice(ticket.getTotalPrice() + passengerList.size() * 15.0);
            }
        }
    }

    private void saveTicketToTxt() {
        String path;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }

            public String getDescription() {
                return ".txt";
            }
        });
        int result = fileChooser.showSaveDialog(this.frame.getRootPane());
        path = "";
        if (result == 0) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
            path = path + ".txt";
        }

        ticket.print(path);

    }

    private class AddAction extends AbstractAction {

        public AddAction(String name) {
            this.putValue(AbstractAction.NAME, name);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Zamknij")) {
                frame.dispose();
            } else if (e.getActionCommand().equalsIgnoreCase("Zamów")) {
                calculatePrice();
                try {
                    overbookingHandler();
                    chargesHandler();
                    saveTicketToTxt();
                    JOptionPane.showMessageDialog(frame, "Dziękujemy za zakup biletu lotniczego w „Who says sky is the limit?”" +
                            " Bilet został zapisany!");
                } catch (OverbookingException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (e.getActionCommand().equalsIgnoreCase("Oblicz cenę")) {
                calculatePrice();
            }
        }
    }
}