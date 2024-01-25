package week_8.q3_garden;

import week_8.q3_garden.InvoiceWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * See the Lab 8 Questions.md file for the instructions.
 * Don't forget to add screenshots of your program's GUI running.
 * Save the screenshots in the screenshots directory of this project.
 */

public class GardenGUI extends JFrame {
    JPanel mainPanel;
    JPanel dataEntryPanel;
    JPanel invoicePreviewPanel;

    JTextArea invoicePreviewTextArea;
    JButton saveInvoiceButton;
    JTextField customerNameTextField;
    JTextField customerAddressTextField;
    JButton generateInvoicePreviewButton;
    JSpinner serviceDateSpinner;
    JCheckBox mowingServiceCheckBox;
    JLabel mowingServiceCost;
    private JComboBox gardenSizeComboBox;
    private JCheckBox leafRakingCheckBox;
    private JLabel leafRakingCost;
    private JLabel invoiceTotal;

    double costMowing = 0.00;
    double costLeafRaking = 0.00;
    double costTotal = 0.00;


    GardenGUI() {
        setTitle("Garden Service Invoice");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(1000, 600));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        configureDateSpinner();   // Sets up the date spinner for you.




        // TODO add event handlers here
        // Remember to use an **Item Changed** listener (not an action listener) for the JCheckBox components.
        // Don't write all of your code in this constructor. You should create methods for different tasks.

        mowingServiceCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String size = Objects.requireNonNull(gardenSizeComboBox.getSelectedItem()).toString();
                if (mowingServiceCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        costMowing = 15.15;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        costMowing = 15.15 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        costMowing = 15.15 * 3;
                    }
                } else {
                    costMowing = 0.0;
                }

                mowingServiceCost.setText("$ " + String.valueOf(costMowing));

                costTotal = costMowing + costLeafRaking;
                String output = String.valueOf(costTotal);
                output = '$' + output;
                invoiceTotal.setText(output);

            }
        });
        gardenSizeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String size = Objects.requireNonNull(gardenSizeComboBox.getSelectedItem()).toString();


                if (leafRakingCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        costLeafRaking = 12.25;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        costLeafRaking = 12.25 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        costLeafRaking = 12.25 * 3;
                    }
                } else {
                    costLeafRaking = 0.0;
                }


                if (mowingServiceCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        costMowing = 15.15;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        costMowing = 15.15 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        costMowing = 15.15 * 3;
                    }
                } else {
                    costMowing = 0.0;
                }


                costTotal = costMowing + costLeafRaking;
                String output = String.valueOf(costTotal);
                output = '$' + output;

                leafRakingCost.setText("$ " + String.valueOf(costLeafRaking));
                mowingServiceCost.setText("$ " + String.valueOf(costMowing));
                invoiceTotal.setText(output);
            }
        });
        leafRakingCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String size = Objects.requireNonNull(gardenSizeComboBox.getSelectedItem()).toString();
                if (leafRakingCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        costLeafRaking = 12.25;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        costLeafRaking = 12.25 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        costLeafRaking = 12.25 * 3;
                    }
                } else {
                    costLeafRaking = 0.0;
                }

                leafRakingCost.setText("$ " + String.valueOf(costLeafRaking));

                costTotal = costMowing + costLeafRaking;
                String output = String.valueOf(costTotal);
                output = '$' + output;
                invoiceTotal.setText(output);

            }
        });
        generateInvoicePreviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String customerName = customerNameTextField.getText().trim();
                String customerAddress = customerAddressTextField.getText().trim();

                if (!leafRakingCheckBox.isSelected() && !mowingServiceCheckBox.isSelected()) {
                    showMessageDialog("You haven't selected any services.\n Please select a service to Continue", "NO SERVICE SELECTED!", 2);
                    invoicePreviewTextArea.setText("");
                } else if (customerName.isEmpty() || customerAddress.isEmpty()) {
                    showMessageDialog("Please enter a CUSTOMER NAME and ADDRESS to CONTINUE!.", "CHECK NAME and ADDRESS!", 2);
                    invoicePreviewTextArea.setText("");
                } else {
                    SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
                    String spinnerValue = formater.format(serviceDateSpinner.getValue());
//        System.out.println(spinnerValue);

                    String invoiceTemplate =
                            "************ Garden Services Invoice ************" +
                                    "\n" +
                                    "\nRose the Gardener, 123 Main Street, Minneapolis. Telephone 612-123-4567" +
                                    "\n" +
                                    "\nCustomer Name: " + customerNameTextField.getText() +
                                    "\nAddress of garden: " + customerAddressTextField.getText()+
                                    "\n" +
                                    "\nDate of service: " + spinnerValue+
                                    "\nSize of garden: " + Objects.requireNonNull(gardenSizeComboBox.getSelectedItem()).toString()+
                                    "\n" +
                                    "\nLawn mowing service charge: $ " + costMowing+
                                    "\nLeaf raking service charge: $ " + costLeafRaking+
                                    "\n" +
                                    "\nTotal: $ " + costTotal+
                                    "\n" +
                                    "\nPlease send payment to the address above." +
                                    "\nThank you for your business.";

                    invoicePreviewTextArea.setText(invoiceTemplate);
                }




            }
        });
        saveInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (invoicePreviewTextArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please generate an invoice preview first.");
                    showMessageDialog("Please generate an invoice preview first inorder to CONTINUE!.", "Please Generate Invoice!", 2);

                } else {
                    String customerName = customerNameTextField.getText().trim();
                    SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
                    String spinnerValue = formater.format(serviceDateSpinner.getValue());

                    String filename = InvoiceWriter.createFileName(customerName, spinnerValue);

                    InvoiceWriter.writeToFile(filename, invoicePreviewTextArea.getText());
                }





            }
        });
    }
    // 1 : Information Message
    // 2 : Warning Message

    // TODO use this method to show an alert dialog
    // type can be JOptionPane.ERROR_MESSAGE, or JOptionPane.INFORMATION_MESSAGE
    void showMessageDialog(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }



    // You don't need to modify this method.
    private void configureDateSpinner() {

        // Dates between Jan 1, 1970 and some time in 2920. I don't suppose this program will be around this long though...
        SpinnerDateModel spinnerDateModel = new SpinnerDateModel(new Date(), new Date(0), new Date(30000000000000L), Calendar.DAY_OF_YEAR);
        serviceDateSpinner.setModel(spinnerDateModel);
        // Create a DateEditor to configure the way dates are displayed and edited
        // Define format the dates will have - month, day, year
        JSpinner.DateEditor editor = new JSpinner.DateEditor(serviceDateSpinner, "MM-dd-yyyy");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        // Attempt to prevent invalid input
        formatter.setAllowsInvalid(false);
        // Allow user to type as well as use up/down buttons
        formatter.setOverwriteMode(true);
        // And tell the serviceDataSpinner to use this Editor
        serviceDateSpinner.setEditor(editor);

    }

}
