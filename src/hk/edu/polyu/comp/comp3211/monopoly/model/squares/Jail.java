package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

/** The In-Jail/Just-Visiting square of the board */
public class Jail implements ISquare {
    private static final int FINE = -150;
    private static final String CONFIRM_PATTERN = "^[nNyY]$";
    private static final String CONFIRM_YES_PATTERN = "^[yY]$";

    private boolean test;
    private final int[] diceResult = new int[2];
    private boolean payResult;

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
        int curInJail = player.getInJail();
        int[] dices = new int[2];
        boolean pay = false;

        // first print a general prompt to notify of the current player
        // then print msgs for each case respectively

        if (curInJail == 0) {
            Printer.printPlayerPrompt(player);
            Printer.printMsg("just pass by the jail, nothing to worry about.\n");
            return;
        }

        if (test) {
            // mock the roll dice result with some given ones
            dices = diceResult;
            pay = payResult;
        } else {
            dices[0] = player.rollDice();
            dices[1] = player.rollDice();
        }

        int stepsToMove = dices[0] + dices[1];

        // if not throw doubles
        if (curInJail == 1) {
            // if 3rd turn in jail (the last)
            PayFineAndGetOut(player, stepsToMove);
            Printer.printPlayerPrompt(player);
            Printer.printWarnMsg("pays a fine of $150 to get out of jail.\n");
        } else { // if not 3rd turn in jail
            // get whether pay
            if (!Main.TEST) {
                String inputConfirm;
                inputConfirm = Printer.scanValidInput(
                        () -> {
                            Printer.printPlayerPrompt(player);
                            Printer.printHelpMsg("opt to pay for getting out of jail? (y/n) ");
                        },
                        "Should be [y] or [n].",
                        CONFIRM_PATTERN);
                // detect user's option, pay or not
                pay = inputConfirm.matches(CONFIRM_YES_PATTERN);
            }
            if (pay) {
                // opt to pay fine to get out
                PayFineAndGetOut(player, stepsToMove);
            } else {
                // if lucky to throw a double
                if (dices[0] == dices[1]) {
                    Printer.printPlayerPrompt(player);
                    Printer.printColoredMsg(
                            Printer.ANSI_MAGENTA,
                            "good luck in throwing doubles, getting out of jail!\n");
                    GetOutOfJail(player, stepsToMove);
                    return;
                }
                // else stay in jail
                player.setInJail(curInJail - 1);
                Printer.printPlayerPrompt(player);
                Printer.printMsg("cannot get out of jail.\n");
            }
        }
    }

    public void setTest(int dice0, int dice1, boolean pay) {
        this.test = true;
        this.diceResult[0] = dice0;
        this.diceResult[1] = dice1;
        this.payResult = pay;
    }

    private static void PayFineAndGetOut(Player player, int stepsToMove) {
        // pay a fine and then get out of jail
        player.addMoney(FINE);
        GetOutOfJail(player, stepsToMove);
    }

    private static void GetOutOfJail(Player player, int stepsToMove) {
        player.setInJail(0);
        player.move(stepsToMove);
        Printer.printPlayerPrompt(player);
        Printer.printInfoMsg("gets out of jail.\n");
    }
}
