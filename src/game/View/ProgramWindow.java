package game.View;

import game.Controller;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramWindow extends JFrame {
    //test
    private Controller controller = null;
    // Arena components
    private JTextField txLength;
    private JComboBox<String> surfaceBox;
    private JComboBox<String> weatherBox;
    private JButton btnBuildArena;

    // Competition components
    private JComboBox<String> competitionBox;
    private JTextField maxCompetitorsField;
    private JComboBox<String> disciplineBox;
    private JComboBox<String> leagueBox;
    private JComboBox<String> genderBox;
    private JButton btnCreateCompetition;

    // Competitor components
    private JTextField nameField;
    private JTextField ageField;
    private JTextField maxSpeedField;
    private JTextField accelerationField;
    private JButton btnAddCompetitor;

    // Bottom buttons
    private JButton btnStartCompetition;
    private JButton btnShowInfo;

    public ProgramWindow(Controller controller) {
        setTitle("Competition");
        setPreferredSize(new Dimension(1000, 700));
        setLocationRelativeTo(null); // center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel field = new ImagePanel();
        JPanel info = new JPanel();
        info.setLayout(new GridLayout(0, 1, 5, 0));

        // Create a line border around all the panels to the right
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Arena Panel
        JPanel setArena = new JPanel(new GridLayout(0, 1));
        setArena.setBorder(lineBorder);
        JLabel LBuildArena = new JLabel("Build Arena");
        JLabel LLength = new JLabel("Arena Length:");
        txLength = new JTextField();
        txLength.setText("700");
        JLabel LSnowSurface = new JLabel("Snow Surface:");
        surfaceBox = new JComboBox<>(new String[]{"Powder", "Crud", "Ice"});
        JLabel LWeatherConditions = new JLabel("Weather Conditions:");
        weatherBox = new JComboBox<>(new String[]{"Sunny", "Cloudy", "Stormy"});
        btnBuildArena = new JButton("Build Arena");
        setArena.add(LBuildArena);
        setArena.add(LLength);
        setArena.add(txLength);
        setArena.add(LSnowSurface);
        setArena.add(surfaceBox);
        setArena.add(LWeatherConditions);
        setArena.add(weatherBox);
        setArena.add(btnBuildArena);

        // Competition Panel
        JPanel setCompetition = new JPanel(new GridLayout(0, 1));
        setCompetition.setBorder(lineBorder);
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
        setCompetition.add(LCreateCompetition);
        setCompetition.add(LChooseCompetition);
        setCompetition.add(competitionBox);
        setCompetition.add(LMaxCompetitors);
        setCompetition.add(maxCompetitorsField);
        setCompetition.add(LDiscipline);
        setCompetition.add(disciplineBox);
        setCompetition.add(LLeague);
        setCompetition.add(leagueBox);
        setCompetition.add(LGender);
        setCompetition.add(genderBox);
        setCompetition.add(btnCreateCompetition);

        // Competitor Panel
        JPanel setCompetitor = new JPanel(new GridLayout(0, 1));
        setCompetitor.setBorder(lineBorder);
        JLabel LCompetitor = new JLabel("Add Competitor");
        JLabel LName = new JLabel("Name:");
        nameField = new JTextField();
        JLabel LAge = new JLabel("Age:");
        ageField = new JTextField();
        JLabel LMaxSpeed = new JLabel("Max Speed:");
        maxSpeedField = new JTextField();
        JLabel LAcceleration = new JLabel("Acceleration:");
        accelerationField = new JTextField();
        btnAddCompetitor = new JButton("Add Competitor");
        setCompetitor.add(LCompetitor);
        setCompetitor.add(LName);
        setCompetitor.add(nameField);
        setCompetitor.add(LAge);
        setCompetitor.add(ageField);
        setCompetitor.add(LMaxSpeed);
        setCompetitor.add(maxSpeedField);
        setCompetitor.add(LAcceleration);
        setCompetitor.add(accelerationField);
        setCompetitor.add(btnAddCompetitor);

        // Buttons at the bottom
        JPanel bottomButtons = new JPanel(new GridLayout(0, 1));
        bottomButtons.setBorder(lineBorder);
        btnStartCompetition = new JButton("Start Competition");
        btnShowInfo = new JButton("Show Info");
        bottomButtons.add(btnStartCompetition);
        bottomButtons.add(btnShowInfo);

        // Add components to info panel
        info.add(setArena);
        info.add(setCompetition);
        info.add(setCompetitor);
        info.add(bottomButtons);

        // Add panels to main frame
        this.getContentPane().add(field, BorderLayout.CENTER);
        this.getContentPane().add(info, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Add action listeners to buttons
        btnBuildArena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IArena arena = controller.handleBuildArena(txLength.getText(),(String)surfaceBox.getSelectedItem(),(String)weatherBox.getSelectedItem());

                if(arena != null) {
                    setSize(new Dimension(1000, (int) ((WinterArena) arena).getLength()));
                    System.out.println((String)weatherBox.getSelectedItem());
                    field.changeImage(String.format("src/icons/%s.jpg", (String)weatherBox.getSelectedItem()));
                    field.repaint();
                }


            }
        });

        btnCreateCompetition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println((String)competitionBox.getSelectedItem());
                Competition competition = controller.handleCreateCompetition(maxCompetitorsField.getText(),(String)competitionBox.getSelectedItem(),
                        (String)disciplineBox.getSelectedItem(), (String)leagueBox.getSelectedItem(), (String)genderBox.getSelectedItem());

                if(competition != null){
                    //todo:implement widening of the screen
                }
            }
        });

        btnAddCompetitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Competitor competitor = controller.handleAddCompetitor(nameField.getText(), ageField.getText(), maxSpeedField.getText(), accelerationField.getText());

            }
        });

        btnStartCompetition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleStartCompetition();
            }
        });

        btnShowInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleShowInfo();
            }
        });
    }




    // Getters for components
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

    public JButton getBtnStartCompetition() {
        return btnStartCompetition;
    }

    public JButton getBtnShowInfo() {
        return btnShowInfo;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }




}
//
//package game;
//
//import game.competition.Competition;
//import game.competition.Competitor;
//import game.arena.WinterArena;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class View extends JFrame {
//    private final Controller controller;
//
//    // Arena components
//    private JTextField txLength;
//    private JComboBox<String> surfaceBox;
//    private JComboBox<String> weatherBox;
//    private JButton btnBuildArena;
//
//    // Competition components
//    private JComboBox<String> competitionBox;
//    private JTextField maxCompetitorsField;
//    private JComboBox<String> disciplineBox;
//    private JComboBox<String> leagueBox;
//    private JComboBox<String> genderBox;
//    private JButton btnCreateCompetition;
//
//    // Competitor components
//    private JTextField nameField;
//    private JTextField ageField;
//    private JTextField maxSpeedField;
//    private JTextField accelerationField;
//    private JButton btnAddCompetitor;
//
//    // Bottom buttons
//    private JButton btnStartCompetition;
//    private JButton btnShowInfo;
//
//    // Arena panel
//    private ImagePanel field;
//
//    public View(Controller controller) {
//        this.controller = controller;
//        controller.setView(this);
//
//        setTitle("Competition");
//        setPreferredSize(new Dimension(1000, 700));
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        field = new ImagePanel();
//        JPanel info = new JPanel();
//        info.setLayout(new GridLayout(0, 1, 5, 0));
//
//        // Create a line border around all the panels to the right
//        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
//
//        // Arena Panel
//        JPanel setArena = new JPanel(new GridLayout(0, 1));
//        setArena.setBorder(lineBorder);
//        JLabel LBuildArena = new JLabel("Build Arena");
//        JLabel LLength = new JLabel("Arena Length:");
//        txLength = new JTextField();
//        txLength.setText("700");
//        JLabel LSnowSurface = new JLabel("Snow Surface:");
//        surfaceBox = new JComboBox<>(new String[]{"Powder", "Crud", "Ice"});
//        JLabel LWeatherConditions = new JLabel("Weather Conditions:");
//        weatherBox = new JComboBox<>(new String[]{"Sunny", "Cloudy", "Stormy"});
//        btnBuildArena = new JButton("Build Arena");
//        setArena.add(LBuildArena);
//        setArena.add(LLength);
//        setArena.add(txLength);
//        setArena.add(LSnowSurface);
//        setArena.add(surfaceBox);
//        setArena.add(LWeatherConditions);
//        setArena.add(weatherBox);
//        setArena.add(btnBuildArena);
//
//        // Competition Panel
//        JPanel setCompetition = new JPanel(new GridLayout(0, 1));
//        setCompetition.setBorder(lineBorder);
//        JLabel LCreateCompetition = new JLabel("Create Competition");
//        JLabel LChooseCompetition = new JLabel("Choose Competition:");
//        competitionBox = new JComboBox<>(new String[]{"Ski", "Snowboard"});
//        JLabel LMaxCompetitors = new JLabel("Max Competitors Number:");
//        maxCompetitorsField = new JTextField();
//        maxCompetitorsField.setText("10");
//        JLabel LDiscipline = new JLabel("Discipline:");
//        disciplineBox = new JComboBox<>(new String[]{"Slalom", "Giant Slalom", "Downhill", "Freestyle"});
//        JLabel LLeague = new JLabel("League:");
//        leagueBox = new JComboBox<>(new String[]{"Junior", "Adult", "Senior"});
//        JLabel LGender = new JLabel("Gender:");
//        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
//        btnCreateCompetition = new JButton("Create Competition");
//        setCompetition.add(LCreateCompetition);
//        setCompetition.add(LChooseCompetition);
//        setCompetition.add(competitionBox);
//        setCompetition.add(LMaxCompetitors);
//        setCompetition.add(maxCompetitorsField);
//        setCompetition.add(LDiscipline);
//        setCompetition.add(disciplineBox);
//        setCompetition.add(LLeague);
//        setCompetition.add(leagueBox);
//        setCompetition.add(LGender);
//        setCompetition.add(genderBox);
//        setCompetition.add(btnCreateCompetition);
//
//        // Competitor Panel
//        JPanel setCompetitor = new JPanel(new GridLayout(0, 1));
//        setCompetitor.setBorder(lineBorder);
//        JLabel LCompetitor = new JLabel("Add Competitor");
//        JLabel LName = new JLabel("Name:");
//        nameField = new JTextField();
//        JLabel LAge = new JLabel("Age:");
//        ageField = new JTextField();
//        JLabel LMaxSpeed = new JLabel("Max Speed:");
//        maxSpeedField = new JTextField();
//        JLabel LAcceleration = new JLabel("Acceleration:");
//        accelerationField = new JTextField();
//        btnAddCompetitor = new JButton("Add Competitor");
//        setCompetitor.add(LCompetitor);
//        setCompetitor.add(LName);
//        setCompetitor.add(nameField);
//        setCompetitor.add(LAge);
//        setCompetitor.add(ageField);
//        setCompetitor.add(LMaxSpeed);
//        setCompetitor.add(maxSpeedField);
//        setCompetitor.add(LAcceleration);
//        setCompetitor.add(accelerationField);
//        setCompetitor.add(btnAddCompetitor);
//
//        // Buttons at the bottom
//        JPanel bottomButtons = new JPanel(new GridLayout(0, 1));
//        bottomButtons.setBorder(lineBorder);
//        btnStartCompetition = new JButton("Start Competition");
//        btnShowInfo = new JButton("Show Info");
//        bottomButtons.add(btnStartCompetition);
//        bottomButtons.add(btnShowInfo);
//
//        // Add components to info panel
//        info.add(setArena);
//        info.add(setCompetition);
//        info.add(setCompetitor);
//        info.add(bottomButtons);
//
//        // Add panels to main frame
//        this.getContentPane().add(field, BorderLayout.CENTER);
//        this.getContentPane().add(info, BorderLayout.EAST);
//
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//
//        // Add action listeners to buttons
//        btnBuildArena.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.buildArena(txLength.getText(), (String) surfaceBox.getSelectedItem(), (String) weatherBox.getSelectedItem());
//            }
//        });
//
//        btnCreateCompetition.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.createCompetition(maxCompetitorsField.getText(), (String) competitionBox.getSelectedItem(), (String) disciplineBox.getSelectedItem(),
//                        (String) leagueBox.getSelectedItem(), (String) genderBox.getSelectedItem());
//            }
//        });
//
//        btnAddCompetitor.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.addCompetitor(nameField.getText(), ageField.getText(), maxSpeedField.getText(), accelerationField.getText());
//            }
//        });
//
//        btnStartCompetition.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.startCompetition();
//            }
//        });
//
//        btnShowInfo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.showInfo();
//            }
//        });
//    }
//
//    // Methods to update the view based on model changes
//    public void updateArena(WinterArena arena) {
//        setSize(new Dimension(1000, (int) arena.getLength()));
//        field.changeImage(String.format("src/icons/%s.jpg", arena.getCondition().toString().toLowerCase()));
//        field.repaint();
//    }
//
//    public void updateCompetition(Competition competition) {
//        // Update the UI to reflect the new competition
//    }
//
//    public void updateCompetitors(List<Competitor> competitors) {
//        // Update the UI to reflect the new competitors
//    }
//
//    public Component getFrame() {
//        return this;
//    }
//}
