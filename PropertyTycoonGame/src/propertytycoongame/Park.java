package propertytycoongame;

/**
 * Park
 *
 * Where fines are to be paid, the proceeds accumulate on the free parking space in the centre of the board.
 * 
 * When a player lands on free parking, they collect all of the funds currently on the free parking space.
 *
 * Documented by Haotian Jiao
 * 
 * @author Mingfeng
 */
public class Park extends Cell {

    public int collectedFine = 0;
    
    /**
     * Constructor for Park.
     *
     * @param position The position of the park
     */
    public Park(int position) {
        super(position);
    }

    /**
     * Adds money to the parking area.
     *
     * @param fine Player paid
     * @return int - the total collected fines
     */
    public int addFine(int fine) {
        collectedFine += fine;
        return collectedFine;
    }

    /**
     * Player who lands on free parking area collect money.
     *
     * @param player Player that has landed on free parking
     */
    public void collectFine(Player player) {
        player.addMoney(collectedFine);
        collectedFine = 0;
    }
}
