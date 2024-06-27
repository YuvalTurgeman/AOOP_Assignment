package game;


import game.competition.Competition;

public class GameEngine {
    //fields
    private Competition comp;
    private GameEngine instance = null;

    private GameEngine(){

    }

    public getInstance(){
        if(this.instance == null)
            this.instance = new GameEngine();
        return this.instance;
    }




}
