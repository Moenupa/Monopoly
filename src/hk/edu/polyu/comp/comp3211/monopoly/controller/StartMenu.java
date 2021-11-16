package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;

import java.util.Scanner;

public class StartMenu implements IBase {
    /**
     * Error message when detecting invalid option numbers
     */
<<<<<<< Updated upstream
    private static final String ERR_INVALID_NUM_OF_Menu = "the option should only be 1-3 number";
=======
    private static final String ERR_INVALID_NUM_OF_Menu = "The option should only be 1-3 number";
>>>>>>> Stashed changes
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
        System.out.println("\nStart Menu");
        System.out.println("1. New Game");
        System.out.println("2. Continue");
        System.out.println("3. Quit");

        while (true) {
            System.out.print("Please enter the number: ");
            String input = in.nextLine();

            try {
                int option = Integer.parseInt(input);
                chooseOption(option);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("The option number should be 1-3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initialize start menu
     */
    public StartMenu() {
        in = Main.getScanner();
    }

    /**
     * Choose one option to play
     *
     * @param num option number
     */
    private static void chooseOption(int num) throws Exception {
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
     * New game option
     */
    private static void startNewGame() {
        Main.setUI(new Game());
    }

    /**
     * Load one of saved game
     */
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
                in.nextLine();

                return savedGameName[index];
            } catch (Exception e) {
                System.out.println(
                        "The index should be an integer ranged 1-" + savedGameName.length);
                // prevent infinite loop caused by the last println and in.nextInt()
            }
        }
    }

    /**
     * Quit game
     */
    private static void quitGame() {
        System.out.println("\nQuit Game");
        System.exit(0);
    }
}
