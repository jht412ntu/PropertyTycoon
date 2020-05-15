package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import propertytycoongame.Dice;

/**
 * Testing class for the Dice class.
 *
 * @author Hayden
 */
public class DiceTest {

    public DiceTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        //Dice dice = new Dice();
    }

    @Test
    public void rollDiceTest() {
        for (int i = 0; i < 9; i++) {
            Dice dice = new Dice();
            dice.rollDice();
            assertEquals(true, (dice.getDiceVal0() > 0 && dice.getDiceVal0() < 7));
            assertEquals(true, (dice.getDiceVal1() > 0 && dice.getDiceVal1() < 7));
            assertEquals(dice.getDiceVal0() + dice.getDiceVal1(), dice.getTotalVal());
        }
    }

    @Test
    public void doubleRollTest() {
        Dice dice = new Dice();
        dice.setRollDice(3, 3);
        assertEquals(true, dice.rollAgain);
        dice.setRollDice(3, 2);
        assertEquals(false, dice.rollAgain);

        dice = new Dice();
        dice.setRollDice(3, 2);
        assertEquals(false, dice.rollAgain);

        dice = new Dice();
        dice.setRollDice(3, 3);
        assertEquals(true, dice.rollAgain);
        dice.setRollDice(3, 3);
        assertEquals(true, dice.rollAgain);
        dice.setRollDice(3, 3);
        assertEquals(false, dice.rollAgain);
    }

    @Test
    public void goToJailTest() {
        Dice dice = new Dice();
        dice.setNumDouble(3);
        dice.setRollDice(3, 3);
        assertEquals(true, dice.goJail);
    }

    @Test
    public void newPlayerTest() {
        Dice dice = new Dice();
        dice.rollDice();
        dice.newPlayer();
        assertEquals(0, dice.getDiceVal0());
        assertEquals(0, dice.getDiceVal1());
        assertEquals(0, dice.getNumDouble());
        assertEquals(0, dice.getTotalVal());
        assertEquals(true, dice.rollAgain);
        assertEquals(false, dice.goJail);
        assertEquals(false, dice.doub);
    }
}
