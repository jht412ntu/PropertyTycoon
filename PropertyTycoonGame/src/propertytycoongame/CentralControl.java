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
 * CentralControl
 *
 * Class that provides functionality for starting and controlling the game.
 *
 * Documented by Haotian Jiao
 *
 * @author Haotian Jiao
 * @version 1.1.0
 *
 */
public class CentralControl {

    private final Date startTime;
    private final Long duration;
    private int votes = 0;
    private Date endTime;
    private String mode;
    private static ArrayList<Player> rank = new ArrayList<>();
    private static int currentPlayer = 0;

    /**
     * All players saved in the list.
     */
    private static ArrayList<Player> players = new ArrayList<>();

    /**
     * An instance of the Board Class.
     */
    public static Board board = new Board();

    /**
     * An instance of the Dice Class.
     */
    public static Dice dices = new Dice();

    /**
     * An instance of the Bank Class.
     */
    public static Bank bank = new Bank();

    /**
     * An instance of the Jail Class.
     */
    public static Jail jail;

    /**
     * Constructor for CentralControl
     *
     * @param duration An input value of the game duration
     */
    public CentralControl(long duration) { // in minutes
        //players 
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
    public void addPlayer(Player p) {
        players.add(p);
    }

    /**
     * Shuffling all the players
     *
     * @throws java.lang.Exception
     */
    public void initPlayers() throws Exception {
        if (players.size() < 1) {
            throw new Exception("Minimum of two players required to start game");
        } else {
            Collections.shuffle(players);
        }
    }

    /**
     * The initial rolling of the game to decided the order of the players' turn.
     *
     */
    public void firstroll() {
        HashMap<Player, Integer> map = new HashMap<>();
        ArrayList<Player> newplayers = new ArrayList<>();
        for (Player player : players) {
            
            dices.rollDice();
            player.setotalValue(dices.getTotalVal());  //each player's points
            map.put(player, player.getTotalValue());//put it in map
            dices.newPlayer();
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
                dices.newPlayer();
            }
        }

    }

    /**
     * Sets the current player to the next player in the list of players. If player is an agent, start to run automatically
     * @throws propertytycoongame.LackMoneyException
     */
    public static void nextPlayer() throws LackMoneyException {
        if (currentPlayer < players.size() - 1) {
            currentPlayer += 1;
        } else {
            currentPlayer = 0;
        }
        dices.newPlayer();
        if (Agent.class.isInstance(getCurrentPlayer())) {
            ((Agent) getCurrentPlayer()).run();
        }
    }

    /**
     * Sets the final end time.
     *
     * Uses only when the game is playing in normal mode
     */
    public void setEndTime() {
        endTime = new Date(getCurrentTime().getTime());
    }

    /**
     * Accesses and returns the current player who should playing the game.
     *
     * @return Player - the current player's instance
     */
    public static Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Accesses and returns all the players.
     *
     * @return ArrayList List of all players currently in the game
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Accesses and returns the current system time.
     *
     * @return Date - current system time
     */
    public Date getCurrentTime() {
        return new Date();
    }

    /**
     * Accesses and returns the start time of the game.
     *
     * @return Date - starting time of the game
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Accesses and returns the end time of the game.
     *
     * @return Date - ending time of the game
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Accesses and returns the remaining time of the game.
     *
     * @return Time - the remaining time of the game
     */
    public Time getRemainingTime() {
        long interval = endTime.getTime() - getCurrentTime().getTime();
        return timeFormat(interval);
    }

    /**
     * Accesses and returns the duration of the game.
     *
     * @return Time - the duration of the game
     */
    public Time getDuration() {
        long interval = endTime.getTime() - startTime.getTime();
        return timeFormat(interval);
    }

    /**
     * Formats the time in milliseconds to 00:00:00(H:m:s).
     *
     * @param milliseconds The milliseconds time
     * @return Time - the time in format "00:00:00(H:m:s)"
     */
    public Time timeFormat(long milliseconds) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
        Time hms = new Time(milliseconds);
        return hms;
    }

    /**
     * Removes the current player from the game.
     *
     */
    public static void leaveGame() {
        players.remove(getCurrentPlayer());
    }

    /**
     * Votes to end this game.
     *
     * author: Mingfeng
     *
     */
    public void voteEnd() {
        if (++votes == players.size()) {
            endGame();
        }
    }

    /**
     * Ranking players when ends the game.
     *
     * @return ArrayList - the ranking list of the players
     */
    public ArrayList<Player> endGame() {
        ArrayList<Player> rankPlayers = (ArrayList<Player>) players.clone();
        Collections.sort(rankPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getMoney() > o2.getMoney()) {
                    return 1;
                }
                return 0;
            }
        });
        rank.addAll(rankPlayers);
        return rank;
    }

    public void addAgent(String name, Token token) throws CreatePlayerException {
        Agent agent = new Agent(name, token);
        players.add(agent);
    }

    /**
     * If it is timer mode. Start the timer
     *
     * author: Mingfeng
     *
     */
    public void run() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            endGame();
        }
    }

    /**
     * Starts an auction through the Bank.
     */
    public void auction() {
        bank.startAuction();
    }
}
