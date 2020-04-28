package propertytycoongame;


/**
 * Park class
 *
 * Where fines are to be paid, the proceeds accumulate on the free parking space in the centre of the board. 
 * When a player lands on free parking, they collect all of the funds currently on the free parking space.
 *
 * @author Mingfeng
 */
public class Park extends Cell{
	public Park(int position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	public int collectedFine = 0;
	
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
     * @param player Player that has landed on free parking
     */
	public void collectFine(Player player) {
		player.addMoney(collectedFine) ;
		collectedFine = 0;
	}
}
