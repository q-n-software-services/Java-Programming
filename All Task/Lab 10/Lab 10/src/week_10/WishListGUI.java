package week_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class WishListGUI extends JFrame {
    
    JPanel mainPanel;
    JList wishList;   // TODO use generic types for this JList
    JList visitedList;   // TODO use generic types for this JList
    JTextField newPlaceNameTextField;
    JButton addButton;
    JButton saveQuitButton;

    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem visitedMenuItem = new JMenuItem("Visited!");
    JMenuItem deleteMenuItem = new JMenuItem("Delete");

    // Create a popup menu for wishlist
    JPopupMenu visitedListPopupMenu = new JPopupMenu();
    JMenuItem visitedListDeleteMenuItem = new JMenuItem("Delete");

//    private JList<String> wishList;
//    private JList<String> visitedList;


    public WishListGUI(List<String> wishListPlaces, List<String> visitedPlaces) {
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 500));
        getRootPane().setDefaultButton(addButton);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
        // TODO finish this code. See Lab 10 Questions.md
        // TODO create and use methods to avoid writing all of your code in this constructor.

        DefaultListModel<String> wishListModel = new DefaultListModel<>();
        DefaultListModel<String> visitedListModel = new DefaultListModel<>();

        wishList = new JList<>(wishListModel);
        visitedList = new JList<>(visitedListModel);

        // Add places from the wishListPlaces to the wishList JList
        for (String place : wishListPlaces) {
//            ((DefaultListModel<String>) wishList.getModel()).addElement(place);
            wishListModel.addElement(place);
        }

        // Add places from the visitedPlaces to the visitedList JList
        for (String place : visitedPlaces) {
//            ((DefaultListModel<String>) visitedList.getModel()).addElement(place);
            visitedListModel.addElement(place);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placeName = newPlaceNameTextField.getText().trim();

                if (!placeName.isEmpty()) {
                    if (!wishListModel.contains(placeName)) {
                        wishListModel.addElement(placeName.toLowerCase());
                        newPlaceNameTextField.setText("");

//                        System.out.println(wishListModel);

                    } else {
                        showMessageDialog("Place name already exists!\nDuplicate Name");
                    }
                }
            }
        });

        // Add ActionListener for "Visited!" menu item
        visitedMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the wishlist
                String selectedItem = wishList.getSelectedValue().toString();
                if (selectedItem != null) {
                    // Remove the item from the wishlist
                    wishListModel.removeElement(selectedItem);
                    // Add the item to the visitedList
                    visitedListModel.addElement(selectedItem);
                }
            }
        });

        // Add ActionListener for "Delete" menu item
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the wishlist
                String selectedItem = wishList.getSelectedValue().toString();
                if (selectedItem != null) {
                    // Remove the item from the wishlist
                    wishListModel.removeElement(selectedItem);
                }
            }
        });

        // Add ActionListener for "Delete" menu item in wishlist popup menu
        visitedListDeleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the wishlist
                String selectedItem = visitedList.getSelectedValue().toString();
                if (selectedItem != null) {
                    // Remove the item from the wishlist
                    visitedListModel.removeElement(selectedItem);
                }
            }
        });

        // Add the "Delete" menu item to the visitedList popup menu
        visitedListPopupMenu.add(visitedListDeleteMenuItem);

        // Attach the visitedList popup menu to the visitedList JList
        visitedList.setComponentPopupMenu(visitedListPopupMenu);

        // Add menu items to the popup menu
        popupMenu.add(visitedMenuItem);
        popupMenu.add(deleteMenuItem);

        // Attach the popup menu to the wishlist JList
        wishList.setComponentPopupMenu(popupMenu);


        saveQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create lists of wish list and visited place names
                List<String> wishListPlaces = new ArrayList<>();
                List<String> visitedListPlaces = new ArrayList<>();

                // Populate wish list places
                for (int i = 0; i < wishListModel.getSize(); i++) {
                    wishListPlaces.add(wishListModel.getElementAt(i));
                }

                // Populate visited list places
                for (int i = 0; i < visitedListModel.getSize(); i++) {
                    visitedListPlaces.add(visitedListModel.getElementAt(i));
                }

                // Call Main.quit() with the lists as arguments
                Main.quit(wishListPlaces, visitedListPlaces);


                // Close the GUI window and end the program
                ((JFrame) SwingUtilities.getRoot((Component) e.getSource())).dispose();

            }
        });
    }
    
    // Use this method to show message dialogs displaying the message given.
    // Otherwise, tests for code that shows alert dialogs will time out and fail.
    // Don't modify or delete this method.
    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    
}
