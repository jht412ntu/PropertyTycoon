package propertytycoongame;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Cards {

    //input a map
    //random choose a number between the size of map
    //to take a card
	protected HashMap<Integer, String> cards = new HashMap<>();
	
	
	public String getAcard(){
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.get(index);
    }//String.valueOf(card.get(index)


    public void pay(Player player ,int amount){
        player.setMoney(player.getMoney()+amount); }

    public void move(Player player,int location){
        player.setLocation(player.getLocation()+location);
    }
}