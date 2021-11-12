package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

import java.util.Random;
import java.util.Scanner;


/** The In-Jail/Just-Visiting square of the board */
public class Jail implements ISquare {
    private static final int fine=-150;

    private boolean test;
    private int[] diceResult = new int[2];
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

        int curInJail=player.getInJail();
        int[] dices=new int[2];
        boolean pay;


        if (curInJail==0)return;


        if (test){
            dices[0]=diceResult[0];
            dices[1]=diceResult[1];
        }
        else {
            dices=new Random().ints(2,1,7).toArray();
        }

        if (dices[0]==dices[1]){//if throw doubles
            player.setInJail(0);
            player.move(dices[0]+dices[1]);
        }
        else {//if not throw doubles
            if (curInJail==3){//if 3rd turn in jail
                player.setInJail(0);
                player.addMoney(fine);
                player.move(dices[0]+dices[1]);
            }
            else {//if not 3rd turn in jail
                //get whether pay
                if (test)pay=payResult;
                else {
                    Scanner in=new Scanner(System.in);
                    String inputString= in.nextLine();
                    if (inputString.equals("Y"))pay=true;
                    else pay=false;
                }

                if (pay){
                    player.setInJail(0);
                    player.addMoney(fine);
                    player.move(dices[0]+dices[1]);
                }
                else player.setInJail(curInJail+1);
            }
        }
    }

    public void setTest(int dice0, int dice1, boolean pay) {
        this.test=true;
        this.diceResult[0]=dice0;
        this.diceResult[1]=dice1;
        this.payResult=pay;
    }
}
