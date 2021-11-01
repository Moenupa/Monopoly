package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;

public class StartMenu implements IBase {
    /**
     * Welcome the user. If there exists a save file in the directory, prompt to the user of the
     * option to load the game. User can try to start a game; load a game if any; and exit in this
     * interface.
     */
    @Override
    public void terminal() {
        var in = Main.GetScanner();
    }
}
