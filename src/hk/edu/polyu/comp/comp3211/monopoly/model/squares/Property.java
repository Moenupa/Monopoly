package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

import java.util.Scanner;

/**
 * The Property squares of the board
 */
public class Property implements ISquare {
    /**
     * Name of the property
     */
    private final String name;
    /**
     * Selling price of the property
     */
    private final int price;
    /**
     * Rental price of the property
     */
    private final int rent;
    /**
     * The owner of the property
     */
    private Player owner;

    private static final String CONFIRM_PATTERN = "^[nNyY]$";
    private static final String CONFIRM_YES_PATTERN = "^[yY]$";
    private boolean isBuy;

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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public void execute(Player player) {
        Printer.printPlayerPrompt(player);
        Printer.printMsg("is visiting " + name + ".\n");

        if (this.owner == null) {
            askToBuy(player);
        } else if (this.owner != player) {
            Printer.printPlayerPrompt(player);
            Printer.printMsg("pays rent to the owner " + this.owner.getName() + " for $" + rent + ".\n");
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
        name = s;
        price = p;
        rent = r;
        owner = null;
    }

    /**
     * Ask player whether to buy this property or not
     *
     * @param player the player to be asked
     */
    private void askToBuy(Player player) {
        String option;

        if (!Main.TEST) {
            option = Printer.scanValidInput(
                    () -> {
                        Printer.printPlayerPrompt(player);
                        Printer.printHelpMsg(
                                "Do you want to buy "
                                        + name
                                        + " for $"
                                        + price
                                        + "? (y/n) ");
                    },
                    "Should be [y] or [n].",
                    CONFIRM_PATTERN);
            isBuy = option.matches(CONFIRM_YES_PATTERN);
        }

        if (isBuy) {
            Printer.printPlayerPrompt(player);
            Printer.printMsg("bought " + name + " for $" + price + ".\n");
            setOwner(player);
            owner.addMoney(-price);
            var p = player.getProperties();
            int i = 0;
            while (p[i] != null) {
                i++;
            }
            p[i] = this;
        }
    }

    /**
     * Get the name of the property
     *
     * @return name of the property
     */
    public String getName() {
        return name;
    }

    /**
     * Get the owner of the property
     *
     * @return owner of the property
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the owner to a custom player
     *
     * @param owner dest. player
     */
    public void setOwner(Player owner) {
        this.owner = owner;
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

    public void setTest(boolean test) {
        isBuy = test;
    }
}
