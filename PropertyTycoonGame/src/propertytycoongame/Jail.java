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
	* @description: constructor
	*/
	public Jail() {
		prisoners = new HashMap<>();
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
	public int inJail(Player player) {
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
		prisoners.remove(player);
	}
		
}
