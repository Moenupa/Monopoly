package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/** The Go square of the board */
public class Go implements ISquare {
    /**
     * Generate an effect to a player
     *
     * <p>Go Effect:
     *
     * <ul>
     *   <li>The player passes through (not necessarily lands on):
     *       <p>gets $1500 salary;
     *   <li>All players starts from this square, and at this time:
     *       <p>this square has no effect.
     * </ul>
     *
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
    }
}
