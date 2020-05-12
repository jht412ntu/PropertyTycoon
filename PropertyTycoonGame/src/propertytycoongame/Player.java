package propertytycoongame;

import java.util.ArrayList;

/**
 * Player
 *
 * Enum tokens can only be choosen from a limited range Players' behaviours in the game
 * eg: roll dices, buy.
 * 
 * Documented by Haotian Jiao
 * 
 * @author Mingfeng
 */
public class Player {

    private int money = 1500;
    private int location = 0;
    private Token token;
    private boolean passGo = false;
    private String name;
    private int releaseCard;
    protected ArrayList<Property> Properties = new ArrayList<>();
    protected int totalvalue;
    /**
     * Limited range of the Tokens.
     */
    public enum Token {
        boot,
        smartphone,
        goblet,
        hatstand,
        cat,
        spoon;
    }

    /**
     * Constructor for Player.
     *
     * @param name Name of the player
     * @param token Token of the player
     * @throws DuplicateException
     */
    public Player(String name, Token token) throws DuplicateException {
        for (Player player : CentralControl.players) {
            if (name.equals(player.getName())) {
                throw new DuplicateException(" name: " + name);
            }
        }
        for (Player player : CentralControl.players) {
            if (token.equals(player.getToken())) {
                throw new DuplicateException(" token: " + token);
            }
        }
        this.name = name;
        this.token = token;
    }

    /**
     * Player rolls dices and acts as the rules required.
     * 
     * author: Mingfeng
     */
    public void rollDices() {
        if (CentralControl.dices.getNumDouble() > 2) {
            CentralControl.board.getJail().put(this);
        } else if (CentralControl.board.getJail().contains(this) && CentralControl.board.getJail().turnInJail(this) > 0) {
            CentralControl.board.getJail().pass(this);
        } else {
            CentralControl.dices.rollDice();
            int tLocation = location + CentralControl.dices.getTotalVal();
            if (tLocation > 40) {
                passGo = true;
                CentralControl.bank.distributeCash(this, 200);
                tLocation -= 40;
                CentralControl.bank.addBalance(-200);
            }
            location = tLocation;
            Cell currentCell = CentralControl.board.getCell(this.getLocation());

            // take action based on cell landed
            // land on the property
            if (Property.class.isInstance(currentCell)) {
                Property p = (Property) currentCell;
                Player owner = p.getOwner();
                if (owner != null && !owner.equals(this)) {
                    payRent(p, owner);
                } else 
						;//  buy or bid (GUI)
            } // PotluckCard cell
            else if (PotluckCard.class.isInstance(currentCell)) {
                ((PotluckCard) currentCell).action(this);
            } //OpportunityknockCard cell
            else if (OpportunityknockCard.class.isInstance(currentCell)) {
                ((OpportunityknockCard) currentCell).action(this);
            } else {
                switch (location) {
                    //park fine collection cell
                    case 21:
                        CentralControl.board.getPark().collectFine(this);
                        break;
                    //income tax
                    case 5:
                        try {
                            minusMoney(200);
                        } catch (LackMoneyException e) {
                            if (raiseMoney(200 - money)) {
                                money -= 200;
                            } else {
                                CentralControl.leaveGame();
                            }
                        }
                        break;
                    //go to Jail
                    case 31:
                        CentralControl.board.getJail().put(this);
                        location = 11;
                        break;
                }
            }
        }
    }

    /**
     * Pays 50$ for releasing and add money to Park.
     * 
     * author: Mingfeng
     * 
     * @return boolean - if the action succeed
     * @throws LackMoneyException
     */
    public boolean payReleased() throws LackMoneyException {
        if (money > 50) {
            minusMoney(50);
        } else {
            throw new LackMoneyException("Operation failed without enough money");
        }
        CentralControl.board.getJail().release(this);
        CentralControl.board.getPark().addFine(50);
        return true;
    }

    /**
     * Releases the player from jail.
     * 
     * author: Mingfeng
     * 
     * @return boolean - if the action succeed
     */
    public boolean released() {
        if (releaseCard > 0) {
            releaseCard -= 1;
            CentralControl.board.getJail().release(this);
            return true;
        }
        return false;
    }

