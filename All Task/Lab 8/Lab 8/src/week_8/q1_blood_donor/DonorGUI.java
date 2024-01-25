package week_8.q1_blood_donor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * See the Lab 8 Questions.md file for the instructions.
 * Don't forget to add screenshots of your program's GUI running.
 * Save the screenshots in the screenshots directory of this project.
 */


public class DonorGUI extends JFrame {


    public JPanel mainPanel = new JPanel();
    private JTextField weightTextField;
    private JTextField ageTextField;
    private JButton checkEligibilityButton = new JButton();
    private JLabel resultLabel;
    public static final String ELIGIBLE = "Eligible!";
    public static final String NOT_ELIGIBLE = "Sorry, not eligible.";
    public static final String INPUT_ERROR = "Error - enter positive numbers";

    public DonorGUI() {
        setTitle("Blood Donor Eligibility");
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // TODO add a listener for your new checkEligibilityButton.
        // This should verify that the user has entered a positive double number
        // in both the weightTextField and ageTextField JTextField
        // If either or both are not valid (nothing entered, or not a number),
        // the resultLabel should display the INPUT_ERROR text.

        // If both weight and age are positive double numbers, use the data
        // to decide if the user is eligible to be a blood donor.
        // To be eligible, a person must be 17 or older,
        // AND weigh 110 lbs or more.

        // Display the ELIGIBLE text in resultLabel if they are eligible.
        // Display the NOT_ELIGIBLE text in resultLabel if they are not eligible.

        // Use the constants ELIGIBLE, NOT_ELIGIBLE, and INPUT_ERROR, provided in this file,
        // to display the text in resultLabel.

        checkEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ageStr = ageTextField.getText();
                String weightStr = weightTextField.getText();

                try {
                    double age = Double.parseDouble(ageStr);
                    double weight = Double.parseDouble(weightStr);

                    if (age >= 17 && weight >= 110) {
                        resultLabel.setText(ELIGIBLE);
                    } else {
                        resultLabel.setText(NOT_ELIGIBLE);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText(INPUT_ERROR);
                }
            }
        });
    }

}
