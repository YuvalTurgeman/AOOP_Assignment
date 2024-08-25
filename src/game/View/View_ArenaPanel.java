/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type View_ArenaPanel, extends JPanel.
 *  this is the class that manages the creation of the Arena Panel.
 * @methods: ctor, getTxLength, getSurfaceBox, getWeatherBox, getBtnBuildArena, getarenaPanel.
 **/

package game.View;

import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;

public class View_ArenaPanel extends JPanel{
    // Arena components
    private JTextField txLength;
    private JComboBox<String> surfaceBox;
    private JComboBox<String> weatherBox;
    private JButton btnBuildArena;
    private JPanel arenaPanel;


    public View_ArenaPanel() {
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        arenaPanel = new JPanel(new GridLayout(0, 1));
        arenaPanel.setBorder(lineBorder);
        JLabel LBuildArena = new JLabel("Build Arena");
        JLabel LLength = new JLabel("Arena Length:");
        txLength = new JTextField();
        txLength.setText("700");
        JLabel LSnowSurface = new JLabel("Snow Surface:");
        surfaceBox = new JComboBox<>(new String[]{"Powder","Crud","Ice"});
        JLabel LWeatherConditions = new JLabel("Weather Conditions:");
        weatherBox = new JComboBox<>(new String[]{"Sunny", "Cloudy", "Stormy"});
        btnBuildArena = new JButton("Build Arena");
        arenaPanel.add(LBuildArena);
        arenaPanel.add(LLength);
        arenaPanel.add(txLength);
        arenaPanel.add(LSnowSurface);
        arenaPanel.add(surfaceBox);
        arenaPanel.add(LWeatherConditions);
        arenaPanel.add(weatherBox);
        arenaPanel.add(btnBuildArena);
    }

    public JTextField getTxLength() {
        return txLength;
    }

    public JComboBox<String> getSurfaceBox() {
        return surfaceBox;
    }

    public JComboBox<String> getWeatherBox() {
        return weatherBox;
    }

    public JButton getBtnBuildArena() {
        return btnBuildArena;
    }

    public JPanel getarenaPanel(){
        return arenaPanel;
    }

}
