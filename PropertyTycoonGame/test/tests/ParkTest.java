package tests;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytycoongame.CreatePlayerException;
import propertytycoongame.Park;
import propertytycoongame.Player;

public class ParkTest {
	private Park park;
	
	@BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
	@Before
	public void setUp() {
		park = new Park(-1);
	}
	
	@Test
	public void moneyAvailableTest() {
		park.addFine(50);
		assertEquals(50, park.getMoney());
	}
	
	@Test
	public void collectTest() throws CreatePlayerException {
		Player player1 = new Player("1",Player.Token.boot);
		park.addFine(50);
		park.collectFine(player1);
		assertEquals(1550, player1.getMoney());
		assertEquals(0, park.getMoney());
	}
}
