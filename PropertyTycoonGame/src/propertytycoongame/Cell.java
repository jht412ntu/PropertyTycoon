package propertytycoongame;

/**
 * Cell
 *
 * The piece of board with location
 *
 * @author Mingfeng
 */
public class Cell {

    private int position;

    public Cell(int position) {
    	this.position = position ;
    }
    
    public Cell() {
    	
    }
    
    /**
     * 
     * @return The position of the cell (integer)
     */
    public int getPosition() {
        return position;
    }

    /** Sets the location of the cell
     * 
     * @param location The location to be set
     */
    public void setPosition(int location) {
        this.position = location;
    }
}
