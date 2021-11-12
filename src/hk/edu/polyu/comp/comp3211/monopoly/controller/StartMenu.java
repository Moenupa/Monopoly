package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

public class StartMenu implements IBase {
    private static final String ERR_INVALID_NUM_OF_Menu = "the option should only be 1-3 number";

    /**
     * Welcome the user. If there exists a save file in the directory, prompt to the user of the
     * option to load the game. User can try to start a game; load a game if any; and exit in this
     * interface.
     */
    @Override
    public void terminal() {

    }

    public StartMenu() {
        var in = Main.GetScanner();
        int num;

        System.out.print("1. New Game");
        System.out.print("2. Continue");
        System.out.print("3. Quit");

        while (true) {
            System.out.print("Please enter the number: ");
            num = in.nextInt();

            try {
                chooseOption(num);
                break;
            } catch (IllegalArgumentException e) {
                // input 'num' is invalid
                // continue;
            }
        }
    }

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

    private static void startNewGame() {

    }

    private static void loadPreviousGame() {

    }

    private static void quitGame() {
        System.exit(0);
    }
}
