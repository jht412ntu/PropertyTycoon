package propertytycoongame;

/** Player 
*
* Enum tokens can only be choosen in a limited range
* 
* 
* @author Mingfeng
*/

public class Player{
	
	private int money = 1500;
	private int location = 0;
	private Token token;
	private boolean passGo = false;
    private String name;
    private int releaseCard; 
    
	public enum Token{
		boot,smartphone,goblet,hatstand,cat,spoon;
	}
	
	
	public Player(String name,Token token) {
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
    	if(CentralControl.dices.getNumDouble() > 2)
    		CentralControl.jail.put(this);
    	else if(CentralControl.jail.inJail(this) > 0)
    		CentralControl.jail.pass(this);
    	else {
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
   	* @methodsName: useCard
   	* @description: use a card
   	*/ 
    
    public void released() {
    	if (releaseCard > 0) {
    		releaseCard -= 1;
    		CentralControl.jail.release(this);
		}
	}
    
    public void addAreleaseCard() {
		releaseCard += 1;
	}
    
    
	/*
	 * field getter and setter
	 */
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public boolean isPassGo() {
		return passGo;
	}

	public void setPassGo(boolean passGo) {
		this.passGo = passGo;
	}

	public Token getToken() {
		return token;
	}
        
    public String getName(){
        return name;
    }
	
}
