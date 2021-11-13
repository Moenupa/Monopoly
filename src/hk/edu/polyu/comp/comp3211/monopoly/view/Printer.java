package hk.edu.polyu.comp.comp3211.monopoly.view;

import hk.edu.polyu.comp.comp3211.monopoly.model.Board;

public class Printer {
    /** current game board */
    private final Board board;

    /**
     * Initialize the printer
     *
     * @param _board the game board to be printed
     */
    public Printer(Board _board) {
        board = _board;
    }

    /** Print out current game board */
    public void printAll() {
        printBoard();
        printInfo();
    }

    /** Only print the game board */
    private void printBoard() {
        board.getSquares();
    }

    /** Only print the game info */
    private void printInfo() {
        board.getPlayers();
        for (var player : board.getPlayers()) {
            System.out.println(player.getName() + ": current  wealth $" + player.getMoney());
        }
    }
}
