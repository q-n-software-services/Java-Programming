package week_8.q2_agile_waterfall;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        recommendMethodology.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int numProgrammers = peopleOnTeam.getValue();
                boolean hasFirmDeadlines = firmDeadlines.isSelected();
                boolean hasExperience = experienceAllPhases.isSelected();
                boolean hasQualityControl = qualityControl.isSelected();
                boolean hasEarlyIntegration = earlyIntegration.isSelected();
                boolean requiresWorkingModels = earlyWorkingModels.isSelected();


                String methodology = agileOrWaterfall(numProgrammers, hasFirmDeadlines, hasExperience, hasQualityControl, hasEarlyIntegration, requiresWorkingModels);

                recommendation.setText(RECOMMENDATION_TEMPLATE + methodology);

            }


            public static String agileOrWaterfall(int numProgrammers, boolean firmDeadlines, boolean reqAnalysisTesting,
                                                  boolean stringentQC, boolean earlyIntegration, boolean customerEarlyModels) {
                int pointsAGILE = 0;
                int pointsWATERFALL = 0;

                if (numProgrammers > 30) {
                    pointsWATERFALL++;
                } else {
                    pointsAGILE++;
                }
                if (firmDeadlines) {
                    pointsWATERFALL++;
                } else {
                    pointsAGILE++;
                }
                if (reqAnalysisTesting) {
                    pointsAGILE++;
                } else {
                    pointsWATERFALL++;
                }
                if (stringentQC) {
                    pointsWATERFALL++;
                } else {
                    pointsAGILE++;
                }
                if (earlyIntegration) {
                    pointsAGILE++;
                }  else {
                    pointsWATERFALL++;
                }
                if (customerEarlyModels) {
                    pointsAGILE++;
                } else {
                    pointsWATERFALL++;
                }

                if (pointsAGILE >= 4) {
                    return AGILE;
                } else if (pointsWATERFALL >= 4) {
                    return WATERFALL;
                } else {
                    return EITHER;
                }
            }


        });
    }

}
