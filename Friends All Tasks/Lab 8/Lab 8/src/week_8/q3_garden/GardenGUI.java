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

    double priceMowing = 0.00;
    double priceLeafRaking = 0.00;
    double priceTotal = 0.00;


    GardenGUI() {
        setTitle("Garden Service Invoice");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(1000, 600));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        configureDateSpinner();   // Sets up the date spinner for you.


        mowingServiceCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String size = Objects.requireNonNull(gardenSizeComboBox.getSelectedItem()).toString();
                if (mowingServiceCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        priceMowing = 15.15;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        priceMowing = 15.15 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        priceMowing = 15.15 * 3;
                    }
                } else {
                    priceMowing = 0.0;
                }

                mowingServiceCost.setText("$ " + String.valueOf(priceMowing));

                priceTotal = priceMowing + priceLeafRaking;
                String output = String.valueOf(priceTotal);
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
                        priceLeafRaking = 12.25;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        priceLeafRaking = 12.25 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        priceLeafRaking = 12.25 * 3;
                    }
                } else {
                    priceLeafRaking = 0.0;
                }


                if (mowingServiceCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        priceMowing = 15.15;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        priceMowing = 15.15 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        priceMowing = 15.15 * 3;
                    }
                } else {
                    priceMowing = 0.0;
                }


                priceTotal = priceMowing + priceLeafRaking;
                String output = String.valueOf(priceTotal);
                output = '$' + output;

                leafRakingCost.setText("$ " + String.valueOf(priceLeafRaking));
                mowingServiceCost.setText("$ " + String.valueOf(priceMowing));
                invoiceTotal.setText(output);
            }
        });
        leafRakingCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String size = Objects.requireNonNull(gardenSizeComboBox.getSelectedItem()).toString();
                if (leafRakingCheckBox.isSelected()){
                    if (Objects.equals(size, "SMALL")){
                        priceLeafRaking = 12.25;
                    }
                    if (Objects.equals(size, "MEDIUM")){
                        priceLeafRaking = 12.25 * 2;
                    }
                    if (Objects.equals(size, "LARGE")){
                        priceLeafRaking = 12.25 * 3;
                    }
                } else {
                    priceLeafRaking = 0.0;
                }

                leafRakingCost.setText("$ " + String.valueOf(priceLeafRaking));

                priceTotal = priceMowing + priceLeafRaking;
                String output = String.valueOf(priceTotal);
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
                                    "\nLawn mowing service charge: $ " + priceMowing+
                                    "\nLeaf raking service charge: $ " + priceLeafRaking+
                                    "\n" +
                                    "\nTotal: $ " + priceTotal+
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
