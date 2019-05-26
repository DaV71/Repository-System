package view;

import model.Flight;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.List;
import java.util.stream.Collectors;

public class Homepage extends JFrame {
    private JButton searchButton;
    private JTextField departurePlaceField;
    private JTextField lowerPriceField;
    private JTextField upperPriceField;
    private JLabel departurePlaceLabel;
    private JLabel dateLabel;
    private JLabel priceLabel;
    private JLabel beetwenPriceLabel;
    private String departurePlace;
    private Date departureDate;
    private int lowerPrice;
    private int upperPrice;
    private List<Flight> flightList;
    final UtilDateModel model = new UtilDateModel();
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;


    public Homepage(List<Flight> flightList) {
        this.flightList = flightList;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 400);
        this.setTitle("Who says sky is the limit?");
        this.setResizable(false);

        Action search = new AddAction("Wyszukaj");
        searchButton = new JButton(search);

        GroupLayout groupLayout = new GroupLayout(this.getContentPane());
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        Properties p = new Properties();
        p.put("text.today", "Dzisiaj");
        p.put("text.month", "Miesiąc");
        p.put("text.year", "Rok");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        departurePlaceLabel = new JLabel("Miejsce wylotu");
        dateLabel = new JLabel("Data wylotu");
        priceLabel = new JLabel("Cena");
        beetwenPriceLabel = new JLabel("-");

        departurePlaceField = new JTextField();
        lowerPriceField = new JTextField();
        lowerPriceField.setText("0");
        upperPriceField = new JTextField();
        upperPriceField.setText("99999");


        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()

                        .addGroup(
                                groupLayout.createParallelGroup()
                                        .addComponent(departurePlaceLabel)
                                        .addComponent(departurePlaceField)
                                        .addComponent(dateLabel)
                                        .addComponent(datePicker)
                                        .addComponent(priceLabel)
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(lowerPriceField)
                                                        .addComponent(beetwenPriceLabel)
                                                        .addComponent(upperPriceField)
                                        )

                                        .addGap(30, 30, Short.MAX_VALUE)
                                        .addComponent(searchButton)
                        )


        );

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(departurePlaceLabel)
                                        .addGap(5, 5, Short.MAX_VALUE)
                                        .addComponent(departurePlaceField)
                                        .addGap(15, 15, Short.MAX_VALUE)
                                        .addComponent(dateLabel)
                                        .addGap(5, 5, Short.MAX_VALUE)
                                        .addComponent(datePicker)
                                        .addGap(15, 15, Short.MAX_VALUE)
                                        .addComponent(priceLabel)
                                        .addGap(5, 5, Short.MAX_VALUE)
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(lowerPriceField)
                                                        .addComponent(beetwenPriceLabel)
                                                        .addComponent(upperPriceField)
                                        )

                                        .addGap(30, 30, Short.MAX_VALUE)
                                        .addComponent(searchButton)
                        )


        );

        this.getContentPane().setLayout(groupLayout);

    }

    private void searchFlight(List<Flight> flightList) {
        departurePlace = departurePlaceField.getText();
        departureDate = (Date) datePicker.getModel().getValue();
        Calendar departureCalDate = Calendar.getInstance();
        if (departureDate != null) {
            departureCalDate.setTime(departureDate);
        }
        try {
            lowerPrice = Integer.parseInt(lowerPriceField.getText());
            upperPrice = Integer.parseInt(upperPriceField.getText());
            List<Flight> filteredFlights = flightList.stream()
                    .filter(flight -> flight.getPlaceOfDeparture().toLowerCase().contains(departurePlace.toLowerCase()) || departurePlace == null || "".equals(departurePlace))
                    .filter(flight -> flight.getDepartureDate().getTimeInMillis() >= departureCalDate.getTimeInMillis() - 86400000 && flight.getDepartureDate().getTimeInMillis() <= departureCalDate.getTimeInMillis() || departureDate == null)
                    .filter(flight -> flight.getPrice() >= lowerPrice)
                    .filter(flight -> flight.getPrice() <= upperPrice)
                    .collect(Collectors.toList());
            showList(filteredFlights);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("Wpisano niepoprawną wartość");
        }
    }

    private void showList(List<Flight> flightList) {
        new FlightsListPage(flightList);
    }

    private class AddAction extends AbstractAction {

        public AddAction(String name) {
            this.putValue(AbstractAction.NAME, name);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            searchFlight(flightList);
        }
    }

    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}


