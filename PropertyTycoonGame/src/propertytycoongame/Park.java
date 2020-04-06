package propertytycoongame;


/**
 * Park class
 *
 * Where fines are to be paid, the proceeds accumulate on the free parking space in the centre of the board. 
 * When a player lands on free parking, they collect all of the funds currently on the free parking space.
 *
 * @author Mingfeng
 */
public class Park {
	private int collectedFine = 0;
	
	
	/**
     * Add money to the parking area
     * 
     * @param fine // player paid
     */
	public int addFine(int fine) {
		collectedFine += fine;
		return collectedFine;
	}
	
	/**
     * Player who lands on free parking area collect money
     * 
     * @param player
     */
	public void collectFine(Player player) {
		player.addMoney(collectedFine) ;
		collectedFine = 0;
	}
}
