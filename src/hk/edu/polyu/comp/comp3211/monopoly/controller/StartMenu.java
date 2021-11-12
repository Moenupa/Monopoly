package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;

import java.util.Scanner;

public class StartMenu implements IBase {
    /**
     * Error message when detecting invalid option numbers
     */
    private static final String ERR_INVALID_NUM_OF_Menu = "the option should only be 1-3 number";
    /**
     * Scanner for user input
     */
    private static Scanner in;

    /**
     * Welcome the user. If there exists a save file in the directory, prompt to the user of the
     * option to load the game. User can try to start a game; load a game if any; and exit in this
     * interface.
     */
    @Override
    public void terminal() {
        System.out.println("1. New Game");
        System.out.println("2. Continue");
        System.out.println("3. Quit");

        while (true) {
            System.out.print("Please enter the number: ");
            int option = in.nextInt();

            try {
                chooseOption(option);
                break;
            } catch (IllegalArgumentException e) {
                // input 'num' is invalid
                // continue;
            }
        }
    }

    public StartMenu() {
        in = Main.getScanner();
    }

    /**
     * choose one option to play
     *
     * @param num option number
     */
    private static void chooseOption(int num) {
        switch (num) {
            case 1:
                startNewGame();
                break;
            case 2:
                loadPreviousGame();
                break;
            case 3:
                quitGame();
                break;
            default:
                throw new IllegalArgumentException(ERR_INVALID_NUM_OF_Menu);
        }
    }

    /**
     * new game option
     */
    private static void startNewGame() {
        Main.setUI(new Game());
    }

    /**
     * load last saved game
     */
    private static void loadPreviousGame() {
    }

    /**
     * quit game
     */
    private static void quitGame() {
        System.exit(0);
    }
}
