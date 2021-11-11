package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

import java.util.Scanner;

/** The Property squares of the board */
public class Property implements ISquare {
    /** Name of the property */
    private String name;
    /** Selling price of the property */
    private int price;
    /** Rental price of the property */
    private int rent;
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
        if (this.owner==null){
            Scanner in = new Scanner(System.in);
            System.out.println("Y to buy");
            String option=in.nextLine();
            if (option.equals("Y")){
                this.owner=player;
                player.addMoney(-this.price);
            }
        }
        else if (this.owner!=player){
            player.addMoney(-this.rent);
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
}
