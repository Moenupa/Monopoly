package hk.edu.polyu.comp.comp3211.monopoly;

import hk.edu.polyu.comp.comp3211.monopoly.controller.*;

import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    /**
     * Set to true to enable test mode. where all user input is ignored and the methods will take
     * the default action.
     */
    public static boolean TEST = false;

    private static IBase curUI; // current UI;

    /**
     * Should be the only scanner used throughout the application
     */
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        setUI(new StartMenu());
        while (true) {
            curUI.terminal();
        }
    }

    /**
     * If a method needs to get user input, use this static method to get the scanner
     *
     * @return The scanner
     */
    public static Scanner GetScanner() {
        return in;
    }

    /**
     * Switch user interface
     *
     * @param ui the new interface
     */
    public static void setUI(IBase ui) {
        curUI = ui;
    }
}
