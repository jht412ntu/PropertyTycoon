package propertytycoongame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Agent extends Player {

    public Agent(String name, Token token) {
        super(name, token);
        // TODO Auto-generated constructor stub
    }

    /**
     * @throws propertytycoongame.PropertyException
     * @author: Mingfeng
     * @throws BankException
     * @methodsName: run
     * @description: run agent
     */
    public void run() throws PropertyException, LackMoneyException, BankException {
        Cell currentCell = CentralControl.board.getCell(this.getLocation());
        // Stay in Jail two turns
        if (CentralControl.board.getJail().turnInJail(this) == 2) // Use card or money to release itself
        {
            if (released() || payReleased()) {
                run();
            } else {
                CentralControl.board.getJail().pass(this);
            }
        } else {
            rollDices();
            autoBuyProperty();
            autoBuild();
            autoPayRent(currentCell);
            autoSelectCard (currentCell);
        }
    }

    /**
     * @author: Mingfeng
     * @throws PropertyException
     * @methodsName: autoBuyProperty
     * @description: try automatically to buy property in suitable condition(as much as possible)
     */
    public void autoBuyProperty() throws PropertyException {
        Property p = (Property) CentralControl.board.theboard.get(getLocation());
        if (p.isAvailable() == true && p.getOwner() == null && getMoney() >= p.getCost()) {
            CentralControl.bank.buyProperty(this, p);
        }
    }

    /**
     * @throws propertytycoongame.BankException
     * @author: Mingfeng
     * @throws PropertyException,BankException
     * @methodsName: autoBuildHouse
     * @description: try automatically to buy house and hotel in suitable condition( money is greater than 400)
     */
    public void autoBuild() throws PropertyException, BankException {
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
     * @author: Mingfeng
     * @return ArrayList
     * @methodsName: searchPropertyBuild
     * @description: search properties can be build
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
     * Pays the rent if the current cell is a property owned by a player
     * 
     * author: Haotian Jiao
     * date: 23/04/2020
     * 
     * @param currentCell the cell of the current position
     */
    public void autoPayRent(Cell currentCell) {
        if (currentCell.getClass().toString().endsWith("Property")) {
            if (!((Property)currentCell).getOwner().equals(this) && ((Property)currentCell).getOwner() != null) {
                if (getMoney() >= ((Property)currentCell).getRent()) {
                    payRent(((Property)currentCell), ((Property)currentCell).getOwner());
                } else {
                    if (raiseMoney()) {
                        autoPayRent(currentCell);
                    } else {
                        setLeaveGame(); //The player will be removed(by GUI via CentralControl) after the current turn.
                    }
                }
            }
        }
    }
    
    /**
     * Selects a card from Potluck or Opportunity
     * 
     * author: Haotian Jiao
     * date: 23/04/2020
     * 
     * @param currentCell the cell of the current position
     */
    public void autoSelectCard (Cell currentCell) {
        if (currentCell.getClass().toString().endsWith("PotluckCard")) {
            ((PotluckCard)currentCell).action(this, CentralControl.bank);
        } else if (currentCell.getClass().toString().endsWith("OpportunityknockCard")) {
            ((OpportunityknockCard)currentCell).action(this, CentralControl.bank);
        }
    }

    /**
     * Generates an offer when an auction is on
     * 
     * author: Haotian Jiao
     * date: 23/04/2020
     * 
     * @return an offer for the auction
     */
    public int autoAuction () {
        Bank bank = CentralControl.bank;
        Random rand = new Random();
        int oneOrZero = rand.nextInt(2);
        int offer = 0;
        if (bank.isOnAuction()) {
            if(bank.getCurrentBidder() == null) {
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
