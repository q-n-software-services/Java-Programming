package week_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class WishListGUI extends JFrame {
    
    JPanel mainPanel;
    JList myWishList;
    JList myVisitedList;
    JTextField newPlaceNameTextField;
    JButton addButton;
    JButton saveQuitButton;

    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem visitedMenuItem = new JMenuItem("Visited!");
    JMenuItem deleteMenuItem = new JMenuItem("Delete");

    JPopupMenu myVisitedListPopupMenu = new JPopupMenu();
    JMenuItem myVisitedListDeleteMenuItem = new JMenuItem("Delete");

    public WishListGUI(List<String> myWishListPlaces, List<String> visitedPlaces) {
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 500));
        getRootPane().setDefaultButton(addButton);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DefaultListModel<String> myWishListModel = new DefaultListModel<>();
        DefaultListModel<String> myVisitedListModel = new DefaultListModel<>();

        myWishList = new JList<>(myWishListModel);
        myVisitedList = new JList<>(myVisitedListModel);

        // Add places from the myWishListPlaces to the myWishList JList
        for (String place : myWishListPlaces) {
            myWishListModel.addElement(place);
        }

        // Add places from the visitedPlaces to the myVisitedList JList
        for (String place : visitedPlaces) {
            myVisitedListModel.addElement(place);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placeName = newPlaceNameTextField.getText().trim();

                if (!placeName.isEmpty()) {
                    if (!myWishListModel.contains(placeName)) {
                        myWishListModel.addElement(placeName.toLowerCase());
                        newPlaceNameTextField.setText("");

//                        System.out.println(myWishListModel);

                    } else {
                        showMessageDialog("Place Exists!\nDuplicate Name");
                    }
                }
            }
        });

        visitedMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the wishlist
                String selectedItem = myWishList.getSelectedValue().toString();
                if (selectedItem != null) {
                    // Remove the item from the wishlist
                    myWishListModel.removeElement(selectedItem);
                    // Add the item to the myVisitedList
                    myVisitedListModel.addElement(selectedItem);
                }
            }
        });

        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the wishlist
                String selectedItem = myWishList.getSelectedValue().toString();
                if (selectedItem != null) {
                    // Remove the item from the wishlist
                    myWishListModel.removeElement(selectedItem);
                }
            }
        });

        myVisitedListDeleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the wishlist
                String selectedItem = myVisitedList.getSelectedValue().toString();
                if (selectedItem != null) {
                    // Remove the item from the wishlist
                    myVisitedListModel.removeElement(selectedItem);
                }
            }
        });

        myVisitedListPopupMenu.add(myVisitedListDeleteMenuItem);

        myVisitedList.setComponentPopupMenu(myVisitedListPopupMenu);

        popupMenu.add(visitedMenuItem);
        popupMenu.add(deleteMenuItem);

        myWishList.setComponentPopupMenu(popupMenu);


        saveQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> myWishListPlaces = new ArrayList<>();
                List<String> myVisitedListPlaces = new ArrayList<>();

                for (int i = 0; i < myWishListModel.getSize(); i++) {
                    myWishListPlaces.add(myWishListModel.getElementAt(i));
                }

                for (int i = 0; i < myVisitedListModel.getSize(); i++) {
                    myVisitedListPlaces.add(myVisitedListModel.getElementAt(i));
                }

                Main.quit(myWishListPlaces, myVisitedListPlaces);


                ((JFrame) SwingUtilities.getRoot((Component) e.getSource())).dispose();

            }
        });
    }

    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    
}
