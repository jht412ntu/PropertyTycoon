package propertytycoongame;
import java.util.*;

public class PotluckCard extends Cards {
	private ArrayList<String> unshuffleList = new ArrayList<String>();
	private Queue<String> shuffledqueue = new LinkedList<>();

	//constructor
	public PotluckCard() {

		unshuffleList.add("Get out of jail free");
		unshuffleList.add("You inherit £100");
		unshuffleList.add("You have won 2nd prize in a beauty contest, collect £20");
		unshuffleList.add("Go back to Crapper Street");
		unshuffleList.add("Student loan refund. Collect £20");
		unshuffleList.add("Bank error in your favour. Collect £200");
		unshuffleList.add("Pay bill for text books of £100");
		unshuffleList.add("Mega late night taxi bill pay £50");
		unshuffleList.add("Advance to go");
		unshuffleList.add("From sale of Bitcoin you get £50");
		unshuffleList.add("Pay a £10 fine or take opportunity knocks");
		unshuffleList.add("Pay insurance fee of £50");
		unshuffleList.add("Savings bond matures, collect £100");
		unshuffleList.add("Go to jail. Do not pass GO, do not collect £200");
		unshuffleList.add("Received interest on shares of £25");
		unshuffleList.add("It's your birthday. Collect £10 from each player");
		Collections.shuffle(unshuffleList);
		Iterator<String> it = unshuffleList.iterator();
		while(it.hasNext()){
			String str = (String) it.next();
			shuffledqueue.offer(str);
			it.remove();
		}}




	public void action(Player player) {
		String topcard=shuffledqueue.poll();
		switch(topcard){
			case "Advance to go":
				player.setLocation(0);
				shuffledqueue.offer(topcard);
			case "You inherit £100":
				player.addMoney(100);
				CentralControl.bank.addBalance(-100);
				shuffledqueue.offer(topcard);
			case "You have won 2nd prize in a beauty contest, collect £20":
				player.addMoney(20);
				CentralControl.bank.addBalance(-20);
				shuffledqueue.offer(topcard);
			case "Go back to Crapper Street":
				player.setLocation(2);
				shuffledqueue.offer(topcard);
			case "Student loan refund. Collect £20":
				CentralControl.bank.addBalance(-20);
				player.addMoney(20);
				shuffledqueue.offer(topcard);
			case  "CentralControl.bank error in your favour. Collect £200"  :
				player.addMoney(200);
				CentralControl.bank.addBalance(-200);
				shuffledqueue.offer(topcard);
			case "Pay bill for text books of £100":
				player.addMoney(-100);
				CentralControl.bank.addBalance(100);
				shuffledqueue.offer(topcard);
			case "Mega late night taxi bill pay £50":
				player.addMoney(-50);
				CentralControl.bank.addBalance(+50);
				shuffledqueue.offer(topcard);
			case "From sale of Bitcoin you get £50":
				player.addMoney(50);
				CentralControl.bank.addBalance(-50);
				shuffledqueue.offer(topcard);
			case "Pay a £10 fine or take opportunity knocks":
				//make a chosse method
			case "Pay insurance fee of £50":
				player.addMoney(-50);
				CentralControl.bank.addBalance(+50);
				shuffledqueue.offer(topcard);
			case "Savings bond matures, collect £100":
				player.addMoney(100);
				CentralControl.bank.addBalance(100);
				shuffledqueue.offer(topcard);
			case "Go to jail. Do not pass GO, do not collect £200":
				player.setLocation(31);//suppose we should have a method gotojail
				//CentralControl.jail.put(player);
				shuffledqueue.offer(topcard);
			case "Received interest on shares of £25":
				player.addMoney(25);
				CentralControl.bank.addBalance(-25);
				shuffledqueue.offer(topcard);
			case "It's your birthday. Collect £10 from each player":
				player.addMoney(+10*CentralControl.getPlayers().size());
				for (Player another : CentralControl.getPlayers()) {
					if (!another.equals(player)) {
						try {
							another.minusMoney(-10);
						} catch (LackMoneyException e) {
							another.raiseMoney(10,player);
						}
					}
				}
			case "Get out of jail free":
				player.addReleaseCard(new PotluckCard());//when user cost it put it back
		}
	}
	
	public Queue<String> getShuffledQueue() {
		return shuffledqueue;
	}
}
