package hk.edu.polyu.comp.comp3211.monopoly.model.squares;

import hk.edu.polyu.comp.comp3211.monopoly.model.Player;

/**
 * The Income-Tax square of the board
 */
public class Tax implements ISquare {
    /**
     * Generate an effect to a player
     *
     * <p>Income-Tax Effect:
     *
     * <ul>
     *   <li>the player pays 10% of the balance (in form 10*n) as tax.
     * </ul>
     *
     * @param player dest. player
     */
    @Override
    public void execute(Player player) {
        int tax = player.getMoney() / 100 * 10;
        player.addMoney(-tax);
        System.out.println("You have paid HKD " + tax + " as tax");
    }
}
