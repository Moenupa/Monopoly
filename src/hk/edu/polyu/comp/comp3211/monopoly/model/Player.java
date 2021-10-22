package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.model.squares.Property;

import java.util.Random;

/**
 * A player and its status in the game
 */
public class Player {

    static final int SALARY = 1500;
    /**
     * Name of the player (in String)
     */
    private String name;
    /**
     * Current balance of the player
     * Starts with 1500
     */
    private int money = SALARY;
    /**
     * Current position of the player
     * from square 1-20 (or index 0-19)
     */
    private int position;
    /**
     * Array of properties owned by the player
     */
    private Property[] properties;
    /**
     * Whether the player is "IN JAIL"
     * 0 if not
     */
    private int inJail;
    /**
     * Whether the player is bankrupted
     * and should be remove from the game
     */
    private boolean bankrupted;

    /**
     * initialize a player
     * and scan input from user
     */
    public Player() {
    }

    /**
     * Get the name of the player
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the player to a custom string
     * @param name dest. name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the balance of the player
     * @return balance of the player
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set balance of the player to a custom number
     * @param money dest. balance
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Get the position of the player
     * @return position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
     * Set position of the player to a custom number
     * @param position dest. position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Advance by the number of steps,
     * note 20th square and 1st square is connected
     * If player goes past the starting position, give him salary(with notice).
     * @param step number of steps
     */
    public void move(int step){

    }

    /**
     * The player goes to jail
     */
    public void goToJail(){

    }

    /**
     * The player is bankrupt
     */
    public void bankrupt(){

    }

    /**
     * Get the "IN JAIL" status of the player
     * @return "IN JAIL" status
     */
    public int getInJail() {
        return inJail;
    }

    /**
     * Set the "IN JAIL" status to a custom number
     * @param inJail dest. "IN JAIL" status
     */
    public void setInJail(int inJail) {
        this.inJail = inJail;
    }

    /**
     * Judge whether the player is bankrupted
     * @return true if bankrupted, else false
     */
    public boolean isBankrupted() {
        return bankrupted;
    }

    /**
     * Roll a dice which is uniformly distributed from 1-4
     * @return the result (ranged from 1-4)
     */
    public int rollDice(){
        var rand = new Random();
        return rand.nextInt(4)+1;
    }
}
