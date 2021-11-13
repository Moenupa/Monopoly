package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.*;
import hk.edu.polyu.comp.comp3211.monopoly.view.*;

public class Game implements IBase {
    private static Board board;
    private static Printer printer;

    /**
     * First print game board, current round and player; If he is in jail, refer to the document
     * Otherwise, prompt the user to roll the dice(user needs to input something); Then player
     * should move forward; then call the execute method of the arriving square. Note that user can
     * save, load or quit at this time. At the beginning of each round, check if the game ends. If
     * so, print the game result and switch to the Endgame Interface
     */
    @Override
    public void terminal() {
        update();
        printGame();

        if (isGameEnd()) endGame();
    }

    /** Initialize game controller */
    public Game() {
        int num;
        var in = Main.getScanner();

        while (true) {
            System.out.print("Please enter the number of players: ");
            num = in.nextInt();

            try {
                board = new Board(num);
                break;
            } catch (IllegalArgumentException e) {
                // input 'num' is invalid
                System.out.println("This game only support 2-6 players");
            }
        }
        printer = new Printer(board);
    }

    /**
     * Initialize game controller by loading the saved game
     *
     * @param boardName saved board (game) name
     * @throws Exception
     */
    public Game(String boardName) throws Exception {
        board = Board.load(boardName);
        printer = new Printer(board);
    }

    /** Update the game by each player's turn */
    private static void update() {}

    /** Print the game */
    private static void printGame() {
        printer.printAll();
    }

    /**
     * Check if the game should be ended
     *
     * @return true if game should end; false if game should not end
     */
    private static boolean isGameEnd() {

        return false;
    }

    /** End the game */
    private static void endGame() {
        Main.setUI(new EndGame(board));
    }
}
