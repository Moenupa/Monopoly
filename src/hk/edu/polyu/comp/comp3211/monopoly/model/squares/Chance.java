package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

import java.util.Random;

/** The Chance square of the board */
public class Chance implements ISquare {
    /**
     * Generate an effect to a player
     *
     * <p>Chance Effect:
     *
     * <ul>
     *   <li>The player either gains a random amount (n*10) up to 200;
     *   <li>or loses a random amount (n*10) up to 300.
     * </ul>
     *
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
        int randomInt=new Random().nextInt(51)-30;
        player.addMoney(10*randomInt);
    }
}
