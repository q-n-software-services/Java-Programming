package week_8.q1_blood_donor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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


        checkEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ageInput = ageTextField.getText();
                String weightInput = weightTextField.getText();

                try {
                    double userAge = Double.parseDouble(ageInput);
                    double userWeight = Double.parseDouble(weightInput);

                    if (userAge >= 17 && userWeight >= 110) {
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
