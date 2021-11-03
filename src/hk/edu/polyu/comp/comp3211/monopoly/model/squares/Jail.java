package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * The In-Jail/Just-Visiting square of the board
 */
public class Jail implements ISquare {
    /**
     * Generate an effect to a player
     *
     * <p>In-Jail/Just-Visiting Effect:
     *
     * <ul>
     *   <li>if the player just passes:
     *       <p>this square has no effect
     *   <li>if the player is at in-jail status:
     *       <p>cannot make a move, and can only get out by:
     *       <ol>
     *         <li>throwing doubles (two dice with same results) within next 3 turns
     *             <p>(and if so, immediately moves by the throw);
     *         <li>paying $150 before rolling the dice within the next 2 turns;
     *         <li>if still not out within 3 turns, paying $150 (must) to get out
     *             <p>(and if <code>cond.2</code> or <code>cond.3</code> is met, the player can
     *             throw once and move).
     *       </ol>
     * </ul>
     *
     * @param player dest. player
     */

    private int[] rollDiceResult = new int[2];
    private Map<Player, Integer> inJailRound = new HashMap<>();
    private boolean payFineForTest;

    @Override
    public void execute(Player player) {
    }

    public void setRollDIceResult(int[] result) {
        rollDiceResult[0] = result[0];
        rollDiceResult[1] = result[1];
    }

    public int getNoMoveRound(Player player) {
        return 0;
    }

    public void setPayFineForTest(boolean pay) {
        payFineForTest = pay;
    }

    /**
     * Paying a fine to get out of jail
     */
    public void payFine(Player player) {

    }
}
