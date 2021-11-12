package hk.edu.polyu.comp.comp3211.monopoly.model;

import static org.junit.jupiter.api.Assertions.*;

import hk.edu.polyu.comp.comp3211.monopoly.Main;
import hk.edu.polyu.comp.comp3211.monopoly.model.squares.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTest {

    Chance chance;
    Free free;
    Go go;
    Oops oops;
    Jail jail;
    Property property;
    Tax tax;
    Player player1, player2;

    @BeforeEach
    void setUp() {
        Main.TEST = true;

        this.player1 = new Player("player1");
        player1.setPosition(3);
        player1.setInJail(0);

        this.player2 = new Player("player2");
        player2.setPosition(3);
        player2.setInJail(1);

        this.chance = new Chance();
        this.go = new Go();
        this.oops = new Oops();
        this.jail = new Jail();
    }

    @Test
    void chanceSquareTest() { // Test if the chance square is working

        // set properties of the player
        String oriName = player1.getName();
        int oriMoney = player1.getMoney();
        Property[] oriProp = player1.getProperties();
        int oriPosition = player1.getPosition();
        int oriInJail = player1.getInJail();
        boolean oriBankrupted = player1.isBankrupted();

        chance.execute(player1); // execute the chance square

        String curName = player1.getName();
        int curMoney = player1.getMoney();
        Property[] curProp = player1.getProperties();
        int curPosition = player1.getPosition();
        int curInjail = player1.getInJail();
        boolean curBankrupted = player1.isBankrupted();
        // Test is unnecessary properties remain unchanged
        assertEquals(oriName, curName);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);

        assertTrue(
                oriMoney + 200 >= curMoney, "Chance: get too much"); // Test if player get too much
        assertTrue(
                oriMoney - 300 <= curMoney,
                "Chance: lose too much"); // Test if player lose too much
        assertEquals(0, (oriMoney - curMoney) % 10, "Chance: not multiple of 10"); // Test if the result is multiple of 10
    }

    @Test
    void freeSquareTest() { // Test if the free square is working
        // set properties of the player
        String oriName = player1.getName();
        int oriMoney = player1.getMoney();
        Property[] oriProp = player1.getProperties();
        int oriPosition = player1.getPosition();
        int oriInJail = player1.getInJail();
        boolean oriBankrupted = player1.isBankrupted();

        free.execute(player1); // execute the free square

        // Test if nothing has been changed after execute()
        String curName = player1.getName();
        int curMoney = player1.getMoney();
        Property[] curProp = player1.getProperties();
        int curPosition = player1.getPosition();
        int curInjail = player1.getInJail();
        boolean curBankrupted = player1.isBankrupted();

        assertEquals(oriName, curName);
        assertEquals(oriMoney, curMoney);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);
    }

    @Test
    void goSquareTest() { // Test if the go square is working
        // set properties of the player
        String oriName = player1.getName();
        int oriMoney = player1.getMoney();
        Property[] oriProp = player1.getProperties();
        int oriPosition = player1.getPosition();
        int oriInJail = player1.getInJail();
        boolean oriBankrupted = player1.isBankrupted();

        free.execute(player1); // execute the free square

        // Test if nothing has been changed after execute()
        String curName = player1.getName();
        int curMoney = player1.getMoney();
        Property[] curProp = player1.getProperties();
        int curPosition = player1.getPosition();
        int curInjail = player1.getInJail();
        boolean curBankrupted = player1.isBankrupted();

        assertEquals(oriName, curName);
        assertEquals(oriMoney, curMoney);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);
    }

    @Test
    void jailSquareTest1() { // Test the jail square for Just Visiting

        // set properties of the player
        String oriName = player1.getName();
        int oriMoney = player1.getMoney();
        Property[] oriProp = player1.getProperties();
        int oriPosition = player1.getPosition();
        int oriInJail = player1.getInJail();
        boolean oriBankrupted = player1.isBankrupted();

        jail.execute(player1); // execute the jail square

        String curName = player1.getName();
        int curMoney = player1.getMoney();
        Property[] curProp = player1.getProperties();
        int curPosition = player1.getPosition();
        int curInJail = player1.getInJail();
        boolean curBankrupted = player1.isBankrupted();

        assertEquals(oriName, curName);
        assertTrue(
                curInJail < oriInJail,
                "Jail: Just Visiting, not in jail"); // Test if the inJail counter is decreased
    }

    @Test
    void jailSquareTest2() { // Test the jail square for paying fine to leave jail
        // set properties of the player
        String oriName = player2.getName();
        int oriMoney = player2.getMoney();
        Property[] oriProp = player2.getProperties();
        int oriPosition = player2.getPosition();
        int oriInJail = player2.getInJail();
        boolean oriBankrupted = player2.isBankrupted();
        // set special flags for test
        jail.setPayFineForTest(true);
        jail.setRollDIceResult(new int[] {1, 2});
        jail.execute(player2);

        String curName = player2.getName();
        int curMoney = player2.getMoney();
        Property[] curProp = player2.getProperties();
        int curPosition = player2.getPosition();
        int curInjail = player2.getInJail();
        boolean curBankrupted = player2.isBankrupted();

        assertEquals(oriName, curName); // Test if the name is not changed
        assertEquals(oriProp, curProp); // Test if the properties are not changed

        assertEquals(
                curMoney,
                oriMoney - 150,
                "Jail: money not reduce when paying fine"); // Test if the money is reduced
        assertTrue(
                curPosition != oriPosition,
                "Jail: pay fine but not moved"); // Test if the player is moved
        assertTrue(
                curInjail != 0,
                "Jail: pay fine but still in jail"); // Test if the player is still in jail
    }

    @Test
    void jailSquareTest3() { // Test the jail square for throwing doubles
        // set properties of the player
        String oriName = player2.getName();
        int oriMoney = player2.getMoney();
        Property[] oriProp = player2.getProperties();
        int oriPosition = player2.getPosition();
        int oriInJail = player2.getInJail();
        boolean oriBankrupted = player2.isBankrupted();
        // set special flags for test
        jail.setRollDIceResult(new int[] {1, 1});
        jail.execute(player2);

        String curName = player2.getName();
        int curMoney = player2.getMoney();
        Property[] curProp = player2.getProperties();
        int curPosition = player2.getPosition();
        int curInjail = player2.getInJail();
        boolean curBankrupted = player2.isBankrupted();

        assertEquals(oriName, curName); // Test if the name is not changed
        assertEquals(oriProp, curProp); // Test if the properties are not changed

        assertEquals(
                curMoney,
                oriMoney - 150,
                "Jail: money not reduce when paying fine"); // Test if the money is reduced

        assertTrue(
                curPosition != oriPosition,
                "Jail: throw doubles but not move"); // Test if the player is moved
        assertTrue(
                curInjail != 0,
                "Jail: throw doubles but still in jail"); // Test if the player is still in jail
    }

    @Test
    void jailSquareTest4() { // Test the jail square for three round no move
        // set properties of the player
        String oriName = player2.getName();
        int oriMoney = player2.getMoney();
        Property[] oriProp = player2.getProperties();
        int oriPosition = player2.getPosition();
        int oriInJail = player2.getInJail();
        boolean oriBankrupted = player2.isBankrupted();
        // set special flags for test
        jail.setRollDIceResult(new int[] {1, 2});
        jail.setPayFineForTest(false);
        jail.execute(player2);
        jail.execute(player2);
        jail.execute(player2);

        String curName = player2.getName();
        int curMoney = player2.getMoney();
        Property[] curProp = player2.getProperties();
        int curPosition = player2.getPosition();
        int curInjail = player2.getInJail();
        boolean curBankrupted = player2.isBankrupted();

        assertEquals(oriName, curName); // Test if the name is not changed
        assertEquals(oriProp, curProp); // Test if the properties are not changed

        assertTrue(
                curPosition != oriPosition,
                "Jail: after 3 round but not move"); // Test if the player is moved
        assertTrue(
                curInjail != 0,
                "Jail: after 3 round but still in jail"); // Test if the player is still in jail
        assertEquals(
                curMoney,
                oriMoney - 150,
                "Jail: after 3 rounds but not pay"); // Test if the player has paid the fine
    }

    @Test
    void oopsSquareTest() { // Test if the oops square is working
        String oriName = player1.getName();
        int oriMoney = player1.getMoney();
        Property[] oriProp = player1.getProperties();
        int oriPosition = player1.getPosition();
        int oriInJail = player1.getInJail();
        boolean oriBankrupted = player1.isBankrupted();

        oops.execute(player1);

        String curName = player1.getName();
        int curMoney = player1.getMoney();
        Property[] curProp = player1.getProperties();
        int curPosition = player1.getPosition();
        int curInjail = player1.getInJail();
        boolean curBankrupted = player1.isBankrupted();

        assertEquals(oriName, curName);
        assertEquals(oriProp, curProp);
        assertEquals(oriBankrupted, curBankrupted);
        assertEquals(oriMoney, curMoney);

        assertEquals(6, curPosition, "Oops: not go to jail"); // Test if the player is in jail
        assertTrue(
                curInjail != 0,
                "Oops: not become in jail"); // Test if the player's inJail counter is increased
    }

    @Test
    void propertySquareTest() { // Test if the property square is working
        // test property (Name: Central, Price: 800, Rent: 90)
        this.property = new Property("Central", 800, 90);

        property.setOwner(player1); // player1 buy the property
        assertEquals(player1.getMoney(), 700); // check player1 money
        assertEquals(this.property.getOwner(), player1); // check property owner

        this.player2 = new Player();
        property.execute(player2); // player2 enter the property
        assertEquals(player1.getMoney(), 790); // check player1 money
        assertEquals(player2.getMoney(), 1410); // check player2 money
    }

    @Test
    void taxSquareTest() { // Test if the tax square is working
        this.tax = new Tax();
        tax.execute(player1); // player1 pay the tax
        int currentMoney = player1.getMoney();
        assertEquals(currentMoney, 1350); // check money after tax
    }
}
