package view;

import model.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FlightsListPage {
    private JFrame frame;
    private List<Flight> flightList;
    private JButton openButton;
    private JTextArea textArea;
    private JTextField textField;
    private JButton closeButton;
    private JLabel label;
    private final static String EMPTY_LIST = "Nie wyszukano lotów spełniających wymagania";

    public FlightsListPage(List<Flight> flightList) {
        frame = new JFrame();
        this.flightList = flightList;

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 700, 400);
        frame.setTitle("Lista lotów");
        frame.setResizable(false);
        frame.setVisible(true);

        Action close = new AddAction("Zamknij");
        Action open = new AddAction("Otwórz");

        closeButton = new JButton(close);
        openButton = new JButton(open);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(30, 20));
        label = new JLabel("Podaj numer lotu który Cię interesuje");


        int id = 1;
        if (flightList.size() == 0) {
            textArea.append(EMPTY_LIST);
        } else {
            for (Flight f : flightList) {
                textArea.append((id++) + ". " + f.toString() + "\n");
            }
        }


        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(textArea)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(label)
                                .addComponent(textField)
                                .addComponent(openButton)
                                .addComponent(closeButton))
        );

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup()
                        .addComponent(textArea)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(label)
                                .addComponent(textField)
                                .addComponent(openButton)
                                .addGap(20,50,Short.MAX_VALUE)
                                .addComponent(closeButton))
        );

        frame.getContentPane().setLayout(groupLayout);

    }

    private void openFlight(Flight flight) {
        new ReservationPage(flight);
    }

    private class AddAction extends AbstractAction {

        public AddAction(String name) {
            this.putValue(AbstractAction.NAME, name);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Zamknij")) {
                frame.dispose();
            } else if (e.getActionCommand().equalsIgnoreCase("Otwórz")) {
                try {
                    int temp = Integer.parseInt(textField.getText());
                    if (temp > 0 && temp <= flightList.size()) {
                        openFlight(flightList.get(temp - 1));
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    System.out.println("Wpisano niepoprawną wartość");
                }
            }
        }
    }
}
