package propertytycoongame;

import java.util.ArrayList;
import java.util.Map;

/**
 * Property Tycoon Game Bank
 *
 * Class that provides functionality for banking.
 *
 * @author Haotian Jiao
 * @version 1.0.2
 */
public class Bank {

    private int balance;
    private ArrayList<Property> properties;
    private int maxOffer;
    private int sameMaxOffer;
    private Player currentBidder;
    private Player sameOfferBidder;
    private boolean onAuction;
    private int brownHousesCanBeBuild;
    private int blueHousesCanBeBuild;
    private int purpleHousesCanBeBuild;
    private int orangeHousesCanBeBuild;
    private int redHousesCanBeBuild;
    private int yellowHousesCanBeBuild;
    private int greenHousesCanBeBuild;
    private int deepblueHousesCanBeBuild;

    public Bank() {
        balance = 50000; // initial balance
        properties = new ArrayList<>();
        maxOffer = 0;
        currentBidder = null;
        brownHousesCanBeBuild = 0;
        blueHousesCanBeBuild = 0;
        purpleHousesCanBeBuild = 0;
        orangeHousesCanBeBuild = 0;
        redHousesCanBeBuild = 0;
        yellowHousesCanBeBuild = 0;
        greenHousesCanBeBuild = 0;
        deepblueHousesCanBeBuild = 0;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the Banks balance.
     *
     * @param balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Adds property to the property list
     *
     * @param p A Property instance
     */
    public void addProperty(Property p) {
        properties.add(p);
    }

    /**
     * Adds money to Banks balance.
     *
     * @param money The value to be added to the bank balance.
     */
    public void addBalance(int money) {
        balance += money;
    }

    /**
     * Sells a property when the property is available and player has enough
     * money
     *
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when a property is not available
     */
    public void buyProperty(Player player, Property p) throws PropertyException {
        if (player.getMoney() >= p.getCost()) {
            p.sell(player);
            player.addMoney(-p.getCost());
            balance += p.getCost();
            player.buyProperty(p);
        }
    }

    /**
     * Buys a property when a player want to sell it
     *
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when a player is not the owner
     */
    public void sellProperty(Player player, Property p) throws PropertyException {
        if (p.getOwner() == player) {
            player.addMoney(p.getCost());
            balance -= p.getCost();
            player.sellProperty(p);
            p.changeOwner(null);
        } else {
            throw new PropertyException("The player does not own this property.");
        }
    }

    /**
     * Builds a house when a player owned all the properties in a group and has
     * enough money
     *
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when "maximum houses exceeded",
     * "player does not owned all properties in a group" and "the player is not
     * the owner"
     * @throws BankException Throwing when the player does not have enough money
     */
    public void buildHouse(Player player, Property p) throws PropertyException, BankException {
        String currentGroup = p.getGroup();
        if (p.getOwner() == player) {
            if (checkPermission(player, p)) {
                if (p.getNumOfHouse() == 4) {
                    throw new PropertyException("Maximum houses exceeded. If you wanted to build a hotel?");
                } else {
                    if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                        checkEnoughMoney(player, 50);
                        switch (currentGroup) {
                            case "Brown":
                                changePermittedHouses("Brown");
                                if (p.getNumOfHouse() == brownHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                            case "Blue":
                                changePermittedHouses("Blue");
                                if (p.getNumOfHouse() == blueHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                        }
                    } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                        checkEnoughMoney(player, 100);
                        switch (currentGroup) {
                            case "Purple":
                                changePermittedHouses("Purple");
                                if (p.getNumOfHouse() == purpleHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                            case "Orange":
                                changePermittedHouses("Orange");
                                if (p.getNumOfHouse() == orangeHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                        }
                    } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                        checkEnoughMoney(player, 150);
                        switch (currentGroup) {
                            case "Red":
                                changePermittedHouses("Red");
                                if (p.getNumOfHouse() == redHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                            case "Yellow":
                                changePermittedHouses("Yellow");
                                if (p.getNumOfHouse() == yellowHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                        }
                    } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                        checkEnoughMoney(player, 200);
                        switch (currentGroup) {
                            case "Green":
                                changePermittedHouses("Green");
                                if (p.getNumOfHouse() == brownHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                            case "Deep blue":
                                changePermittedHouses("Deep blue");
                                if (p.getNumOfHouse() == blueHousesCanBeBuild) {
                                    throw new PropertyException("You should build house on other properties before doing this.");
                                }
                        }
                    }
                    p.buildHouse();
                }
            } else {
                throw new PropertyException("Player should own all properties in the group before building a house.");
            }
        } else {
            throw new PropertyException("The player does not own this property.");
        }
    }

    /**
     * Changes the number of current houses can be built on one specific group
     * when all properties in the group has the same houses
     *
     * added on 23/04/2020 by Haotian Jiao
     *
     * @param group The specific property group
     */
    public void changePermittedHouses(String group) {
        Boolean permission = false;
        int housesCanBeBuild = 0;
        switch (group) {
            case "Brown":
                housesCanBeBuild = brownHousesCanBeBuild;
            case "Blue":
                housesCanBeBuild = blueHousesCanBeBuild;
            case "Purple":
                housesCanBeBuild = purpleHousesCanBeBuild;
            case "Orange":
                housesCanBeBuild = orangeHousesCanBeBuild;
            case "Red":
                housesCanBeBuild = redHousesCanBeBuild;
            case "Yellow":
                housesCanBeBuild = yellowHousesCanBeBuild;
            case "Green":
                housesCanBeBuild = greenHousesCanBeBuild;
            case "Deep blue":
                housesCanBeBuild = deepblueHousesCanBeBuild;
        }
        for (Property pp : properties) {
            if (pp.getGroup().equals(group)) {
                if (pp.getNumOfHouse() == housesCanBeBuild + 1) {
                    permission = true;
                } else {
                    permission = false;
                    break;
                }
            }
        }
        if (permission) {
            housesCanBeBuild += 1;
            switch (group) {
                case "Brown":
                    brownHousesCanBeBuild = housesCanBeBuild;
                case "Blue":
                    blueHousesCanBeBuild = housesCanBeBuild;
                case "Purple":
                    purpleHousesCanBeBuild = housesCanBeBuild;
                case "Orange":
                    orangeHousesCanBeBuild = housesCanBeBuild;
                case "Red":
                    redHousesCanBeBuild = housesCanBeBuild;
                case "Yellow":
                    yellowHousesCanBeBuild = housesCanBeBuild;
                case "Green":
                    greenHousesCanBeBuild = housesCanBeBuild;
                case "Deep blue":
                    deepblueHousesCanBeBuild = housesCanBeBuild;
            }
        }

    }

    /**
     * Buys a house when a player has one.
     *
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when "a property has none house" and
     * "they are not the owner"
     */
    public void sellHouse(Player player, Property p) throws PropertyException {
        String currentGroup = p.getGroup();
        if (p.getOwner().equals(player)) {
            if (p.getNumOfHouse() > 0) {
                if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                    balance -= 50;
                    player.addMoney(50);
                } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                    balance -= 100;
                    player.addMoney(100);
                } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                    balance -= 150;
                    player.addMoney(150);
                } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                    balance -= 200;
                    player.addMoney(200);
                }
                p.sellHouse();
            } else {
                throw new PropertyException("This property does not have any house.");
            }
        } else {
            throw new PropertyException("The player does not owned this property.");
        }
    }

