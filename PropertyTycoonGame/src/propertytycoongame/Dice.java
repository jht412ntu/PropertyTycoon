package propertytycoongame;

import java.util.Random;
import static java.util.stream.IntStream.range;

/** Dice rolling class 
 *
 * Class that provides functionality for rolling the dice, reseting the dice
 * for the next player and signals if the current player should go to jail.
 * 
 * Instead of having this class return the value of the rolled dice, it is
 * stored in public variables, so that the game can look up these values at
 * anytime
 * 
 * @author Hayden
 */
public class Dice {
    private final int[] sides = range(1,6).toArray();
    private int diceVal0, diceVal1;
    private int numDouble = 0;
    public int totalVal;
    public boolean goJail = false;
    public boolean rollAgain = true;
    
    /**
    * Rolls the dice and returns the total combined value of both dice.
    * 
    * If the player rolls 3 doubles in a row, the goToJail method is called
    */
    public void rollDice(){
        if(rollAgain){
            diceVal0 = sides[new Random().nextInt(sides.length)];
            diceVal1 = sides[new Random().nextInt(sides.length)];
            if(diceVal0 == diceVal1){
                numDouble += 1;
                rollAgain();
            }
            totalValue();
        }
    }
    
    public void totalValue(){
        totalVal = diceVal0 + diceVal1;
    }
    
    /**
    * Resets all Dice variables, so the dice are ready for the next player
    */
    public void newPlayer(){
        diceVal0 = 0;
        diceVal1 = 0;
        totalVal = 0;
        numDouble = 0;
        goJail = false;
        rollAgain = true;
    }
    
    /*
    * Sets goJail as true if the player has exceeded 3 consecutive rolls
    */
    public void goToJail(){
        goJail = numDouble > 2;
    }
    
    /*
    * Decides if the player can roll again, if not they can't 
    * roll again and should go to jail.
    */
    
    public void rollAgain(){
        if(numDouble <= 2){
            rollAgain = true;
        }
        else{
            rollAgain = false;
            goToJail();
        }
    }
    
    
    /*
     * The tests below are for testing purposes.
     */
    
    public int getDiceVal0() {
        return diceVal0;
    }

    public void setDiceVal0(int diceVal0) {
        this.diceVal0 = diceVal0;
    }

    public int getDiceVal1() {
        return diceVal1;
    }

    public void setDiceVal1(int diceVal1) {
        this.diceVal1 = diceVal1;
    }

    public int getTotalVal() {
        return totalVal;
    }

    public void setTotalVal(int totalVal) {
        this.totalVal = totalVal;
    }

    public int getNumDouble() {
        return numDouble;
    }

    public void setNumDouble(int numDouble) {
        this.numDouble = numDouble;
    }
}
