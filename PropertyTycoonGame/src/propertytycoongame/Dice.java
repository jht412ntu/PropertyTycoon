package propertytycoongame;

import java.util.Random;
import static java.util.stream.IntStream.range;

/**
 * Dice
 *
 * Class that provides functionality for rolling the dice,
 * reseting the dice for the next player and signals if the current player should go to jail.
 *
 * Instead of having this class return the value of the rolled dice,
 * it is stored in public variables, so that the game can look up these values at anytime
 *
 * Documented by Haotian Jiao
 *
 * @author Hayden
 */
public class Dice {

    private final int[] sides = range(1, 6).toArray();
    private int diceVal0, diceVal1;
    private int numDouble = 0;
    private int totalVal;
    public boolean goJail = false;
    public boolean rollAgain = true;
    public boolean doub = false;

    /**
     * Rolls the dice and returns the total combined value of both dice.
     *
     * If the player rolls 3 doubles in a row, the goToJail method is called
     */
    public void rollDice() {
        if (rollAgain) {
            diceVal0 = sides[new Random().nextInt(sides.length)];
            diceVal1 = sides[new Random().nextInt(sides.length)];
            if (diceVal0 == diceVal1) {
                numDouble += 1;
                rollAgain();
                doub = true;
            } else {
                rollAgain = false;
                doub = false;
            }
            totalValue();
        }
    }

    /**
     * Calculates the total value of the two dice.
     * 
     */
    public void totalValue() {
        totalVal = diceVal0 + diceVal1;
    }

    /**
     * Resets all Dice variables,
     * so the dice are ready for the next player.
     * 
     */
    public void newPlayer() {
        diceVal0 = 0;
        diceVal1 = 0;
        totalVal = 0;
        numDouble = 0;
        goJail = false;
        rollAgain = true;
        doub = false;
    }

    /**
     * Sets goJail as true if the player has exceeded 3 consecutive rolls.
     *
     */
    public void goToJail() {
        goJail = numDouble > 2;
    }

    /**
     * Decides if the player can roll again,
     * if not they can't roll again and should go to jail.
     * 
     */
    public void rollAgain() {
        if (numDouble <= 2) {
            rollAgain = true;
        } else {
            rollAgain = false;
            goToJail();
        }
    }

    /**
     * Accesses and returns the field 'numDouble'.
     * 
     * @return int - the number of times a double has been rolled under the current player
     */
    public int getNumDouble() {
        return numDouble;
    }

    /**
     * Accesses and returns the total value of both dice.
     *
     * @return int - the total value of both dice
     */
    public int getTotalVal() {
        return totalVal;
    }

    /*
     * The methods below are for testing purposes only.
     */
    
    /**
     * FOR TESTING ONLY. Returns the value of dice0
     *
     * @return The value of dice0
     */
    public int getDiceVal0() {
        return diceVal0;
    }

    /**
     * FOR TESTING ONLY. Sets the value of dice0.
     *
     * @param diceVal0 The value to be set
     */
    public void setDiceVal0(int diceVal0) {
        this.diceVal0 = diceVal0;
    }

    /**
     * FOR TESTING ONLY. Returns the value of dice1.
     *
     * @return The value of dice1
     */
    public int getDiceVal1() {
        return diceVal1;
    }

    /**
     * FOR TESTING ONLY. Sets the value of dice1.
     *
     * @param diceVal1 The value to be set to dice1
     */
    public void setDiceVal1(int diceVal1) {
        this.diceVal1 = diceVal1;
    }

    /**
     * FOR TESTING ONLY. Sets the total value of both dice.
     *
     * @param totalVal the total value of both Dice
     */
    public void setTotalVal(int totalVal) {
        this.totalVal = totalVal;
    }

    /**
     * FOR TESTING ONLY. Sets the number of doubles rolled in a turn by a single player
     *
     * @param numDouble The number of doubles to be set
     */
    public void setNumDouble(int numDouble) {
        this.numDouble = numDouble;
    }

    /**
     * FOR TESTING ONLY. Same logic as roll dice method, but the value of each dice can be set manually
     *
     * @param x The value of dice0
     * @param y The value of dice1
     */
    public void setRollDice(int x, int y) {
        if (rollAgain) {
            diceVal0 = x;
            diceVal1 = y;
            if (diceVal0 == diceVal1) {
                numDouble += 1;
                rollAgain();
            } else {
                rollAgain = false;
            }
            totalValue();
        }
    }
}
