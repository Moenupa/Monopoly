package hk.edu.polyu.comp.comp3211.monopoly.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player[] testPlayers;

    @BeforeEach
    void setUp() {
        this.testPlayers = new Player[2];
    }

    @Test
    void test() {
        assertTrue(this.testPlayers.length == 2);
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getMoney() {
    }

    @Test
    void setMoney() {
    }

    @Test
    void getPosition() {
    }

    @Test
    void setPosition() {
    }

    @Test
    void move() {
    }

    @Test
    void goToJail() {
    }

    @Test
    void bankrupt() {
    }

    @Test
    void getInJail() {
    }

    @Test
    void setInJail() {
    }

    @Test
    void isBankrupted() {
    }

    @Test
    void rollDice() {
    }
}