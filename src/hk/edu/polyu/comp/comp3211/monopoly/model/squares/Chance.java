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
        int multiple;

        if (new Random().nextInt(2) == 0) {
            multiple = new Random().nextInt(20) + 1;
            multiple *= 10;
            Printer.printConfMsg("get $" + multiple + " from Chance.\n");
        } else {
            multiple = new Random().nextInt(30) + 1;
            multiple *= 10;
            Printer.printWarnMsg("lose $" + multiple + " from Chance.\n");
            multiple = -multiple;
        }
        player.addMoney(multiple);
    }
}
