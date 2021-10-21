package hk.edu.polyu.comp.comp3211.monopoly;

import hk.edu.polyu.comp.comp3211.monopoly.controller.*;

import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {

    private static IBase curUI;
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        setUI(new StartMenu());
        while (true) {
            curUI.terminal();
        }
    }

    public static Scanner GetScanner() {
        return in;
    }

    public static void setUI(IBase ui) {
        curUI = ui;
    }
}
