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
        position = this.position;
    }

    /**
     * 
     * @return 
     */
    public int getPosition() {
        return position;
    }

    /**
     * 
     * @param location 
     */
    public void setPosition(int location) {
        this.position = location;
    }
}
