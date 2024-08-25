/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type View_CompetitorPanel, extends JPanel.
 *  this is the class that manages the creation of the Competitor Panel.
 * @methods: ctor, getNameField, getAgeField, getMaxSpeedField, getAccelerationField, getBtnAddCompetitor, getCompetitorPanel.
 **/

package game.View;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.logging.Logger;

public class View_CompetitorPanel extends JPanel{

    // Competitor components
    private JTextField nameField;
    private JTextField ageField;
    private JTextField maxSpeedField;
    private JTextField accelerationField;
    private JButton btnAddCompetitor;
    private JPanel competitorPanel;

    public View_CompetitorPanel() {
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        // Competitor Panel
        competitorPanel = new JPanel(new GridLayout(0, 1));
        competitorPanel.setBorder(lineBorder);
        JLabel LCompetitor = new JLabel("Add Competitor");
        JLabel LName = new JLabel("Name:");
        nameField = new JTextField();
//        nameField.setText("asd"); // for testing addition of competitor functionality
        JLabel LAge = new JLabel("Age:");
        ageField = new JTextField();
//        ageField.setText("15");// for testing addition of competitor functionality
        JLabel LMaxSpeed = new JLabel("Max Speed:");
        maxSpeedField = new JTextField();
//        maxSpeedField.setText("15");// for testing addition of competitor functionality
        JLabel LAcceleration = new JLabel("Acceleration:");
        accelerationField = new JTextField();
//        accelerationField.setText("15");// for testing addition of competitor functionality
        btnAddCompetitor = new JButton("Add Competitor");
        competitorPanel.add(LCompetitor);
        competitorPanel.add(LName);
        competitorPanel.add(nameField);
        competitorPanel.add(LAge);
        competitorPanel.add(ageField);
        competitorPanel.add(LMaxSpeed);
        competitorPanel.add(maxSpeedField);
        competitorPanel.add(LAcceleration);
        competitorPanel.add(accelerationField);
        competitorPanel.add(btnAddCompetitor);

    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getMaxSpeedField() {
        return maxSpeedField;
    }

    public JTextField getAccelerationField() {
        return accelerationField;
    }

    public JButton getBtnAddCompetitor() {
        return btnAddCompetitor;
    }

    public JPanel getCompetitorPanel(){
        return competitorPanel;
    }
}
