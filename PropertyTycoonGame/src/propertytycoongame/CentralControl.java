package propertytycoongame;

import java.sql.Time;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.TimeZone;
import propertytycoongame.Player;

/**
 * Property Tycoon Game Central Control
 * 
 * Class that provides functionality for starting and controlling the game.
 * 
 * @author Haotian Jiao
 * @version 1.0.1
 */
public class CentralControl {
    private final Date startTime;
    private final Long duration;
    private Date endTime;
    private String mode;
    private ArrayList<Player> players;
    private int currentPlayer = 0;
    private ArrayList<Player> passedGoPlayers; // players that completed at least a full circuit
    public static Board board  = new Board();
    public static Dice dices = new Dice();
    public static Bank bank = new Bank();
    
    
    public CentralControl(long duration) { // in minutes
        players = new ArrayList<Player>();
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
     * @param name 
     */
    public void addPlayer(Player p) {
        players.add(p);
    }

    /**
     * Shuffling all the players
     * 
     */
    public void initPlayers() {
        Collections.shuffle(players);
    }
    
    /**
     * Set the current player to the next
     * 
     */
    public void nextPlayer() {
        if(currentPlayer < players.size()-1){
            currentPlayer += 1;
        }else{
            currentPlayer = 0;
        }
    }

    /**
     * Set the final end time
     * 
     * Uses only when the game is playing in normal mode
     */
    public void setEndTime() {
        endTime = new Date(getCurrentTime().getTime());
    }
    
    /**
     * Get the current player who should playing the game
     * 
     * @return Player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
    
    /**
     * Get all the players
     * 
     * @return ArrayList
     */
    public ArrayList getPlayers() {
        return players;
    }

    /**
     * Get the current system time
     * 
     * @return Date
     */
    public Date getCurrentTime() {
        return new Date();
    }
    
    /**
     * Get the start time of the game
     * 
     * @return Date
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

    public void setPassedGoPlayer(Player player) {
        for (Player p:players){
            if (p.isPassGo() && !passedGoPlayers.contains(p)) {
                passedGoPlayers.add(player);
            }
        }
    }
    
    public boolean isPlayerPassedGo(Player player) {
        if (passedGoPlayers.contains(player)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void leaveGame() {
    	passedGoPlayers.remove(currentPlayer);
    	players.remove(currentPlayer);
    }
}