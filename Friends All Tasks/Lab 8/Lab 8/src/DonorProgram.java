//package week_8.q1_blood_donor;


/* The instructions are in grades/Lab 8 Questions.md  */


import week_8.q1_blood_donor.DonorGUI;

import javax.swing.*;

public class DonorProgram {
    public static void main(String[] args) {
        DonorGUI gui = new DonorGUI();
        gui.setContentPane(gui.mainPanel);
        gui.setSize(300,400);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
