package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.model.squares.ISquare;

/**
 * A board, containing players, squares, and game status
 */
public class Board {
    /**
     * The array of players in the board
     */
    private Player[] players;
    /**
     * The array of squares in the board
     */
    private ISquare[] squares;
    /**
     * Current round index
     */
    private int round;
    /**
     * Current active player index
     */
    private int p_index;

    /**
     * Initialize the board with fixed number of squares
     * and customized number of players (2-6)
     * @param num number of players in the board
     */
    public Board(int num) {}

    /**
     * Initialize the board with fixed number of squares
     * and customized number of players (2-6)
     * @param names array of player names
     */
    protected Board(String[] names) {}

    /**
     * Get all players in the board
     * @return array of players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Get all squares in the board
     * @return array of squares
     */
    public ISquare[] getSquares() {
        return squares;
    }

    /**
     * Get the index of current round
     * @return round index
     */
    public int getRound() {
        return round;
    }

    /**
     * Set round index to a custom number
     * @param round dest round index
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Get the index of current active player
     * @return round index
     */
    public int getP_index() {
        return p_index;
    }

    /**
     * Set active player index to a custom number
     * @param p_index dest player index
     */
    public void setP_index(int p_index) {
        this.p_index = p_index;
    }

    /**
     * Save the board to a local file
     * @param name the path (name) of the local file
     */
    public void save(String name){

    }

    /**
     * Load the board from a local file
     * @param name the path (name) of the local file
     */
    public void load(String name){

    }
}
