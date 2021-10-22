package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/**
 * The In-Jail/Just-Visiting square of the board
 */
public class Jail implements ISquare{
    /**
     * Generate an effect to a player
     * <p>In-Jail/Just-Visiting Effect:</p><ul>
     *   <li>if the player just passes:
     *      <p>this square has no effect</p></li>
     *   <li>if the player is at in-jail status:
     *     <p>cannot make a move, and can only get out by:
     *     <ol>
     *       <li>throwing doubles (two dice with same results) within next 3 turns
     *         <p>(and if so, immediately moves by the throw);</p></li>
     *       <li>paying $150 before rolling the dice within the next 2 turns;</li>
     *       <li>if still not out within 3 turns, paying $150 (must) to get out
     *         <p>(and if <code>cond.2</code> or <code>cond.3</code> is met,
     *           the player can throw once and move).</p></li>
     *     </ol></p></li></ul>
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {

    }
}
