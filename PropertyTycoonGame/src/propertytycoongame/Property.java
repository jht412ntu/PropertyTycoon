package propertytycoongame;

/**
 * Property
 *
 * Class that provides functionality to access and manage a property.
 *
 * Documented by Haotian Jiao
 *
 * @author Haotian Jiao
 * @version 1.1.0
 */
public class Property extends Cell implements Comparable<Property> {

    protected final String name;
    private final String group;
    private final int cost;
    private int rent;
    private int improvedRent;
    private final int[] improvedRents;
    private boolean available;
    private boolean hotelBuilded;
    private int numOfHouse;
    private int status; // 1: one house 2: two houses 3: three houses 4: four houses 5: a hotel
    private Player owner;
    protected boolean undermortgage;//if property undermortage player cant collect money;

    /**
     * Constructor for Property
     *
     * @param location The property's cell location
     * @param propertyname The property's name
     * @param group The property's group
     * @param cost The property's cost
     * @param rent The property's initial rent
     * @param oneHouseRent The property's rent when a house has built
     * @param twoHousesRent The property's rent when two house has built
     * @param threeHousesRent The property's rent when three house has built
     * @param fourHousesRent The property's rent when four house has built
     * @param hotelRent The property's rent when a hotel has built
     */
    public Property(int location, String propertyname, String group, int cost, int rent, int oneHouseRent, int twoHousesRent, int threeHousesRent, int fourHousesRent, int hotelRent) {
        super(location);
        name = propertyname;
        this.group = group;
        this.cost = cost;
        this.rent = rent;
        improvedRent = 0;
        improvedRents = new int[5];
        improvedRents[0] = oneHouseRent;
        improvedRents[1] = twoHousesRent;
        improvedRents[2] = threeHousesRent;
        improvedRents[3] = fourHousesRent;
        improvedRents[4] = hotelRent;
        available = true;
        hotelBuilded = false;
        numOfHouse = 0;
        status = 0;
        owner = null;

    }

    /**
     * Accesses and returns the name of the property.
     *
     * @author Hayden
     * @return String - property's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sells the property to a player.
     *
     * @param player The player receiving the property
     * @throws PropertyException The property cannot be sold
     */
    public void sell(Player player) throws PropertyException {
        if (available) {
            owner = player;
            available = false;
        } else {
            throw new PropertyException("The property has been sold.");
        }
    }

    /**
     * Checks if the property is unsold.
     *
     * @return boolean - if the property is unsold (boolean)
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Accesses and returns the rent of the property.
     *
     * @return int - the rent in integer
     */
    public int getRent() {
        if (improvedRent == 0) {
            return rent;
        } else {
            return improvedRent;
        }
    }

    /**
     * Accesses and returns the initial cost of the property.
     *
     * @return int - the cost of the property in integer
     */
    public int getCost() {
        return cost;
    }

    /**
     * Accesses and returns the owner of the property.
     *
     * @return int - the instance of the player with type Player
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Accesses and returns the group of the property.
     *
     * @return String - the group of the property in String
     */
    public String getGroup() {
        return group;
    }

    /**
     * Accesses and returns the number of houses on the property.
     *
     * @return int - the number of houses in integer
     */
    public int getNumOfHouse() {
        return numOfHouse;
    }

    /**
     * Checks if a hotel has built on the property.
     *
     * @return boolean - if hotel has built (boolean)
     */
    public boolean ifHotelBuilded() {
        return hotelBuilded;
    }

    /**
     * Builds a house on the property and changes the current rent.
     *
     */
    public void buildHouse() {
        numOfHouse += 1;
        improvedRent = improvedRents[numOfHouse - 1];
        setStatus();
    }

    /**
     * Sells a house on the property and changes the current rent.
     *
     */
    public void sellHouse() {
        numOfHouse -= 1;
        if (numOfHouse == 0) {
            improvedRent = 0;
        } else {
            improvedRent = improvedRents[numOfHouse - 1];
        }
        setStatus();
    }

    /**
     * Builds a hotel on the property and change the current rent and number of house.
     *
     */
    public void buildHotel() {
        numOfHouse = 0;
        hotelBuilded = true;
        improvedRent = improvedRents[4];
        setStatus();
    }

    /**
     * Sells a hotel on the property and recovers the houses and changes the current rent.
     */
    public void sellHotel() {
        numOfHouse = 4;
        hotelBuilded = false;
        improvedRent = improvedRents[3];
        setStatus();
    }

    /**
     * Changes the owner of the property.
     *
     * @param player A Player instance
     */
    public void changeOwner(Player player) {
        owner = player;
    }

    /**
     * Sets the status of the property.
     *
     * 0: empty 1: one house 2: two houses 3: three houses 4: four houses 5: a hotel
     *
     */
    public void setStatus() {
        if (hotelBuilded) {
            status = 5;
        } else if (numOfHouse == 4) {
            status = 4;
        } else if (numOfHouse == 3) {
            status = 3;
        } else if (numOfHouse == 2) {
            status = 2;
        } else if (numOfHouse == 1) {
            status = 1;
        } else {
            status = 0;
        }
    }

    /**
     * Mortgages the property to the bank.
     *
     * Note: The player needs to land on the property to mortgage
     *
     * @param bank The bank paying out the mortgage
     * @param player The player mortgaging the property
     */
    public void mortgage(Bank bank, Player player) {
        if (player.getLocation() == super.getPosition()) {
            undermortgage = true;//when property under mortgage player can't collect rent
            bank.addBalance(-(cost / 2));
            player.addMoney(cost / 2);
        }
    }

    /**
     * Sets availability of a property.
     *
     * @param available if the property is available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Player resells the property to bank and property back to available.
     *
     * @param bank The Bank instance
     * @param player The player sells the property
     */
    public void liquidate(Bank bank, Player player) {
        player.getPropertiesList().remove(name);
        bank.addBalance(-(cost / 2));
        player.addMoney(cost / 2);
        owner = null;
        status = 0;
        available = true;
        undermortgage = false;
        numOfHouse = 0;
    }

    /**
     * Redeems the mortagaed property from bank
     *
     * author: Mingfeng
     *
     * @param player The Player instance
     * @throws LackMoneyException
     */
    public void redeemed(Player player) throws LackMoneyException {
        player.minusMoney(cost / 2);
        CentralControl.bank.addBalance(cost / 2);
        undermortgage = false;
    }

    /**
     * Initialises this property, no owner, no houses or hotels no mortgage.
     *
     * author: Mingfeng
     *
     */
    public void initilized() {
        owner = null;
        numOfHouse = 0;
        undermortgage = false;
        status = 0;
        hotelBuilded = false;
    }

    /**
     * Gets the status of the property
     *
     * 0: an empty property 1: one house 2: two houses 3: three houses 4: four houses 5: a hotel
     *
     * @return int - the status of the property in integer
     */
    public int getStatus() {
        return status;
    }

    /**
     * Compares the number of houses with other property.
     *
     * @param o The property that need to be compared
     * @return int - the number of differences
     */
    @Override
    public int compareTo(Property o) {
        int p1 = this.numOfHouse;
        int p2 = o.getNumOfHouse();
        if (this.ifHotelBuilded()) {
            p1 += 5;
        }
        if (o.ifHotelBuilded()) {
            p2 += 5;
        }
        if (this.numOfHouse > o.getNumOfHouse()) {
            return 1;
        }
        return 0;
    }
}
