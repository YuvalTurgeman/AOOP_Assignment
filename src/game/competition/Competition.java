package game.competition;
import game.enums.ArenaType;

public class Competition {

    //Fields
    private ArenaType arenaType;
    private Object currentArena;
    private double minAge;
    private  String gender;
    private  String league;
    private String qualification;


    //ctor

    public Competition(double minAge, String gender, String league, String qualification) {
        this.minAge = minAge;
        this.gender = gender;
        this.league = league;
        this.qualification = qualification;
    }

    public Competition(Competition comp){

        this.gender = comp.gender;

    }

    //methods
    public void initRace(){
//        (Arena)currentArena.initRace();
    }

}