package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.ISquare;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.Property;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

public class Game implements IBase {
    private Board board;
    private Player[] players;
    private ISquare[] squares;
    private int numPlayerLeft;
    private final Printer printer;

    /**
     * First print game board, current round and player; If he is in jail, refer to the document
     * Otherwise, prompt the user to roll the dice(user needs to input something); Then player
     * should move forward; then call the execute method of the arriving square. Note that user can
     * save, load or quit at this time. At the beginning of each round, check if the game ends. If
     * so, print the game result and switch to the Endgame Interface
     */
    @Override
    public void terminal() {
        printGame();
        update();

        if (isGameEnd()) endGame();
    }

    /** Initialize game controller */
    public Game() throws InterruptedException {
        String option;

        while (true) {
            // ensure inputs to be valid
            option =
                    Printer.scanValidInputWithQuit(
                            () -> Printer.printHelpMsg("Input the number of players or q(uit): "),
                            "Should be an integer or q(uit).",
                            "^\\d+$");

            // then get choose an option
            try {
                board = new Board(Integer.parseInt(option));
                players = board.getPlayers();
                squares = board.getSquares();
                numPlayerLeft = players.length;
                break;
            } catch (Exception e) {
                Printer.printWarnMsg(e.getMessage());
            }
        }
        printer = new Printer(board);
    }

    /**
     * Initialize game controller by loading the saved game
     *
     * @param boardName saved board (game) name
     */
    public Game(String boardName) {
        board = Board.load(boardName);
        players = board.getPlayers();
        squares = board.getSquares();
        numPlayerLeft = players.length;
        printer = new Printer(board);
    }

    /** Update the game by each player's turn */
    private void update() {
        int p_index = board.getP_index();
        Player curPlayer = players[p_index];

        while (curPlayer.isBankrupted()) {
            p_index = updateP_index(p_index);
            curPlayer = players[p_index];
        }
        Printer.printMsg(
                "\nRound "
                        + (board.getRound() + 1)
                        + ", Player No."
                        + (p_index + 1)
                        + ": "
                        + curPlayer.getName()
                        + "'s turn.\n");

        ISquare curSquare = squares[curPlayer.getPosition()];
        String curSquareStr =
                (curSquare.getClass() == Property.class)
                        ? "Property " + ((Property) curSquare).getName()
                        : curSquare.getClass().getSimpleName();
        Printer.printPlayerPrompt(curPlayer);
        Printer.printMsg(
                "is now in square " + (curPlayer.getPosition() + 1) + ": " + curSquareStr + ".\n");
        while (true) {
            String option;
            try {
                option =
                        Printer.scanValidInputWithQuit(
                                () ->
                                        Printer.printHelpMsg(
                                                "type \"s(ave)\" for saving the game, \"q(uit)\" to"
                                                    + " exit to main menu, \"r(un)\" to continue"
                                                    + " the game.\n"),
                                "Should be any of \"s(ave)\", \"q(uit)\" or \"r(un)\".",
                                "^([sS](ave)?|[qQ](uit)?|[rR](un)?)$");
            } catch (InterruptedException e) {
                // interrupt by user option
                Printer.printMsg("Quiting the current game...\n");
                Main.setUI(new StartMenu());
                return;
            }

            switch (option.charAt(0)) {
                case 's':
                    String boardName =
                            Printer.scanValidInput(
                                    () -> Printer.printHelpMsg("Input name of the game save: "),
                                    "Should be a non-empty string.",
                                    Printer.NON_EMPTY_REGEX);
                    try {
                        board.save(boardName);
                    } catch (IllegalArgumentException e) {
                        Printer.printWarnMsg(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    continue;
                case 'r':
                    break;
            }
            break;
        }

        movement(curPlayer);

        if (checkBankrupt(curPlayer)) {
            retire(curPlayer);
            Printer.printPlayerPrompt(curPlayer);
            Printer.printMsg("loses all properties.\n");
            numPlayerLeft -= 1;
        }

        updateP_index(p_index);
    }

    /**
     * Update active player index to next player
     *
     * @param p_index active player index
     * @return updated index
     */
    private int updateP_index(int p_index) {
        p_index += 1;
        p_index %= players.length;
        board.setP_index(p_index);

        if (p_index == 0) board.setRound(board.getRound() + 1);
        return p_index;
    }

    /**
     * Player's movement by rolling dices
     *
     * @param player player to move
     */
    private void movement(Player player) {
        if (player.getInJail() == 0) {
            int die1, die2, die_sum;
            die1 = player.rollDice();
            die2 = player.rollDice();

            die_sum = die1 + die2;
            player.move(die_sum);
            Printer.printPlayerPrompt(player);
            Printer.printMsg("roll the dice and advances by " + die_sum + ".\n");
        }

        takeEffect(player);
    }

    /**
     * Take the effect of square to player
     *
     * @param curPlayer player to take effect
     */
    public void takeEffect(Player curPlayer) {
        squares[curPlayer.getPosition()].execute(curPlayer);
    }

    /**
     * Check if the player has a negative amount of money
     *
     * @param player player to be checked
     * @return ture if player is bankrupt; false if not
     */
    private static boolean checkBankrupt(Player player) {
        return player.isBankrupted();
    }

    /**
     * Retire the player, set all her properties to unowned
     *
     * @param player player to retire
     */
    private static void retire(Player player) {
        for (Property property : player.getProperties()) {
            property.setOwner(null);
        }
    }

    /** Print the game (call Printer) */
    private void printGame() {
        printer.printAll();
    }

    /**
     * Check if the game should be ended
     *
     * @return true if game should end; false if game should not end
     */
    private boolean isGameEnd() {
        return numPlayerLeft <= 1 || board.getRound() >= 100;
    }

    /** End the game */
    private void endGame() {

        Main.setUI(new EndGame(board));
    }
}
