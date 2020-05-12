package propertytycoongame;

import java.util.HashMap;

/**
 * Board
 *
 * Class that provides functionality for running the board.
 *
 * Documented by Haotian Jiao
 *
 * @author Zhenyu
 */
public class Board {

    protected HashMap<Integer, Cell> theboard = new HashMap<>();
    private Park park = new Park(21);
    private Jail jail = new Jail(11);
    private PotluckCard potluckCard = new PotluckCard(18);
    private OpportunityknockCard opportunityknockCard = new OpportunityknockCard(8);

    /**
     * Accesses and returns the field jail.
     *
     * @return Jail - the Jail instance
     */
    public Jail getJail() {
        return jail;
    }

    /**
     * Accesses and returns the field park.
     *
     * @return Park - the Park instance
     */
    public Park getPark() {
        return park;
    }

    /**
     * Gets a cell with specific location.
     *
     * @param loc The specific location
     * @return Cell - a specific cell
     */
    public Cell getCell(int loc) {
        return theboard.get(loc);
    }

    /**
     * Accesses and returns the field potluckCard.
     *
     * @return PotluckCard - the PotluckCard instance
     */
    public PotluckCard getPotluckCard() {
        return potluckCard;
    }

    /**
     * Accesses and returns the field opportunityknockCard.
     *
     * @return OpportunityknockCard - the OpportunityCard instance
     */
    public OpportunityknockCard getOpportunityknockCard() {
        return opportunityknockCard;
    }
}
