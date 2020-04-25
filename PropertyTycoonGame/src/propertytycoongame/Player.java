package propertytycoongame;

import java.util.ArrayList;

/**
 * Player
 *
 * Enum tokens can only be choosen in a limited range Players` behaviour in game eg: roll dices, buy
 *
 * @author Mingfeng
 */
public class Player implements Comparable<Player> {

    private int money = 1500;
    private int location = 0;
    private Token token;
    private boolean passGo = false;
    private String name;
    private int releaseCard;
    protected ArrayList<Property> Properties = new ArrayList<>();
    private boolean leavedGame;
    protected int totalvalue;
    public enum Token {
        boot, smartphone, goblet, hatstand, cat, spoon;
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
        leavedGame = false;
    }

    /**
     * @author: Mingfeng
     * @methodsName: rollDices
     * @description: Roll dices and checks whether player needs to go to Jail
     */
    public void rollDices() {
        if (CentralControl.dices.getNumDouble() > 2) {
            CentralControl.board.getJail().put(this);
        } else if (CentralControl.board.getJail().turnInJail(this) > 0) {
            CentralControl.board.getJail().pass(this);
        } else {
            CentralControl.dices.rollDice();
            location += CentralControl.dices.getTotalVal();
            if (location > 40) {
                passGo = true;
                CentralControl.bank.distributeCash(this, 200);
                location -= 40;
                CentralControl.bank.addBalance(-200);
            }
            switch (location) {
                //park fine collection
                case 21:
                    CentralControl.board.getPark().collectFine(this);
                    break;
                //income tax	
                case 5:
                    try {
                        minusMoney(200);
                    } catch (LackMoneyException e) {
                        // TODO Auto-generated catch block
                        raiseMoney();
                    }
                    break;
                case 31:
                    CentralControl.board.getJail().put(this);
                    location = 11;
                    break;
                default:
                    Property p = (Property) CentralControl.board.theboard.get(location);
                    Player owner = p.getOwner();
                    payRent(p, owner);
            }
        }
    }

    /**
     * @return @author: Mingfeng
     * @throws LackMoneyException
     * @methodsName: payReleased
     * @description: pay 50$ for releasing and add money to Park
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
     * @return @author: Mingfeng
     * @methodsName: released
     * @description: release yourself from jail
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
     * @author: Mingfeng
     * @methodsName: addReleaseCard
     * @description: add a realeasd card in your hand
     */
    public void addReleaseCard() {
        releaseCard += 1;
    }

    /**
     * @param property
     * @author: Mingfeng
     * @return	ArrayList, Properties this player owns
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
     * @return	ArrayList, Properties this player owns
     * @methodsName: sellProperty
     * @description: remove a house in your houses list
     */
    public ArrayList<Property> sellProperty(Property property) {
        Properties.remove(property);
        return Properties;
    }

    /**
     * Trading mechanic: selling properties between players used by the GUI
     * when players defined an agreed price for a property
     *
     * author: Haotian Jiao
     * date: 20/04/2020
     *
     * @param player The instance of Player
     * @param p The instance of Property
     * @param price A defined price(agreed between players)
     */
    public void sellPropertyToPlayer(Player player, Property p, int price) {
        sellProperty(p);
        player.buyProperty(p);
        player.addMoney(-price);
        addMoney(price);
    }

    /**
     * @author: Mingfeng
     * @return	boolean wheather player raises money succeed
     * @methodsName: raiseMoney
     * @description: sell something for raising money
     */
    public boolean raiseMoney() {
        // should include "sellPropertyToPlayer"
        // need GUI finished (when player cannot pay rent, the mortage button and sell button will be hignlight
        // player is not abole to act other behaviours but leave game
        boolean enough = false;
        return enough;
    }

    /**
     *
     *
     * @param p
     * @param reciever
     */
    public void payRent(Property p, Player reciever) {
        if (CentralControl.board.getJail().turnInJail(reciever) == 0 && p.undermortgage != true) {
            try {
                minusMoney(p.getRent());
            } catch (LackMoneyException e) {
                if (raiseMoney()) {

                } else {
                    return;
                }
            }
            reciever.addMoney(p.getRent());
        }
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
     * @param money
     * @description: add money to balance
     */
    public void addMoney(int money) {
        this.money = money + this.money;
    }

    /**
     *
     * @param money
     * @description: minus money from balance
     */
    public void minusMoney(int money) throws LackMoneyException {
        if (this.money - money < 0) {
            throw new LackMoneyException("lack money");
        }
        this.money = this.money - money;
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

    @Override
    public int compareTo(Player o) {
        // TODO Auto-generated method stub
        if (this.getMoney() > o.getMoney()) {
            return 1;
        }
        return 0;
    }
    
    /**
     * For players to mark themselves as leaved
     * 
     * author: Haotian Jiao
     * date: 23/04/2020
     * 
     */
    public void setLeaveGame() {
        leavedGame = true;
    }
}
