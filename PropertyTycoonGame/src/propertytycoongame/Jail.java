package propertytycoongame;

import java.util.HashMap;

/** Jail
*
* This class is resposible for putting or releasing prisoner in Jail
* Also pass a turn for a prisoner
* @author Mingfeng
*/
public class Jail extends Cell{
	private HashMap<Player, Integer> prisoners;
	
	
	/**
	* @author: Mingfeng
	* @methodsName: Jail
	* @description: Initilze prisoner with 0 turn in jail of each player 
	*/
	public Jail(int position) {
		super(position);
		prisoners = new HashMap<>();
		for (Player player : CentralControl.players) {
			prisoners.put(player, 0);
		}
		
	}
	
	/**
	* @author: Mingfeng
	* @methodsName: put
	* @description: put a player in jail
	* @param:  Player
	*/
	public void put(Player player) {
		prisoners.put(player, 2);
	}
	
	/**
	* @author: Mingfeng
	* @methodsName: inJail
	* @description: get the left stay turn of the player
	* @param:  Player
	* @return:  Int
	*/
	public int turnInJail(Player player) {
		return prisoners.get(player);
	}
	
	
	/**
	* @author: Mingfeng
	* @methodsName: pass
	* @description: pass one turn for prisioner
	* @param:  Player
	* @return:  int
	*/
	public int pass(Player player) {
		int oldTurn = prisoners.get(player);
		prisoners.replace(player, oldTurn - 1);
		return oldTurn - 1; 
	}
	
	/**
	* @author: Mingfeng
	* @methodsName: pass
	* @description: pass one turn for prisioner
	* @param:  Player
	*/
	public void release(Player player) {
		prisoners.replace(player, 0);
	}
		
}
