package game;



import game.View.ProgramWindow;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.competition.SnowboardCompetition;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Snowboarder;
import game.enums.*;

import javax.swing.*;

public class Controller {
    private ProgramWindow programWindow = null;
    private IArena arena = null;
    private Competition competition = null;
    private Competitor competitor = null;

    public Controller() {
    }

    public void setView(ProgramWindow programWindow){
        this.programWindow = programWindow;
    }

    public IArena handleBuildArena(String arenaLength, String snowSurface, String weatherConditions) {
        try {
//            if(arena!=null)//todo:if arena is built already and competition is still running, or arena is built and finished, dunnooooo

            if (arenaLength.isEmpty() || Integer.parseInt(arenaLength) < 700 || Integer.parseInt(arenaLength) > 900) {
                throw new IllegalArgumentException("Invalid input values! Please try again. Arena Length must be between 700-900");
            }

            double doubleArenaLength = Double.parseDouble(arenaLength);
            SnowSurface surface = SnowSurface.convert((String) programWindow.getSurfaceBox().getSelectedItem());
            WeatherCondition wc = WeatherCondition.convert((String) programWindow.getWeatherBox().getSelectedItem());

            arena = new WinterArena(doubleArenaLength, surface, wc);
            competition = null;


            return arena;
//            JOptionPane.showMessageDialog(view, "Arena Built Successfully");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(programWindow, "Please enter a valid number for the arena length.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | NoSuchFieldException ex ) {
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public Competition handleCreateCompetition(String maxCompetitors, String competitionType, String discipline, String league, String gender) {
        try {
            if(arena == null){
                throw new NullPointerException("Please build arena, create competition and add competitors!");
            }//todo:this if + nullpointer catch will be repeated code, ask if there is a better way
//            int maxCompetitorsInt = Integer.parseInt(maxCompetitors);


            if (maxCompetitors.isEmpty() || Integer.parseInt(maxCompetitors) < 1 || Integer.parseInt(maxCompetitors) > 20) {
                throw new IllegalArgumentException("Invalid Max Competitors Number, must be between 1-20");
            }
//            model.setCompetitionType((String) view.getCompetitionBox().getSelectedItem());//
//            model.setMaxCompetitors(maxCompetitors);
//            model.setDiscipline((String) view.getDisciplineBox().getSelectedItem());
//            model.setLeague((String) view.getLeagueBox().getSelectedItem());
//            model.setGender((String) view.getGenderBox().getSelectedItem());


            int maxCompetitorsInt = Integer.parseInt(maxCompetitors);
            Discipline discipline1 = Discipline.convert(discipline);
            League league1 = League.convert(league);
            Gender gender1 = Gender.convert(gender);


            switch (competitionType){
                case "Ski":
                    competition = new SkiCompetition((WinterArena) arena, maxCompetitorsInt ,discipline1,league1,gender1);
                    break;
                case "Snowbaord":
                    competition = new SnowboardCompetition((WinterArena) arena, maxCompetitorsInt ,discipline1,league1,gender1);
                    break;
            }

            return competition;

//            JOptionPane.showMessageDialog(view, "Competition Created Successfully");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(programWindow, "Please enter a valid number for the max competitors.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | NoSuchFieldException ex) {
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void handleAddCompetitor(String name, String age, String maxSpeed, String acceleration) {
        try {
            if(arena == null){
                throw new NullPointerException("Please build arena, create competition and add competitors!");
            }

            if(competition == null){
                throw new NullPointerException("Please create competition before adding competitors!");
            }

            if (age.isEmpty() || Integer.parseInt(age) <= 0) {
                throw new IllegalArgumentException("Invalid Age");
            }
            if (maxSpeed.isEmpty() || Double.parseDouble(maxSpeed) <= 0) {
                throw new IllegalArgumentException("Invalid Max Speed");
            }
            if (acceleration.isEmpty()) {
                throw new IllegalArgumentException("Invalid Acceleration");
            }

            double age1 = Double.parseDouble(age);
            double maxSpeed1 = Double.parseDouble(maxSpeed);
            double acceleration1 = Double.parseDouble(acceleration);


            if(competition instanceof SkiCompetition)
                competitor = new Skier(name,age1,((SkiCompetition)competition).getGender(), acceleration1,maxSpeed1, ((SkiCompetition)competition).getDiscipline());
            else if (competition instanceof SnowboardCompetition)
                competitor = new Snowboarder(name,age1,((SnowboardCompetition)competition).getGender(), acceleration1,maxSpeed1, ((SnowboardCompetition)competition).getDiscipline());

            competition.addCompetitor(competitor);
//            model.setCompetitorName(view.getNameField().getText());
//            model.setAge(age);
//            model.setMaxSpeed(maxSpeed);
//            model.setAcceleration(acceleration);
            JOptionPane.showMessageDialog(programWindow, "Competitor Added Successfully");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(programWindow, "Please enter valid numbers for age, max speed, and acceleration.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleStartCompetition() {
        // Logic to start competition
        try {
            if (arena == null) {
                throw new NullPointerException("Please build arena, create competition and add competitors!");
            }
            JOptionPane.showMessageDialog(programWindow, "Competition Started");
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleShowInfo() {
        // Logic to show info
        try {
            if (arena == null) {
                throw new NullPointerException("Please build arena, create competition and add competitors!");
            }
            JOptionPane.showMessageDialog(programWindow, "Showing Competition Info");
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(programWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
//
//package game;
//
//import game.arena.IArena;
//import game.arena.WinterArena;
//import game.competition.Competition;
//import game.competition.Competitor;
//import game.competition.SkiCompetition;
//import game.competition.SnowboardCompetition;
//import game.entities.sportsman.Skier;
//import game.entities.sportsman.Snowboarder;
//import game.enums.*;
//
//import javax.swing.*;
//import java.util.List;
//
//public class Controller {
//    private View view;
//    private Model model;
//
//    public Controller(Model model) {
//        this.model = model;
//    }
//
//    public void setView(View view) {
//        this.view = view;
//    }
//
//    public void buildArena(String arenaLength, String snowSurface, String weatherConditions) {
//        try {
//            double length = Double.parseDouble(arenaLength);
//            if (length < 700 || length > 900) {
//                throw new IllegalArgumentException("Arena Length must be between 700-900");
//            }
//            SnowSurface surface = SnowSurface.convert(snowSurface);
//            WeatherCondition condition = WeatherCondition.convert(weatherConditions);
//
//            model.createArena(length, surface, condition);
//            view.updateArena((WinterArena) model.getArena());
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid number for the arena length.", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (IllegalArgumentException | NoSuchFieldException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public void createCompetition(String maxCompetitors, String competitionType, String discipline, String league, String gender) {
//        try {
//            if (model.getArena() == null) {
//                throw new NullPointerException("Please build arena, create competition and add competitors!");
//            }
//
//            int maxComp = Integer.parseInt(maxCompetitors);
//            if (maxComp < 1 || maxComp > 20) {
//                throw new IllegalArgumentException("Max Competitors Number must be between 1-20");
//            }
//
//            Discipline disc = Discipline.convert(discipline);
//            League leag = League.convert(league);
//            Gender gen = Gender.convert(gender);
//
//            model.createCompetition(competitionType, maxComp, disc, leag, gen);
//            view.updateCompetition(model.getCompetition());
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid number for the max competitors.", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (IllegalArgumentException | NoSuchFieldException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (NullPointerException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public void addCompetitor(String name, String age, String maxSpeed, String acceleration) {
//        try {
//            if (model.getArena() == null) {
//                throw new NullPointerException("Please build arena, create competition and add competitors!");
//            }
//
//            if (model.getCompetition() == null) {
//                throw new NullPointerException("Please create competition before adding competitors!");
//            }
//
//            double ageVal = Double.parseDouble(age);
//            double maxSpeedVal = Double.parseDouble(maxSpeed);
//            double accelerationVal = Double.parseDouble(acceleration);
//
//            model.addCompetitor(name, ageVal, maxSpeedVal, accelerationVal);
//            view.updateCompetitors(model.getActiveCompetitors());
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), "Please enter valid numbers for age, max speed, and acceleration.", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (IllegalArgumentException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (NullPointerException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public void startCompetition() {
//        try {
//            if (model.getArena() == null) {
//                throw new NullPointerException("Please build arena, create competition and add competitors!");
//            }
//            JOptionPane.showMessageDialog(view.getFrame(), "Competition Started");
//            new Thread(String.valueOf(model.getCompetition())).start();
//        } catch (NullPointerException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public void showInfo() {
//        try {
//            if (model.getArena() == null) {
//                throw new NullPointerException("Please build arena, create competition and add competitors!");
//            }
//            JOptionPane.showMessageDialog(view.getFrame(), "Showing Competition Info");
//            // Implement the logic to display competitor info
//        } catch (NullPointerException ex) {
//            JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//}
