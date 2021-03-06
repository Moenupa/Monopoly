package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

/** The Free-Parking square of the board */
public class Free implements ISquare {
    /**
     * Generate an effect to a player
     *
     * <p>Free-Parking Effect:
     *
     * <ul>
     *   <li>None.
     * </ul>
     *
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
        Printer.printPlayerPrompt(player);
        Printer.printMsg("arrives at free-parking.\n");
    }
}
