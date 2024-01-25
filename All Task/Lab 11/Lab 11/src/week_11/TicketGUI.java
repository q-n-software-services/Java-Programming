package week_11;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class TicketGUI extends JFrame {
    
    // TODO complete the tasks described in Lab 11 Questions.md

    // You don't need to modify the form or the GUI design in TicketGUI.form
    
    protected JPanel mainPanel;
    
    // Components for adding tickets
    protected JPanel addTicketPanel;
    protected JTextField descriptionTextField;
    protected JTextField reporterTextField;
    protected JComboBox priorityComboBox;
    protected JButton addButton;
    
    // Components for displaying ticket list
    protected JPanel ticketListPanel;
    protected JList<Ticket> ticketList;
    protected JLabel ticketListStatusDescription;
    
    // Components for searching
    protected JPanel searchPanel;
    protected JTextField descriptionSearchTextBox;
    protected JTextField idSearchTextBox;
    protected JButton searchDescriptionButton;
    protected JButton searchIdButton;
    protected JButton showAllOpenTicketsButton;
    
    // quit button and JPanel container
    protected JPanel controlsPanel;
    protected JButton quitButton;
    
    // Resolving
    protected JButton resolveSelectedButton;

    // TODO initialize this DefaultListModel in the constructor
    //  Use this DefaultListModel for the JList ticketList
    protected DefaultListModel ticketListModel;

    // Strings for messages that will be shown in ticketListStatusDescription
    // TODO Use these instead of your own Strings. The tests expect you to use these constants.
    static final String ALL_TICKETS = "Showing all open tickets";
    static final String TICKETS_MATCHING_SEARCH_DESCRIPTION = "Open tickets matching search description";
    static final String TICKET_MATCHING_ID = "Ticket matching ID";
    static final String NO_TICKETS_FOUND = "No matching tickets";
    static final String INVALID_TICKET_ID = "Invalid ticket ID";
    
    
    // A reference to the TicketController object
    // This GUI will be able to call the methods in this class to add, search for, and update tickets.
    // See example in quitProgram method.
    private TicketController controller;
    
    
    TicketGUI(TicketController controller) {


        this.controller = controller;



        /* In your code, when you need to send
        a message to the TicketProgram controller, use this controller object. So if you need
        to add a new ticket, you'll create a new Ticket object, then ask the TicketProgram controller
        to add the new Ticket to the database with
        controller.newTicket(myNewTicket);  */

        // GUI window setup and configuration
        setTitle("Support Ticket Manager");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(700, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // TODO configure JComboBox

        // TODO Configure JList, JList model

        // TODO add action listeners for each button

        // TODO show all of the open tickets in priority order in the JList

        // Create the ticketListModel
        ticketListModel = new DefaultListModel<>();

        // Create the ticketList with the ticketListModel
        ticketList = new JList<>(ticketListModel);
        ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ticketList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        JScrollPane ticketListScrollPane = new JScrollPane(ticketList);
        ticketListScrollPane.setBorder(BorderFactory.createTitledBorder("Ticket List"));

        // Add the ticketListScrollPane to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(ticketListScrollPane, BorderLayout.CENTER);

        setVisible(true);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionTextField.getText().trim();
                String reporter = reporterTextField.getText().trim();
                int priority = (int) priorityComboBox.getSelectedItem();

                if (description.isEmpty() || reporter.isEmpty() || priority == 0) {
                    JOptionPane.showMessageDialog(TicketGUI.this, "Please enter all the required data.",
                            "Data Missing", JOptionPane.WARNING_MESSAGE);
                } else {
                    Ticket newTicket = new Ticket(description,  priority, reporter, new Date());
                    try {
                        controller.addTicket(newTicket);
                        List openTickets = (List) controller.ticketStore.getAllOpenTickets();
                        setTicketList(openTickets);
                    } catch (SQLException ex) {
                        showMessageDialog("Error adding ticket to the store. \nDatabase Error");
                    }
                }
            }
        });

        // Configure ticketList to display open tickets
        List openTickets = (List) controller.loadAllOpenTicketsFromStore();
        setTicketList(openTickets);

        // Add listener to showAllTicketsButton

        showAllOpenTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List allOpenTickets = (List) controller.loadAllOpenTicketsFromStore();
                setTicketList(allOpenTickets);
            }
        });

        // Add listener to searchIdButton
        searchIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idSearchTextBox.getText().trim();

                if (idText.isEmpty()) {
                    ticketListModel.clear();
                    ticketListStatusDescription.setText("INVALID_TICKET_ID");
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idText);
                    if (id <= 0) {
                        ticketListModel.clear();
                        ticketListStatusDescription.setText("INVALID_TICKET_ID");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    ticketListModel.clear();
                    ticketListStatusDescription.setText("INVALID_TICKET_ID");
                    return;
                }

                Ticket ticket = TicketStore.getTicketById(id);
                if (ticket != null) {
                    ticketListModel.clear();
                    ticketListModel.addElement(ticket);
                    ticketListStatusDescription.setText("TICKET_MATCHING_ID");
                } else {
                    ticketListModel.clear();
                    ticketListStatusDescription.setText("NO_TICKETS_FOUND");
                }
            }

            });


        searchDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionSearchTextBox.getText().trim();

                if (description.isEmpty()) {
                    ticketListModel.clear();
                    ticketListStatusDescription.setText("NO_TICKETS_FOUND");
                    return;
                }

                List matchingTickets = (List) TicketStore.searchTicketsByDescription(description);

                ticketListModel.clear();
                if (matchingTickets.getItemCount() > 0) {

                    for (int i = 0; i < matchingTickets.getItemCount(); i++) {
                        String ticket = matchingTickets.getItem(i);
                        ticketListModel.addElement(ticket);
                    }
                    ticketListStatusDescription.setText("");
                } else {
                    ticketListStatusDescription.setText("NO_TICKETS_FOUND");
                }

            }
        });

        // Add listener to resolveSelectedButton
        resolveSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = ticketList.getSelectedIndex();

                // Check if a ticket is selected
                if (selectedIndex == -1) {
                    showMessageDialog("No ticket selected\nNo Ticket Selected");
                    return;
                }

                Ticket selectedTicket = (Ticket) ticketListModel.getElementAt(selectedIndex);

                // Check if the selected ticket is already resolved
                if (selectedTicket.getStatus().equals(Ticket.TicketStatus.RESOLVED)) {
                    showMessageDialog("This ticket is already resolved\nTicket Already Resolved");
                    return;
                }

                // Show input dialog for resolution
                String resolution = showInputDialog("Enter resolution\nResolve Ticket");

                // Check if the user canceled or entered null
                if (resolution == null) {
                    return;
                }

                // Update the ticket with resolution and status
                selectedTicket.setResolution(resolution);
                selectedTicket.setDateResolved(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())));
                selectedTicket.setStatus(Ticket.TicketStatus.RESOLVED);

                // Update the ticket in the database
                controller.updateTicket(selectedTicket);

                // Remove the resolved ticket from the list
                ticketListModel.removeElement(selectedTicket);

                // Show confirmation message
                showMessageDialog("Ticket resolved successfully\nTicket Resolved");

                // Update ticketList and status description
                List openTickets = (List) TicketStore.getAllOpenTickets();



                ticketListModel.clear();
                if (openTickets.getItemCount() > 0) {

                    for (int i = 0; i < openTickets.getItemCount(); i++) {
                        String ticket = openTickets.getItem(i);
                        ticketListModel.addElement(ticket);
                    }
                    ticketListStatusDescription.setText("");
                } else {
                    ticketListStatusDescription.setText("");
                }

                ticketListStatusDescription.setText("ALL TICKETS");

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitProgram();
            }
        });
    }


    public void setTicketList(List tickets) {
        // Sort the tickets in priority order (most urgent first)
        try {
            Collections.sort(tickets, Comparator.comparingInt(Ticket::getPriority));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Clear the existing ticketListModel
        ticketListModel.clear();

        // Add the tickets to the ticketListModel
        for (int i = 0; i < tickets.getItemCount(); i++) {
            String ticket = tickets.getItem(i);
            ticketListModel.addElement(ticket);
        }

    }


    // Call this method to quit the program.
    // Don't modify or delete this method.
    protected void quitProgram() {
        controller.quitProgram();    // Ask the controller to quit the program.
    }
    
    // Use this method if you need to show a message dialog displaying the message given.
    // Otherwise tests for code that shows alert dialogs will time out and fail.
    // Don't modify or delete this method.
    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    // Use this method if you need to show input dialogs asking the given question.
    // Otherwise tests for code that shows input dialogs will time out and fail.
    // If user presses the cancel button, this method will return null.
    // Don't modify or delete this method.
    protected String showInputDialog(String question) {
        return JOptionPane.showInputDialog(this, question);
    }
    
}


