package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.model.squares.*;

/** A board, containing players, squares, and game status */
public class Board {
    /** The array of players in the board */
    private Player[] players;
    /** The array of squares in the board */
    private ISquare[] squares;
    /** Current round index */
    private int round;
    /** Current active player index */
    private int p_index;

    // constants, storing all property values
    /** the position of the square (numbered from 1-20) */
    private static final int[] PROPERTY_INDEX = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    /** the name of the properties */
    private static final String[] PROPERTY_NAMES = {
        "central", "wan chai", "stanley", "shek o", "mong kok", "tsing yi",
        "shatin", "tuen mun", "tai po", "sai kung", "yuen long", "tai o"
    };
    /** the selling price of the properties */
    private static final int[] PROPERTY_SELL = {
        800, 700, 600, 400, 500, 400, 700, 400, 500, 400, 400, 600
    };
    /** the rental price of the properties */
    private static final int[] PROPERTY_RENT = {90, 65, 60, 10, 40, 15, 75, 20, 25, 10, 25, 25};

    /**
     * Initialize the board with fixed number of squares and customized number of players (2-6)
     *
     * @param num number of players in the board
     */
    public Board(int num) {
        // first initialize the players
        this.players = new Player[num];
        for (int i = 0; i < num; i++) {
            this.players[i] = new Player();
        }
        // then initialize the squares on the board
        this.init_squares();
        // set round and p_index to 0
        this.reset_rounding_info();
        // initialization finished!
    }

    /**
     * Initialize the board with fixed number of squares and customized number of players (2-6)
     *
     * @param names array of player names
     */
    protected Board(String[] names) {
        // first initialize the players
        int num = names.length;
        this.players = new Player[num];
        for (int i = 0; i < num; i++) {
            this.players[i] = new Player(names[i]);
        }
        // then initialize the squares on the board
        this.init_squares();
        // set round and p_index to 0
        this.reset_rounding_info();
        // initialization finished!
    }

    /**
     * Get all players in the board
     *
     * @return array of players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Get all squares in the board
     *
     * @return array of squares
     */
    public ISquare[] getSquares() {
        return squares;
    }

    /**
     * Get the index of current round
     *
     * @return round index
     */
    public int getRound() {
        return round;
    }

    /**
     * Set round index to a custom number
     *
     * @param round dest round index
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Get the index of current active player
     *
     * @return round index
     */
    public int getP_index() {
        return p_index;
    }

    /**
     * Set active player index to a custom number
     *
     * @param p_index dest player index
     */
    public void setP_index(int p_index) {
        this.p_index = p_index;
    }

    /**
     * Save the board to a local file
     *
     * @param name the path (name) of the local file
     */
    public void save(String name) {}

    /**
     * Load the board from a local file
     *
     * @param name the path (name) of the local file
     */
    public void load(String name) {}

    /** Initialize the board's squares according to definitions */
    private void init_squares() {
        if (squares != null) return;

        this.squares = new ISquare[20];

        // initialize go square
        squares[0] = new Go();

        // initialize every property
        for (int i = 0; i < 12; i++) {
            squares[PROPERTY_INDEX[i] - 1] =
                    new Property(PROPERTY_NAMES[i], PROPERTY_SELL[i], PROPERTY_RENT[i]);
        }

        // the 4th square is tax
        squares[3] = new Tax();
        // the 6th square is in-jail/visiting
        squares[5] = new Jail();
        // the 11th square is free-parking
        squares[10] = new Free();
        // the 16th square is go-to-jail
        squares[15] = new Oops();
        // 9th, 13th, 19th square are chances
        squares[8] = new Chance();
        squares[12] = new Chance();
        squares[18] = new Chance();

        // the board is initialized with 1+12+(1+1+1+1)+3=20 squares
    }

    /** Reset board's <code>round</code> and <code>p_index</code> to 0 */
    private void reset_rounding_info() {
        this.round = 0;
        this.p_index = 0;
    }
}
