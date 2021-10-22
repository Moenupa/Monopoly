package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/**
 * The Income-Tax square of the board
 */
public class Tax implements ISquare{
    /**
     * Generate an effect to a player
     * <p>Income-Tax Effect:</p><ul>
     *   <li>the player pays 10% of the balance (in form 10*n) as tax.</li></ul>
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {

    }
}
