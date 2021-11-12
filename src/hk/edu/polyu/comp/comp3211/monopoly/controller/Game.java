package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.*;
import hk.edu.polyu.comp.comp3211.monopoly.view.*;

public class Game implements IBase {
    private Board board;
    private Printer printer;

    /**
     * First print game board, current round and player; If he is in jail, refer to the document
     * Otherwise, prompt the user to roll the dice(user needs to input something); Then player
     * should move forward; then call the execute method of the arriving square. Note that user can
     * save, load or quit at this time. At the beginning of each round, check if the game ends. If
     * so, print the game result and switch to the Endgame Interface
     */
    @Override
    public void terminal() {}

    public Game() {
        int num;
        System.out.print("Please enter the number of players: ");
        var in = Main.GetScanner();
        while (true) {
            num = in.nextInt();
            try {
                board = new Board(num);
                break;
            } catch (IllegalArgumentException e) {
                // input 'num' is invalid
                continue;
            }
        }

        printer = new Printer(board);
    }
}
