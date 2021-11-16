package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

public class EndGame implements IBase {
    private static Board board;

    /**
     * Check who is the winner if needed Print the game over message, and ask if the player want to
     * restart, load or quit.
     */
    @Override
    public void terminal() {
        Printer.printWarnMsg("Game over!\n");
        var winner = board.getWinner();
        if (winner != null) {
            Printer.printWarnMsg("Winner is " + winner.getName() + "!\n");
        } else {
            Printer.printWarnMsg("No winner...\n");
        }
        Printer.printHelpMsg("type anything to go back to Start menu.\n");
        var in = Main.getScanner();
        in.nextLine();
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
