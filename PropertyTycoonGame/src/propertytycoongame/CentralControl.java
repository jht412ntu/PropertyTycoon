package propertytycoongame;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import propertytycoongame.Player.Token;

/**
 * Property Tycoon Game Central Control
 *
 * Class that provides functionality for starting and controlling the game.
 *
 * @author Haotian Jiao
 * @version 1.0.1
 */
public class CentralControl implements Runnable{
	
    private final Date startTime;
    private final Long duration;
    private int votes = 0;
    private Date endTime;
    private String mode;
    private static ArrayList<Player> rank = new ArrayList<Player>();
    private static  ArrayList<Player> players = new ArrayList<>();
    private static int currentPlayer = 0;
    public static Board board = new Board();
    public static Dice dices = new Dice();
    public static Bank bank = new Bank();

    public CentralControl(long duration) { // in minutes
        startTime = new Date(); // set start time to be current system time
        this.duration = duration * 60000; // time in milliseconds
        if (duration == 0) { // Normal(survival) mode
            mode = "Normal";
            endTime = startTime; // the normal mode does not have specific end time
        } else { // Abridged(timed) mode  **cannot be negative time**
            mode = "Abridged";
            endTime = new Date(startTime.getTime() + this.duration);
        }
    }

    /**
     * Add player to the game
     *
     * @param p The player to be added to the game
     */
    public void addPlayer(Player p){
        players.add(p);
    }

    /**
     * Shuffling all the players
     *
     */
    public void initPlayers() throws Exception{
        if(players.size() < 1){
            throw new Exception("Minimum of two players required to start game");
        } else{
            Collections.shuffle(players);
        }
    }

    public void firstroll() {
        HashMap<Player, Integer> map = new HashMap<>();
        ArrayList<Player> newplayers = new ArrayList<>();
        for (Player player : players) {
            dices.rollDice();
            player.setotalValue(dices.getTotalVal());  //each player's points
            map.put(player, player.getTotalValue());//put it in map
        }
        List<Map.Entry<Player, Integer>> orderedlist = new ArrayList<>(map.entrySet()); //trans to a list
        Collections.sort(orderedlist, new Comparator<Map.Entry<Player, Integer>>() {
            public int compare(Map.Entry<Player, Integer> o1, Map.Entry<Player, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        for (int i = 0; i < orderedlist.size(); i++) {
            newplayers.add(orderedlist.get(i).getKey());
        }
        players = newplayers;
        for (int j = 0; j > players.size(); j++) {
            if (players.get(j).getTotalValue() == players.get(j + 1).getTotalValue()) {
                dices.rollDice();
                players.get(j).setotalValue(dices.getTotalVal());
                dices.rollDice();
                players.get(j + 1).setotalValue(dices.getTotalVal());
                if (players.get(j).getTotalValue() < players.get(j + 1).getTotalValue()) {
                    Collections.swap(players, j, j + 1);
                }
            }
        }

    }

    /**
     * Set the current player to the next player in the list of players.
     * If player is an agent, start to run automatically
     */
    public static void nextPlayer() {
        if (currentPlayer < players.size() - 1) {
            currentPlayer += 1;
        } else {
            currentPlayer = 0;
        }
        dices.newPlayer();
        if (Agent.class.isInstance(getCurrentPlayer())) {
			((Agent)getCurrentPlayer()).run();
		}
    }

    /**
     * Set the final end time.
     *
     * Uses only when the game is playing in normal mode
     */
    public void setEndTime() {
        endTime = new Date(getCurrentTime().getTime());
    }

    /**
     * Get the current player who should playing the game.
     *
     * @return Player The current players turn
     */
    public static Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Get all the players.
     *
     * @return ArrayList List of all players currently in the game
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Get the current system time.
     *
     * @return Date Current system time
     */
    public Date getCurrentTime() {
        return new Date();
    }

    /**
     * Get the start time of the game.
     *
     * @return Date Start time of the game
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Get the end time of the game
     *
     * @return Date
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Get the remaining time of the game
     *
     * @return String
     */
    public Time getRemainingTime() {
        long interval = endTime.getTime() - getCurrentTime().getTime();
        return timeFormat(interval);
    }

    /**
     * Get the duration of the game
     *
     * @return String
     */
    public Time getDuration() {
        long interval = endTime.getTime() - startTime.getTime();
        return timeFormat(interval);
    }
    
    /**
     * Format the time in milliseconds to 00:00:00(H:m:s)
     *
     * @param milliseconds
     * @return Time
     */
    public Time timeFormat(long milliseconds) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
        Time hms = new Time(milliseconds);
        return hms;
    }

    
    /**
     * @description Remove a player from the game
     *
     */
    public static void leaveGame() {
    	rank.add(getCurrentPlayer());
        players.remove(getCurrentPlayer());
    }
    
    /**
     * @author: Mingfeng
     * @methodsName: voteEnd
     * @description: Vote to end this game
     */
    public void voteEnd() {
		if (++votes == players.size()) {
			endGame();
		}
	}
    /**
     * @author: Mingfeng
     * @methodsName: endGame
     * @description Rank players when ends the game
     * @return ArrayList<Player>
     */
    public ArrayList<Player> endGame() {
    	ArrayList<Player> rankPlayers = (ArrayList<Player>) players.clone();
    	Collections.sort(rankPlayers, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				// TODO Auto-generated method stub
				if (o1.getMoney() > o2.getMoney()) {
		            return 1;
		        }
		        return 0;
			}
        });
		rank.addAll(rankPlayers);
		return rank;
	}
    
    public void addAgent(String name,Token token) throws CreatePlayerException {
		Agent agent = new Agent(name, token);
		players.add(agent);
	}
    
    /**
     * @author: Mingfeng
     * @methodsName: run
     * @description: If it is timer mode. Start the timer
     */
	@Override
	public void run() {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			endGame();
		}
	}
	
	
}
