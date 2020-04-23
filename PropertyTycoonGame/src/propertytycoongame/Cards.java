package propertytycoongame;
import java.util.HashMap;
import java.util.Random;
public class Cards extends Cell{

    public Cards(int position) {
        super(position);
        // TODO Auto-generated constructor stub
    }

    //input a map
    //random choose a number between the size of map
    //to take a card
    protected HashMap<Integer, String> cards = new HashMap<>();


    public String getCard(){
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.get(index);
    }//String.valueOf(card.get(index)




    public void move(Player player,int location){
        player.setLocation(player.getLocation()+location);
    }
}