    /**
     * Builds a hotel when a property has 4 houses and the player has enough
     * money
     *
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when "hotel already exists" and "not
     * has four houses"
     * @throws BankException Throwing when the player does not have enough money
     */
    public void buildHotel(Player player, Property p) throws PropertyException, BankException {
        String currentGroup = p.getGroup();
        if (p.ifHotelBuilded()) {
            throw new PropertyException("Hotel already exists. Each property can only has one hotel.");
        } else {
            if (p.getNumOfHouse() == 4) {
                if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                    checkEnoughMoney(player, 50);
                    p.buildHotel();
                } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                    checkEnoughMoney(player, 100);
                    p.buildHotel();
                } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                    checkEnoughMoney(player, 150);
                    p.buildHotel();
                } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                    checkEnoughMoney(player, 200);
                    p.buildHotel();
                }
            } else {
                throw new PropertyException("Four houses needed before building a hotel.");
            }
        }
    }

    /**
     * Buys a hotel when a player has one
     *
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when there isn't a hotel on the
     * property
     */
    public void sellHotel(Player player, Property p) throws PropertyException {
        String currentGroup = p.getGroup();
        if (p.ifHotelBuilded()) {
            if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                balance -= 50;
                player.addMoney(50);
                p.sellHotel();
            } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                balance -= 100;
                player.addMoney(100);
                p.sellHotel();
            } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                balance -= 150;
                player.addMoney(150);
                p.sellHotel();
            } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                balance -= 200;
                player.addMoney(200);
                p.sellHotel();
            }
        } else {
            throw new PropertyException("The property does not have a hotel.");
        }
    }

    /**
     * Checks if a player can build house(owned all properties in a group)
     *
     * @param player A Player instance
     * @param p A Property instance
     * @return if the player can build house or not
     */
    public boolean checkPermission(Player player, Property p) {
        boolean permitted = false;
        for (Property pp : properties) {
            if (pp.getGroup().equals(p.getGroup())) {
                if (pp.getOwner() == player) {
                    permitted = true;
                } else {
                    permitted = false;
                    break;
                }
            }
        }
        return permitted;
    }

    /**
     * Checks if a player has enough amount of money
     *
     * @param player A Player instance
     * @param amount Set amount
     * @throws BankException Throwing when the player does not have enough money
     */
    public void checkEnoughMoney(Player player, int amount) throws BankException {
        if (player.getMoney() >= amount) {
            player.addMoney(-amount);
            balance += amount;
        } else {
            throw new BankException("The player does not have enough money to build.");
        }
    }

    /**
     * Distributes amount of cash to certain player
     *
     * @param player A Player instance
     * @param amount Set amount
     */
    public void distributeCash(Player player, int amount) { // 1500 initial distribution and 200 when passes GO
        player.addMoney(amount);
        balance -= amount;
    }

    public void startAuction() {
        onAuction = true;
    }

    public boolean isOnAuction() {
        return onAuction;
    }

    /**
     * Accepts new offer and refuses low offer also keeps the current winner
     *
     * @param p A Property instance
     * @param player Current bidder
     * @param offer Current offer
     * @throws PropertyException
     */
    public void bid(Property p, Player player, int offer) throws PropertyException {
        if (p.isAvailable()) {
            if (currentBidder == null) {
                if (player.getMoney() >= offer) {
                    maxOffer = offer;
                    currentBidder = player;
                }
            } else {
                if (offer > maxOffer) {
                    if (player.getMoney() >= offer) {
                        maxOffer = offer;
                        sameMaxOffer = 0;
                        currentBidder = player;
                        sameOfferBidder = null;
                    }
                } else if (offer == maxOffer) {
                    sameMaxOffer = offer;
                    sameOfferBidder = player;
                }
            }
        } else {
            throw new PropertyException("The property has been sold.");
        }
    }

    public Player getCurrentBidder() {
        return currentBidder;
    }

    public int getMaxOffer() {
        return maxOffer;
    }
}
