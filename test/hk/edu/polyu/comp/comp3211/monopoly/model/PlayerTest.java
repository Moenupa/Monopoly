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
        assertEquals(
                player.getPosition(),
                0); // Test if the player is initialized at the starting position
        player.setPosition(5);
        assertEquals(player.getPosition(), 5); // Test if setPosition() works well
        player.move(5);
        assertEquals(player.getPosition(), 10); // Test if move() works well
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
        assertEquals(player.getPosition(), 6); // Test if the player is moved to the jail correctly
        assertEquals(player.getInJail(), 3); // Test if the inJail status is set correctly
        player.setInJail(1);
        assertEquals(player.getInJail(), 2); // Test if setInJail() works well
    }

    @Test
    void bankruptTest() { // Test if bankrupt related functions work well
        var player = new Player("test");
        assertFalse(player.isBankrupted()); // Test if the player is initialized correctly
        player.bankrupt();
        assertTrue(player.isBankrupted()); // Test if bankrupt() works correctly
    }

    @Test
    void diceTest() { // Test if rollDice() works correctly
        var player = new Player("test");
        for (int i = 0; i < 100; i++) { // repeat 100 times
            int a = player.rollDice();
            assertTrue(a > 0);
            assertTrue(a < 5);
        }
    }
}
