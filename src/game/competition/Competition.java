package game.competition;
import game.arena.WinterArena;
import game.entities.sportsman.Sportsman;
import game.enums.ArenaType;

public abstract class Competition {

    //Fields //make getters and setters
    private ArenaType arenaType;
    private Object currentArena;
    private double minAge;
    private  String gender;
    private  String league;
    private String qualification;




    //ctor

    public Competition(double minAge, String league, String gender, String qualification) {
        this.minAge = minAge;
        this.league = league;
        this.gender = gender;
        this.qualification = qualification;
    }//add checks

    public Competition(Competition comp){
        this.gender = comp.gender;
        this.gender = comp.gender;
        this.league = comp.league;
        this.qualification = comp.qualification;
    }// is it supposed to be like this? without checks?

    //methods
    public void initRace(){

    }

    public boolean setArena(Object arena){
        //todo:implement correctly
        if(currentArena instanceof WinterArena)
            ((WinterArena)currentArena).initRace();//change in the future to include all kinds of arenas
    }// i don't understand why to do all these checks and castings, why not to make Arena abstract class?

    public boolean validCompetitor(Sportsman sportsman){
        //todo:implement
        return true;
    }

    public boolean add(Sportsman sportsman){
        //todo:implement
        return true;
    }

    public boolean playTurn(){
        //todo:implement
        //return currentArena.playTurn, maybe playTurn()
        return true;
    }


    //getters and setters:

    public ArenaType getArenaType() {
        return arenaType;
    }

    public Object getCurrentArena() {
        return currentArena;
    }

    public double getMinAge() {
        return minAge;
    }

    public String getGender() {
        return gender;
    }

    public String getLeague() {
        return league;
    }

    public String getQualification() {
        return qualification;
    }

    public void setArenaType(ArenaType arenaType) {
        this.arenaType = arenaType;
    }

    public void setCurrentArena(Object currentArena) {
        this.currentArena = currentArena;
    }

    public void setMinAge(double minAge) {
        this.minAge = minAge;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    //toString + equals

    public String toString(){
        //todo:implement
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        //todo:implement
        return true;
    }

}