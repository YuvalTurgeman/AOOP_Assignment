//package game.GUI;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//
////import factory.Builder;
////import factory.RaceBuilder;
////import game.arenas.Arena;
////import game.arenas.exceptions.RaceStartException;
////import game.arenas.exceptions.RacerLimitException;
////import game.arenas.exceptions.RacerTypeException;
////import game.racers.Racer;
////import utilities.EnumContainer;
////import game.racers.Workshop;
//
//import java.util.List;
//import java.util.ArrayList;
//
//
//public class Window extends JFrame {
////    RaceBuilder racebuilder = RaceBuilder.getInstance();
////    Arena arena;
//    private boolean menuType = false;
//
//    public Window() {
//        setTitle("Race");
//        setMinimumSize(new Dimension(1000, 700));
//
//        setLocationRelativeTo(null); // center the window on the screen
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        // JPanel field =new JPanel();
//        ImagePanel field = new ImagePanel();
//
//        JPanel info = new JPanel();
//        info.setPreferredSize(new Dimension(200, 0));
//        ///////////////////////
//        JPanel setArena = new JPanel(new BorderLayout());
//        setArena.setVisible(true);
//        JPanel setCompetition = new JPanel();
//        JPanel setCompetitor = new JPanel();
//        JPanel setAll = new JPanel(new GridBagLayout());
//
//        ///////////////////////
//
//
//
//
//        //arena
//        JLabel LBuildArena = new JLabel("BUILD ARENA");
//        JLabel LLength = new JLabel("Arena Length:");
//        JLabel LSnowSurface = new JLabel("Snow Surface:");
//        JLabel LWeatherConditions = new JLabel("Weather Conditions:");
//
//        JTextField txLength = new JTextField();
//
//        JButton btnBuildArena = new JButton("Build Arena");
//
//        String[] surfaces = {"Powder", "Crud", "Ice"};
//        String[] weatherConditions = {"Sunny", "Cloudy", "Stormy"};
//        JComboBox<String> surfaceBox = new JComboBox<>(surfaces);
//        JComboBox<String> weatherBox = new JComboBox<>(weatherConditions);
//
//        setArena.setLayout(new GridLayout(7, 1));
//        setArena.add(LBuildArena);
//        setArena.add(LLength);
//        setArena.add(txLength);
//        setArena.add(LSnowSurface);
//        setArena.add(surfaceBox);
//        setArena.add(LWeatherConditions);
//        setArena.add(weatherBox);
//        setArena.add(btnBuildArena);
//
//        //////////////////////////
//        //racers
//        JLabel LChooseCompetition = new JLabel("Choose Competition:");
//        JLabel LMaxCompetitors = new JLabel("Max Competitors Number:");
//        JLabel Lracername = new JLabel("Racer Name:");
//        JLabel Lmaxspeed = new JLabel("Max Speed:");
//        JLabel Laccelerat = new JLabel("Acceleration:");
//
//        JTextField txracername = new JTextField();
//        JTextField txmaxspeed = new JTextField();
//        JTextField txaccelerat = new JTextField();
//
//        JButton addracer = new JButton("Add Racer");
//        JButton copylast = new JButton("Copy Last Racer");
//
//        JComboBox<String> racerBOX;
//        JComboBox<String> colorBOX;
//        String[] racer = {"Airplane", "Helicopter", "Bicycle", "Car", "Horse", "RowBoat", "SpeedBoat"};
//        String[] color = {"RED", "BLUE", "GREEN", "BLACK", "YELLOW"};
//        racerBOX = new JComboBox<>(racer);
//        colorBOX = new JComboBox<>(color);
//        JComboBox<String> colorBOXcopy;
//        colorBOXcopy = new JComboBox<>(color);
//        JLabel Lcopy = new JLabel("Choose Color for copy Racer");
//
//        setCompetition.setLayout(new GridLayout(14, 1));
//        setCompetition.add(LChooseCompetition);
//        setCompetition.add(racerBOX);
//        setCompetition.add(LMaxCompetitors);
//        setCompetition.add(colorBOX);
//        setCompetition.add(Lracername);
//        setCompetition.add(txracername);
//        setCompetition.add(Lmaxspeed);
//        setCompetition.add(txmaxspeed);
//        setCompetition.add(Laccelerat);
//        setCompetition.add(txaccelerat);
//        setCompetition.add(addracer);
//        setCompetition.add(Lcopy);
//        setCompetition.add(colorBOXcopy);
//        setCompetition.add(copylast);
//        ////////////////////////
//        //set competitors
//
//        setCompetitor.add(LLength);
//        //set all
//        JButton btnStartCompetition = new JButton("Start competition");
//        JButton showinfo = new JButton("Show Info");
//        setAll.setLayout(null);
//        btnStartCompetition.setBounds(50, 20, 100, 30);
//        showinfo.setBounds(50, 70, 100, 30);
//        setAll.add(btnStartCompetition);
//        setAll.add(showinfo);
//        setAll.add(btnStartCompetition, BorderLayout.NORTH);
//        setAll.add(showinfo, BorderLayout.SOUTH);
//        setAll.setPreferredSize(new Dimension(0, 120));
//        //////////////////////
//
//
//        ////////////////////
//        //put the 3 panels in the info border
//        info.setLayout(new BorderLayout());
//        info.add(setArena, BorderLayout.NORTH);
//        info.add(setCompetition, BorderLayout.CENTER);
////        info.add(setCompetitor, BorderLayout.CENTER);
//        info.add(setAll, BorderLayout.SOUTH);
//        Border outerBorder = BorderFactory.createEmptyBorder(5, 0, 5, 0);
//        Border innerBorder = BorderFactory.createLineBorder(Color.black, 1);
//        setArena.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
//        setCompetition.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
//        setAll.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
//
//        ////
//
//
//        /////////////////////
//        //button function
//
////        showinfo.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                try {
////                    Table t = new Table(arena.getActiveRacers(), arena.getCompletedRacers(), arena.getDisabledRacers(), arena.get_max_racers());
////                } catch (NullPointerException ex) {
////                    JOptionPane.showMessageDialog(null, "The Arena Does Not Exist");
////                }
////            }
////        });
//
////        btnStartCompetition.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////
////                try {
////                    if (arena.isRaceStart()) {
////                        throw new RaceStartException();
////                    }
////                    if (!arena.hasActiveRacers()) {
////                        throw new Exception();
////                    }
////                    for (Racer r : arena.getCompletedRacers()) {
////                        field.remove(r.getlabel());
////                    }
////                    arena.btnStartCompetition();
////                } catch (NullPointerException ex) {
////                    JOptionPane.showMessageDialog(null, "The arena does not exist");
////                } catch (RaceStartException ex) {
////                    JOptionPane.showMessageDialog(null, ex.getMessage());
////                } catch (Exception ex) {
////                    JOptionPane.showMessageDialog(null, "There are no competitors in the arena");
////                }
////
////            }
////        });
//
//        List<JLabel> iconLabels = new ArrayList<>();
//        field.setLayout(null);
//
////        btnBuildArena.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////
////                if (!menuType) {
////                    try {
////                        if (arena != null && arena.getRaceEnded() == false)
////                            throw new RaceStartException("The Race Isnt Over Yet");
////                        if (arena != null) {
////                            for (Racer r : arena.getCompletedRacers()) {
////                                field.remove(r.getlabel());
////                            }
////                            for (Racer r : arena.getActiveRacers()) {
////                                field.remove(r.getlabel());
////                            }
////                            for (Racer r : arena.getDisabledRacers()) {
////                                field.remove(r.getlabel());
////                            }
////                        }
////                        double len = 0;
////                        int maxRacers = 0;
////                        boolean isError = false;
////                        int width = 1100;
////
////                        try {
////                            len = Double.parseDouble(txLength.getText());
////                            width = (int) len + 265;
////                            if (len < 100 || len > 3000) {
////                                isError = true;
////                                throw new IllegalArgumentException("Error! Arena Length Must Be Between 100 and 3000!");
////                            }
////                        } catch (NumberFormatException ex) {
////                            JOptionPane.showMessageDialog(null, "Error! Invalid Arena Length");
////                            isError = true;
////                        } catch (IllegalArgumentException msg) {
////                            JOptionPane.showMessageDialog(null, msg.getMessage());
////                        }
////
////                        try {
////                            maxRacers = Integer.parseInt(txSurface.getText());
////                            if (maxRacers < 1 || maxRacers > 20) {
////                                isError = true;
////                                throw new IllegalArgumentException("Error! Max Racers Must Be Between 1 and 20");
////                            }
////                        } catch (NumberFormatException ex) {
////                            JOptionPane.showMessageDialog(null, "Error! Invalid Max Racers Number");
////                            isError = true;
////
////                        } catch (IllegalArgumentException msg) {
////                            JOptionPane.showMessageDialog(null, msg.getMessage());
////                        }
////
////                        if (!isError) {
////                            if (maxRacers > 10) {
////                                setSize(new Dimension(width, 1300));
////                            } else {
////                                setSize(new Dimension(width, 700));
////                            }
////                            selectArena(surfaceBox, racerBOX, field, len, maxRacers, field);
////                        }
////
////
////                    } catch (RaceStartException msg) {
////                        JOptionPane.showMessageDialog(null, msg.getMessage());
////                    }
////                } else {
////                    try {
////                        if (arena != null && arena.getRaceEnded() == false)
////                            throw new RaceStartException("The Race Isnt Over Yet");
////                        if (arena != null) {
////                            for (Racer r : arena.getCompletedRacers()) {
////                                field.remove(r.getlabel());
////                            }
////                            for (Racer r : arena.getActiveRacers()) {
////                                field.remove(r.getlabel());
////                            }
////                            for (Racer r : arena.getDisabledRacers()) {
////                                field.remove(r.getlabel());
////                            }
////                        }
////                        Builder carRace = new Builder(field, iconLabels);
////                        carRace.setN(Integer.parseInt(txSurface.getText()));
////                        carRace.buildRacers();
////                        carRace.setImage();
////                        arena = carRace.getArena();
////                    }catch (RaceStartException msg) {
////                        JOptionPane.showMessageDialog(null, msg.getMessage());
////                    }
////                }
////            }
////
////        });
//
//
//
//
////        addracer.addActionListener(new
////
////                                           ActionListener() {
////
////                                               @Override
////                                               public void actionPerformed (ActionEvent e){
////                                                   try {
////                                                       if (arena.isRaceStart()) {
////                                                           throw new RaceStartException();
////                                                       }
////                                                       String racertype = racerBOX.getSelectedItem().toString();
////                                                       String racerpath = "";
////                                                       ImageIcon playerIcon = new ImageIcon(String.format("icons/%s%s.png", racertype, colorBOX.getSelectedItem().toString()));
////
////                                                       Image resizedImage = playerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
////                                                       // Create a new ImageIcon with the resized image
////                                                       ImageIcon resizedIcon = new ImageIcon(resizedImage);
////                                                       JLabel iconLabel = new JLabel(resizedIcon);
////                                                       int yPosition;
////
////                                                       if (racertype.equals("Car") || racertype.equals("Horse") || racertype.equals("Bicycle")) {
////                                                           racerpath = "game.racers.land.";
////                                                       } else if (racertype.equals("Airplane") || racertype.equals("Helicopter")) {
////                                                           racerpath = "game.racers.air.";
////                                                       } else {
////                                                           racerpath = "game.racers.naval.";
////                                                       }
////
////                                                       try {
////                                                           if (racertype.equals("Car") || racertype.equals("Bicycle") || racertype.equals("Airplane")) {
////                                                               if (txracername.getText().equals(""))
////                                                                   throw new IllegalArgumentException("Invalid Racer Name");
////                                                               Racer racer = racebuilder.buildWheeledRacer(racerpath + racertype, txracername.getText(), Double.parseDouble(txmaxspeed.getText()), Double.parseDouble(txaccelerat.getText()), getcolor(colorBOX.getSelectedItem().toString()), 0);
////                                                               txracername.setText("");
////                                                               txmaxspeed.setText("");
////                                                               txaccelerat.setText("");
////                                                               arena.addRacer(racer);
////                                                               arena.initRace();
////                                                               racer.setLabel(iconLabel);
////                                                               Point p = racer.getCurrentLocation();
////                                                               yPosition = (int) p.get_y();
////                                                               iconLabel.setBounds(0, yPosition, 50, 50);
////                                                               iconLabels.add(iconLabel);
////                                                               field.add(iconLabel);
////                                                               field.repaint();
////                                                           } else {
////                                                               Racer racer = racebuilder.buildRacer(racerpath + racertype, txracername.getText(), Double.parseDouble(txmaxspeed.getText()), Double.parseDouble(txaccelerat.getText()), getcolor(colorBOX.getSelectedItem().toString()));
////                                                               if (txracername.getText().equals(""))
////                                                                   throw new IllegalArgumentException("Invalid Racer Name");
////                                                               txracername.setText("");
////                                                               txmaxspeed.setText("");
////                                                               txaccelerat.setText("");
////                                                               arena.addRacer(racer);
////                                                               arena.initRace();
////                                                               racer.setLabel(iconLabel);
////                                                               Point p = racer.getCurrentLocation();
////                                                               yPosition = (int) p.get_y();
////                                                               iconLabel.setBounds(0, yPosition, 50, 50);
////                                                               iconLabels.add(iconLabel);
////                                                               field.add(iconLabel);
////                                                               field.repaint();
////                                                           }
////                                                       } catch (InvocationTargetException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (InstantiationException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (IllegalAccessException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (ClassNotFoundException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (NoSuchMethodException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (RacerTypeException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (RacerLimitException ex) {
////                                                           JOptionPane.showMessageDialog(null, ex.getMessage());
////                                                       } catch (IllegalArgumentException ex) {
////                                                           JOptionPane.showMessageDialog(null, "Invalid Racer Input");
////                                                       }
////
////                                                   } catch (RaceStartException ex) {
////                                                       JOptionPane.showMessageDialog(null, ex.getMessage() + ". Please build a new arena.");
////
////                                                   } catch (NullPointerException msg) {
////                                                       JOptionPane.showMessageDialog(null, "The Arena Does Not Exist");
////                                                   }
////
////                                               }
////                                           });
////
////        copylast.addActionListener(new
////
////                                           ActionListener() {
////
////                                               @Override
////                                               public void actionPerformed (ActionEvent e){
////                                                   try {
////                                                       if (arena.isRaceStart()) {
////                                                           throw new RaceStartException();
////                                                       }
////                                                       int lastIndex = arena.getActiveRacers().size() - 1;
////                                                       Racer copyandup = arena.getActiveRacers().get(lastIndex);
////                                                       Workshop workshop = Workshop.getInstance();
////                                                       Racer updet = copyandup.clone();
////                                                       workshop.up(updet, getcolor(colorBOXcopy.getSelectedItem().toString()));
////                                                       ImageIcon playerIcon = new ImageIcon(String.format("icons/%s%s.png", updet.getClass().getSimpleName(), colorBOXcopy.getSelectedItem().toString()));
////
////                                                       Image resizedImage = playerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
////                                                       ImageIcon resizedIcon = new ImageIcon(resizedImage);
////                                                       JLabel iconLabel = new JLabel(resizedIcon);
////                                                       int yPosition;
////                                                       try {
////                                                           arena.addRacer(updet);
////                                                           arena.initRace();
////                                                           updet.setLabel(iconLabel);
////                                                           Point p = updet.getCurrentLocation();
////                                                           yPosition = (int) p.get_y();
////                                                           iconLabel.setBounds(0, yPosition, 50, 50);
////                                                           iconLabels.add(iconLabel);
////                                                           field.add(iconLabel);
////                                                           field.repaint();
////                                                       } catch (RacerTypeException ex) {
////                                                           throw new RuntimeException(ex);
////                                                       } catch (RacerLimitException ex) {
////                                                           JOptionPane.showMessageDialog(null, ex.getMessage());
////                                                       }
////                                                   } catch (RaceStartException ex) {
////                                                       JOptionPane.showMessageDialog(null, ex.getMessage() + ". Please build a new arena.");
////
////                                                   } catch (NullPointerException msg) {
////                                                       JOptionPane.showMessageDialog(null, "The Arena Does Not Exist");
////                                                   } catch (IndexOutOfBoundsException ex) {
////                                                       JOptionPane.showMessageDialog(null, "No Previous Racers");
////
////                                                   }
////
////
////                                               }
////                                           });
////
////        buildCarRace.addActionListener(new
////
////                                               ActionListener() {
////                                                   public void actionPerformed (ActionEvent e){
////                                                       if (menuType == false) {
////                                                           LBuildArena.hide();
////                                                           LLength.hide();
////                                                           LSnowSurface.setText("Enter Amount Of Cars");
////                                                           buildCarRace.setText("Back To Default Menu");
////                                                           btnBuildArena.setText("Build Race");
////                                                           txLength.hide();
////                                                           surfaceBox.hide();
////
////                                                           JTextField txSurface = new JTextField();
////                                                           menuType = true;
////                                                       } else {
////                                                           LBuildArena.show();
////                                                           LLength.show();
////                                                           LSnowSurface.setText("Max Racers Number:");
////                                                           buildCarRace.setText("Build Car Race");
////                                                           btnBuildArena.setText("Build Arena");
////                                                           txLength.show();
////                                                           surfaceBox.show();
////
////                                                           JTextField txSurface = new JTextField();
////                                                           menuType = false;
////                                                       }
////                                                   }
////                                               });
//
//        this.getContentPane().add(field, BorderLayout.CENTER);
//        this.getContentPane().add(info, BorderLayout.EAST);
//
//        pack(); // sets the window size based on the preferred size of its components
//
//        setLocationRelativeTo(null); // centers the window on the screen
//
//        setVisible(true);
//    }
//
////    public void selectArena (JComboBox < String > surfaceBox, JComboBox < String > racerBox, ImagePanel field,
////                             double len, int max, ImagePanel p){
////        // Your method code goes here
////        String type = (String) surfaceBox.getSelectedItem();
////        String typebuild;
////        field.changeImage(String.format("icons/%s.jpg", type));
////
////        field.repaint();
////        String[] newracers;
////        if (type.equals("AerialArena")) {
////            newracers = new String[]{"Airplane", "Helicopter"};
////            typebuild = "game.arenas.air.AerialArena";
////        } else if (type.equals("LandArena")) {
////            newracers = new String[]{"Bicycle", "Car", "Horse"};
////            typebuild = "game.arenas.land.LandArena";
////        } else if (type.equals("NavalArena")) {
////            newracers = new String[]{"RowBoat", "SpeedBoat"};
////            typebuild = "game.arenas.naval.NavalArena";
////        } else {
////            newracers = new String[]{};
////            typebuild = "nothing";
////        }
////        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(newracers);
////        racerBox.setModel(model);
////        try {
////            arena = racebuilder.btnBuildArena(typebuild, len, max);
////            arena.setpanel(p);
////        } catch (ClassNotFoundException ex) {
////            throw new RuntimeException(ex);
////        } catch (NoSuchMethodException ex) {
////            throw new RuntimeException(ex);
////        } catch (InstantiationException ex) {
////            throw new RuntimeException(ex);
////        } catch (IllegalAccessException ex) {
////            throw new RuntimeException(ex);
////        } catch (InvocationTargetException ex) {
////            throw new RuntimeException(ex);
////        }
////        racerBox.repaint();
////
////    }
//
////    public EnumContainer.Color getcolor (String c){
////        if (c.equals("RED")) {
////            return EnumContainer.Color.RED;
////        } else if (c.equals("BLUE")) {
////            return EnumContainer.Color.BLUE;
////        } else if (c.equals("YELLOW")) {
////            return EnumContainer.Color.YELLOW;
////        } else if (c.equals("GREEN")) {
////            return EnumContainer.Color.GREEN;
////        } else return EnumContainer.Color.BLACK;
////    }
//
//
//}

