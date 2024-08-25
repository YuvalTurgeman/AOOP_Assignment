/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type View_StartInfoPanel, extends JPanel.
 *  this is the class that manages the creation of the Start race and show info Panel.
 * @methods: ctor, getBtnStartCompetition, getBtnShowInfo, getStartInfoPanel.
 **/

package game.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View_StartInfoPanel extends JPanel{

    // components
    private JButton btnStartCompetition;
    private JButton btnShowInfo;
    private JPanel startInfoPanel;


    public View_StartInfoPanel() {
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        startInfoPanel = new JPanel(new GridLayout(0, 1));
        startInfoPanel.setBorder(lineBorder);
        btnStartCompetition = new JButton("Start Competition");
        btnShowInfo = new JButton("Show Info");
        startInfoPanel.add(btnStartCompetition);
        startInfoPanel.add(btnShowInfo);

    }


    public JButton getBtnStartCompetition() {
        return btnStartCompetition;
    }

    public JButton getBtnShowInfo() {
        return btnShowInfo;
    }

    public JPanel getStartInfoPanel() {
        return startInfoPanel;
    }
}
