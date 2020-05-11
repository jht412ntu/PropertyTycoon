package propertytycoongame;

/**
 * Cell
 *
 * The piece of board with location.
 * 
 * Documented by Haotian Jiao
 *
 * @author Mingfeng
 */
public class Cell {

    private int position;

    /**
     * Constructor for Cell.
     * 
     * @param position The position of the cell
     */
    public Cell(int position) {
        position = this.position;
    }

    /**
     * Accesses and returns the position.
     * 
     * @return The position of the cell (integer)
     */
    public int getPosition() {
        return position;
    }

    /** 
     * Sets the location of the cell.
     * 
     * @param location The location to be set
     */
    public void setPosition(int location) {
        this.position = location;
    }
}
