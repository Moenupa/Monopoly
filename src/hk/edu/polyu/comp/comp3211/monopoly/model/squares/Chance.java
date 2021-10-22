package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/**
 * The Chance square of the board
 */
public class Chance implements ISquare{
    /**
     * Generate an effect to a player
     * <p>Chance Effect:</p><ul>
     *   <li>The player either gains a random amount (n*10) up to 200;</li>
     *   <li>or loses a random amount (n*10) up to 300.</li></ul>
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
    }
}
