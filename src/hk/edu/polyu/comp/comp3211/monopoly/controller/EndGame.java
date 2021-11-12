package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.model.Board;

public class EndGame implements IBase {
    private Board board;

    /**
     * Check who is the winner if needed Print the game over message, and ask if the player want to
     * restart, load or quit.
     */
    @Override
    public void terminal() {
        System.out.println("\nGame Over");
    }

    public EndGame(Board _board) {
        board = _board;
    }
}
