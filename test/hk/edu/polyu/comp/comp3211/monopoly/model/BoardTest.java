package hk.edu.polyu.comp.comp3211.monopoly.model;

import static org.junit.jupiter.api.Assertions.*;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.*;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.*;

class BoardTest {
    Board board1, board2, board3;
    // --Commented out by Inspection (2021/11/18 16:19):Board test;
    static final String[] PLAYER_NAMES = {
        "player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8"
    };
    static final int[] PROPERTY_INDEX = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    static final String[] PROPERTY_NAMES = {
        "central", "wan chai", "stanley", "shek o", "mong kok", "tsing yi",
        "shatin", "tuen mun", "tai po", "sai kung", "yuen long", "tai o"
    };
    static final int[] PROPERTY_SELL = {800, 700, 600, 400, 500, 400, 700, 400, 500, 400, 400, 600};
    static final int[] PROPERTY_RENT = {90, 65, 60, 10, 40, 15, 75, 20, 25, 10, 25, 25};
    static final int[] SECTION = {2, 6};
    String[] playerNameSet1, playerNameSet2;

    @BeforeEach
    void setUp() {
        // valid boards for further testing
        this.playerNameSet1 = Arrays.copyOfRange(PLAYER_NAMES, 0, SECTION[0]);
        this.playerNameSet2 = Arrays.copyOfRange(PLAYER_NAMES, SECTION[0], SECTION[0] + SECTION[1]);

        this.board1 = new Board(playerNameSet1);
        this.board2 = new Board(playerNameSet2);

        this.board2.setRound(3);
        this.board2.setP_index(4);
        Main.TEST = true;
    }

    @Test
    void getBoardStatusTest() {
        assertEquals(0, this.board1.getRound(), "Board1 round not equal to 0");
        assertEquals(0, this.board1.getP_index(), "Board1 p_index not equal to 0");
        assertEquals(3, this.board2.getRound(), "Board2 round not equal to 4");
        assertEquals(4, this.board2.getP_index(), "Board2 p_index not equal to 3");
    }

    @Test
    void OverallBoardTest() {
        // new Board() should throw error when not initialized with 2-6 players
        Exception exception1 = assertThrows(Exception.class, () -> new Board(7));
        board3 = new Board(6); // Test if the constructor works
        String[] ss = new String[1];
        ss[0] = "";
        assertThrows(IllegalArgumentException.class, () -> new Board(ss));

        // "2-6 players" is one of game constraints
        String expectedMessage = "2-6 players";
        String actualMessage = exception1.getMessage();

        // and should prompt a message containing relevant information
        assertTrue(
                actualMessage.contains(expectedMessage),
                "NOT THROW EXCEPTION containing '2-6 players'");
    }

    @Test
    void getPlayersTest() {
        List<Player> players2 = Arrays.asList(this.board2.getPlayers());
        // store the names of the players in an array
        // names = players.map(e => e.name)
        List<String> playerNames2 =
                players2.stream().map(Player::getName).collect(Collectors.toList());

        assertTrue(
                hasSamePlayers(playerNames2, Arrays.asList(playerNameSet2)), "PLAYERS NOT EQUAL");
    }

    @Test
    void getSquaresTest() {
        ISquare[] squares = board1.getSquares();

        Map<Class, List<Integer>> map = new HashMap<>();
        map.put(Go.class, List.of(1));
        map.put(Property.class, Arrays.asList(2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20));
        map.put(Tax.class, List.of(4));
        map.put(Jail.class, List.of(6));
        map.put(Chance.class, Arrays.asList(9, 13, 19));
        map.put(Free.class, List.of(11));
        map.put(Oops.class, List.of(16));

        map.forEach((k, v) -> v.forEach((e) -> assertEquals(k, squares[e - 1].getClass())));

        for (int i = 0; i < 12; i++) {
            Property cur = (Property) squares[PROPERTY_INDEX[i] - 1];
            assertEquals(cur.getName(), PROPERTY_NAMES[i].toUpperCase(), "PROPERTY NAME NOT EQUAL");
            assertEquals(cur.getPrice(), PROPERTY_SELL[i], "PROPERTY SELL PRICE NOT EQUAL");
            assertEquals(cur.getRent(), PROPERTY_RENT[i], "PROPERTY RENT PRICE NOT EQUAL");
        }
    }

    @Test
    void saveAndLoadTest() {
        // saving and loading
        final String save_name = "test_board_2";
        Board board2_load;
        try {
            board2.save(save_name);
            board2_load = Board.load(save_name);
        } catch (Exception e) {
            e.printStackTrace();
            fail("EXCEPTION OCCUR");
            return;
        }

        // compare the status before/after saving+loading
        // round index and active player index
        assertEquals(board2.getRound(), board2_load.getRound(), "ROUND INDEX NOT EQUAL");
        assertEquals(board2.getP_index(), board2_load.getP_index(), "P INDEX NOT EQUAL");
        assertTrue(Arrays.asList(Board.getSavedGameName()).contains(save_name), "NAME NOT EQUAL");

        assertThrows(
                Exception.class,
                () -> Board.load("")); // Test if exception is thrown when no file is given

        // has the same players with same names, money, etc.
        List<Player> players2_load = Arrays.asList(board2_load.getPlayers());
        List<String> playerNames2_load =
                players2_load.stream().map(Player::getName).collect(Collectors.toList());

        assertTrue(
                hasSamePlayers(playerNames2_load, Arrays.asList(playerNameSet2)),
                "PLAYERS NOT EQUAL");
    }

    @Test
    void getWinnerTest() {
        Player winner = board1.getPlayers()[0];
        winner.addMoney(10000);
        assertEquals(board1.getWinner(), winner, "WRONG WINNER");

        for (var player : board1.getPlayers()) {
            player.setMoney(-100);
        }
        assertNull(board1.getWinner(), "No WINNER");
    }

    private boolean hasSamePlayers(List<String> list1, List<String> list2) {
        return ((Collection<String>) list1).containsAll(list2)
                && ((Collection<String>) list2).containsAll(list1);
    }
}
