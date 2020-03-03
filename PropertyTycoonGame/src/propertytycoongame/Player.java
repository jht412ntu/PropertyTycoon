package propertytycoongame;

import java.util.ArrayList;

/** Player 
*
* Enum tokens can only be choosen in a limited range
* 
* 
* @author Mingfeng
*/

public class Player{
	
	private int money;
	private int location = 0;
	private Token token;
	private boolean passGo = false;

	
	public enum Token{
		boot,smartphone,goblet,hatstand,cat,spoon;
	}
	
	
	public Player(int money,Token token) {
		// TODO Auto-generated constructor stub
		this.money = money;
		this.token = token;
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
	
}
