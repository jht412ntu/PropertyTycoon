package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytycoongame.Agent;
import propertytycoongame.Board;
import propertytycoongame.Cell;
import propertytycoongame.CentralControl;
import propertytycoongame.CreatePlayerException;
import propertytycoongame.Jail;
import propertytycoongame.Player;
import propertytycoongame.Property;

public class AgentTest {
	
	private Agent agent;
	private CentralControl centralControl;
	private Jail jail;
	
	@BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
	@Before
    public void setUp() throws CreatePlayerException  {
		agent  = new Agent("agent1", Player.Token.cat);
		centralControl = new CentralControl(100);
		CentralControl.getPlayers().clear();
		CentralControl.board = new Board();
		jail = CentralControl.board.getJail();
    }
	
	@After
    public void tearDown(){
            
    }
	
	@Test
	public void rollDiceTest() {
		centralControl.dices.newPlayer();
		agent.rollDices();
	}
	
	@Test
	public void autoBuyPropertyTest() {
		agent.setLocation(2);
		Cell currentCell = CentralControl.board.getCell(agent.getLocation());
		assertEquals(0, agent.getPropertiesList().size());
		agent.autoBuyProperty(currentCell);
		assertEquals(1, agent.getPropertiesList().size());
	}
	
	@Test
	public void autoBuildHouseTest() {
		Property property  = (Property)CentralControl.board.getCell(2);
		property.changeOwner(agent);
		assertEquals(0,property.getNumOfHouse());
		agent.autoBuild();
		assertEquals(1,property.getNumOfHouse());
	}
	
	@Test
	public void autoBuildHotelTest() {
		Property property  = (Property)CentralControl.board.getCell(2);
		property.changeOwner(agent);
		assertFalse(property.ifHotelBuilded());
		agent.autoBuild();
		agent.autoBuild();
		agent.autoBuild();
		agent.autoBuild();
		agent.autoBuild();
		assertTrue(property.ifHotelBuilded());
	}
	
	@Test
	public void autoPayRentTest() throws CreatePlayerException {
		Property property  = (Property)CentralControl.board.getCell(2);
		Agent agent2 = new Agent("agent2", Player.Token.goblet);
		property.changeOwner(agent2);
		agent.setLocation(2);
		Cell currentCell = CentralControl.board.getCell(agent.getLocation());
		agent.autoPayRent(currentCell);
		assertEquals(1501, agent2.getMoney());
		assertEquals(1499, agent2.getMoney());
	}
	
	@Test
	public void runTest() {
		agent.run();
	}
}
