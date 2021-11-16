package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

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
        int randomInt = new Random().nextInt(51) - 30;
        int m = 10 * randomInt;
        player.addMoney(m);

        Printer.printPlayerPrompt(player);
        Printer.printMsg("lands on Chance Square and ");
        if (m == 0) {
            Printer.printConfMsg("nothing happens.\n");
        } else if (m > 0) {
            Printer.printConfMsg("get $" + m + " from Chance.\n");
        } else {
            Printer.printWarnMsg("lose $" + -m + " from Chance.\n");
        }
    }
}
