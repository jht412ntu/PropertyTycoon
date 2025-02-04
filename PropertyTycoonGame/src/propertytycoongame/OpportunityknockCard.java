package propertytycoongame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

/**
 * OpportunityknockCard
 *
 * Class that provides actions that make the opportunity cards work.
 *
 * Documented by Haotian Jiao
 *
 * @author Zhenyu
 */
public class OpportunityknockCard extends Cards {

    ArrayList unshuffleList1 = new ArrayList();
    Queue<String> shuffledqueue1 = new LinkedList<>();

    /**
     * Constructor for OpportunityknockCard
     *
     * @param position The position of the OpportunityknockCard
     */
    public OpportunityknockCard() {

        unshuffleList1.add("Bank pays you divided of £50");
        unshuffleList1.add("You have won a lip sync battle. Collect £100");
        unshuffleList1.add("Advance to Turing Heights");
        unshuffleList1.add("Advance to Han Xin Gardens. If you pass GO, collect £200");
        unshuffleList1.add("Fined £15 for speeding");
        unshuffleList1.add("Pay university fees of £150");
        unshuffleList1.add("Take a trip to Hove station. If you pass GO collect £200");
        unshuffleList1.add("Loan matures, collect £150");
        unshuffleList1.add("You are assessed for repairs, £40/house, £115/hotel");
        unshuffleList1.add("Advance to GO");
        unshuffleList1.add("You are assessed for repairs, £25/house, £100/hotel");
        unshuffleList1.add("Go back 3 spaces");
        unshuffleList1.add("Advance to Skywalker Drive. If you pass GO collect £200");
        unshuffleList1.add("Go to jail. Do not pass GO, do not collect £200");
        unshuffleList1.add("Drunk in charge of a skateboard. Fine £20");
        unshuffleList1.add("Get out of jail free");
        Collections.shuffle(unshuffleList1);
        Iterator it = unshuffleList1.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            shuffledqueue1.offer(str);
            it.remove();
        }
    }

    /**
     * Picks a card from the top of the cards set and takes the action.
     *
     * @param player The player that takes the card
     */
    public void action(Player player) {
        String topcard = shuffledqueue1.poll();
        switch (topcard) {
            case "Bank pays you divided of £50":
                player.addMoney(50);
                CentralControl.bank.addBalance(-50);
                shuffledqueue1.offer(topcard);
            case "You have won a lip sync battle. Collect £100":
                player.addMoney(100);
                CentralControl.bank.addBalance(-100);
                shuffledqueue1.offer(topcard);
            case "Advance to Turing Heights":
                player.setLocation(40);
            case "Advance to Han Xin Gardens. If you pass GO, collect £200":
                player.setLocation(25);
                if (player.isPassGo() == true) {
                    player.addMoney(200);
                }
            case "Fined £15 for speeding":
                player.addMoney(-15);
                CentralControl.board.getPark().addFine(15);
            case "Pay university fees of £150":
                player.addMoney(150);
                CentralControl.bank.addBalance(-150);
                shuffledqueue1.offer(topcard);
            case "Take a trip to Hove station. If you pass GO collect £200":
                player.setLocation(16);
                if (player.isPassGo() == true) {
                    player.addMoney(200);
                }
            case "Loan matures, collect £150":
                CentralControl.bank.addBalance(-150);
                player.addMoney(150);
            case "You are assessed for repairs, £40/house, £115/hotel":
                for (Property property : player.getPropertiesList()) {
                    player.addMoney(-property.getNumOfHouse() * 40);
                    if (property.ifHotelBuilded() != false);
                    player.addMoney(-115);
                }
            case "Advance to GO":
                player.setLocation(0);
                shuffledqueue1.offer(topcard);
            case "You are assessed for repairs, £25/house, £100/hotel":
                for (Property property : player.getPropertiesList()) {
                    player.addMoney(-property.getNumOfHouse() * 40);
                    if (property.ifHotelBuilded() != false);
                    player.addMoney(-115);
                }
            case "Go back 3 spaces":
                player.setLocation(player.getLocation() - 3);
            case "Advance to Skywalker Drive. If you pass GO collect £200":
                player.setLocation(12);
                if (player.isPassGo() == true) {
                    player.addMoney(200);
                }
            case "Go to jail. Do not pass GO, do not collect £200":
                player.setLocation(31);//suppose we should have a method gotojail
                CentralControl.board.getJail().put(player);
                shuffledqueue1.offer(topcard);
            case "Drunk in charge of a skateboard. Fine £20":
                player.addMoney(-20);
                CentralControl.board.getPark().addFine(20);

            case "Get out of jail free":
                player.addReleaseCard(new OpportunityknockCard());

        }
    }

    public Queue<String> getShuffledQueue() {
        return shuffledqueue1;
    }

}
