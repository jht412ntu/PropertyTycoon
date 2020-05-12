package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import propertytycoongame.Bank;
import propertytycoongame.BankException;
import propertytycoongame.DuplicateException;
import propertytycoongame.Player;
import propertytycoongame.Property;
import propertytycoongame.PropertyException;
import propertytycoongame.CentralControl;

/**
 * Testing for sprint 6.
 * 
 * @author Haotian Jiao
 */
public class TradingTest {
    private Bank bank;
    private Player p;
    private Player p2;
    private Property pp;
    private CentralControl normal;
    
    public TradingTest() {
        bank = new Bank();
        normal = new CentralControl(0);
        try {
            p = new Player("Player 1", Player.Token.boot);
            p2 = new Player("Player 2", Player.Token.cat);
        } catch (DuplicateException e) {
            System.err.println(e);
        }
        pp = new Property(2, "Crapper Street", "Brown", 60, 2, 10, 30, 90, 160, 250);
    }
    
    /**
     * Sprint6: TC3: Trading mechanics
     * 
     * TC3-F4: A player should be able to sell the property for more than the original value
     * TC3-F6: The owner of the property shall be changed.
     * 
     * @throws Exception 
     */
    @Test
    public void testSellMoreThanOriginal() throws Exception {
        System.out.println("A player should be able to sell the property for more than the original value:");        
        normal.addPlayer(p);
        normal.addPlayer(p2);
        bank.addProperty(pp);
        bank.buyProperty(p, pp);
        p.sellPropertyToPlayer(p2, pp, 120);
        Player result = pp.getOwner();
        Player expResult = p2;
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Sprint6: TC3: Trading mechanics
     * 
     * TC3-F5: When buying the property, the buyer shall transfer the money to the seller’s bank account.
     * 
     * @throws Exception 
     */
    @Test
    public void testAbleToTransferToOther() throws Exception {
        System.out.println("When buying the property, the buyer shall transfer the money to the seller’s bank account:");        
        normal.addPlayer(p);
        normal.addPlayer(p2);
        bank.addProperty(pp);
        bank.buyProperty(p, pp);
        p.sellPropertyToPlayer(p2, pp, 120);
        int result = p.getMoney();
        int expResult = 1500 + 60;
        
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
}