    /**
     * Adds a realeasd card in your hand.
     * 
     * author: Mingfeng
     */
    public void addReleaseCard() {
        releaseCard += 1;
    }

    /**
     * Adds a house in your houses list.
     * 
     * author: Mingfeng
     * 
     * @param property The property that needs to buy
     * @return	ArrayList - properties this player owns
     */
    public ArrayList<Property> buyProperty(Property property) {
        this.Properties.add(property);
        property.setAvailable(false);

        return Properties;
    }

    /**
     * Removes a house in your houses list.
     * 
     * author: Mingfeng
     * 
     * @param property The property that need to be sold
     * @return	ArrayList - properties this player owned
     */
    public ArrayList<Property> sellProperty(Property property) {
        Properties.remove(property);
        return Properties;
    }

    /**
     * Trading mechanic: selling properties between players used by the GUI
     * when players defined an agreed price for a property.
     *
     * author: Haotian Jiao
     * date: 20/04/2020
     *
     * @param player The instance of Player
     * @param p The instance of Property
     * @param price A defined price(agreed between players)
     * @throws LackMoneyException
     */
    public void sellPropertyToPlayer(Player player, Property p, int price) throws LackMoneyException {
        sellProperty(p);
        player.buyProperty(p);
        p.changeOwner(player);
        player.minusMoney(price);
        addMoney(price);
    }

    /**
     * Sells something for raising money.
     * 
     * author: Mingfeng
     * 
     * @param difference The least money that need to be raised
     * @return	boolean - wheather player raises money succeed
     */
    public boolean raiseMoney(int difference) {
        // should include "sellPropertyToPlayer"
        // need GUI finished (when player cannot pay rent, the mortage button and sell button will be highlighted
        // player is unable to act other behaviours but leave the game
        boolean enough = false;
        while (!enough) {
            int choose = 0;
            switch (choose) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    enough = money >= difference;
            }
        }
        return enough;
    }

    /**
     * Pays a property's rent to a player.
     * 
     * @param p The Property instance that need to be paid
     * @param reciever The Player instance that need to be paid
     */
    public void payRent(Property p, Player reciever) {
        if (CentralControl.board.getJail().turnInJail(reciever) == 0 && p.undermortgage != true) {
            try {
                minusMoney(p.getRent());
            } catch (LackMoneyException e) {
                if (raiseMoney(p.getRent() - money)) {
                    payRent(p, reciever);
                } else {
                    CentralControl.leaveGame();
                }
            }
            reciever.addMoney(p.getRent());
        }
    }

    /**
     * Accesses and returns the balance of the player.
     * 
     * @return int - the money the player has
     */
    public int getMoney() {
        return money;
    }

    /**
     * Adds money to the balance.
     * 
     * @param money The value of money
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * Subtracts money from the balance.
     * 
     * @param money The money that need to be subtracted
     * @throws propertytycoongame.LackMoneyException
     */
    public void minusMoney(int money) throws LackMoneyException {
        if (this.money - money < 0) {
            throw new LackMoneyException("lack money");
        }
        this.money -= money;
    }

    /**
     * Accesses and returns the current location.
     * 
     * @return int - the position number
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets location.
     * 
     * @param location - the position number that need to be set
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Accesses and returns the field 'passGo'.
     * 
     * @return boolean - if the player has passed GO.
     */
    public boolean isPassGo() {
        return passGo;
    }

    /**
     * Sets the player passed GO or not.
     * 
     * @param passGo - true and false
     */
    public void setPassGo(boolean passGo) {
        this.passGo = passGo;
    }

    /**
     * Accesses and returns the token that the player has assigned.
     * 
     * @return Token - the token in the Token range list
     */
    public Token getToken() {
        return token;
    }

    /**
     * Accesses and returns the name of the player.
     * 
     * @return String - the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Accesses and returns a list of properties the player has.
     * 
     * @return ArrayList - the list of Property
     */
    public ArrayList<Property> getPropertiesList() {
        return Properties;
    }

}
