package hk.edu.polyu.comp.comp3211.monopoly.view;

import hk.edu.polyu.comp.comp3211.monopoly.model.Board;

public class Printer {
    /**
     * current game board
     */
    private static Board board;

    /**
     * Initialize the printer
     *
     * @param board the game board to be printed
     */
    public Printer(Board board) {
        this.board = board;
    }

    /**
     * Print out current game board
     */
    public static void printAll() {
        printBoard();
        printInfo();
    }

    /**
     * Only print the game board
     */
    private static void printBoard() {
    }

    /**
     * Only print the game info
     */
    private static void printInfo() {
    }
}
