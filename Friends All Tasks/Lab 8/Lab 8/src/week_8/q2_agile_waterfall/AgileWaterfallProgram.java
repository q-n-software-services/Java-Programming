package week_8.q2_agile_waterfall;


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
