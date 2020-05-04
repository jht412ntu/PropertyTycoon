package propertytycoongame;

import java.util.ArrayList;


/**
 * Player
 *
 * Enum tokens can only be choosen in a limited range Players` behaviour in game
 * eg: roll dices, buy
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
    private ArrayList<Property> Properties = new ArrayList<>();
    private int totalValue = 0;
    private int totalAssets = 0;

    public enum Token {
        boot, smartphone, goblet, hatstand, cat, spoon;
    }

    /** Constructor for class Player.
     *
     * @param name Name of the player
     * @param token Token of the player
     * @throws DuplicateException 
     */
    public Player(String name, Token token) throws CreatePlayerException {
        
    	for (Player player : CentralControl.players) {
			if (name.equals(player.getName())) {
				throw new CreatePlayerException("Duplicate name: " + name);
			}
		}
    	for (Player player : CentralControl.players) {
			if (token.equals(player.getToken())) {
				throw new CreatePlayerException("Duplicate token: " + token);
			}
		}
        this.name = name;
        this.token = token;
    }

    /**
     * @author: Mingfeng
     * @methodsName: rollDices
     * @description: Player rolls dices and acts as the rules require
     */
    public void rollDices() {
    	while (CentralControl.dices.rollAgain) {
    		if (CentralControl.dices.getNumDouble() == 2) {
                CentralControl.board.getJail().put(this);
    			break;
    		}
            else if (CentralControl.board.getJail().turnInJail(this) > 0) {
            	CentralControl.board.getJail().pass(this);
            	break;
            }
            else {
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
                		Property p = (Property)currentCell;
                    	Player owner = p.getOwner();
                    	if (owner != null && !owner.equals(this)) 
                        	payRent(p);
                    	else 
    						;//  buy or bid (GUI)
                }
                // PotluckCard cell
                else if (PotluckCard.class.isInstance(currentCell)) {
    				((PotluckCard)currentCell).action(this);
    			}
                //OpportunityknockCard cell
                else if (OpportunityknockCard.class.isInstance(currentCell)) {
                	((OpportunityknockCard)currentCell).action(this);
    			}
                else 
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
                            if (raiseMoney(200 - money)) 
    							money -= 200;
                            else
                            	CentralControl.leaveGame();
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
     * @return @author: Mingfeng
     * @throws LackMoneyException
     * @throws NotInJailException 
     * @methodsName: payReleased
     * @description: pay 50$ for releasing and add money to Park
     */
    public boolean payReleased() throws LackMoneyException, NotInJailException {
    	if (CentralControl.board.getJail().turnInJail(this) == 0) {
			throw new NotInJailException("You are not in jail!");
		}
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
     * @throws NotInJailException 
     * @throws Exception 
     * @methodsName: released
     * @description: release yourself from jail by free-prisoner card
     */
    public boolean released() throws NotInJailException {
    	if (CentralControl.board.getJail().turnInJail(this) == 0) {
			throw new NotInJailException("You are not in jail!");
		}
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
        this.Properties.add(property);
        property.setAvailable(false);
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
     * Trading mechanic: selling properties between players used by the GUI when
     * players defined an agreed price for a property
     *
     * author: Haotian Jiao date: 20/04/2020
     *
     * @param player The instance of Player
     * @param p The instance of Property
     * @param price A defined price(agreed between players)
     * @throws LackMoneyException 
     */
    public void sellPropertyToPlayer(Player player, Property p, int price) throws LackMoneyException {
        sellProperty(p);
        player.buyProperty(p);
        player.minusMoney(price);
        addMoney(price);
    }
    
    
    /**
     * @author: Mingfeng
     * @return	boolean wheather player raises money succeed
     * @methodsName: raiseMoney
     * @description: sell or mortage something for raising money
     */
    public boolean raiseMoney(int rent) {
        // should include "sellPropertyToPlayer"
        // need GUI finished (when player cannot pay rent, the mortage button and sell button will be highlighted
        // player is unable to act other behaviours but leave the game
    	// TO-DO
        boolean enough = money >= rent;
        calculateAssets();
        while (!enough && totalAssets >= rent) {
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
				enough = totalValue >= rent;
			}
		}
        return enough;
    }

    /**
     *
     * @param Property,Player
     * @throws LackMoneyException 
     */
    public void payRent(Property p) {
    	Player reciever = p.getOwner();
        if (CentralControl.board.getJail().turnInJail(reciever) == 0 && p.undermortgage != true) {
            try {
                minusMoney(p.getRent());
            } catch (LackMoneyException e) {
                if (raiseMoney(p.getRent())) {
                	payRent(p);
                } else {
                    CentralControl.leaveGame();
                    return ;
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
    	this.money += money;
    }
    
    /**
     * @param money
     * @description: add money to balance
     */
    public void minusMoney(int money) throws LackMoneyException{
    	if (this.money - money < 0) 
            throw new LackMoneyException("lack money");
    	this.money -= money;
    }
    
    /**
     * @param money
     * @description: add money to balance
     */
    public void calculateAssets()  {
    	totalAssets = 0;
    	for (Property property : Properties) {
			if (property.undermortgage) {
				totalAssets += property.getCost();
			}
			else {
				totalAssets += property.getStatus() * CentralControl.bank.getHousePrice(property);
			}
		}
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
     * @param int location
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
     * @param boolean passGo
     */
    public void setPassGo(boolean passGo) {
        this.passGo = passGo;
    }
    
    /**
    *
    * @param int money
    */
   public void setMoeny(int money) {
       this.money = money;
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
    
    /**
    *
    * @return ArrayList<Property>
    */
    public ArrayList<Property> getPropertiesList() {
        return Properties;
    }
    
    /**
    *
    * @return int
    */
    public int getTotalValue() {
        return totalValue;
    }
    
    /**
    *
    * @return int
    */
    public void setotalValue(int totalValue) {
    	this.totalValue = totalValue;
	}
}
