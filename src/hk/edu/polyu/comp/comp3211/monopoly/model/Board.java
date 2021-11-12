package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.*;

import java.io.*;

/**
 * A board, containing players, squares, and game status
 */
public class Board implements Serializable {
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
     * Default game-save directory
     */
    private static final String GAME_SAVE_DIR = "./out/saves/";
    /**
     * Error message when detecting invalid player numbers
     */
    private static final String ERR_INVALID_NUM_OF_PLAYERS =
            "the board should contain only 2-6 players";

    // constants, storing all property values
    /**
     * the position of the square (numbered from 1-20)
     */
    private static final int[] PROPERTY_INDEX = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    /**
     * the name of the properties
     */
    private static final String[] PROPERTY_NAMES = {
            "central", "wan chai", "stanley", "shek o", "mong kok", "tsing yi",
            "shatin", "tuen mun", "tai po", "sai kung", "yuen long", "tai o"
    };
    /**
     * the selling price of the properties
     */
    private static final int[] PROPERTY_SELL = {
            800, 700, 600, 400, 500, 400, 700, 400, 500, 400, 400, 600
    };
    /**
     * the rental price of the properties
     */
    private static final int[] PROPERTY_RENT = {90, 65, 60, 10, 40, 15, 75, 20, 25, 10, 25, 25};

    /**
     * Initialize the board with fixed number of squares and customized number of players (2-6)
     *
     * @param num number of players in the board
     * @throws IllegalArgumentException if num not in [2,6] range
     */
    public Board(int num) throws IllegalArgumentException {
        if (num < 2 || num > 6) throw new IllegalArgumentException(ERR_INVALID_NUM_OF_PLAYERS);

        // first initialize the players
        this.players = new Player[num];
        for (int i = 0; i < num; i++) {
            if (!Main.TEST) System.out.printf("Player %d :", i);
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
     * @throws IllegalArgumentException if num not in [2,6] range
     */
    protected Board(String[] names) throws IllegalArgumentException {
        // first initialize the players
        int num = names.length;
        if (num < 2 || num > 6) throw new IllegalArgumentException(ERR_INVALID_NUM_OF_PLAYERS);

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
     * @throws IllegalArgumentException if write permission not granted
     * @throws RuntimeException         if other unknown exceptions occur when writing to file
     */
    public void save(String name) throws Exception {
        // creating game-save directory if not exists
        File dir = new File(GAME_SAVE_DIR);
        if (!dir.exists()) dir.mkdirs();

        // create game save file
        File file = new File(GAME_SAVE_DIR + name);
        if (file.exists() && !file.canWrite())
            throw new IllegalArgumentException("Write permission not granted");

        try {
            FileOutputStream fo;
            if (file.createNewFile()) {
                // create success: prepare to write
                fo = new FileOutputStream(file);
            } else {
                // else if file already exist: clear all its content
                fo = new FileOutputStream(file);
                fo.write("".getBytes());
            }
            // write object to file
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(this);

            // close output streams
            oo.close();
            fo.close();
        } catch (IOException e) {
            // 1. IOException when executing file.createNewFile()
            // 2. Exception when executing new FileOutputStream()
            // both cases should NOT happen
            e.printStackTrace();
            throw new RuntimeException("Internal error when saving a game save");
        }
    }

    /**
     * Load the board from a local file
     *
     * @param name the path (name) of the local file
     * @throws IllegalArgumentException if no such game-save or read permission not granted
     * @throws RuntimeException         if other unknown exceptions occur when reading file
     */
    public static Board load(String name) throws Exception {
        File file = new File(GAME_SAVE_DIR + name);
        if (!file.exists()) throw new IllegalArgumentException("No save match");
        if (!file.canRead()) throw new IllegalArgumentException("Read permission not granted");

        try {
            // read from input
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);
            Board board = (Board) oi.readObject();

            // close input stream
            oi.close();
            fi.close();
            return board;
        } catch (Exception e) {
            // 1. FileNotFoundException;
            // 2. Exception when executing new ObjectInputStream();
            // both cases should NOT happen
            e.printStackTrace();
            throw new RuntimeException("Internal error when loading a game save");
        }
    }

    /**
     * get the name list of the saved game
     *
     * @return list of saved name
     */
    public static String[] getSavedGameName() {
        File dir = new File(GAME_SAVE_DIR);
        if (!dir.exists())
            if (!dir.mkdirs())
                throw new RuntimeException("Internal error when creating a save directory");

        if (dir.isDirectory()) return dir.list();

        throw new IllegalArgumentException(GAME_SAVE_DIR + " is not a directory");
    }

    /**
     * Initialize the board's squares according to definitions
     */
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

    /**
     * Reset board's <code>round</code> and <code>p_index</code> to 0
     */
    private void reset_rounding_info() {
        this.round = 0;
        this.p_index = 0;
    }
}
