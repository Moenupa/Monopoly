package hk.edu.polyu.comp.comp3211.monopoly.view;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.*;

public class Printer {
    /**
     * current game board
     */
    private final Board board;

    public static final String ANSI_RESET = "\u001b[0m";
    public static final String ANSI_BLACK = "\u001b[30m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_MAGENTA = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE = "\u001b[37m";

    private static final String QUIT_REGEX = "^[qQ](uit)?$";

    /**
     * Initialize the printer
     *
     * @param _board the game board to be printed
     */
    public Printer(Board _board) {
        board = _board;
    }

    /**
     * Print out current game board
     */
    public void printAll() {
        printBoard();
        printInfo();
    }

    /**
     * Only print the game board
     */
    private void printBoard() {
        board.getSquares();
    }

    /**
     * Only print the game info
     */
    private void printInfo() {
        board.getPlayers();
        Printer.printInfoMsg("\n\tCurrent Wealth:\n");
        for (var player : board.getPlayers()) {
            Printer.printInfoMsg("\t" + player.getName() + ": $" + player.getMoney() + ".\n");
        }
    }

    /**
     * Print a given message in a given color without any line-breaks
     *
     * @param ansi_color color in format <code>[0m</code>
     * @param msg        message to display
     */
    public static void printColoredMsg(String ansi_color, String msg) {
        System.out.print(ansi_color + msg + ANSI_RESET);
        System.out.flush();
    }

    /**
     * Print message for help (<code>ANSI_YELLOW</code> by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printHelpMsg(String msg) {
        printColoredMsg(ANSI_YELLOW, msg);
    }

    /**
     * Print message for warning (<code>ANSI_RED</code> by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printWarnMsg(String msg) {
        printColoredMsg(ANSI_RED, msg);
    }

    /**
     * Print message for information (<code>ANSI_BLUE</code> by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printInfoMsg(String msg) {
        printColoredMsg(ANSI_BLUE, msg);
    }

    /**
     * Print message for confirmation (<code>ANSI_GREEN</code> by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printConfMsg(String msg) {
        printColoredMsg(ANSI_GREEN, msg);
    }

    /**
     * Print message for general purposes (<code>ANSI_RESET</code> by default)
     *
     * @param msg message to display
     */
    public static void printMsg(String msg) {
        printColoredMsg(ANSI_RESET, msg);
    }

    /**
     * Print a yellow player prompt in form of "Player [player_name]: "
     *
     * @param player the player
     */
    public static void printPlayerPrompt(Player player) {
        printInfoMsg("Player " + player.getName() + ": ");
    }

    /**
     * Scan an input until it is valid or interrupted by user
     *
     * @param prompt       prompt method (lambda)
     * @param fallback_msg msg display to after invalid input
     * @param regex        regex of valid prompt
     * @return scanned valid input
     * @throws InterruptedException if user interrupt the input
     */
    public static String scanValidInputWithQuit(Runnable prompt, String fallback_msg, String regex)
            throws InterruptedException {
        String input;
        while (true) {
            prompt.run();
            input = Main.getScanner().nextLine();

            // user type quit, interrupt the process
            if (input.matches(QUIT_REGEX)) throw new InterruptedException("Interrupted by user.\n");
            if (input.matches(regex)) return input;

            Printer.printWarnMsg("INVALID INPUT! " + fallback_msg + "\n");
        }
    }

    /**
     * Scan an input until it is valid
     *
     * @param prompt       prompt method (lambda)
     * @param fallback_msg msg display to after invalid input
     * @param regex        regex of valid prompt
     * @return scanned valid input
     */
    public static String scanValidInput(Runnable prompt, String fallback_msg, String regex) {
        String input;
        while (true) {
            prompt.run();
            input = Main.getScanner().nextLine();

            if (input.matches(regex)) {
                return input;
            }

            Printer.printWarnMsg("INVALID INPUT! " + fallback_msg + "\n");
        }
    }
}
