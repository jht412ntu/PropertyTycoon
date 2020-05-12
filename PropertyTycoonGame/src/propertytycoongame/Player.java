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
    private ArrayList releaseCard;
    private ArrayList<Property> Properties;
    private int totalValue = 0;
    private int totalAssets = 0;

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
     * @throws propertytycoongame.CreatePlayerException
     */
    public Player(String name, Token token) throws CreatePlayerException {
        for (Player player : CentralControl.getPlayers()) {
            if (name.equals(player.getName())) {
                throw new CreatePlayerException("Duplicate name: " + name);
            }
        }
        for (Player player : CentralControl.getPlayers()) {
            if (token.equals(player.getToken())) {
                throw new CreatePlayerException("Duplicate token: " + token);
            }
        }
        this.name = name;
        this.token = token;
	Properties = new ArrayList<>();
        releaseCard = new ArrayList<>();
    }

    /**
     * Player rolls dices and acts as the rules required.
     *
     * author: Mingfeng
     */
    public void rollDices() {
        while (CentralControl.dices.rollAgain) {
            if (CentralControl.dices.getNumDouble() == 2) {
                CentralControl.board.getJail().put(this);
                break;
            } else if (CentralControl.board.getJail().turnInJail(this) > 0) {
                CentralControl.board.getJail().pass(this);
                break;
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

                // take action based on landed cell
                // land on the property
                if (Property.class.isInstance(currentCell)) {
                    Property p = (Property) currentCell;
                    Player owner = p.getOwner();
                    if (owner != null && !owner.equals(this)) {
                        payRent(p);
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
                                if (raiseMoney(200, null)) {
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
    }

    /**
     * Pays 50$ for releasing and add money to Park.
     *
     * author: Mingfeng
     *
     * @return boolean - if the action succeed
     * @throws LackMoneyException
     * @throws propertytycoongame.NotInJailException
     */
    public boolean payReleased() throws LackMoneyException, NotInJailException {
        if (CentralControl.board.getJail().turnInJail(this) == 0) {
            throw new NotInJailException("You are not in jail!");
        }
        if (money > 50) {
            minusMoney(50);
            CentralControl.board.getJail().release(this);
            CentralControl.board.getPark().addFine(50);
            CentralControl.nextPlayer();
            return true;
        } else {
            throw new LackMoneyException("Operation failed without enough money");
        }
    }

    /**
     * Releases the player from jail by free-prisoner card and
     * placed on the bottom of the corresponding pile.
     *
     * author: Mingfeng
     *
     * @return boolean - if the action succeed
     * @throws propertytycoongame.NotInJailException
     */
    public boolean released() throws NotInJailException {
        if (CentralControl.board.getJail().turnInJail(this) == 0) {
            throw new NotInJailException("You are not in jail!");
        }
        if (releaseCard.size() > 0) {
            if (PotluckCard.class.isInstance(releaseCard.remove(releaseCard.size() - 1))) {
                CentralControl.board.getPotluckCard().getShuffledQueue().add("Get out of jail free");
            } else {
                CentralControl.board.getOpportunityknockCard().shuffledqueue1.add("Get out of jail free");
            }
            CentralControl.board.getJail().release(this);
            return true;
        }
        //when the number of release card is 0, please set button unclickabel
        return false;
    }

    /**
     * Adds a realeasd card in your hand.
     *
     * author: Mingfeng
     * @param card
     */
    public void addReleaseCard(Cards card) {
        releaseCard.add(card);
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
     * If player does not have enough money,
     * initialize all his propety and give money to receiver.
     *
     * author: Mingfeng
     *
     * @param rent
     * @param reciever
     * @return	boolean - wheather player raises money succeed
     */
    public boolean raiseMoney(int rent, Player reciever) {
        // should include "sellPropertyToPlayer"
        // need GUI finished (when player cannot pay rent, the mortage button and sell button will be highlighted
        // player is unable to act other behaviours but leave the game
        // TO-DO
        boolean enough = false;
        if (calculateAssets() >= rent) {
            while (!enough) {
                int choose = 0;
                switch (choose) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        return false;
                    default:
                        enough = money >= rent;
                }
            }
        } else {
            // If it is not paid to bank
            if (reciever != null) {
                reciever.addMoney(calculateAssets());
            } else {
                CentralControl.bank.addBalance(calculateAssets());
            }
            for (Property property : Properties) {
                property.initilized();
            }
        }
        return enough;
    }

    /**
     * Pays a property's rent to a player.
     *
     * @param p The Property instance that need to be paid
     */
    public void payRent(Property p) {
        Player reciever = p.getOwner();
        if (CentralControl.board.getJail().turnInJail(reciever) == 0 && p.undermortgage != true) {
            try {
                minusMoney(p.getRent());
                reciever.addMoney(p.getRent());
            } catch (LackMoneyException e) {
                if (raiseMoney(p.getRent(), reciever)) {
                    payRent(p);
                } else {
                    CentralControl.leaveGame();
                }
            }
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
     * Adds money to balance.
     *
     * @return totalAssets int - the total assets
     */
    public int calculateAssets() {
        totalAssets = money;
        for (Property property : Properties) {
            if (property.undermortgage) {
                totalAssets += property.getCost();
            } else {
                totalAssets += property.getStatus() * CentralControl.bank.getHousePrice(property);
            }
        }
        return totalAssets;
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
     * Sets the money.
     *
     * @param money The value of the money
     */
    public void setMoeny(int money) {
        this.money = money;
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

    /**
     * Gets the total value.
     *
     * @return int - the total value
     */
    public int getTotalValue() {
        return totalValue;
    }

    /**
     * Gets the card size.
     *
     * @return int - the size of the releaseCard
     */
    public int getCardSize() {
        return releaseCard.size();
    }

    /**
     * Sets the total value.
     *
     * @param totalValue A value
     */
    public void setotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

}
