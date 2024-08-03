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
import game.ViewModel.VM_ArenaPanel;
import game.ViewModel.VM_CompetitionPanel;
import game.ViewModel.VM_CompetitorPanel;
import game.competition.Competition;
import game.competition.Competitor;
import game.competition.CustomObservable;
import game.competition.WinterCompetition;
import game.entities.sportsman.WinterSportsman;
import game.enums.Gender;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ProgramWindow extends JFrame implements Observer {
    // Fields
    private View_ArenaPanel viewArenaPanel;
    private View_CompetitionPanel viewCompetitionPanel;
    private View_CompetitorPanel viewCompetitorPanel;
    private View_StartInfoPanel viewStartInfoPanel;
    private VM_CompetitionPanel vm_competitionPanel;
    private VM_ArenaPanel vm_arenaPanel;
    private VM_CompetitorPanel vm_competitorPanel;
    private ImagePanel field;
    private JFrame infoFrame;

    private IArena arena;
    private Competition competition;
    private Map<Competitor, JLabel> competitorIcons;
    private DefaultTableModel tableModel;
    private JTable showInfoTable;

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

            vm_arenaPanel = new VM_ArenaPanel();
            vm_competitorPanel = new VM_CompetitorPanel();
            vm_competitionPanel = new VM_CompetitionPanel(vm_arenaPanel);

            viewArenaPanel = new View_ArenaPanel();
            viewCompetitionPanel = new View_CompetitionPanel();
            viewCompetitorPanel = new View_CompetitorPanel();
            viewStartInfoPanel = new View_StartInfoPanel();

            //infoTable settings
            showInfoTable = new JTable();
            tableModel = new DefaultTableModel(new Object[]{"Position", "Name", "Speed", "Max Speed", "Location", "Finished"}, 0);
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

            //printing of the movement of the icons, for developer
//            if (icon != null) {
//                SwingUtilities.invokeLater(() -> {
//                    // Ensure the position is updated correctly
//                    java.awt.Point newPosition = convertToPoint(competitor.getLocation());
//                    System.out.println("Updated icon position to: " + newPosition);
//                });
//            }
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

                    arena = vm_arenaPanel.buildArena(viewArenaPanel.getTxLength().getText(), (String) viewArenaPanel.getSurfaceBox().getSelectedItem(), (String) viewArenaPanel.getWeatherBox().getSelectedItem());

                    if (arena != null) {
                        setSize(new Dimension(1000, (int) ((WinterArena) arena).getLength()));
                        // Set the background image dynamically
                        field.setBackgroundImage(String.format("src/icons/%s.jpg", (String) viewArenaPanel.getWeatherBox().getSelectedItem()));
                        field.repaint();
                    }
                    JOptionPane.showMessageDialog(viewArenaPanel, "Arena Created Successfully");
//                    System.out.println("Arena created successfully");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewArenaPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex) {
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

                    competition = vm_competitionPanel.createCompetition(viewCompetitionPanel.getMaxCompetitorsField().getText(), (String) viewCompetitionPanel.getCompetitionBox().getSelectedItem(),
                            (String) viewCompetitionPanel.getDisciplineBox().getSelectedItem(), (String) viewCompetitionPanel.getLeagueBox().getSelectedItem(), (String) viewCompetitionPanel.getGenderBox().getSelectedItem());


                    if (competition != null) {
                        clearCompetitorIcons(); // clears icons from screen
                        if (competition.getMaxCompetitors() == 20) {
                            setSize(new Dimension(1200, (int) ((WinterArena) arena).getLength()));
                        }
                        JOptionPane.showMessageDialog(viewCompetitionPanel, "Competition Created Successfully");
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
                        throw new RuntimeException("the race isnt over yet");
                    }

                    if (competition != null && competition.getIsFinished()) {
                        throw new RuntimeException("Please create a new competition first");
                    }


                    if(!((WinterCompetition)competition).getLeague().isInLeague(Double.parseDouble(viewCompetitorPanel.getAgeField().getText())))
                        throw new RuntimeException("Competitor age out of bounds");

                    Competitor competitor = vm_competitorPanel.createCompetitor(
                            viewCompetitorPanel.getNameField().getText(),
                            viewCompetitorPanel.getAgeField().getText(),
                            viewCompetitorPanel.getMaxSpeedField().getText(),
                            viewCompetitorPanel.getAccelerationField().getText(),
                            ((WinterCompetition) vm_competitionPanel.getCompetition()).getGender(),
                            ((WinterCompetition) vm_competitionPanel.getCompetition()).getDiscipline(),
                            vm_competitionPanel.getCompetition().getClass()
                    );

                    competition.addCompetitor(competitor);
                    // Add the competitor icon
                    String iconPath = determineIconPath(competitor);
                    setCompetitorIcon(competitor, iconPath);
                    System.out.println(competition.getActiveCompetitors().size());

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(viewCompetitorPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        viewStartInfoPanel.getBtnStartCompetition().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
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
                        throw new RuntimeException("Please create a competitor in order to see competitor's info");

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
                tableModel.setColumnIdentifiers(new Object[]{"Position", "Name", "Speed", "Max Speed", "Location", "Finished"});
            }

            // Clear existing rows
            tableModel.setRowCount(0);

            try {
                int index = 1;
                // Add active competitors
                for (Competitor cmp : competition.getActiveCompetitors()) {
                    WinterSportsman comp = (WinterSportsman) cmp;
                    Object[] rowData = {
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
}

