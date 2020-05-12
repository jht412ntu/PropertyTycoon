package propertytycoongame;

import java.util.HashMap;

/**
 * Jail
 *
 * This class is resposible for putting or releasing prisoner in Jail Also pass a turn for a prisoner.
 * 
 * Documented by Haotian Jiao
 *
 * @author Mingfeng
 */
public class Jail extends Cell {

    private HashMap<Player, Integer> prisoners;

    /**
     * Constructor for Jail.
     * 
     * @param position The position of the jail
     */
    public Jail(int position) {
        super(position);
        prisoners = new HashMap<>();

    }

    /**
     * Puts a player in jail.
     * 
     * author: Mingfeng
     * 
     * @param player the player that need to be put in the jail
     */
    public void put(Player player) {
        prisoners.put(player, 2);
    }

    /**
     * Gets the remaining stay turns of the player.
     * 
     * author: Mingfeng
     * 
     * @param player The player that need to be checked
     * @return int - the remaining turns of the player
     */
    public int turnInJail(Player player) {
        return prisoners.get(player);
    }

    /**
     * Passes one turn for prisioner.
     * 
     * author: Mingfeng
     * 
     * @param player The player that need to be passed
     * @return int - the remaining turns
     */
    public int pass(Player player) {
        int oldTurn = prisoners.get(player);
        prisoners.replace(player, oldTurn - 1);
        return oldTurn - 1;
    }

    /**
     * Releases a player.
     * 
     * author: Mingfeng
     * 
     * @param player The player that need to be released
     */
    public void release(Player player) {
        prisoners.remove(player);
    }
    
    
   /**
    * Checks if given player is in jail.
    * author: Hayden.
    * 
    * @param p The player to check
    * @return true, if the player is in jail.
    */
    public boolean contains(Player p){
        if(prisoners.containsKey(p)){
            return true;
        } else {
            return false;
        }
    }

}
