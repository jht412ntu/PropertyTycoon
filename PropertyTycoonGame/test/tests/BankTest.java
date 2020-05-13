package tests;

import org.junit.Test;
import propertytycoongame.Bank;
import propertytycoongame.BankException;
import propertytycoongame.DuplicateException;
import propertytycoongame.Player;
import propertytycoongame.Property;
import propertytycoongame.PropertyException;
import propertytycoongame.CentralControl;
import propertytycoongame.CreatePlayerException;

/**
 * Testing for sprint 2, 3 and 5.
 * 
 * @author Haotian Jiao
 */
public class BankTest {
    private Bank bank;
    private Player p;
    private Player p2;
    private Property pp;
    private Property pp2;
    private CentralControl normal;
    
    public BankTest() throws CreatePlayerException {
        bank = new Bank();
        normal = new CentralControl(0);
        p = new Player("Player 1", Player.Token.boot);
        p2 = new Player("Player 2", Player.Token.cat);
        pp = new Property(2, "Crapper Street", "Brown", 60, 2, 10, 30, 90, 160, 250);
        pp2 = new Property(4, "Gangsters Paradise", "Brown", 60, 4, 20, 60, 180, 320, 450);
    }

    /**
     * Sprint2: TC2: Buy Properties
     * 
     * TC2-F1: Player shall be able to buy a property
     * TC2-F4: When a player has enough money to buy property, 
     * the system shall change the owner of the property.
     *
     * @throws PropertyException
     * @throws Exception
     */
    @Test
    public void testBuyProperty() throws PropertyException, Exception {
        System.out.println("Player shall be able to buy a property:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        bank.buyProperty(p, pp);
        Player expResult = p;
        Player result = pp.getOwner();
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint2: TC2: Buy Properties
     * 
     * TC2-F3:
     * Before a player buys a property, 
     * the amount of money the player shall be compared with property price 
     * in order to check whether the player has enough money.
     * 
     * test 1
     * 
     * @throws Exception 
     */
    @Test
    public void testCheckEnoughMoney1() throws Exception {
        System.out.println("Compare player's balance with the property price:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        p.minusMoney(1500);
        String result;
        try {
            bank.buyProperty(p, pp);
            result = "";
        } catch (PropertyException e) {
            result = e.getMessage();
        }
        String expResult = "Your balance is not enough to buy this.";
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint2: TC2: Buy Properties
     * 
     * TC2-F3:
     * Before a player buys a property, 
     * the amount of money the player shall be compared with property price 
     * in order to check whether the player has enough money.
     * 
     * test 2
     * 
     * @throws Exception 
     */
    @Test
    public void testCheckEnoughMoney2() throws Exception {
        System.out.println("Compare player's balance with the property price:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        String result;
        try {
            bank.buyProperty(p, pp);
            result = "Succeed";
        } catch (PropertyException e) {
            result = e.getMessage();
        }
        String expResult = "Succeed";
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint2: TC2: Buy Properties
     * 
     * TC2-F5: The amount of money for the property shall be transferred 
     * from the player's bank account to the bank.
     * 
     * @throws Exception 
     */
    @Test
    public void testMoneyTransferred() throws Exception {
        System.out.println("Money transferred from player to the bank:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        bank.buyProperty(p, pp);
        int result = bank.getBalance() - 50000;
        int expResult = 1500 - p.getMoney();
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint3: TC1: Auctioning
     * 
     * TC1-F1: Before a player is allowed to make a bid, 
     * it shall be checked whether a player has completed one full circuit.
     * 
     * test 1
     * 
     * @throws Exception 
     */
    @Test
    public void testCompletedFullCircuitWhenBid() throws Exception {
        System.out.println("A player has completed one full circuit before making a bid:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        String result;
        try {
            bank.bid(pp, p, 0);
            result = "";
        } catch (BankException e) {
            result = e.getMessage();
        }
        String expResult = "A player has to completed a full circuit before join an auction.";
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint3: TC1: Auctioning
     * 
     * TC1-F1: Before a player is allowed to make a bid, 
     * it shall be checked whether a player has completed one full circuit.
     * 
     * test 2
     * 
     * @throws Exception 
     */
    @Test
    public void testCompletedFullCircuitWhenBid2() throws Exception {
        System.out.println("A player has completed one full circuit before making a bid:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        p.setPassGo(true);
        String result;
        try {
            bank.bid(pp, p, 0);
            result = "Succeed";
        } catch (BankException e) {
            result = e.getMessage();
        }
        String expResult = "Succeed";
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint3: TC1: Auctioning
     * 
     * TC1-F2: It should be checked that the player's bid is a valid number.
     * 
     * test 1
     * 
     * @throws Exception 
     */
    @Test
    public void testValidNumberWhenBid1() throws Exception {
        System.out.println("A player has completed one full circuit before making a bid:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        p.setPassGo(true);
        try {
            bank.bid(pp, p, -100);
        } catch (BankException e) {
            
        }
        Player result = bank.getCurrentBidder();
        Player expResult = null;
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint3: TC1: Auctioning
     * 
     * TC1-F2: It should be checked that the player's bid is a valid number.
     * 
     * test 2
     * 
     * @throws Exception 
     */
    @Test
    public void testValidNumberWhenBid2() throws Exception {
        System.out.println("A player has completed one full circuit before making a bid:");
        bank.addProperty(pp);
        normal.addPlayer(p);
        p.setPassGo(true);
        bank.bid(pp, p, 100);
        Player result = bank.getCurrentBidder();
        Player expResult = p;
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint5: TC2: Re-sell properties
     * 
     * TC2-F2: Bank shall transfer the original value of the property back to the player's bank account.
     * 
     * @throws Exception 
     */
    @Test
    public void testTransferredOriginalValueBack() throws Exception {
        System.out.println("Bank shall transfer the original cost back to the player:");
        normal.addPlayer(p);
        bank.addProperty(pp);
        bank.buyProperty(p, pp);
        bank.sellProperty(p, pp);
        int result = p.getMoney();
        int expResult = 1500;
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint5: TC2: Re-sell properties
     * 
     * TC2-F3: The attribute owner of the property shall be changed and the stored data shall be updated.
     * 
     * @throws Exception 
     */
    @Test
    public void testUpdatesOwner() throws Exception {
        System.out.println("The owner of the property shall be changed:");
        normal.addPlayer(p);
        bank.addProperty(pp);
        bank.buyProperty(p, pp);
        bank.sellProperty(p, pp);
        Player result = pp.getOwner();
        Player expResult = null;
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint5: TC2: Re-sell properties
     * 
     * TC2-F5: A player shall only be able to re-sell its own properties.
     * 
     * @throws Exception 
     */
    @Test
    public void testResellOwnProperty() throws Exception {
        System.out.println("A player shall only be able to re-sell its own properties:");        
        normal.addPlayer(p);
        normal.addPlayer(p2);
        bank.addProperty(pp);
        bank.addProperty(pp2);
        bank.buyProperty(p, pp);
        bank.buyProperty(p2, pp2);
        String result;
        try {
            bank.sellProperty(p, pp2);
            result = "";
        } catch (PropertyException e) {
            result = e.getMessage();
        }
        String expResult = "The player does not own this property.";
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
}
