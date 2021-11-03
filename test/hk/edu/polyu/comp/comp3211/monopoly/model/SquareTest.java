package hk.edu.polyu.comp.comp3211.monopoly.model;

import hk.edu.polyu.comp.comp3211.monopoly.model.squares.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    Chance chance;
    Free free;
    Go go;
    Oops oops;
    Jail jail;
    Player player1, player2, player3;

    @BeforeEach
    void setUp() {


        this.player1=new Player("player");

        this.chance=new Chance();
        this.go=new Go();
        this.oops=new Oops();
        this.jail=new Jail();




    }

    @Test
    void test() {
        assertTrue(true);
    }

    @Test
    void chanceSquareTest() {
        String oriName=player1.getName();
        int oriMoney= player1.getMoney();
        Property[] oriProp= player1.getProperties();
        int oriPosition=player1.getPosition();
        int oriInJail=player1.getInJail();
        boolean oriBankrupted=player1.isBankrupted();



        chance.execute(player1);

        String curName=player1.getName();
        int curMoney= player1.getMoney();
        Property[] curProp=player1.getProperties();
        int curPosition=player1.getPosition();
        int curInjail=player1.getInJail();
        boolean curBankrupted=player1.isBankrupted();

        assertEquals(oriName,curName);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);

        assertTrue(oriMoney+200 >= curMoney, "Chance: get too much");
        assertTrue(oriMoney-300  <= curMoney, "Chance: lose too much");
        assertTrue((oriMoney-curMoney)%10 ==0, "Chance: not multiple of 10");
    }

    @Test
    void freeSquareTest() {
        String oriName=player1.getName();
        int oriMoney= player1.getMoney();
        Property[] oriProp= player1.getProperties();
        int oriPosition=player1.getPosition();
        int oriInJail=player1.getInJail();
        boolean oriBankrupted=player1.isBankrupted();

        free.execute(player1);

        String curName=player1.getName();
        int curMoney= player1.getMoney();
        Property[] curProp=player1.getProperties();
        int curPosition=player1.getPosition();
        int curInjail=player1.getInJail();
        boolean curBankrupted=player1.isBankrupted();

        assertEquals(oriName,curName);
        assertEquals(oriMoney, curMoney);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);
    }

    @Test
    void goSquareTest() {

        String oriName=player1.getName();
        int oriMoney= player1.getMoney();
        Property[] oriProp= player1.getProperties();
        int oriPosition=player1.getPosition();
        int oriInJail=player1.getInJail();
        boolean oriBankrupted=player1.isBankrupted();

        go.execute(player1);

        String curName=player1.getName();
        int curMoney= player1.getMoney();
        Property[] curProp=player1.getProperties();
        int curPosition=player1.getPosition();
        int curInjail=player1.getInJail();
        boolean curBankrupted=player1.isBankrupted();

        assertEquals(oriName,curName);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);

        assertTrue(oriMoney+1500 == curMoney, "Go: not get 1500HKD");

    }

    @Test
    void jailSquareTest() {

        String oriName=player1.getName();
        int oriMoney= player1.getMoney();
        Property[] oriProp= player1.getProperties();
        int oriPosition=player1.getPosition();
        int oriInJail=player1.getInJail();
        boolean oriBankrupted=player1.isBankrupted();

        go.execute(player1);

        String curName=player1.getName();
        int curMoney= player1.getMoney();
        Property[] curProp=player1.getProperties();
        int curPosition=player1.getPosition();
        int curInjail=player1.getInJail();
        boolean curBankrupted=player1.isBankrupted();

        assertEquals(oriName,curName);
        assertEquals(oriProp, curProp);
        assertEquals(oriPosition, curPosition);
        assertEquals(oriInJail, curInjail);
        assertEquals(oriBankrupted, curBankrupted);
    }

    @Test
    void oopsSquareTest() {
        String oriName=player1.getName();
        int oriMoney= player1.getMoney();
        Property[] oriProp= player1.getProperties();
        int oriPosition=player1.getPosition();
        int oriInJail=player1.getInJail();
        boolean oriBankrupted=player1.isBankrupted();

        oops.execute(player1);

        String curName=player1.getName();
        int curMoney= player1.getMoney();
        Property[] curProp=player1.getProperties();
        int curPosition=player1.getPosition();
        int curInjail=player1.getInJail();
        boolean curBankrupted=player1.isBankrupted();

        assertEquals(oriName,curName);
        assertEquals(oriProp, curProp);
        assertEquals(oriBankrupted, curBankrupted);
        assertEquals(oriMoney,curMoney);

        assertTrue(curPosition == 6, "Oops: not go to jail");
        assertTrue(curInjail!=0, "Oops: not become in jail");
    }

    @Test
    void propertySquareTest() {}

    @Test
    void taxSquareTest() {}
}
