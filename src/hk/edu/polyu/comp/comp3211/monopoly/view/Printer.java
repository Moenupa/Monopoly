package hk.edu.polyu.comp.comp3211.monopoly.view;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.*;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.ISquare;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.Property;

import java.util.Arrays;

import java.util.*;
import java.util.stream.*;

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

    public static final String ANSI_BG_RED = "\u001b[41m";
    public static final String ANSI_BG_GREEN = "\u001b[42m";
    public static final String ANSI_BG_YELLOW = "\u001b[43m";
    public static final String ANSI_BG_BLUE = "\u001b[44m";
    public static final String ANSI_BG_MAGENTA = "\u001b[45m";
    public static final String ANSI_BG_CYAN = "\u001b[46m";

    public static final String ANSI_RESET = "\u001b[0m";
    public static final String[] colors = {
        ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_MAGENTA, ANSI_CYAN, ANSI_RESET
    };

    private static final String QUIT_REGEX = "^[qQ](uit)?$";
    public static final String NON_EMPTY_REGEX = "^(?!\\s*$).+";
    public static final String CONFIRM_REGEX = "^([yY](es)?|[nN](o)?)$";
    public static final String CONFIRM_YES_REGEX = "^([yY](es)?)$";

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

    /**
     * Given a string and a length, return a new string such that the original string is appended
     * with blank spaces while the total length is equal to the given length, and the original
     * string should be centered.
     *
     * @param str the original string
     * @param length the total length of the new string
     * @return the new string
     */
    public String middle(String str, int length) {
        int len = str.length();
        int diff = length - len;
        int right = diff / 2;
        int left = diff - right;
        return " ".repeat(Math.max(0, left)) + str + " ".repeat(Math.max(0, right));
    }

    /** Only print the game board */
    private void printBoard() {
        String[] squareColor = new String[20];
        String[][] squarePlayer = new String[20][6];
        int[] squarePlayerNum = new int[20];
        ISquare[] squares = board.getSquares();
        Player[] players = board.getPlayers();
        String[] inJailPlayers = new String[6];
        String[] inJailColors = new String[6];
        int inJailNum = 0;
        String[] justVisitingPlayer = new String[6];
        int justVisitingNum = 0;

        for (int i = 0; i < squares.length; i++) {

            squareColor[i] = colors[colors.length - 1];

            if (squares[i] instanceof Property) {
                Property curProp = (Property) squares[i];
                Player curOwner = curProp.getOwner();
                if (curOwner != null) {
                    int pi = Arrays.asList(players).indexOf(curOwner);
                    squareColor[i] = colors[pi];
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) squarePlayer[i][j] = ANSI_BLACK + ANSI_RESET;
        }

        for (int i = 0; i < 6; i++) {
            inJailPlayers[i] = "";
            justVisitingPlayer[i] = ANSI_BLACK + ANSI_RESET;
            inJailColors[i] = ANSI_BLACK;
        }

        for (int i = 0; i < players.length; i++) {
            int curPosition = players[i].getPosition();
            squarePlayer[curPosition][squarePlayerNum[curPosition]] =
                    colors[i] + players[i].getName() + ANSI_RESET;
            squarePlayerNum[curPosition]++;

            if (curPosition == 5) {
                if (players[i].getInJail() == 0) {
                    justVisitingPlayer[justVisitingNum] =
                            colors[i] + players[i].getName() + ANSI_RESET;
                    justVisitingNum++;
                } else {
                    inJailPlayers[inJailNum] = players[i].getName();
                    inJailColors[inJailNum] = colors[i];
                    inJailNum++;
                }
            }
        }

        // line1
        System.out.print(
                "|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|\n");
        System.out.print(
                "|    FREE PARKING    |       Shatin       |       CHANCE       |      Tuen Mun    "
                    + "  |       Tai Po       |     GO TO GAIL     |\n");
        System.out.print(
                "|                    |                    |                    |                  "
                    + "  |                    |                    |\n");
        System.out.print(
                "|                    |                    |                    |                  "
                    + "  |                    |                    |\n");
        System.out.print(
                "|                    |      HKD 700       |                    |      HKD 400     "
                    + "  |      HKD 500       |                    |\n");
        System.out.print(
                "|                    |                    |                    |                  "
                    + "  |                    |                    |\n");
        System.out.print(
                "|    player here:    |    player here:    |    player here:    |    player here:  "
                    + "  |    player here:    |    player here:    |\n");
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                middle(squarePlayer[10][0],29),
                middle(squarePlayer[11][0],29),
                middle(squarePlayer[12][0],29),
                middle(squarePlayer[13][0],29),
                middle(squarePlayer[14][0],29),
                middle(squarePlayer[15][0],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                middle(squarePlayer[10][1],29),
                middle(squarePlayer[11][1],29),
                middle(squarePlayer[12][1],29),
                middle(squarePlayer[13][1],29),
                middle(squarePlayer[14][1],29),
                middle(squarePlayer[15][1],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                middle(squarePlayer[10][2],29),
                middle(squarePlayer[11][2],29),
                middle(squarePlayer[12][2],29),
                middle(squarePlayer[13][2],29),
                middle(squarePlayer[14][2],29),
                middle(squarePlayer[15][2],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                middle(squarePlayer[10][3],29),
                middle(squarePlayer[11][3],29),
                middle(squarePlayer[12][3],29),
                middle(squarePlayer[13][3],29),
                middle(squarePlayer[14][3],29),
                middle(squarePlayer[15][3],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                middle(squarePlayer[10][4],29),
                middle(squarePlayer[11][4],29),
                middle(squarePlayer[12][4],29),
                middle(squarePlayer[13][4],29),
                middle(squarePlayer[14][4],29),
                middle(squarePlayer[15][4],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                middle(squarePlayer[10][5],29),
                middle(squarePlayer[11][5],29),
                middle(squarePlayer[12][5],29),
                middle(squarePlayer[13][5],29),
                middle(squarePlayer[14][5],29),
                middle(squarePlayer[15][5],29));
        System.out.printf(
                "|%s--------------------|%s--------------------|%s--------------------|%s--------------------|%s--------------------|%s--------------------%s|\n",
                squareColor[10],
                squareColor[11],
                squareColor[12],
                squareColor[13],
                squareColor[14],
                squareColor[15],
                ANSI_RESET);

        System.out.print(
                "|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|\n");

        // line2
        System.out.printf(
                "|      Tsing Yi     %s|%s|                                                        "
                    + "                           |%s|%s     Sai Kung      |\n",
                squareColor[9], ANSI_RESET, squareColor[16], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[9], ANSI_RESET, squareColor[16], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[9], ANSI_RESET, squareColor[16], ANSI_RESET);
        System.out.printf(
                "|      HKD 400      %s|%s|                                                        "
                    + "                           |%s|%s     HKD 400       |\n",
                squareColor[9], ANSI_RESET, squareColor[16], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[9], ANSI_RESET, squareColor[16], ANSI_RESET);
        System.out.printf(
                "|    player here:   %s|%s|                                                        "
                    + "                           |%s|%s   player here:    |\n",
                squareColor[9], ANSI_RESET, squareColor[16], ANSI_RESET);
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[9][0],29),
                squareColor[9],
                ANSI_RESET,
                squareColor[16],
                ANSI_RESET,
                middle(squarePlayer[16][0],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[9][1],29),
                squareColor[9],
                ANSI_RESET,
                squareColor[16],
                ANSI_RESET,
                middle(squarePlayer[16][1],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[9][2],29),
                squareColor[9],
                ANSI_RESET,
                squareColor[16],
                ANSI_RESET,
                middle(squarePlayer[16][2],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[9][3],29),
                squareColor[9],
                ANSI_RESET,
                squareColor[16],
                ANSI_RESET,
                middle(squarePlayer[16][3],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[9][4],29),
                squareColor[9],
                ANSI_RESET,
                squareColor[16],
                ANSI_RESET,
                middle(squarePlayer[16][4],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[9][5],29),
                squareColor[9],
                ANSI_RESET,
                squareColor[16],
                ANSI_RESET,
                middle(squarePlayer[16][5],29));

        System.out.print(
                "|--------------------|                                                            "
                    + "                       |--------------------|\n");

        // line3
        System.out.printf(
                "|       CHANCE      %s|%s|                                                        "
                    + "                           |%s|%s    Yuen Long      |\n",
                squareColor[8], ANSI_RESET, squareColor[17], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[8], ANSI_RESET, squareColor[17], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[8], ANSI_RESET, squareColor[17], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s     HKD 400       |\n",
                squareColor[8], ANSI_RESET, squareColor[17], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[8], ANSI_RESET, squareColor[17], ANSI_RESET);
        System.out.printf(
                "|    player here:   %s|%s|                                                        "
                    + "                           |%s|%s   player here:    |\n",
                squareColor[8], ANSI_RESET, squareColor[17], ANSI_RESET);
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[8][0],29),
                squareColor[8],
                ANSI_RESET,
                squareColor[17],
                ANSI_RESET,
                middle(squarePlayer[17][0],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[8][1],29),
                squareColor[8],
                ANSI_RESET,
                squareColor[17],
                ANSI_RESET,
                middle(squarePlayer[17][1],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[8][2],29),
                squareColor[8],
                ANSI_RESET,
                squareColor[17],
                ANSI_RESET,
                middle(squarePlayer[17][2],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[8][3],29),
                squareColor[8],
                ANSI_RESET,
                squareColor[17],
                ANSI_RESET,
                middle(squarePlayer[17][3],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[8][4],29),
                squareColor[8],
                ANSI_RESET,
                squareColor[17],
                ANSI_RESET,
                middle(squarePlayer[17][4],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[8][5],29),
                squareColor[8],
                ANSI_RESET,
                squareColor[17],
                ANSI_RESET,
                middle(squarePlayer[17][5],29));

        System.out.print(
                "|--------------------|                                                            "
                    + "                       |--------------------|\n");

        // line4
        System.out.printf(
                "|      Mong Kok     %s|%s|                                                        "
                    + "                           |%s|%s      CHANCE       |\n",
                squareColor[7], ANSI_RESET, squareColor[18], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[7], ANSI_RESET, squareColor[18], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[7], ANSI_RESET, squareColor[18], ANSI_RESET);
        System.out.printf(
                "|      HKD 500      %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[7], ANSI_RESET, squareColor[18], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[7], ANSI_RESET, squareColor[18], ANSI_RESET);
        System.out.printf(
                "|    player here:   %s|%s|                                                        "
                    + "                           |%s|%s   player here:    |\n",
                squareColor[7], ANSI_RESET, squareColor[18], ANSI_RESET);
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[7][0],29),
                squareColor[7],
                ANSI_RESET,
                squareColor[18],
                ANSI_RESET,
                middle(squarePlayer[18][0],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[7][1],29),
                squareColor[7],
                ANSI_RESET,
                squareColor[18],
                ANSI_RESET,
                middle(squarePlayer[18][1],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[7][2],29),
                squareColor[7],
                ANSI_RESET,
                squareColor[18],
                ANSI_RESET,
                middle(squarePlayer[18][2],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[7][3],29),
                squareColor[7],
                ANSI_RESET,
                squareColor[18],
                ANSI_RESET,
                middle(squarePlayer[18][3],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[7][4],29),
                squareColor[7],
                ANSI_RESET,
                squareColor[18],
                ANSI_RESET,
                middle(squarePlayer[18][4],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[7][5],29),
                squareColor[7],
                ANSI_RESET,
                squareColor[18],
                ANSI_RESET,
                middle(squarePlayer[18][5],29));

        System.out.print(
                "|--------------------|                                                            "
                    + "                       |--------------------|\n");

        // line5
        System.out.printf(
                "|       Shek O      %s|%s|                                                        "
                    + "                           |%s|%s      Tai O        |\n",
                squareColor[6], ANSI_RESET, squareColor[19], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[6], ANSI_RESET, squareColor[19], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[6], ANSI_RESET, squareColor[19], ANSI_RESET);
        System.out.printf(
                "|      HKD 400      %s|%s|                                                        "
                    + "                           |%s|%s     HKD 600       |\n",
                squareColor[6], ANSI_RESET, squareColor[19], ANSI_RESET);
        System.out.printf(
                "|                   %s|%s|                                                        "
                    + "                           |%s|%s                   |\n",
                squareColor[6], ANSI_RESET, squareColor[19], ANSI_RESET);
        System.out.printf(
                "|    player here:   %s|%s|                                                        "
                    + "                           |%s|%s   player here:    |\n",
                squareColor[6], ANSI_RESET, squareColor[19], ANSI_RESET);
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[6][0],29),
                squareColor[6],
                ANSI_RESET,
                squareColor[19],
                ANSI_RESET,
                middle(squarePlayer[19][0],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[6][1],29),
                squareColor[6],
                ANSI_RESET,
                squareColor[19],
                ANSI_RESET,
                middle(squarePlayer[19][1],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[6][2],29),
                squareColor[6],
                ANSI_RESET,
                squareColor[19],
                ANSI_RESET,
                middle(squarePlayer[19][2],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[6][3],29),
                squareColor[6],
                ANSI_RESET,
                squareColor[19],
                ANSI_RESET,
                middle(squarePlayer[19][3],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[6][4],29),
                squareColor[6],
                ANSI_RESET,
                squareColor[19],
                ANSI_RESET,
                middle(squarePlayer[19][4],29));
        System.out.printf(
                "|%-28s%s|%s|                                                                      "
                    + "             |%s|%s%-28s|\n",
                middle(squarePlayer[6][5],29),
                squareColor[6],
                ANSI_RESET,
                squareColor[19],
                ANSI_RESET,
                middle(squarePlayer[19][5],29));

        System.out.print(
                "|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|\n");

        // line6
        System.out.printf(
                "|%s--------------------|%s--------------------|%s--------------------|%s--------------------|%s--------------------|%s--------------------%s|\n",
                squareColor[5],
                squareColor[4],
                squareColor[3],
                squareColor[2],
                squareColor[1],
                squareColor[0],
                ANSI_RESET);
        System.out.print(
                "|        JAIL        |       Stanley      |     INCOME TAX     |      Wan Chai    "
                    + "  |       Central      |         GO         |\n");
        System.out.print(
                "|      in jail:      |                    |                    |                  "
                    + "  |                    |  collect HKD 1500  |\n");
        System.out.printf(
                "|%s%-9s%s  %s%-9s%s|                    |                    |                   "
                    + " |                    |    as your salary  |\n",
                inJailColors[0],
                inJailPlayers[0],
                ANSI_RESET,
                inJailColors[1],
                inJailPlayers[1],
                ANSI_RESET);
        System.out.printf(
                "|%s%-9s%s  %s%-9s%s|      HKD 700       |                    |      HKD 400      "
                    + " |      HKD 500       |    when you pass   |\n",
                inJailColors[2],
                inJailPlayers[2],
                ANSI_RESET,
                inJailColors[3],
                inJailPlayers[3],
                ANSI_RESET);
        System.out.printf(
                "|%s%-9s%s  %s%-9s%s|                    |                    |                   "
                    + " |                    |        <<----      |\n",
                inJailColors[4],
                inJailPlayers[4],
                ANSI_RESET,
                inJailColors[5],
                inJailPlayers[5],
                ANSI_RESET);
        System.out.print(
                "|   just visiting:   |    player here:    |    player here:    |    player here:  "
                    + "  |    player here:    |    player here:    |\n");
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                justVisitingPlayer[0],
                middle(squarePlayer[4][0],29),
                middle(squarePlayer[3][0],29),
                middle(squarePlayer[2][0],29),
                middle(squarePlayer[1][0],29),
                middle(squarePlayer[0][0],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                justVisitingPlayer[1],
                middle(squarePlayer[4][1],29),
                middle(squarePlayer[3][1],29),
                middle(squarePlayer[2][1],29),
                middle(squarePlayer[1][1],29),
                middle(squarePlayer[0][1],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                justVisitingPlayer[2],
                middle(squarePlayer[4][2],29),
                middle(squarePlayer[3][2],29),
                middle(squarePlayer[2][2],29),
                middle(squarePlayer[1][2],29),
                middle(squarePlayer[0][2],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                justVisitingPlayer[3],
                middle(squarePlayer[4][3],29),
                middle(squarePlayer[3][3],29),
                middle(squarePlayer[2][3],29),
                middle(squarePlayer[1][3],29),
                middle(squarePlayer[0][3],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                justVisitingPlayer[4],
                middle(squarePlayer[4][4],29),
                middle(squarePlayer[3][4],29),
                middle(squarePlayer[2][4],29),
                middle(squarePlayer[1][4],29),
                middle(squarePlayer[0][4],29));
        System.out.printf(
                "|%-29s|%-29s|%-29s|%-29s|%-29s|%-29s|\n",
                justVisitingPlayer[5],
                middle(squarePlayer[4][5],29),
                middle(squarePlayer[3][5],29),
                middle(squarePlayer[2][5],29),
                middle(squarePlayer[1][5],29),
                middle(squarePlayer[0][5],29));
        System.out.print(
                "|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|\n");
    }

    /** Only print the game info */
    private void printInfo() {
        Printer.printInfoMsg("\n\t|    Current Wealth    |\n");
        for (var player : board.getPlayers()) {
            String line =
                    String.format("\t| %-10s | $%-6d |\n", player.getName(), player.getMoney());
            Printer.printColoredMsg(Printer.ANSI_CYAN, line);
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
     * @param prompt prompt method (lambda)
     * @param fallback_msg msg display to after invalid input
     * @param regex regex of valid prompt
     * @return scanned valid input
     * @throws InterruptedException if user interrupt the input
     */
    public static String scanValidInputWithQuit(Runnable prompt, String fallback_msg, String regex)
            throws InterruptedException {
        String input;
        do {
            prompt.run();
            input = Main.getScanner().nextLine();

            // user type quit, interrupt the process
            if (input.matches(QUIT_REGEX)) throw new InterruptedException("Interrupted by user.\n");
            if (input.matches(regex)) return input;

            Printer.printWarnMsg("INVALID INPUT! " + fallback_msg + "\n");
        } while (true);
    }

    /**
     * Scan an input until it is valid
     *
     * @param prompt prompt method (lambda)
     * @param fallback_msg msg display to after invalid input
     * @param regex regex of valid prompt
     * @return scanned valid input
     */
    public static String scanValidInput(Runnable prompt, String fallback_msg, String regex) {
        String input;
        do {
            prompt.run();
            input = Main.getScanner().nextLine();

            if (input.matches(regex)) return input;

            Printer.printWarnMsg("INVALID INPUT! " + fallback_msg + "\n");
        } while (true);
    }

    public static void main(String[] args) {
        System.out.printf(
                "|%s--------------------%s|--------------------|--------------------|--------------------|--------------------|--------------------|",
                ANSI_BLUE, ANSI_RESET);
    }
}
