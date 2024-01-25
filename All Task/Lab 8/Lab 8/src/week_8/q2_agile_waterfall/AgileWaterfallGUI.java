package week_8.q2_agile_waterfall;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * See the Lab 8 Questions.md file for the instructions.
 * Don't forget to add screenshots of your program's GUI running.
 * Save the screenshots in the screenshots directory of this project.
 * */

public class AgileWaterfallGUI extends JFrame {

    public JPanel mainPanel;
    private JTextField projectName;
    private JSlider peopleOnTeam;
    private JCheckBox firmDeadlines;
    private JCheckBox earlyWorkingModels;
    private JCheckBox earlyIntegration;
    private JButton recommendMethodology;
    private JCheckBox qualityControl;
    private JCheckBox experienceAllPhases;
    private JLabel recommendation;

    public final static String AGILE = "Agile";
    public final static String WATERFALL = "Waterfall";
    public final static String EITHER = "either";

    public final static String RECOMMENDATION_TEMPLATE = "%s could use %s";

    public AgileWaterfallGUI() {
        setTitle("Agile or Waterfall?");
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //TODO any GUI configuration needed
        //TODO add event handler to read the data entered, and selections made,
        //TODO recommend a methodology, display in JLabel.
        // Use the recommendationTemplate to display a String like "Android App should use Agile"

        recommendMethodology.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask the user the 6 questions
                int numProgrammers = peopleOnTeam.getValue();
                boolean hasFirmDeadlines = firmDeadlines.isSelected();
                boolean hasExperience = experienceAllPhases.isSelected();
                boolean hasQualityControl = qualityControl.isSelected();
                boolean hasEarlyIntegration = earlyIntegration.isSelected();
                boolean requiresWorkingModels = earlyWorkingModels.isSelected();

                // Call your agileOrWaterfall method, passing it the data obtained from the user as arguments
                String methodology = agileOrWaterfall(numProgrammers, hasFirmDeadlines, hasExperience, hasQualityControl, hasEarlyIntegration, requiresWorkingModels);

                recommendation.setText(RECOMMENDATION_TEMPLATE + methodology);

            }


            public static String agileOrWaterfall(int numProgrammers, boolean firmDeadlines, boolean reqAnalysisTesting,
                                                  boolean stringentQC, boolean earlyIntegration, boolean customerEarlyModels) {
                int agileFactors = 0;
                int waterfallFactors = 0;

                if (numProgrammers > 30) {
                    waterfallFactors++;
                } else {
                    agileFactors++;
                }
                if (firmDeadlines) {
                    waterfallFactors++;
                } else {
                    agileFactors++;
                }
                if (reqAnalysisTesting) {
                    agileFactors++;
                } else {
                    waterfallFactors++;
                }
                if (stringentQC) {
                    waterfallFactors++;
                } else {
                    agileFactors++;
                }
                if (earlyIntegration) {
                    agileFactors++;
                }  else {
                    waterfallFactors++;
                }
                if (customerEarlyModels) {
                    agileFactors++;
                } else {
                    waterfallFactors++;
                }

                if (agileFactors >= 4) {
                    return AGILE;
                } else if (waterfallFactors >= 4) {
                    return WATERFALL;
                } else {
                    return EITHER;
                }
            }


        });
    }

}
