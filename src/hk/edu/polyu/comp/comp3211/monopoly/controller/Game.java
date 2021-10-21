package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.*;
import hk.edu.polyu.comp.comp3211.monopoly.view.*;

public class Game implements IBase {
    private Board board;
    private Printer printer;

    @Override
    public void terminal() {

    }

    public Game() {
        int num;
        System.out.print("Please enter the number of players:");
        var in = Main.GetScanner();
        num = in.nextInt();
        board = new Board(num);
        printer = new Printer(board);

    }
}
