package propertytycoongame;


public class PotluckCard extends Cards {

     //constructor
    public PotluckCard(Player player) {
    	cards.put(0, "Get out of jail free");
        cards.put(1, "You inherit £100");
        cards.put(2, "You have won 2nd prize in a beauty contest, collect £20");
        cards.put(3, "Go back to Crapper Street");
        cards.put(4, "Student loan refund. Collect £20");
        cards.put(5, "Bank error in your favour. Collect £200");
        cards.put(6, "Pay bill for text books of £100");
        cards.put(7, "Mega late night taxi bill pay £50");
        cards.put(8, "Advance to go");
        cards.put(9, "From sale of Bitcoin you get £50");
        cards.put(10, "Pay a £10 fine or take opportunity knocks");
        cards.put(11, "Pay insurance fee of £50");
        cards.put(12, "Savings bond matures, collect £100");
        cards.put(13, "Go to jail. Do not pass GO, do not collect £200");
        cards.put(14, "Received interest on shares of £25");
        cards.put(15, "It's your birthday. Collect £10 from each player");
    }
    
    public void action(Player player) {
    	String randomcard = getAcard();
    	switch(randomcard){
	      case "Advance to go":
	           player.setLocation(0);
	      case "You inherit £100":
	          pay(player,100);
	      case "You have won 2nd prize in a beauty contest, collect £20":
	          pay(player,20);
	      case "Go back to Crapper Street":
	          player.setLocation(2);
	      case "Student loan refund. Collect £20":
	          pay(player,20);
	      case  "Bank error in your favour. Collect £200"  :
	          pay(player,200);
	      case "Pay bill for text books of £100":
	          pay(player,-100);
	      case "Mega late night taxi bill pay £50":
	          pay(player,-50);
	      case "From sale of Bitcoin you get £50":
	          pay(player,50);
	      case "Pay a £10 fine or take opportunity knocks":
	                    ;
	      case "Pay insurance fee of £50":
	          ;
	      case "Savings bond matures, collect £100":
	          pay(player,100);
	      case "Go to jail. Do not pass GO, do not collect £200":
	          player.setLocation(31);//suppose we should have a method gotojail
	          CentralControl.jail.put(player);
	      case "Received interest on shares of £25":
	          pay(player,25);
	      case "It's your birthday. Collect £10 from each player":
	          ;//?????????????????
	      case "Get out of jail free":
	          player.addAreleaseCard();;;//????????????????????
    	}
    }
}


