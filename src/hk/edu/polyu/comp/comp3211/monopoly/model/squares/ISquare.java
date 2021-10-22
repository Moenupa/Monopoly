package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/**
 * Any square of the board
 */
public interface ISquare {
    /**
     * Generate an effect to a player
     * @param player dest. player
     */
    void execute(Player player);
}
