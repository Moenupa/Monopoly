package hk.edu.polyu.comp.comp3211.monopoly.view;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.*;

public class Printer {
    /** current game board */
    private final Board board;

    public static final String ANSI_BLACK = "\u001b[30m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_MAGENTA = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE = "\u001b[37m";
    public static final String ANSI_RESET = "\u001b[0m";

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
        Printer.printInfoMsg("\tCurrent Wealth:\n");
        for (var player : board.getPlayers()) {
            Printer.printInfoMsg("\t" + player.getName() + ": $" + player.getMoney() + ".\n");
        }
    }

    /**
     * Print a given message in a given color without any line-breaks
     *
     * @param ansi_color color in format <code>[0m</code>
     * @param msg message to display
     */
    public static void printColoredMsg(String ansi_color, String msg) {
        System.out.print(ansi_color + msg + ANSI_RESET);
        System.out.flush();
    }

    /**
     * Print message for help (ANSI_YELLOW by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printHelpMsg(String msg) {
        printColoredMsg(ANSI_YELLOW, msg);
    }

    /**
     * Print message for warning (ANSI_RED by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printWarnMsg(String msg) {
        printColoredMsg(ANSI_RED, msg);
    }

    /**
     * Print message for information (ANSI_BLUE by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printInfoMsg(String msg) {
        printColoredMsg(ANSI_BLUE, msg);
    }

    /**
     * Print message for confirmation (ANSI_GREEN by default) without any line-breaks
     *
     * @param msg message to display
     */
    public static void printConfMsg(String msg) {
        printColoredMsg(ANSI_GREEN, msg);
    }

    /**
     * Print message for general purposes (ANSI_RESET by default)
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

    public static String scanInput() {
        var in = Main.getScanner();
        String input = in.nextLine();
        return input;
    }
}
