package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.model.squares.ISquare;

public class Board {
    private Player[] players;
    private ISquare[] squares;
    private int round;
    private int p_index;
    public Board(int num) {}

    public Player[] getPlayers() {
        return players;
    }

    public ISquare[] getSquares() {
        return squares;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getP_index() {
        return p_index;
    }

    public void setP_index(int p_index) {
        this.p_index = p_index;
    }

    public void save(String name){

    }

    public void load(String name){

    }
}
