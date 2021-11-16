package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

import java.util.Scanner;

public class StartMenu implements IBase {
    /** Error message when detecting invalid option numbers */
    private static final String ERR_INVALID_NUM_OF_Menu = "the option should only be 1-3 number";
    /** Scanner for user input */
    private static Scanner in;

    /**
     * Welcome the user. If there exists a save file in the directory, prompt to the user of the
     * option to load the game. User can try to start a game; load a game if any; and exit in this
     * interface.
     */
    @Override
    public void terminal() {
        Printer.printHelpMsg("\nStart Menu\n1. New Game\n2. Continue\n3. Quit\n");
        String option;
        // intended infinite loop
        while (true) {
            // ensure inputs to be valid
            do {
                Printer.printHelpMsg("Please enter option index: ");
                option = in.nextLine();
            } while (!option.matches("^[1-3]$"));

            // then get choose an option
            try {
                // if no err return directly otherwise loop back and ask for user option
                switch (Integer.parseInt(option)) {
                    case 1:
                        startNewGame();
                        return;
                    case 2:
                        loadPreviousGame();
                        return;
                    case 3:
                        quitGame();
                        return;
                }
            } catch (Exception e) {
                // any error will cause it loop back to ask for option
                // input 'num' is invalid
                Printer.printWarnMsg(e.getMessage());
            }
        }
    }

    /** Initialize start menu */
    public StartMenu() {
        in = Main.getScanner();
    }

    /** New game option */
    private static void startNewGame() {
        Main.setUI(new Game());
    }

    /** Load one of saved game */
    private static void loadPreviousGame() throws Exception {
        String name = chooseStoredGame();

        if (name != null) {
            Main.setUI(new Game(name));
        } else {
            throw new IllegalArgumentException("No game has been stored");
        }
    }

    /**
     * Choose which saved game to be loaded
     *
     * @return name of the chosen game
     */
    private static String chooseStoredGame() {
        String[] savedGameName = Board.getSavedGameName();

        if (savedGameName.length < 1) {
            System.out.println("No game has been stored");
            return null;
        } else if (savedGameName.length == 1) return savedGameName[0];

        System.out.println("\nSelect the game save file:");
        for (int i = 0; i < savedGameName.length; i++) {
            System.out.println((i + 1) + ". " + savedGameName[i]);
        }

        while (true) {
            System.out.print("Choose the index of game to be load: ");
            try {
                int index = in.nextInt() - 1;
                return savedGameName[index];
            } catch (Exception e) {
                System.out.println(
                        "The index should be an integer ranged 1-" + savedGameName.length);
                // prevent infinite loop caused by the last println and in.nextInt()
                in.nextLine();
            }
        }
    }

    /** Quit game */
    private static void quitGame() {
        System.out.println("\nQuit Game");
        System.exit(0);
    }
}
