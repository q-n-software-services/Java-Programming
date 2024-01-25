package week_8.q2_agile_waterfall;

/* The instructions are in grades/Lab 8 Questions.md
 *
 *  For this lab, you probably don't need to modify this class.
 */

import javax.swing.*;

public class AgileWaterfallProgram {
    public static void main(String[] args) {
        week_8.q2_agile_waterfall.AgileWaterfallGUI gui = new week_8.q2_agile_waterfall.AgileWaterfallGUI();
        gui.setContentPane(gui.mainPanel);
        gui.setSize(300,400);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
