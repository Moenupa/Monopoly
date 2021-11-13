package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

import java.util.Scanner;

/** The Property squares of the board */
public class Property implements ISquare {
    /** Name of the property */
    private final String name;
    /** Selling price of the property */
    private final int price;
    /** Rental price of the property */
    private final int rent;
    /** The owner of the property */
    private Player owner;

    /**
     * Generate an effect to a player
     *
     * <p>Property Effect:
     *
     * <ul>
     *   <li>if property is not owned by any player:
     *       <p>the visiting player can buy the property at the selling price or do nothing;
     *   <li>if property is owned by a player:
     *       <p>then if visiting player is the owner, nothing happens, otherwise the visiting player
     *       must pay the owner a rent.
     * </ul>
     *
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
        if (this.owner == null) {
            askToBuy(player);
        } else if (this.owner != player) {
            System.out.println("This property is owned by " + this.owner);
            System.out.println("You should pay HKD " + this.rent + " to " + this.owner);
            player.addMoney(-this.rent);
            this.owner.addMoney(this.rent);
        }
    }

    /**
     * Initialize a property with params:
     *
     * @param s name of the property
     * @param p selling price of the property
     * @param r rental price of the property
     */
    public Property(String s, int p, int r) {
        this.name = s;
        this.price = p;
        this.rent = r;
        this.owner = null;
    }

    /**
     * Ask player whether to buy this property or not
     *
     * @param player the player to be asked
     */
    private void askToBuy(Player player) {
        Scanner in = Main.getScanner();
        String option;

        while (true) {
            System.out.print("Do you want to buy this property? (Y/N): ");

            if (Main.TEST) option = "Y";
            else option = in.nextLine().toUpperCase();

            if (option.equals("Y")) {
                setOwner(player);
                this.owner.addMoney(-this.price);
                System.out.println(this.name + " is owned by " + this.owner + " now");
                break;
            } else if (option.equals("N")) break;
            else System.out.println("Please enter \"Y\" or \"N\"");
        }
    }

    /**
     * Get the name of the property
     *
     * @return name of the property
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the owner of the property
     *
     * @return owner of the property
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Set the owner to a custom player
     *
     * @param _owner dest. player
     */
    public void setOwner(Player _owner) {
        this.owner = _owner;
        System.out.println(this.name + " is owned by " + this.owner);
    }

    /**
     * Get the price of the property
     *
     * @return price of the property
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Get the rent of the property
     *
     * @return rent of the property
     */
    public int getRent() {
        return this.rent;
    }
}
