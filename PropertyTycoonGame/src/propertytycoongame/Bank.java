package propertytycoongame;

import java.util.ArrayList;

/**
 * Property Tycoon Game Bank
 * 
 * Class that provides functionality for banking.
 * 
 * @author Haotian Jiao
 * @version 1.0.0
 */
public class Bank {
    private int balance;
    private ArrayList<Property> properties;
    
    public Bank() {
        balance = 50000; // initial balance
        properties = new ArrayList<>();
        
    }

    /**
     * Adds property to the property list
     * 
     * @param p A Property instance
     */
    public void addProperty (Property p) {
        properties.add(p);
    }
    
    /**
     * Sells a property when the property is available and player has enough money
     * 
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when a property is not available
     */
    public void buyProperty(Player player, Property p) throws PropertyException {
        if (player.getMoney() >= p.getCost()) {
            p.sell(player);
            player.setMoney(player.getMoney() - p.getCost());
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
    public void sellProperty (Player player, Property p) throws PropertyException {
        if (p.getOwner() == player) {
            player.setMoney(player.getMoney() + p.getCost());
            balance -= p.getCost();
            player.sellProperty(p);
        } else {
            throw new PropertyException("The player does not own this property.");
        }
    }
    
    /**
     * Builds a house when a player owned all the properties in a group and has enough money
     * 
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when "maximum houses exceeded", "player does not owned all properties in a group" and "the player is not the owner"
     * @throws BankException Throwing when the player does not have enough money
     */
    public void buildHouse (Player player, Property p) throws PropertyException, BankException {
        String currentGroup = p.getGroup();
        if (p.getOwner() == player) {
            if (checkPermission(player, p)) {
                if (p.getNumOfHouse() == 4) {
                    throw new PropertyException("Maximum houses exceeded. If you wanted to build a hotel?");
                } else {
                    if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                        checkEnoughMoney(player, 50);
                    } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                        checkEnoughMoney(player, 100);
                    } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                        checkEnoughMoney(player, 150);
                    } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                        checkEnoughMoney(player, 200);
                    }
                }
            } else {
                throw new PropertyException("Player should own all properties in the group before building a house.");
            }
        } else {
            throw new PropertyException("The player does not own this property.");
        }
    }

    /**
     * Buys a house when a player has one.
     * 
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when "a property has none house" and "they are not the owner"
     */
    public void sellHouse (Player player, Property p) throws PropertyException {
        String currentGroup = p.getGroup();
        if (p.getOwner().equals(player)) {
            if (p.getNumOfHouse() > 0) {
                if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                    balance -= 50;
                    player.setMoney(player.getMoney() + 50);
                } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                    balance -= 100;
                    player.setMoney(player.getMoney() + 100);
                } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                    balance -= 150;
                    player.setMoney(player.getMoney() + 150);
                } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                    balance -= 200;
                    player.setMoney(player.getMoney() + 200);
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
     * Builds a hotel when a property has 4 houses and the player has enough money
     * 
     * @param player A Player instance
     * @param p A Property instance
     * @throws PropertyException Throwing when "hotel already exists" and "not has four houses"
     * @throws BankException Throwing when the player does not have enough money
     */
    public void buildHotel (Player player, Property p) throws PropertyException, BankException {
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
     * @throws PropertyException Throwing when there isn't a hotel on the property
     */
    public void sellHotel (Player player, Property p) throws PropertyException {
        String currentGroup = p.getGroup();
        if (p.ifHotelBuilded()) {
            if (currentGroup.equals("Brown") || currentGroup.equals("Blue")) {
                balance -= 50;
                player.setMoney(player.getMoney() + 50);
                p.sellHotel();
            } else if (currentGroup.equals("Purple") || currentGroup.equals("Orange")) {
                balance -= 100;
                player.setMoney(player.getMoney() + 100);
                p.sellHotel();
            } else if (currentGroup.equals("Red") || currentGroup.equals("Yellow")) {
                balance -= 150;
                player.setMoney(player.getMoney() + 150);
                p.sellHotel();
            } else if (currentGroup.equals("Green") || currentGroup.equals("Deep blue")) {
                balance -= 200;
                player.setMoney(player.getMoney() + 200);
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
    public boolean checkPermission (Player player, Property p) {
        boolean permitted = false;
        for (Property pp:properties){
            if (pp.getGroup().equals(p.getGroup())) {
                if (pp.getOwner() == player) {
                    permitted = true;
                } else {
                    permitted = false;
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
            player.setMoney(player.getMoney() - amount);
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
    public void distributeCash (Player player, int amount) { // 1500 initial distribution and 200 when passes GO
        player.setMoney(player.getMoney() + amount);
        balance -= amount;
    }
    
    /**
     * // NEED TO BE FINISHED
     * @param p A Property instance
     * @throws PropertyException 
     */
    public void bid(Property p) throws PropertyException {
        if (p.isAvailable()) {
            // NEED TO BE FINISHED
        } else {
            throw new PropertyException("The property has been sold.");
        }
    }
}
