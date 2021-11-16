package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

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
        Printer.printPlayerPrompt(player);
        Printer.printWarnMsg("goes to jail.\n");
    }
}
