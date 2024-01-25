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

    protected JPanel mainPanel;
    
    // Components for adding tickets
    protected JPanel addTicketPanel;
    protected JTextField descriptionTextField;
    protected JTextField reporterTextField;
    protected JComboBox priorityComboBox;
    protected JButton addButton;
    
    // Components for displaying ticket list
    protected JPanel myTicketsListPanel;
    protected JList<Ticket> myTicketsList;
    protected JLabel myTicketsListStatusDescription;
    
    // Components for searching
    protected JPanel searchPanel;
    protected JTextField descriptionSearchTextBox;
    protected JTextField idSearchTextBox;
    protected JButton searchDescriptionButton;
    protected JButton searchIdButton;
    protected JButton showAllOpenTicketsButton;
    
    protected JPanel controlsPanel;
    protected JButton quitButton;
    
    protected JButton resolveSelectedButton;

    protected DefaultListModel myTicketsListModel;

    static final String ALL_TICKETS = "Showing all open tickets";
    static final String TICKETS_MATCHING_SEARCH_DESCRIPTION = "Open tickets matching search description";
    static final String TICKET_MATCHING_ID = "Ticket matching ID";
    static final String NO_TICKETS_FOUND = "No matching tickets";
    static final String INVALID_TICKET_ID = "Invalid ticket ID";
    

    private TicketController myController;
    
    
    TicketGUI(TicketController myController) {


        this.myController = myController;


        setTitle("Support Ticket Manager");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(700, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        myTicketsListModel = new DefaultListModel<>();
        myTicketsList = new JList<>(myTicketsListModel);
        myTicketsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myTicketsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        JScrollPane myTicketsListScrollPane = new JScrollPane(myTicketsList);
        myTicketsListScrollPane.setBorder(BorderFactory.createTitledBorder("Ticket List"));

        Container myContentsPane = getContentPane();
        myContentsPane.setLayout(new BorderLayout());
        myContentsPane.add(myTicketsListScrollPane, BorderLayout.CENTER);

        setVisible(true);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionTextField.getText().trim();
                String reporter = reporterTextField.getText().trim();
                int priority = (int) priorityComboBox.getSelectedItem();

                if (description.isEmpty() || reporter.isEmpty() || priority == 0) {
                    JOptionPane.showMessageDialog(TicketGUI.this, "Please input all data.",
                            "Data Missing", JOptionPane.WARNING_MESSAGE);
                } else {
                    Ticket newTicket = new Ticket(description,  priority, reporter, new Date());
                    try {
                        myController.addTicket(newTicket);
                        List openTickets = (List) myController.ticketStore.getAllOpenTickets();
                        setTicketList(openTickets);
                    } catch (SQLException ex) {
                        showMessageDialog("Error adding ticket to the store. \nDatabase Error");
                    }
                }
            }
        });

        List openTickets = (List) myController.loadAllOpenTicketsFromStore();
        setTicketList(openTickets);

        showAllOpenTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List allOpenTicketsList = (List) myController.loadAllOpenTicketsFromStore();
                setTicketList(allOpenTicketsList);
            }
        });

        searchIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idSearchTextBox.getText().trim();

                if (idText.isEmpty()) {
                    myTicketsListModel.clear();
                    myTicketsListStatusDescription.setText("INVALID_TICKET_ID");
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idText);
                    if (id <= 0) {
                        myTicketsListModel.clear();
                        myTicketsListStatusDescription.setText("INVALID_TICKET_ID");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    myTicketsListModel.clear();
                    myTicketsListStatusDescription.setText("INVALID_TICKET_ID");
                    return;
                }

                Ticket ticket = TicketStore.getTicketById(id);
                if (ticket != null) {
                    myTicketsListModel.clear();
                    myTicketsListModel.addElement(ticket);
                    myTicketsListStatusDescription.setText("TICKET_MATCHING_ID");
                } else {
                    myTicketsListModel.clear();
                    myTicketsListStatusDescription.setText("NO_TICKETS_FOUND");
                }
            }

            });


        searchDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionSearchTextBox.getText().trim();

                if (description.isEmpty()) {
                    myTicketsListModel.clear();
                    myTicketsListStatusDescription.setText("NO_TICKETS_FOUND");
                    return;
                }

                List matchingTickets = (List) TicketStore.searchTicketsByDescription(description);

                myTicketsListModel.clear();
                if (matchingTickets.getItemCount() > 0) {

                    for (int i = 0; i < matchingTickets.getItemCount(); i++) {
                        String ticket = matchingTickets.getItem(i);
                        myTicketsListModel.addElement(ticket);
                    }
                    myTicketsListStatusDescription.setText("");
                } else {
                    myTicketsListStatusDescription.setText("NO_TICKETS_FOUND");
                }

            }
        });

        resolveSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = myTicketsList.getSelectedIndex();

                if (selectedIndex == -1) {
                    showMessageDialog("No ticket selected\nNo Ticket Selected");
                    return;
                }

                Ticket ticketAtHand = (Ticket) myTicketsListModel.getElementAt(selectedIndex);

                if (ticketAtHand.getStatus().equals(Ticket.TicketStatus.RESOLVED)) {
                    showMessageDialog("Ticket already resolved");
                    return;
                }

                String resolution = showInputDialog("Enter resolution");

                // Check if the user canceled or entered null
                if (resolution == null) {
                    return;
                }

                // Update the ticket with resolution and status
                ticketAtHand.setResolution(resolution);
                ticketAtHand.setDateResolved(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())));
                ticketAtHand.setStatus(Ticket.TicketStatus.RESOLVED);

                myController.updateTicket(ticketAtHand);

                // Remove resolved ticket
                myTicketsListModel.removeElement(ticketAtHand);

                // confirmation
                showMessageDialog("Ticket Resolved");

                // Update myTicketsList
                List openTickets = (List) TicketStore.getAllOpenTickets();



                myTicketsListModel.clear();
                if (openTickets.getItemCount() > 0) {

                    for (int i = 0; i < openTickets.getItemCount(); i++) {
                        String ticket = openTickets.getItem(i);
                        myTicketsListModel.addElement(ticket);
                    }
                    myTicketsListStatusDescription.setText("");
                } else {
                    myTicketsListStatusDescription.setText("");
                }

                myTicketsListStatusDescription.setText("ALL TICKETS");

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
        // Sort the tickets in priority order
        try {
            Collections.sort(tickets, Comparator.comparingInt(Ticket::getPriority));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Clear myTicketsListModel
        myTicketsListModel.clear();

        // Add tickets in myTicketsListModel
        for (int i = 0; i < tickets.getItemCount(); i++) {
            String ticket = tickets.getItem(i);
            myTicketsListModel.addElement(ticket);
        }

    }

    protected void quitProgram() {
        myController.quitProgram();
    }

    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    protected String showInputDialog(String question) {
        return JOptionPane.showInputDialog(this, question);
    }
    
}


