package propertytycoongame;

/**
 * Property Tycoon Game Property
 * 
 * Class that provides functionality to access and manage a property.
 * 
 * @author Haotian Jiao
 * @version 1.0.0
 */
public class Property {
    private final String name;
    private final String group;
    private final int cost;
    private final int rent;
    private int improvedRent;
    private final int[] improvedRents;
    private boolean available;
    private boolean hotelBuilded;
    private int numOfHouse;
    private int status; // 1: one house 2: two houses 3: three houses 4: four houses 5: a hotel
    private Player owner;
    
    public Property(String property, String group, int cost, int rent, int oneHouseRent, int twoHousesRent, int threeHousesRent, int fourHousesRent, int hotelRent) {
        name = property;
        this.group = group;
        this.cost = cost;
        this.rent = rent;
        improvedRent = 0;
        improvedRents = new int[4];
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
     * Sells the property to a player
     * 
     * @param player
     * @throws PropertyException 
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
     * Checks if the property is unsold
     * 
     * @return if the property is unsold (boolean)
     */
    public boolean isAvailable() {
        return available;
    }
    
    /**
     * Gets the rent of the property
     * 
     * @return the rent in integer
     */
    public int getRent() {
        if (improvedRent == 0) {
            return rent;
        } else {
            return improvedRent;
        }
    }
    
    /**
     * Gets the initial cost of the property
     * 
     * @return the cost of the property in integer
     */
    public int getCost() {
        return cost;
    }
    
    /**
     * Gets the owner of the property
     * 
     * @return the instance of the player with type Player
     */
    public Player getOwner() {
        return owner;
    }
    
    /**
     * Gets the group of the property
     * 
     * @return the group of the property in String
     */
    public String getGroup() {
        return group;
    }
    
    /**
     * Gets the number of house on the property
     * 
     * @return the number of house in integer
     */
    public int getNumOfHouse() {
        return numOfHouse;
    }

    /**
     * Check if a hotel has built on the property
     * 
     * @return if hotel has built (boolean)
     */
    public boolean ifHotelBuilded() {
        return hotelBuilded;
    }

    /**
     * Builds a house on the property and 
     * change the current rent
     * 
     */
    public void buildHouse() {
        numOfHouse += 1;
        improvedRent = improvedRents[numOfHouse - 1];
        setStatus();
    }
    
    /**
     * Sells a house on the property and 
     * change the current rent
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
     * Builds a hotel on the property and 
     * change the current rent and 
     * number of house
     * 
     */
    public void buildHotel() {
        numOfHouse = 0;
        hotelBuilded = true;
        improvedRent = improvedRents[4];
        setStatus();
    }
    
    /**
     * Sells a hotel on the property and 
     * recover the houses and 
     * change the current rent
     */
    public void sellHotel() {
        numOfHouse = 4;
        hotelBuilded = false;
        improvedRent = improvedRents[3];
        setStatus();
    }
    
    /**
     * Changes the owner of the property
     * 
     * @param player A Player instance
     */
    public void changeOwner(Player player) {
        owner = player;
    }
    
    /**
     * Sets the status of the property
     * 
     * 0: empty
     * 1: one house
     * 2: two houses
     * 3: three houses
     * 4: four houses
     * 5: a hotel
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
    
    
    // 1: one house 2: two houses 3: three houses 4: four houses 5: a hotel
    /**
     * Gets the status of the property
     * 
     * 0: an empty property
     * 1: one house
     * 2: two houses
     * 3: three houses
     * 4: four houses
     * 5: a hotel
     * 
     * @return the status of the property in integer
     */
    public int getStatus() {
        return status;
    }
    
}
