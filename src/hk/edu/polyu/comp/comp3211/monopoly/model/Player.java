package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.Property;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

import java.io.Serializable;
import java.util.Random;

/** A player and its status in the game */
public class Player implements Serializable {
    /** Notice to player when completed a full round (cross 20th and 1st) */
    private static final String COMPLETE_ROUND_NOTICE =
            "has completed one round of the board and gets a salary of $1500.";
    /** The Jail Square's index on board */
    private static final int JAIL_INDEX = 5;
    /** The Jail default cool-down time (in num of rounds) */
    private static final int JAIL_COOLDOWN = 3;

    /** Constant amount of money given every round */
    static final int SALARY = 1500;
    /** Name of the player (in String) */
    private String name;
    /** Current balance of the player Starts with 1500 */
    private int money = SALARY;
    /** Current position of the player from square 1-20 (or index 0-19) */
    private int position = 0;

    /** Array of properties owned by the player */
    private final Property[] properties = new Property[12];
    /** The player's in-jail cool-down time (in num of rounds), ranged 0-3 */
    private int inJail = 0;

    /** Whether the player is bankrupted and should be removed from the game */
    private boolean bankrupted = false;

    /** initialize a player and scan input from user */
    public Player() {
        if (Main.TEST) {
            return;
        }
        name =
                Printer.scanValidInput(
                        () -> {
                            Printer.printHelpMsg("Please input player name: ");
                        },
                        "Should be a non-empty string.",
                        "^(?!\\s*$).+");
    }

    /**
     * Initialize a player
     *
     * @param name name of the user
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Get the name of the player
     *
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the player to a custom string
     *
     * @param name dest. name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the balance of the player
     *
     * @return balance of the player
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set balance of the player to a custom number
     *
     * @param money dest. balance
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Add to th balance of the player
     *
     * @param money money to add
     */
    public void addMoney(int money) {
        this.money += money;
        if (this.money < 0) {
            bankrupt();
        }
    }

    /**
     * Get the position of the player
     *
     * @return position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
     * Set position of the player to a custom number
     *
     * @param position dest. position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Advance by the number of steps, note 20th square and 1st square is connected If player goes
     * past the starting position, give him salary(with notice).
     *
     * @param step number of steps
     */
    public void move(int step) {
        this.position += step;
        // if complete a whole round, give a salary and generate a notice
        if (this.position >= 20) {
            this.position %= 20;
            this.addMoney(SALARY);
            Printer.printPlayerPrompt(this);
            Printer.printMsg(COMPLETE_ROUND_NOTICE);
        }
    }

    /**
     * Get the array of properties
     *
     * @return the array of properties
     */
    public Property[] getProperties() {
        return properties;
    }

    /** The player goes to jail */
    public void goToJail() {
        this.position = JAIL_INDEX;
        this.inJail = JAIL_COOLDOWN;
    }

    /** Judge whether the player is bankrupted or not and store */
    public void bankrupt() {
        bankrupted = true;
        Printer.printPlayerPrompt(this);
        Printer.printWarnMsg("is bankrupted and retired from the game.\n");
    }

    /**
     * Get the "IN JAIL" status of the player
     *
     * @return "IN JAIL" status
     */
    public int getInJail() {
        return inJail;
    }

    /**
     * Set the "IN JAIL" status to a custom number
     *
     * @param inJail dest. "IN JAIL" status
     */
    public void setInJail(int inJail) {
        this.inJail = inJail;
    }

    /**
     * Get the player's bankrupted status
     *
     * @return true if bankrupted, else false
     */
    public boolean isBankrupted() {
        return bankrupted;
    }

    /**
     * Roll a die which is uniformly distributed from 1-4
     *
     * @return the result (ranged from 1-4)
     */
    public int rollDice() {
        var rand = new Random();
        int n = rand.nextInt(4) + 1;
        return n;
    }
}
