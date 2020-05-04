package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytycoongame.Board;
import propertytycoongame.CentralControl;
import propertytycoongame.CreatePlayerException;
import propertytycoongame.Dice;
import propertytycoongame.Jail;
import propertytycoongame.LackMoneyException;
import propertytycoongame.NotInJailException;
import propertytycoongame.Player;
import propertytycoongame.Property;


/** Testing class for the Player class.
*
* @author Mingfeng
*/
public class PlayerTest {
	private static Player player1;
	private static CentralControl centralControl;
	
	public PlayerTest() {
		
	}
	
	@BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
	@Before
    public void setUp() throws CreatePlayerException {
		centralControl = new CentralControl(100);
		CentralControl.players = new ArrayList<Player>();
		player1 = new Player("1",Player.Token.boot);
		CentralControl.board = new Board();
    }
	
	@After
    public void tearDown(){
            
    }
	
	@Test(expected = CreatePlayerException.class)
    public void duplicatePlayerTest() throws CreatePlayerException  {
		centralControl.addPlayer(player1);
        Player player2 = new Player("1",Player.Token.boot);
    }
	
	@Test (expected = LackMoneyException.class)
    public void payReleasedNoMoneyTests() throws LackMoneyException, NotInJailException {
        centralControl.addPlayer(player1);
        Jail jail = centralControl.board.getJail();
        
        jail.put(player1);
        player1.payReleased();
        assertEquals(0,jail.turnInJail(player1));
        
        jail.put(player1);
        player1.setMoeny(5);
        player1.payReleased();
        
        player1.setMoeny(5);
        player1.payReleased();
    }
	
	@Test (expected = NotInJailException.class)
    public void cardReleasedTests() throws NotInJailException {
		Jail jail = CentralControl.board.getJail();
        jail.put(player1);
        player1.addReleaseCard();
        player1.released();
        assertEquals(0,jail.turnInJail(player1));
        player1.addReleaseCard();
        player1.released();
    }
	
	@Test
	public void rollDicesTests() {
		centralControl.dices = new Dice();
		Jail jail = CentralControl.board.getJail();
        jail.put(player1);
        player1.rollDices();
        assertEquals(1, jail.turnInJail(player1));
        centralControl.dices.newPlayer();
        player1.rollDices();
        
        centralControl.dices.newPlayer();
        player1.setLocation(38);
        player1.rollDices();
        assertEquals(true, player1.isPassGo());
	}
	
	@Test
	public void buyPropertyTest() {
		Property property  = (Property)CentralControl.board.getCell(2);
		player1.buyProperty(property);
		assertEquals(property, player1.getPropertiesList().get(0));
	}
	
	@Test
	public void sellPropertyTest() {
		Property property  = (Property)CentralControl.board.getCell(2);
		player1.buyProperty(property);
		player1.sellProperty(property);
		assertEquals(0, player1.getPropertiesList().size());
	}
	
	@Test
	public void sellPropertyToPlayerTest() throws CreatePlayerException, LackMoneyException {
		Property property  = (Property)CentralControl.board.getCell(2);
		player1.setMoeny(1500);
		player1.buyProperty(property);
		Player player2 = new Player("2",Player.Token.goblet);
		assertEquals(1500, player1.getMoney());
		player1.sellPropertyToPlayer(player2, property, 500);
		assertEquals(0, player1.getPropertiesList().size());
		assertEquals(property, player2.getPropertiesList().get(0));
		assertEquals(2000, player1.getMoney());
		assertEquals(1000, player2.getMoney());
	}
	
	@Test
	public void payRentTest() throws CreatePlayerException, LackMoneyException {
		Property property  = (Property)CentralControl.board.getCell(2);
		property.changeOwner(player1);
		player1.setMoeny(1500);
		Player player2 = new Player("2",Player.Token.goblet);
		Jail jail = CentralControl.board.getJail();
        jail.put(player1);
        player2.payRent(property);
		assertEquals(1500, player1.getMoney());
		assertEquals(1500, player2.getMoney());
		
		jail.release(player1);
		player2.payRent(property);
		assertEquals(1502, player1.getMoney());
		assertEquals(1498, player2.getMoney());
		
		player1.setLocation(2);
		property.mortgage(centralControl.bank, player1);
		player2.payRent(property);
		assertEquals(1532, player1.getMoney());
		assertEquals(1498, player2.getMoney());
		
		property.redeemed(player1);
		centralControl.addPlayer(player1);
		centralControl.addPlayer(player2);
		player2.setMoeny(0);
		player2.payRent(property);
		assertEquals(1502, player1.getMoney());
		assertEquals(1, centralControl.getPlayers().size());
		
		
	}
}
