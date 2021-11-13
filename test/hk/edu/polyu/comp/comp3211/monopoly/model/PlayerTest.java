package hk.edu.polyu.comp.comp3211.monopoly.model;

import static org.junit.jupiter.api.Assertions.*;

import hk.edu.polyu.comp.comp3211.monopoly.Main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player[] testPlayers;

    @BeforeEach
    void setUp() {
        this.testPlayers = new Player[2];
        Main.TEST = true;
    }

    @Test
    void test() {
        assertEquals(2, this.testPlayers.length);
    }

    @Test
    void nameTest() { // Test if the naming related functions work normally
        String s = "test";
        var player = new Player(s);
        assertEquals(s, player.getName()); // Test if the constructor and getName() works well
        s = "test2";
        player.setName(s);
        assertEquals(s, player.getName()); // Test if setName() works well
    }

    @Test
    void moneyTest() { // Test is the money related functions work normally
        var player = new Player("test");
        assertEquals(
                player.getMoney(), Player.SALARY); // Test if the player begins with the base money
        player.addMoney(500);
        assertEquals(player.getMoney(), Player.SALARY + 500); //  Test if addMoney() works well
        player.setMoney(500);
        assertEquals(player.getMoney(), 500); // Test if setMoney() works well
    }

    @Test
    void positionTest() { // Test is the position related functions work normally
        var player = new Player("test");
        // Test if the player is initialized at the starting position
        assertEquals(0, player.getPosition());
        player.setPosition(5);
        // Test if setPosition() works well
        assertEquals(5, player.getPosition());
        player.move(5);
        // Test if move() works well
        assertEquals(10, player.getPosition());
    }

    @Test
    void salaryTest() { //  Test if the player can get the salary normally
        var player = new Player("test");
        player.setPosition(19);
        player.move(5);
        assertEquals(
                player.getMoney(),
                Player.SALARY
                        * 2); // Test if the function works well if player goes through the starting
        // point
        player.setPosition(18);
        player.move(2);
        assertEquals(
                player.getMoney(),
                Player.SALARY
                        * 3); // Test if the function works well if the player steps on the starting
        // point
    }

    @Test
    void jailTest() { // Test if the jail related functions work normally
        var player = new Player("test");

        player.goToJail();
        // Test if the player is moved to the jail correctly
        assertEquals(5, player.getPosition());
        // Test if the inJail status is set correctly
        assertEquals(3, player.getInJail());

        // Test if setInJail() works well
        player.setInJail(1);
        assertEquals(1, player.getInJail());
    }

    @Test
    void bankruptTest() { // Test if bankrupt related functions work well
        var player = new Player("test");
        assertFalse(player.isBankrupted()); // Test if the player is initialized correctly
        player.setMoney(-10);
        player.bankrupt();
        assertTrue(player.isBankrupted()); // Test if bankrupt() works correctly
    }

    @Test
    void diceTest() { // Test if rollDice() works correctly
        var player = new Player("test");
        for (int i = 0; i < 100; i++) { // repeat 100 times
            int[] diceResult = new int[2];
            diceResult[0]=player.rollDice();
            diceResult[1]=player.rollDice();
            assertTrue(diceResult[0] > 0);
            assertTrue(diceResult[0] < 5);
            assertTrue(diceResult[1] > 0);
            assertTrue(diceResult[1] < 5);
        }
    }
}
