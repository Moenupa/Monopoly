package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/** The In-Jail/Just-Visiting square of the board */
public class Jail implements ISquare {
    private static final int FINE = -150;
    private static final String CONFIRM_PATTERN = "^[nNyY]$";
    private static final String CONFIRM_YES_PATTERN = "^[yY]$";
    private static final String GET_CONFIRM_MSG = "Pay or not (y/n): ";

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

        if (curInJail == 0) {
            System.out.println("You are not in jail, pass by.");
            return;
        }

        if (test) {
            dices = diceResult;
            pay = payResult;
        } else {
            dices[0] = player.rollDice();
            dices[1] = player.rollDice();
        }

        int stepsToMove = dices[0] + dices[1];

        if (dices[0] == dices[1]) { // if throw doubles
            System.out.println("You throw doubles, getting out of jail.");
            GetOutOfJail(player, stepsToMove);
        } else {
            // if not throw doubles
            if (curInJail == 1) { // if 3rd turn in jail (the last)
                PayFineAndGetOut(player, stepsToMove);
                System.out.println("You pay $150 to get out of jail.");
            } else { // if not 3rd turn in jail
                // get whether pay
                if (!Main.TEST) {
                    String inputConfirm;
                    System.out.print("You need to pay to get out of the jail.");
                    do {
                        System.out.println(GET_CONFIRM_MSG);
                        inputConfirm = Main.GetScanner().next();
                    } while (!inputConfirm.matches(CONFIRM_PATTERN));

                    pay = inputConfirm.matches(CONFIRM_YES_PATTERN);
                }
                if (pay) {
                    PayFineAndGetOut(player, stepsToMove);
                } else {
                    player.setInJail(curInJail - 1);
                    System.out.println("You cannot get out of jail.");
                }
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
        System.out.println("You get out of jail.");
    }
}
