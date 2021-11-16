package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;

public class EndGame implements IBase {
    private static Board board;

    /**
     * Check who is the winner if needed Print the game over message, and ask if the player want to
     * restart, load or quit.
     */
    @Override
    public void terminal() {
        System.out.println("Game over!");
        var winner = board.getWinner();
        if (winner != null) {
            System.out.println("Winner is " + winner.getName());
        } else {
            System.out.println("No winner");
        }
        System.out.println("type anything to go back to Start menu. ");
        var in = Main.getScanner();
        in.next();
        Main.setUI(new StartMenu());
    }

    /**
     * Initialize the end game state
     *
     * @param _board the game board
     */
    public EndGame(Board _board) {
        board = _board;
    }
}
