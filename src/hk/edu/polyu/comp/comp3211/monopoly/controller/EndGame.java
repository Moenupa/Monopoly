package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.model.Board;

public class EndGame implements IBase {
    private static Board board;

    /**
     * Check who is the winner if needed Print the game over message, and ask if the player want to
     * restart, load or quit.
     */
    @Override
    public void terminal() {
        System.out.println("\nGame Over");
    }

    /**
     * Initialize the end game state
     *
     * @param _board the game board
     */
    public EndGame(Board _board) {
        board = _board;
    }

    private void findWinner() {}
}
