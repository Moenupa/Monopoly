package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;
import hk.edu.polyu.comp.comp3211.monopoly.model.Player;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.ISquare;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.Property;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

public class Game implements IBase {
    private static Board board;
    private static Player[] players;
    private static ISquare[] squares;
    private static int numPlayerLeft;

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

    /**
     * Initialize game controller
     */
    public Game() {
        int num;
        var in = Main.getScanner();

        while (true) {
            System.out.print("Please enter the number of players: ");
            num = in.nextInt();
            in.nextLine();

            try {
                board = new Board(num);
                players = board.getPlayers();
                squares = board.getSquares();
                numPlayerLeft = players.length;
                break;
            } catch (IllegalArgumentException e) {
                // input 'num' is invalid
                System.out.println("This game only support 2-6 players");
                // e.printStackTrace();
            }
        }
    }

    /**
     * Initialize game controller by loading the saved game
     *
     * @param boardName saved board (game) name
     * @throws Exception for exception
     */
    public Game(String boardName) throws Exception {
        board = Board.load(boardName);
        players = board.getPlayers();
        squares = board.getSquares();
        numPlayerLeft = players.length;
    }

    /**
     * Update the game by each player's turn
     */
    private static void update() {
        int p_index = board.getP_index();
        Player curPlayer = players[p_index];

        while (curPlayer.isBankrupted()) {
            numPlayerLeft -= 1;
            p_index = updateP_index(p_index);
            curPlayer = players[p_index];
        }

        movement(curPlayer);

        if (checkBankrupt(curPlayer)) {
            retire(curPlayer);
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
    private static int updateP_index(int p_index) {
        p_index += 1;
        p_index /= players.length;
        board.setP_index(p_index);

        if (p_index == 0) board.setRound(board.getRound() + 1);
        return p_index;
    }

    /**
     * Player's movement by rolling dices
     *
     * @param player player to move
     */
    private static void movement(Player player) {
        if (!player.isInJail()) {
            int[] diceResult = player.rollDice();
            player.move(diceResult[0] + diceResult[1]);
        }

        takeEffect(player);
    }

    /**
     * Take the effect of square to player
     *
     * @param curPlayer player to take effect
     */
    public static void takeEffect(Player curPlayer) {
        squares[curPlayer.getPosition()].execute(curPlayer);
    }

    /**
     * Check if the player has a negative amount of money
     *
     * @param player player to be checked
     * @return ture if player is bankrupt; false if not
     */
    private static boolean checkBankrupt(Player player) {
        player.bankrupt();
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

    /**
     * Print the game (call Printer)
     */
    private static void printGame() {
        Printer.printAll();
    }

    /**
     * Check if the game should be ended
     *
     * @return true if game should end; false if game should not end
     */
    private static boolean isGameEnd() {
        return numPlayerLeft <= 1 || board.getRound() >= 100;
    }

    /**
     * End the game
     */
    private static void endGame() {
        Main.setUI(new EndGame(board));
    }
}
