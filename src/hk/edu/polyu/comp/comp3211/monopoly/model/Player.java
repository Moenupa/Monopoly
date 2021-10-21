package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.model.squares.Property;

import java.util.Random;

public class Player {
    private String name;
    private int money = 1500;
    private int position;
    private Property[] properties;
    private int inJail;
    private boolean bankrupted;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move(int step){

    }

    public void goToJail(){

    }

    public void bankrupt(){

    }

    public int getInJail() {
        return inJail;
    }

    public void setInJail(int inJail) {
        this.inJail = inJail;
    }

    public boolean isBankrupted() {
        return bankrupted;
    }

    public int rollDice(){
        var rand = new Random();
        return rand.nextInt(4)+1;
    }
}