package game.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        setTitle("Competition");
        setPreferredSize(new Dimension(1000, 700));
        setLocationRelativeTo(null); // center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel field = new JPanel();
        JPanel info = new JPanel();
        info.setLayout(new GridLayout(0, 1, 5, 0));

        // Create a line border around all the panels to the right
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Arena Panel
        JPanel setArena = new JPanel(new GridLayout(0, 1));
        setArena.setBorder(lineBorder);
        JLabel LBuildArena = new JLabel("Build Arena");
        JLabel LLength = new JLabel("Arena Length:");
        JTextField txLength = new JTextField();
        txLength.setText("700");
        JLabel LSnowSurface = new JLabel("Snow Surface:");
        JComboBox<String> surfaceBox = new JComboBox<>(new String[]{"Powder", "Crud", "Ice"});
        JLabel LWeatherConditions = new JLabel("Weather Conditions:");
        JComboBox<String> weatherBox = new JComboBox<>(new String[]{"Sunny", "Cloudy", "Stormy"});
        JButton btnBuildArena = new JButton("Build Arena");
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
        JComboBox<String> competitionBox = new JComboBox<>(new String[]{"Ski", "Snowboard"});
        JLabel LMaxCompetitors = new JLabel("Max Competitors Number:");
        JTextField maxCompetitorsField = new JTextField();
        maxCompetitorsField.setText("10");
        JLabel LDiscipline = new JLabel("Discipline:");
        JComboBox<String> disciplineBox = new JComboBox<>(new String[]{"Slalom", "Giant Slalom", "Downhill", "Freestyle"});
        JLabel LLeague = new JLabel("League:");
        JComboBox<String> leagueBox = new JComboBox<>(new String[]{"Junior", "Adult", "Senior"});
        JLabel LGender = new JLabel("Gender:");
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        JButton btnCreateCompetition = new JButton("Create Competition");
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
        JTextField nameField = new JTextField();
        JLabel LAge = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel LMaxSpeed = new JLabel("Max Speed:");
        JTextField maxSpeedField = new JTextField();
        JLabel LAcceleration = new JLabel("Acceleration:");
        JTextField accelerationField = new JTextField();
        JButton btnAddCompetitor = new JButton("Add Competitor");
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
        JButton btnStartCompetition = new JButton("Start Competition");
        JButton btnShowInfo = new JButton("Show Info");
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
    }

    public static void main(String[] args) {
        new Window();
    }
}

