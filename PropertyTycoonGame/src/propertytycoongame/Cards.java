package propertytycoongame;
import java.util.HashMap;
import java.util.Random;

/**
 * Cards
 * 
 * Class that provides basic functions that building and using the Cards.
 * 
 * Documented by Haotian Jiao
 * 
 * @author Zhenyu
 */
public class Cards extends Cell{

    protected HashMap<Integer, String> cards = new HashMap<>();
    
    /**
     * Constructor for Cards.
     * 
     * @param position The position of the Cards
     */
    public Cards(int position) {
        super(position);
    }

    //input a map
    //random choose a number between the size of map
    //to take a card

    /**
     * Chooses a card randomly.
     * 
     * @return String - a card
     */
    public String getCard(){
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.get(index);
    }
}