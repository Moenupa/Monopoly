package hk.edu.polyu.comp.comp3211.monopoly.controller;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.Board;
import hk.edu.polyu.comp.comp3211.monopoly.view.Printer;

public class StartMenu implements IBase {
    /**
     * Scanner for user input
     */
    private static final String[] ASCII_MONOPOLY = {
            "$$\\      $$\\                                                   $$\\           ",
            "$$$\\    $$$ |                                                  $$ |          ",
            "$$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  $$ |$$\\   $$\\ ",
            "$$\\$$\\$$ $$ |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |$$ |  $$ |",
            "$$ \\$$$  $$ |$$ /  $$ |$$ |  $$ |$$ /  $$ |$$ /  $$ |$$ /  $$ |$$ |$$ |  $$ |",
            "$$ |\\$  /$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |$$ |  $$ |",
            "$$ | \\_/ $$ |\\$$$$$$  |$$ |  $$ |\\$$$$$$  |$$$$$$$  |\\$$$$$$  |$$ |\\$$$$$$$ |",
            "\\__|     \\__| \\______/ \\__|  \\__| \\______/ $$  ____/  \\______/ \\__| \\____$$ |",
            "                                           $$ |                    $$\\   $$ |",
            "                                           $$ |                    \\$$$$$$  |",
            "                                           \\__|                     \\______/ "
    };

    private static final String[] START_MENU = {
            "╔═══════════════════╗",
            "║     Start Menu    ║",
            "╟─────┬─────────────╢",
            "║ NUM │  ACTION     ║",
            "╟─────┼─────────────╢",
            "║  1  │  New Game   ║",
            "║  2  │  Load Game  ║",
            "║  3  │  Quit       ║",
            "╚═════╧═════════════╝"
    };

    /**
     * Welcome the user. If there exists a save file in the directory, prompt to the user of the
     * option to load the game. User can try to start a game; load a game if any; and exit in this
     * interface.
     */
    @Override
    public void terminal() {
        printStartMenu();

        String option;
        // intended infinite loop
        while (true) {
            // ensure inputs to be valid
            option =
                    Printer.scanValidInput(
                            () -> {
                                Printer.printHelpMsg("Please enter option index (1-3): ");
                            },
                            "Should be an integer.",
                            "^[1-3]$");

            // then get choose an option
            try {
                // if no err return directly otherwise loop back and ask for user option
                switch (Integer.parseInt(option)) {
                    case 1:
                        startNewGame();
                        return;
                    case 2:
                        loadPreviousGame();
                        return;
                    case 3:
                        quitGame();
                        return;
                }
            } catch (Exception e) {
                // any error will cause it loop back to ask for option
                // input 'num' is invalid
                Printer.printWarnMsg(e.getMessage());
            }
        }
    }

    /**
     * Initialize start menu
     */
    public StartMenu() {
    }

    /**
     * New game option
     */
    private static void startNewGame() {
        try {
            Main.setUI(new Game());
        } catch (InterruptedException e) {
            // game init is interrupted by user option
            Printer.printMsg(e.getMessage());
            Main.setUI(new StartMenu());
        }
    }

    /**
     * Load one of saved game
     */
    private static void loadPreviousGame() throws Exception {
        String name = chooseStoredGame();

        if (name != null) {
            Main.setUI(new Game(name));
        } else {
            throw new IllegalArgumentException("No game has been stored");
        }
    }

    /**
     * Choose which saved game to be loaded
     *
     * @return name of the chosen game
     * @throws InterruptedException if user quits
     */
    private static String chooseStoredGame() throws InterruptedException {
        String[] savedGameName = Board.getSavedGameName();

        if (savedGameName.length < 1) {
            System.out.println("No game has been stored");
            return null;
        } else if (savedGameName.length == 1) return savedGameName[0];

        System.out.println("\nSelect the game save file:");
        for (int i = 0; i < savedGameName.length; i++) {
            System.out.println((i + 1) + ". " + savedGameName[i]);
        }

        while (true) {
            String option =
                    Printer.scanValidInputWithQuit(
                            () -> {
                                System.out.print("Choose the index of game-save (integer): ");
                            },
                            "Should be an integer.",
                            "^\\d+$");

            try {
                int index = Integer.parseInt(option) - 1;
                if (index >= 0 && index < savedGameName.length) return savedGameName[index];
                else Printer.printWarnMsg("INVALID INPUT! The input is Out of Bound.\n");
            } catch (Exception e) {
                // internal error
                e.printStackTrace();
            }
        }
    }

    /**
     * Quit game
     */
    private static void quitGame() {
        System.out.println("\nQuit Game");
        System.exit(0);
    }

    private static void printStartMenu() {
        Printer.printMsg("\n");
        int offset = 1;
        for (int i = 0; i < ASCII_MONOPOLY.length; i++) {

            if (offset <= i && i < START_MENU.length + offset)
                Printer.printHelpMsg("\t" + START_MENU[i - offset]);
            else Printer.printMsg("\t" + " ".repeat(START_MENU[0].length()));
            Printer.printMsg("\t" + ASCII_MONOPOLY[i]);
            Printer.printMsg("\n");
        }
    }
}
