package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * The In-Jail/Just-Visiting square of the board
 */
public class Jail implements ISquare {
    /**
     * Amount to be paid to leave jail
     */
    private static final int FINE = 150;
    /**
     * Dice result of the player in jail
     */
    private static int[] diceResult = new int[2];
    /**
     * Flag for test to indicate if player pay fine or not
     */
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
        // check if in jail
        if (!isInJail(player))
            return;

        // pay or not
        if (player.getInJail() > 1)
            askPayFine(player);

        // throw dice
        if (tryDoubles(player))
            player.setInJail(0);

        if (player.getInJail() == 1)
            payFine(player);

        if (!isInJail(player)) {
            player.move(diceResult[0] + diceResult[1]);
            return;
        }

        player.setInJail(player.getInJail() - 1);
    }

    /**
     * Check if the player is in jail
     *
     * @param player player to be checked
     * @return true if she is in jail; false if she is not
     */
    private static boolean isInJail(Player player) {
        return player.getInJail() > 0;
    }

    /**
     * Ask player to pay fine
     *
     * @param player player to be asked
     */
    private static void askPayFine(Player player) {
        Scanner in = Main.getScanner();
        String option;

        while (true) {
            System.out.print("Do you want to pay HKD " + FINE + " to get out of jail? (Y/N): ");

            if (Main.TEST)
                if (isPayFine)
                    option = "Y";
                else
                    option = "N";
            else
                option = in.nextLine();

            if (option.equals("Y")) {
                payFine(player);
                break;
            } else if (option.equals("N"))
                break;
            else
                System.out.println("Please enter \"Y\" or \"N\"");
        }
    }

    /**
     * Try to throw doubles to get out of jail or move
     *
     * @param player player who throw dice
     * @return true if is doubles; false if not doubles
     */
    private static boolean tryDoubles(Player player) {
        if (!Main.TEST)
            diceResult = player.rollDice();
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
        System.out.println(player.getName() + " has paid fine to get out of jail");
    }

    /**
     * Used for test
     *
     * @param dice0 first dice result
     * @param dice1 second dice result
     * @param isPay selection of pay fine or not
     */
    public static void setTest(int dice0, int dice1, boolean isPay) {
        diceResult[0] = dice0;
        diceResult[1] = dice1;
        isPayFine = isPay;
    }
}
