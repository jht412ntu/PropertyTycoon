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
    protected ArrayList<Property> Properties = new ArrayList<Property>();

    public enum Token {
        boot,smartphone,goblet,hatstand,cat,spoon;
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
            CentralControl.board.getJail().put(this);
        } else if (CentralControl.board.getJail().inJail(this) > 0) {
            CentralControl.board.getJail().pass(this);
        } else {
            CentralControl.dices.rollDice();
            location += CentralControl.dices.getTotalVal();
            if (location > 40) {
                passGo = true;
                CentralControl.bank.distributeCash(this,200);
                location -= 40;
            }
            else if (location == 21) {
                CentralControl.board.getPark().collectFine(this);
            }
        }
    }

    /**
     * @author: Mingfeng
     * @methodsName: payReleased
     * @description: pay 50$ for releasing and add money to Park
     */
    public void payReleased() {
        money -= 50;
        CentralControl.board.getJail().release(this);
        CentralControl.board.getPark().addFine(50);
    }

    /**
     * @author: Mingfeng
     * @methodsName: released
     * @description: release yourself from jail
     */
    public void released() {
        if (releaseCard > 0) {
            releaseCard -= 1;
            CentralControl.board.getJail().release(this);
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
     * @return	ArrayList<Property>, Properties this player owns
     * @methodsName: buyProperty
     * @description: add a house in your houses list
     */
    public ArrayList<Property> buyProperty(Property property) {
        Properties.add(property);
        return Properties;
    }

    /**
     * @param property
     * @author: Mingfeng
     * @return	ArrayList<Property>, Properties this player owns
     * @methodsName: sellProperty
     * @description: remove a house in your houses list
     */
    public ArrayList<Property> sellProperty(Property property) {
        Properties.remove(property);
        return Properties;
    }

    /*
     * field getter and setter
     */

    /**
     *
     * @return int
     */

    public int getMoney() {
        return money;
    }

    /**
     *
     * @param money
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     *
     * @return int
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
     * @return boolean
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
     * @return Token
     */
    public Token getToken() {
        return token;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }
    public ArrayList<Property> propertiesList() {
        return Properties;
    }


    /**
     *
     *
     */
    public void collectrent(Property p ,Player payer){
        if(payer.getLocation()==p.location && p.undermortgage!=true){
            payer.addMoney(-p.getRent());
            addMoney(p.getRent());

        }
    }

    public void payrent(Property p,Player reciever){
        if(getLocation()==p.location && p.undermortgage!=true){
            addMoney(-p.getRent());
            reciever.addMoney(p.getRent());


        }
    }}