package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.controller.Game;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

/** The In-Jail/Just-Visiting square of the board */
public class Jail implements ISquare {
    private static final int FINE = 150;
    private static final String CONFIRM_PATTERN = "^[nNyY]$";
    private static final String CONFIRM_YES_PATTERN = "^[yY]$";

    private static int[] diceResult = new int[2];
    private static boolean isPayFine;

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
    @Override
    public void execute(Player player) {
        // first print a general prompt to notify of the current player
        // then print msgs for each case respectively

        // check if in jail
        if (player.getInJail() == 0) {
            Printer.printPlayerPrompt(player);
            Printer.printMsg("just pass by the jail, nothing to worry about.\n");
            return;
        }

        // pay or not
        if (player.getInJail() > 1) {
            askPayFine(player);
        }

        // throw dice
        if (tryDoubles(player) && !isPayFine) {
            Printer.printPlayerPrompt(player);
            Printer.printColoredMsg(
                    Printer.ANSI_MAGENTA, "good luck in throwing doubles, getting out of jail!\n");
            player.setInJail(0);
        }

        Printer.printMsg("Your dice result is " + diceResult[0] + " and " + diceResult[1]);

        if (player.getInJail() == 1) {
            payFine(player);
        }

        if (player.getInJail() == 0) {
            getOutOfJail(player, diceResult[0] + diceResult[1]);
            return;
        }

        // else stay in jail
        player.setInJail(player.getInJail() - 1);
        Printer.printPlayerPrompt(player);
        Printer.printMsg("cannot get out of jail.\n");
    }

    /**
     * Ask player to pay fine
     *
     * @param player player to be asked
     */
    private static void askPayFine(Player player) {
        String option;

        if (!Main.TEST) {
            option =
                    Printer.scanValidInput(
                            () -> {
                                Printer.printPlayerPrompt(player);
                                Printer.printHelpMsg("opt to pay for getting out of jail? (y/n) ");
                            },
                            "Should be [y] or [n].",
                            CONFIRM_PATTERN);
            isPayFine = option.matches(CONFIRM_YES_PATTERN);
        }

        if (isPayFine) {
            payFine(player);
        }
    }

    /**
     * Try to throw doubles to get out of jail or move
     *
     * @param player player who throw dice
     * @return true if is doubles; false if not doubles
     */
    private static boolean tryDoubles(Player player) {
        if (!Main.TEST) {
            diceResult[0] = player.rollDice();
            diceResult[1] = player.rollDice();
        }
        return diceResult[0] == diceResult[1];
    }

    /**
     * Pay fine to get out of jail
     *
     * @param player player who to pay fine
     */
    private static void payFine(Player player) {
        player.addMoney(-FINE);
        player.setInJail(0);
        Printer.printPlayerPrompt(player);
        Printer.printWarnMsg("pays a fine of $150 to get out of jail.\n");
    }

    private static void getOutOfJail(Player player, int stepsToMove) {
        player.move(stepsToMove);
        Printer.printPlayerPrompt(player);
        Printer.printInfoMsg("gets out of jail.\n");
        Game.takeEffect(player);
    }

    /**
     * Used for test
     *
     * @param dice0 first dice result
     * @param dice1 second dice result
     * @param isPay selection of pay fine or not
     */
    public void setTest(int dice0, int dice1, boolean isPay) {
        // mock the roll dice result with some given ones
        diceResult[0] = dice0;
        diceResult[1] = dice1;
        isPayFine = isPay;
    }
}
