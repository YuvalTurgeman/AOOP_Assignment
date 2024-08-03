/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type View_CompetitionPanel, extends JPanel.
 *  this is the class that manages the creation of the Competition Panel.
 * @methods: ctor, getCompetitionBox, getMaxCompetitorsField, getDisciplineBox, getLeagueBox, getGenderBox, getBtnCreateCompetition, getCompetitionPanel.
 **/

package game.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View_CompetitionPanel extends JPanel{

    // Competition components
    private JComboBox<String> competitionBox;
    private JTextField maxCompetitorsField;
    private JComboBox<String> disciplineBox;
    private JComboBox<String> leagueBox;
    private JComboBox<String> genderBox;
    private JButton btnCreateCompetition;
    private JPanel competitionPanel;


    public View_CompetitionPanel(){
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Competition Panel
        competitionPanel = new JPanel(new GridLayout(0, 1));
        competitionPanel.setBorder(lineBorder);
        JLabel LCreateCompetition = new JLabel("Create Competition");
        JLabel LChooseCompetition = new JLabel("Choose Competition:");
        competitionBox = new JComboBox<>(new String[]{"Ski", "Snowboard"});
        JLabel LMaxCompetitors = new JLabel("Max Competitors Number:");
        maxCompetitorsField = new JTextField();
        maxCompetitorsField.setText("10");
        JLabel LDiscipline = new JLabel("Discipline:");
        disciplineBox = new JComboBox<>(new String[]{"Slalom", "Giant Slalom", "Downhill", "Freestyle"});
        JLabel LLeague = new JLabel("League:");
        leagueBox = new JComboBox<>(new String[]{"Junior", "Adult", "Senior"});
        JLabel LGender = new JLabel("Gender:");
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        btnCreateCompetition = new JButton("Create Competition");
        competitionPanel.add(LCreateCompetition);
        competitionPanel.add(LChooseCompetition);
        competitionPanel.add(competitionBox);
        competitionPanel.add(LMaxCompetitors);
        competitionPanel.add(maxCompetitorsField);
        competitionPanel.add(LDiscipline);
        competitionPanel.add(disciplineBox);
        competitionPanel.add(LLeague);
        competitionPanel.add(leagueBox);
        competitionPanel.add(LGender);
        competitionPanel.add(genderBox);
        competitionPanel.add(btnCreateCompetition);

    }


    public JComboBox<String> getCompetitionBox() {
        return competitionBox;
    }

    public JTextField getMaxCompetitorsField() {
        return maxCompetitorsField;
    }

    public JComboBox<String> getDisciplineBox() {
        return disciplineBox;
    }

    public JComboBox<String> getLeagueBox() {
        return leagueBox;
    }

    public JComboBox<String> getGenderBox() {
        return genderBox;
    }

    public JButton getBtnCreateCompetition() {
        return btnCreateCompetition;
    }

    public JPanel getCompetitionPanel() {
        return competitionPanel;
    }
}
