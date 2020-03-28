package propertytycoongame;

import java.util.ArrayList;

/**
 * Player
 *
 * Enum tokens can only be choosen in a limited range Players` behaviour in game eg: roll dices, buy
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
    private ArrayList<Property> ownedProperties = new ArrayList<Property>();

    /**
     *
     */
    public enum Token {

        /**
         *
         */
        boot,

        /**
         *
         */
        smartphone,

        /**
         *
         */
        goblet,

        /**
         *
         */
        hatstand,

        /**
         *
         */
        cat,

        /**
         *
         */
        spoon;
    }

    /**
     *
     * @param name
     * @param token
     */
    public Player(String name, Token token) {
        // TODO Auto-generated constructor stub
        this.name = name;
        this.token = token;
    }

    /**
     * @author: Mingfeng
     * @methodsName: rollDices
     * @description: Roll dices and check wheather play need to go to Jail
     */
    public void rollDices() {
        if (CentralControl.dices.getNumDouble() > 2) {
            CentralControl.jail.put(this);
        } else if (CentralControl.jail.inJail(this) > 0) {
            CentralControl.jail.pass(this);
        } else {
            CentralControl.dices.rollDice();
            location += CentralControl.dices.getTotalVal();
        }
    }

    /**
     * @author: Mingfeng
     * @methodsName: payReleased
     * @description: pay 50$ for releasing
     */
    public void payReleased() {
        money -= 50;
        CentralControl.jail.release(this);
    }

    /**
     * @author: Mingfeng
     * @methodsName: released
     * @description: release yourself from jail
     */
    public void released() {
        if (releaseCard > 0) {
            releaseCard -= 1;
            CentralControl.jail.release(this);
        }
    }

    /**
     * @author: Mingfeng
     * @methodsName: addAreleaseCard
     * @description: add a realeasd card in your hand
     */
    public void addAreleaseCard() {
        releaseCard += 1;
    }

    /**
     * @author: Mingfeng
     * @return
     * @methodsName: buyProperty
     * @description: add a house in your houses list
     */
    public ArrayList<Property> buyProperty(Property property) {
        ownedProperties.add(property);
        return ownedProperties;
    }

    /**
     * @param property
     * @author: Mingfeng
     * @return
     * @methodsName: sellProperty
     * @description: remove a house in your houses list
     */
    public ArrayList<Property> sellProperty(Property property) {
        ownedProperties.remove(property);
        return ownedProperties;
    }

    /*
	 * field getter and setter
     */

    /**
     *
     * @return
     */

    public int getMoney() {
        return money;
    }

    /**
     *
     * @param money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     *
     * @return
     */
    public int getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public boolean isPassGo() {
        return passGo;
    }

    /**
     *
     * @param passGo
     */
    public void setPassGo(boolean passGo) {
        this.passGo = passGo;
    }

    /**
     *
     * @return
     */
    public Token getToken() {
        return token;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

}
