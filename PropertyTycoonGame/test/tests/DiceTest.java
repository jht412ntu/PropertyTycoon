package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import propertytycoongame.Dice;

/** Testing class for the Dice class.
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
        Dice dice = new Dice();
        dice.rollDice();
        assertEquals(dice.getDiceVal0() + dice.getDiceVal1(), dice.getTotalVal());
    }
    
    @Test
    public void goToJailTest(){
        Dice dice = new Dice();
        dice.setNumDouble(3);
        dice.goToJail();
        assertEquals(true, dice.goJail);
    }
    
    @Test
    public void doubleRollTest(){
        Dice dice = new Dice();
        dice.setNumDouble(3);
        dice.rollAgain();
        assertEquals(false, dice.rollAgain);
        dice.setNumDouble(2);
        dice.rollAgain();
        assertEquals(true, dice.rollAgain);
    }
    
    @Test
    public void newPlayerTest(){
        Dice dice = new Dice();
        dice.rollDice();
        dice.newPlayer();
        assertEquals(0, dice.getDiceVal0());
        assertEquals(0, dice.getDiceVal1());
        assertEquals(0, dice.getNumDouble());
        assertEquals(0, dice.getTotalVal());
        assertEquals(true, dice.rollAgain);
        assertEquals(false, dice.goJail);
        
    }
    
    
    @Test
    public void rollAgainTest(){
        Dice dice = new Dice();
        dice.setRollDice(3,5);
        assertEquals(false, dice.rollAgain);
        dice.newPlayer();
        dice.setRollDice(3, 3);
        assertEquals(true, dice.rollAgain);
    }
        
}
