package propertytycoongame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Agent
 *
 * Class that provides functionality for running a game agent.
 *
 * Documented by Haotian Jiao
 *
 * @author Mingfeng & Haotian Jiao
 */
public class Agent extends Player {

    /**
     * Constructor for Agent.
     * 
     * @param name The name of the agent
     * @param token A specific token
     * @throws DuplicateException
     */
    public Agent(String name, Token token) throws DuplicateException {
        super(name, token);
    }

    /**
     * The main function for running the agent.
     * 
     * author: Mingfeng
     * 
     * @throws propertytycoongame.PropertyException
     * @throws propertytycoongame.LackMoneyException
     */
    public void run() throws PropertyException, LackMoneyException {
        rollDices();
        Cell currentCell = CentralControl.board.getCell(this.getLocation());
        autoBuyProperty(currentCell);
        autoBuild();
        autoPayRent(currentCell);
        autoSelectCard(currentCell);
    }

    /**
     * The overrode rollDices method.
     * 
     * @methodsName:rollDices
     * @author: Mingfeng
     *
     * @description: roll dices
     */
    @Override
    public void rollDices() {
        if (CentralControl.board.getJail().turnInJail(this) == 2) // Use card or money to release itself
        {
            try {
                if (released() || payReleased()) {
                    rollDices();
                }
            } catch (LackMoneyException e) {
                CentralControl.board.getJail().pass(this);
            }
        } else {
            CentralControl.dices.rollDice();
        }
        int tLocation = getLocation() + CentralControl.dices.getTotalVal();
        if (tLocation > 40) {
            setPassGo(true);
            CentralControl.bank.distributeCash(this, 200);
            tLocation -= 40;
            CentralControl.bank.addBalance(-200);
        }
        setLocation(tLocation);
    }

    /**
     * Trys automatically to buy property in suitable condition(as much as possible).
     *
     * author: Mingfeng
     * 
     * @param currentCell The current landed cell
     * @throws PropertyException 
     */
    public void autoBuyProperty(Cell currentCell) throws PropertyException {
        if (Property.class.isInstance(currentCell)) {
            Property p = (Property) currentCell;
            CentralControl.bank.buyProperty(this, p);
        } else {
            return;
        }
    }

    /**
     * Trys automatically to buy house and hotel in suitable condition( money is greater than 400).
     * 
     * author: Mingfeng
     * 
     * @throws PropertyException, LackMoneyException
     */
    public void autoBuild() throws PropertyException, LackMoneyException {
        ArrayList<Property> propertiesBuild = searchPropertyBuild();
        while (getMoney() >= 400) {
            for (Property property : propertiesBuild) {
                if (getMoney() >= 400) {
                    if (property.getNumOfHouse() != 4) {
                        CentralControl.bank.buildHouse(this, property);
                    } else {
                        CentralControl.bank.buildHotel(this, property);
                    }
                }
            }
        }
    }

    /**
     * Searches properties that can be built.
     * 
     * author: Mingfeng
     * @return ArrayList - the properties list
     */
    public ArrayList<Property> searchPropertyBuild() {
        ArrayList<Property> propertiesBuild = new ArrayList<>();
        for (Property property : Properties) {
            if (CentralControl.bank.checkPermission(this, property)) {
                propertiesBuild.add(property);
            }
        }
        Collections.sort(propertiesBuild);
        return propertiesBuild;
    }

    /**
     * Pays the rent if the current cell is a property owned by a player.
     *
     * author: Haotian Jiao
     * date: 23/04/2020
     *
     * @param currentCell the cell of the current position
     */
    public void autoPayRent(Cell currentCell) {
        if (currentCell.getClass().toString().endsWith("Property")) {
            if (!((Property) currentCell).getOwner().equals(this) && ((Property) currentCell).getOwner() != null) {
                if (getMoney() >= ((Property) currentCell).getRent()) {
                    payRent(((Property) currentCell), ((Property) currentCell).getOwner());
                }
            }
        }
    }

    /**
     * Selects a card from Potluck or Opportunity.
     *
     * author: Haotian Jiao
     * date: 23/04/2020
     *
     * @param currentCell the cell of the current position
     */
    public void autoSelectCard(Cell currentCell) {
        if (currentCell.getClass().toString().endsWith("PotluckCard")) {
            ((PotluckCard) currentCell).action(this);
        } else if (currentCell.getClass().toString().endsWith("OpportunityknockCard")) {
            ((OpportunityknockCard) currentCell).action(this);
        }
    }

    /**
     * Generates an offer when an auction is on.
     *
     * author: Haotian Jiao
     * date: 23/04/2020
     *
     * @return int - an offer for the auction
     */
    public int autoAuction() {
        Bank bank = CentralControl.bank;
        Random rand = new Random();
        int oneOrZero = rand.nextInt(2);
        int offer = 0;
        if (bank.isOnAuction()) {
            if (bank.getCurrentBidder() == null) {
                if (oneOrZero == 1) {
                    offer = Math.round(getMoney() / 100 * 5); // the initial offer will be 5% of its total balance
                } else {
                    offer = 0;
                }
            } else {
                if (oneOrZero == 1) {
                    if (bank.getMaxOffer() <= getMoney() - 1) {
                        offer = bank.getMaxOffer() + 1;
                    } else {
                        offer = 0;
                    }
                } else {
                    offer = 0;
                }
            }
        }
        return offer;
    }
}
