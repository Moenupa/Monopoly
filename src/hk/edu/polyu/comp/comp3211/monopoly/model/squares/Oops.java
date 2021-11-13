package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/** The Go-to-Jail square of the board */
public class Oops implements ISquare {
    /**
     * Generate an effect to a player
     *
     * <p>Go-to-Jail Effect:
     *
     * <ul>
     *   <li>The player immediately goes to Jail.
     * </ul>
     *
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
        player.goToJail();
        System.out.println(player.getName() + " goes into Jail");
    }
}
