/**
 * @author Yuval Turgeman id: 209299205
 *  represensts a class of type ProgramWindow, extends JFrame, Implements Observer.
 *  this is the class that manages all the changes in the GUI.
 * @methods: ctor, update, initiateListeners, determineIconPath, setCompetitorIcon, clearCompetitorIcons, updateInfoTable
 **/

package game.View;

import game.GameEngine;
import game.arena.IArena;
import game.arena.WinterArena;
import game.ViewModel.ArenaFactory;
import game.ViewModel.CompetitionBuilder;
import game.ViewModel.VM_CompetitorPanel;
import game.competition.Competition;
import game.competition.Competitor;
import game.competition.CustomObservable;
import game.competition.WinterCompetition;
import game.entities.sportsman.WinterSportsman;
import game.enums.Gender;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ProgramWindow extends JFrame implements Observer {
    // Fields
    private View_ArenaPanel viewArenaPanel;
    private View_CompetitionPanel viewCompetitionPanel;
    private View_CompetitorPanel viewCompetitorPanel;
    private View_StartInfoPanel viewStartInfoPanel;
    private CompetitionBuilder competitionBuilder;
    private ArenaFactory arenaFactory;
    private VM_CompetitorPanel vm_competitorPanel;
    private ImagePanel field;
    private JFrame infoFrame;

    private IArena arena;
    private Competition competition;
    private Map<Competitor, JLabel> competitorIcons;
    private DefaultTableModel tableModel;
    private JTable showInfoTable;

    private Integer competitorId;

    //////test:
    private JComboBox<String> activeCompetitorsComboBox;
    private JDialog activeCompetitorsDialog;
    private JButton regularClone;
    private JButton cancelBtn;
    private JPanel buttonsPanel;

//    private JDialog activeCompetitorsDialog;
//    private JComboBox<Competitor> activeCompetitorsComboBox;
    private JPanel decoratorTypesPanel;
    private JButton speedyBtn;
    private JButton coloredBtn;
    private JButton coloredClone;
    private JButton speedyClone;



    // Constructor
    public ProgramWindow() {
        try {
            competitorIcons = Collections.synchronizedMap(new HashMap<>());
            setTitle("Competition");
            setMinimumSize(new Dimension(1000, 700));
            setPreferredSize(new Dimension(1000, 700));
            setLocationRelativeTo(null); // Center the window on the screen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            field = new ImagePanel();
            field.setLayout(null);
            JPanel info = new JPanel();
            info.setLayout(new GridLayout(0, 1, 5, 0));

            arenaFactory = new ArenaFactory();
            vm_competitorPanel = new VM_CompetitorPanel();
            competitionBuilder = new CompetitionBuilder(arenaFactory);

            viewArenaPanel = new View_ArenaPanel();
            viewCompetitionPanel = new View_CompetitionPanel();
            viewCompetitorPanel = new View_CompetitorPanel();
            viewStartInfoPanel = new View_StartInfoPanel();



            //infoTable settings
            showInfoTable = new JTable();
            tableModel = new DefaultTableModel(new Object[]{"id", "Position", "Name", "Speed", "Max Speed", "Location", "Finished"}, 0);
            showInfoTable.setModel(tableModel);
            TableColumnModel columnModel = showInfoTable.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(20);             // Position Column

            // Add components to info panel
            info.add(viewArenaPanel.getarenaPanel());
            info.add(viewCompetitionPanel.getCompetitionPanel());
            info.add(viewCompetitorPanel.getCompetitorPanel());
            info.add(viewStartInfoPanel.getStartInfoPanel());

            // Add panels to main frame
            this.getContentPane().add(field, BorderLayout.CENTER);
            this.getContentPane().add(info, BorderLayout.EAST);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

            initiateListeners();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Implement the update method from Observer
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof CustomObservable) {
            Competitor competitor = ((CustomObservable) o).getCompetitor();
            JLabel icon = competitorIcons.get(competitor);
            icon.setBounds(icon.getX(), Math.min((int) competitor.getLocation().getY(), (int) ((WinterArena) arena).getLength() - 60), icon.getWidth(), icon.getHeight());
            // Force repaint and revalidate
            field.revalidate();
            field.repaint();


        }
        updateInfoTable();
    }

    private void initiateListeners() {
        viewArenaPanel.getBtnBuildArena().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (viewArenaPanel.getTxLength().getText().isEmpty() || Integer.parseInt(viewArenaPanel.getTxLength().getText()) < 700 || Integer.parseInt(viewArenaPanel.getTxLength().getText()) > 900) {
                        throw new IllegalArgumentException("Invalid input values! Please try again. Arena Length must be between 700-900");
                    }
                    if (competition != null && competition.getIsRunning()) {
                        throw new RuntimeException("the race isnt over yet");
                    }

                    if (competition != null && competition.getIsFinished()) {
                        clearCompetitorIcons();
                    }

                    if (competition != null && !competition.getIsFinished() && !competition.getIsRunning()) {
                        competition = null;
                    }

                    arena = arenaFactory.buildArena((String) viewArenaPanel.getArenaType().getSelectedItem(), viewArenaPanel.getTxLength().getText(), (String) viewArenaPanel.getSurfaceBox().getSelectedItem(), (String) viewArenaPanel.getWeatherBox().getSelectedItem());
                    //System.out.println(arena);
                    if (arena != null) {
                        setSize(new Dimension(1000, (int) ((WinterArena) arena).getLength()));
                        // Set the background image dynamically
                        field.setBackgroundImage(String.format("src/icons/%s.jpg", (String) viewArenaPanel.getWeatherBox().getSelectedItem()));
                        clearCompetitorIcons(); // clears icons from screen
                        JOptionPane.showMessageDialog(viewArenaPanel, "Arena Created Successfully");
                        //System.out.println("Arena created successfully");
                    } else {
//                        System.out.println(arena);
                        field.setBackgroundImage(null);
                    }
                    field.repaint();
//                    updateInfoTable();

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewArenaPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(viewArenaPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewCompetitionPanel.getBtnCreateCompetition().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (arena == null) {
                        throw new NullPointerException("please create an arena first");
                    }
                    if (competition != null && competition.getIsRunning()) {
                        throw new RuntimeException("the race isnt over yet");
                    }

                    if (competition != null && competition.getIsFinished()) {
                        if (arena == null)
                            throw new RuntimeException("Please create a new Arena first");
                    }

                    competition = competitionBuilder.createCompetition(viewCompetitionPanel.getMaxCompetitorsField().getText(), (String) viewCompetitionPanel.getCompetitionBox().getSelectedItem(),
                            (String) viewCompetitionPanel.getDisciplineBox().getSelectedItem(), (String) viewCompetitionPanel.getLeagueBox().getSelectedItem(), (String) viewCompetitionPanel.getGenderBox().getSelectedItem());


                    if (competition != null) {
                        clearCompetitorIcons(); // clears icons from screen
                        competitorId = 1;
                        if (competition.getMaxCompetitors() == 20) {
                            setSize(new Dimension(1200, (int) ((WinterArena) arena).getLength()));
                        }
                        JOptionPane.showMessageDialog(viewCompetitionPanel, "Competition Created Successfully");
                        updateInfoTable();
//                        System.out.println("Competition created successfully");
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(viewCompetitionPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewCompetitionPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        viewCompetitionPanel.getBtnCreateDefaultCompetition().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                    if (competition != null && competition.getIsRunning()) {
                        throw new RuntimeException("the race isnt over yet");
                    }



                    String input = JOptionPane.showInputDialog(null, "Enter number of maximum competitors :", "Input Max Competitors", JOptionPane.QUESTION_MESSAGE);
                    if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(viewCompetitionPanel, "please specify max competitors!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //todo:handle cancel of default competition


                    arena = arenaFactory.buildArena("Winter", "700", "Powder", "Sunny"); //use of ArenaFactory to build a default arena
                    setSize(new Dimension(1000, (int) ((WinterArena) arena).getLength()));
                    // Set the background image dynamically
                    field.setBackgroundImage(String.format("src/icons/%s.jpg", (String) viewArenaPanel.getWeatherBox().getSelectedItem()));
                    clearCompetitorIcons(); // clears icons from screen
                    JOptionPane.showMessageDialog(viewArenaPanel, "Arena Created Successfully");
                    //System.out.println("Arena created successfully");

                    competition = competitionBuilder.createDefaultCompetition(input);


                    if (competition != null) {
                        clearCompetitorIcons(); // clears icons from screen
                        competitorId = 1;
                        if (competition.getMaxCompetitors() == 20) {
                            setSize(new Dimension(1200, (int) ((WinterArena) arena).getLength()));
                        }

                        for (int i = 0; i < competition.getMaxCompetitors(); i++) {
                            Competitor competitor = vm_competitorPanel.createDefaultCompetitor("Default_Name_" + String.valueOf(competitorId), competitorId);
                            competition.addCompetitor(competitor);

                            // Add the competitor icon
                            String iconPath = determineIconPath(competitor);
                            setCompetitorIcon(competitor, iconPath);
                            System.out.println(competition.getActiveCompetitors().size());
//                            updateInfoTable();
                            competitorId++;
                        }


                        JOptionPane.showMessageDialog(viewCompetitionPanel, "Competition Created Successfully");
                        updateInfoTable();
//                        System.out.println("Competition created successfully");
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(viewCompetitionPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewCompetitionPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewCompetitorPanel.getBtnAddCompetitor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (arena == null)
                        throw new NullPointerException("cannot add competitor because arena does not exist");
                    if (competition == null)
                        throw new NullPointerException("cannot add competitor because competition does not exist");
                    if (competition != null && competition.getIsRunning()) {
                        throw new RuntimeException("the race isn't over yet");
                    }
                    if (competition != null && competition.getIsFinished()) {
                        throw new RuntimeException("Please create a new competition first");
                    }


                    if (!((WinterCompetition) competition).getLeague().isInLeague(Double.parseDouble(viewCompetitorPanel.getAgeField().getText())))
                        throw new RuntimeException("Competitor age out of bounds");

                    Competitor competitor = vm_competitorPanel.createCompetitor(
                            viewCompetitorPanel.getNameField().getText(),
                            viewCompetitorPanel.getAgeField().getText(),
                            viewCompetitorPanel.getMaxSpeedField().getText(),
                            viewCompetitorPanel.getAccelerationField().getText(),
                            ((WinterCompetition) competitionBuilder.getCompetition()).getGender(),
                            ((WinterCompetition) competitionBuilder.getCompetition()).getDiscipline(),
                            competitionBuilder.getCompetition().getClass(),
                            competitorId++//todo:take care of competitorId resets and increase. i think i did but make sure
                    );


                    competition.addCompetitor(competitor);
                    System.out.println("acceleration: " + ((WinterSportsman) competitor).getAcceleration());


                    // Add the competitor icon
                    String iconPath = determineIconPath(competitor);
                    setCompetitorIcon(competitor, iconPath);
                    System.out.println(competition.getActiveCompetitors().size());
                    System.out.println(competitorIcons.size());
//                    System.out.println(competitorIcons);
                    updateInfoTable();

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewCompetitorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        viewCompetitorPanel.getBtnCloneCompetitor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (arena == null)
                        throw new NullPointerException("cannot clone competitor because arena does not exist");
                    if (competition == null)
                        throw new NullPointerException("cannot clone competitor because competition does not exist");
                    if (competition != null && competition.getIsRunning()) {
                        throw new RuntimeException("the race isn't over yet");
                    }
                    if (competition != null && competition.getIsFinished()) {
                        throw new RuntimeException("Please create a new competition first");
                    }
                    if (competition.getActiveCompetitors().isEmpty())
                        throw new RuntimeException("Please create a competitor before attempting to clone");




                    activeCompetitorsComboBox = new JComboBox(getActiveCompetitorNames());
                    activeCompetitorsDialog = new JDialog();
                    regularClone = new JButton("Regular");
                    coloredClone = new JButton("colored");
                    speedyClone = new JButton("Speedy");
                    cancelBtn = new JButton("Cancel");
                    activeCompetitorsDialog.add(activeCompetitorsComboBox, BorderLayout.CENTER);
                    buttonsPanel = new JPanel(new FlowLayout());
                    buttonsPanel.add(regularClone);
                    buttonsPanel.add(coloredClone);
                    buttonsPanel.add(speedyClone);
                    buttonsPanel.add(cancelBtn);
                    activeCompetitorsDialog.add(buttonsPanel, BorderLayout.SOUTH);
                    activeCompetitorsDialog.pack();
                    activeCompetitorsDialog.setVisible(true);
                    int indexSelected = activeCompetitorsComboBox.getSelectedIndex();


                    cancelBtn.addActionListener(cancel -> {
                        activeCompetitorsDialog.dispose();
                    });

                    coloredClone.addActionListener(cc -> {
                        regularCloneCompetitor(indexSelected);
                        colorCompetitor(competition.getActiveCompetitors().size()-1);
                    });
                    speedyClone.addActionListener(sc -> {
                        regularCloneCompetitor(indexSelected);
                        speedyCompetitor(competition.getActiveCompetitors().size()-1);
                    });


                    regularClone.addActionListener(rg -> regularCloneCompetitor(indexSelected));


//                    System.out.println("attempting to clone");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(viewCompetitorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewCompetitorPanel.getBtnDecorateCompetitor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (arena == null)
                        throw new NullPointerException("cannot decorate competitor because arena does not exist");
                    if (competition == null)
                        throw new NullPointerException("cannot decorate competitor because competition does not exist");
                    if (competition != null && competition.getIsRunning()) {
                        throw new RuntimeException("the race isn't over yet");
                    }
                    if (competition != null && competition.getIsFinished()) {
                        throw new RuntimeException("Please create a new competition first");
                    }
                    if (competition.getActiveCompetitors().isEmpty())
                        throw new RuntimeException("Please create a competitor before attempting to decorate");

//                    JDialog activeCompetitorsDialog = new JDialog();
//                    JComboBox<Competitor> activeCompetitorsComboBox = new JComboBox(getActiveCompetitorNames());//todo:change
//                    JPanel decoratorTypesPanel = new JPanel(new FlowLayout());
//                    JButton speedyBtn = new JButton("Speedy");
//                    JButton coloredBtn = new JButton("Colored");
//                    activeCompetitorsDialog.add(activeCompetitorsComboBox, BorderLayout.CENTER);
//                    decoratorTypesPanel.add(speedyBtn);
//                    decoratorTypesPanel.add(coloredBtn);
//                    activeCompetitorsDialog.add(decoratorTypesPanel, BorderLayout.SOUTH);
//                    activeCompetitorsDialog.pack();
//                    activeCompetitorsDialog.setVisible(true);//todo:consider making a view_decorateCompetitorPanel

                    activeCompetitorsDialog = new JDialog();
                    activeCompetitorsComboBox = new JComboBox(getActiveCompetitorNames());//todo:change
                    decoratorTypesPanel = new JPanel(new FlowLayout());
                    speedyBtn = new JButton("Speedy");
                    coloredBtn = new JButton("Colored");
                    activeCompetitorsDialog.add(activeCompetitorsComboBox, BorderLayout.CENTER);
                    decoratorTypesPanel.add(speedyBtn);
                    decoratorTypesPanel.add(coloredBtn);
                    activeCompetitorsDialog.add(decoratorTypesPanel, BorderLayout.SOUTH);
                    activeCompetitorsDialog.pack();
                    activeCompetitorsDialog.setVisible(true);//todo:consider making a view_decorateCompetitorPanel

                    int indexSelected = activeCompetitorsComboBox.getSelectedIndex();

                    speedyBtn.addActionListener(sb -> speedyCompetitor(indexSelected));
                    coloredBtn.addActionListener(cb -> colorCompetitor(indexSelected));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(viewCompetitorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        viewStartInfoPanel.getBtnStartCompetition().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (arena == null)
                        throw new NullPointerException("cannot start competition because arena does not exist");
                    if (competition == null)
                        throw new NullPointerException("cannot start competition because competition does not exist");

                    if (competition.getIsRunning())
                        throw new RuntimeException("Competition is already running");
                    if (competition.getIsFinished())
                        throw new RuntimeException("This competition is finished, please create a new competition");
                    if (competition.getActiveCompetitors().isEmpty())
                        throw new RuntimeException("There are no active competitors, please add competitors before starting the competition");
                    if (!competition.getIsRunning() && !competition.getIsFinished())
                        GameEngine.getInstance().startRace(competition);

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewStartInfoPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        viewStartInfoPanel.getBtnShowInfo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (arena == null)
                        throw new NullPointerException("cannot show info because arena does not exist");
                    if (competition == null)
                        throw new NullPointerException("cannot show info because competition does not exist");
                    if (competition.getActiveCompetitors().isEmpty() && !competition.getIsFinished())
                        throw new NullPointerException("Please create a competitor in order to see competitor's info");

                    if (infoFrame == null) {
                        infoFrame = new JFrame("Competitors Information");
                        infoFrame.setSize(600, 400);
                        JScrollPane showInfoTable_scroll = new JScrollPane(showInfoTable);
                        infoFrame.add(showInfoTable_scroll);

                        infoFrame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                infoFrame = null;
                            }
                        });
                    }

                    updateInfoTable();
                    infoFrame.setVisible(true);
                    System.out.println(competition.getIsRunning());
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(viewStartInfoPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    private String determineIconPath(Competitor competitor) {
        // Logic to determine the correct icon path for the competitor
        Gender gender = ((WinterCompetition) competition).getGender();
        String competitionType = ((WinterCompetition) competition).getClass().toString();

        if (competitionType.equals("class game.competition.SkiCompetition") && gender == Gender.MALE)
            return "/icons/SkiMale.png";
        if (competitionType.equals("class game.competition.SnowboardCompetition") && gender == Gender.MALE)
            return "/icons/SnowboardMale.png";
        if (competitionType.equals("class game.competition.SkiCompetition") && gender == Gender.FEMALE)
            return "/icons/SkiFemale.png";
        if (competitionType.equals("class game.competition.SnowboardCompetition") && gender == Gender.FEMALE)
            return "/icons/SnowboardFemale.png";

        return "/icons/SkiMale.png";  // Placeholder path
    }

    private void setCompetitorIcon(Competitor competitor, String icon_path) {

        ImageIcon originalIcon = new ImageIcon(getClass().getResource(icon_path));
        Image scaledImage;
        JLabel iconLabel;

        int iconCount = field.getComponentCount();
        int x;
        int y = 10;
        ImageIcon icon_competitor;

        if (icon_path.charAt(12) == 'n') {  // Snowboard
            scaledImage = originalIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            icon_competitor = new ImageIcon(scaledImage);
            iconLabel = new JLabel(icon_competitor);
            x = 10 + (iconCount * 45);
            iconLabel.setBounds(x, y, 65, 55);
        } else {
            scaledImage = originalIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            icon_competitor = new ImageIcon(scaledImage);
            iconLabel = new JLabel(icon_competitor);
            x = 10 + (iconCount * 35);
            iconLabel.setBounds(x, y, 35, 35);
        }

        competitorIcons.put(competitor, iconLabel); // Add to the map
        field.add(iconLabel);
        field.revalidate();
        field.repaint();

        ((WinterSportsman) competitor).getObservable().addObserver(this);
    }


    private java.awt.Point convertToPoint(utilities.Point point) {
        return new java.awt.Point((int) point.getX(), (int) point.getY());
    }

    private void clearCompetitorIcons() {
        field.removeAll();
        field.revalidate();
        field.repaint();
    }

    private void updateInfoTable() {
        SwingUtilities.invokeLater(() -> {
            // Ensure table model is initialized correctly
            if (tableModel.getColumnCount() != 6) {
                tableModel.setColumnIdentifiers(new Object[]{"id", "Position", "Name", "Speed", "Max Speed", "Location", "Finished"});
            }

            // Clear existing rows
            tableModel.setRowCount(0);

            try {
                int index = 1;
                // Add active competitors
                for (Competitor cmp : competition.getActiveCompetitors()) {
                    WinterSportsman comp = (WinterSportsman) cmp;
                    Object[] rowData = {
                            comp.getID(),
                            index++,
                            comp.getName(),
                            comp.getSpeed(),
                            comp.getMaxSpeed(),
                            Math.min(comp.getLocation().getY(), ((WinterArena) arena).getLength()),
                            arena.isFinished(cmp)
                    };
                    if (rowData.length == tableModel.getColumnCount()) {
                        tableModel.addRow(rowData);
                    } else {
                        throw new IllegalArgumentException("Row data length does not match the number of columns in the table model.");
                    }
                }

                // Add finished competitors
                for (Competitor cmp : competition.getFinishedCompetitors()) {
                    WinterSportsman comp = (WinterSportsman) cmp;
                    Object[] rowData = {
                            comp.getID(),
                            index++,
                            comp.getName(),
                            comp.getSpeed(),
                            comp.getMaxSpeed(),
                            Math.min(comp.getLocation().getY(), ((WinterArena) arena).getLength()),
                            arena.isFinished(cmp)
                    };
                    if (rowData.length == tableModel.getColumnCount()) {
                        tableModel.addRow(rowData);
                    } else {
                        throw new IllegalArgumentException("Row data length does not match the number of columns in the table model.");
                    }
                }
            } catch (NullPointerException err) {
                JOptionPane.showMessageDialog(null, "Please build Arena, Competition and add Competitors" +
                        " to view info.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JComboBox<Competitor> getActiveCompetitorsComboBox() {
        JComboBox<Competitor> comboBox = new JComboBox<>();
        for (Competitor cmp : competition.getActiveCompetitors()) {
            comboBox.addItem(cmp);
        }
        return comboBox;
    }

    private void modifyCompetitorIcon(Competitor competitor, String icon_path, Color newColor) {

        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(getClass().getResource(icon_path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;  // Exit the method if the image can't be loaded
        }

        BufferedImage finalImage = originalImage;
        if (newColor != null) {
            // Create a new BufferedImage to store the recolored image
            BufferedImage coloredImage = new BufferedImage(
                    originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            // Replace colors in the image
            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    int pixel = originalImage.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    if (alpha > 0) { // Only change non-transparent pixels
                        coloredImage.setRGB(x, y, (newColor.getRGB() & 0x00FFFFFF) | (alpha << 24));
                    } else {
                        coloredImage.setRGB(x, y, pixel); // Preserve transparency
                    }
                }
            }
            finalImage = coloredImage;
        }

        Image scaledImage;
        if (icon_path.charAt(12) == 'n') {  // Snowboard
            scaledImage = finalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        } else {
            scaledImage = finalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        }

        // Update the existing icon
        JLabel iconLabel = competitorIcons.get(competitor);
        iconLabel.setIcon(new ImageIcon(scaledImage));

        field.revalidate();
        field.repaint();
    }

    private String[] getActiveCompetitorNames() {
        List<Competitor> competitors = competition.getActiveCompetitors();
        String[] competitorNames = new String[competition.getActiveCompetitors().size()];

        for (int i = 0; i < competition.getActiveCompetitors().size(); i++) {
            competitorNames[i] = competition.getActiveCompetitors().get(i).toString();
        }

        return competitorNames;
    }

    private void colorCompetitor(int indexSelected){
//        int indexSelected = activeCompetitorsComboBox.getSelectedIndex();
        WinterSportsman selectedCompetitor = (WinterSportsman) competition.getActiveCompetitors().get(indexSelected);
        Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);

        if (chosenColor == null) {
            activeCompetitorsDialog.dispose();
            return;//?
        }

        String competitor_icon_path;
        competitor_icon_path = determineIconPath(selectedCompetitor);
        modifyCompetitorIcon(selectedCompetitor, competitor_icon_path, chosenColor);
        JOptionPane.showMessageDialog(null, "Decoration completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        activeCompetitorsDialog.dispose();
    }

    private void speedyCompetitor(int indexSelected){
//        int indexSelected = activeCompetitorsComboBox.getSelectedIndex();
        WinterSportsman selectedCompetitor = (WinterSportsman) competition.getActiveCompetitors().get(indexSelected);
        System.out.println(selectedCompetitor.getAcceleration());
        String newAccelerationStr;
        double newAcceleration = selectedCompetitor.getAcceleration();
        // Prompt the user for input
        newAccelerationStr = JOptionPane.showInputDialog(ProgramWindow.this,
                "Enter Acceleration for the competitor:", "New Acceleration", JOptionPane.QUESTION_MESSAGE);

        // Check if the user pressed Cancel or closed the dialog
        if (newAccelerationStr == null) {
            activeCompetitorsDialog.dispose();
            return;//?
        }

        try {
            // Try to parse the input to a double
            newAcceleration = Double.parseDouble(newAccelerationStr);
            JOptionPane.showMessageDialog(
                    ProgramWindow.this,
                    "Acceleration updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NumberFormatException ex) {
            // Handle invalid number format
            JOptionPane.showMessageDialog(
                    ProgramWindow.this,
                    "Invalid input. Please enter a valid number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        selectedCompetitor.setAcceleration(newAcceleration);
        System.out.println(selectedCompetitor.getAcceleration());

        activeCompetitorsDialog.dispose();
    }

    private void regularCloneCompetitor(int indexSelected){
//        int indexSelected = activeCompetitorsComboBox.getSelectedIndex();
        Competitor selectedCompetitor = competition.getActiveCompetitors().get(indexSelected);
        Competitor cloned = ((WinterSportsman) selectedCompetitor).clone();
        cloned.setId(competitorId++);
        competition.addCompetitor(cloned);
        String iconPath = determineIconPath(cloned);
        setCompetitorIcon(cloned, iconPath);
        System.out.println(competition.getActiveCompetitors().size());
        activeCompetitorsDialog.dispose();
        updateInfoTable();
    }
}

