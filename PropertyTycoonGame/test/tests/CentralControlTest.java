/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Time;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import propertytycoongame.CentralControl;
import propertytycoongame.DuplicateException;
import propertytycoongame.Player;

/**
 * Sprint1 test and some methods test.
 * 
 * @author Haotian Jiao
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CentralControlTest {
    private Player p1;
    private Player p2;
    private Player p3;
    private CentralControl normal;
    private CentralControl timed;
    
    public CentralControlTest() {
        try {
            p1 = new Player("Player 1", Player.Token.boot);
            p2 = new Player("Player 2", Player.Token.cat);
            p3 = new Player("Player 3", Player.Token.spoon);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        normal = new CentralControl(0);
        timed = new CentralControl(90);
    }

    /**
     * Sprint1: TC4: Central Control
     * 
     * TC4-F1: Determine who’s turn it is.
     * 
     * @throws Exception 
     */
    @Test
    public void testWhosTurnItIs() throws Exception {
        System.out.println("Who's turn it is:");
        normal.addPlayer(p1);
        normal.addPlayer(p2);
        normal.addPlayer(p3);
        normal.nextPlayer();
        Player expResult = p2;
        Player result = normal.getCurrentPlayer();
        System.out.println("Expected result: " + expResult + "\nResult: " + result);
    }
    
    /**
     * Test of getCurrentTime method, of class CentralControl.
     */
    @Test
    public void test0GetCurrentTime() {
        System.out.println("getCurrentTime");
        CentralControl instance = new CentralControl(0);
        Date expResult = new Date();
        Date result = instance.getCurrentTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of addPlayer method, of class CentralControl.
     */
    @Test
    public void test1AddPlayer() throws Exception {
        System.out.println("addPlayer");
        try{
        p1 = new Player("Player1", Player.Token.boot);
        normal.addPlayer(p1);
        } catch (DuplicateException de){
            
        } catch (Exception e){
            
        }
        //System.out.println(normal.getCurrentPlayer());
    }

    /**
     * Test of getCurrentPlayer method, of class CentralControl.
     */
    @Test
    public void test2GetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
//        normal.addPlayer(p2);
//        normal.addPlayer(p1);
        //Player result = normal.getCurrentPlayer();
        //assertEquals(p2, result);
    }

    /**
     * Test of nextPlayer method, of class CentralControl.
     */
    @Test
    public void test3NextPlayer() {
        System.out.println("nextPlayer");
//        normal.addPlayer(p2);
//        normal.addPlayer(p1);
        normal.nextPlayer();
    }

    /**
     * Test of initPlayers method, of class CentralControl.
     */
    @Test
    public void test4InitPlayers() {
        System.out.println("initPlayers");
//        normal.addPlayer(p3);
//        normal.addPlayer(p2);
//        normal.addPlayer(p1);
        System.out.println(normal.getPlayers());
    }

    /**
     * Test of getPlayers method, of class CentralControl.
     */
    @Test
    public void test5GetPlayers() {
        System.out.println("getPlayers");
//        normal.addPlayer(p3);
//        normal.addPlayer(p2);
//        normal.addPlayer(p1);
        System.out.println(normal.getPlayers());
    }

    /**
     * Test of getStartTime method, of class CentralControl.
     */
    @Test
    public void test6GetStartTime() {
        System.out.println("getStartTime");
        Date result = normal.getStartTime();      
        assertEquals(new Date(), result);
        System.out.println(result);
    }

    /**
     * Test of setEndTime method, of class CentralControl.
     */
    @Test
    public void test7SetEndTime() {
        System.out.println("setEndTime");
        normal.setEndTime();
    }

    /**
     * Test of getRemainingTime method, of class CentralControl.
     */
    @Test
    public void test8GetRemainingTime() {
        System.out.println("getRemainingTime");
        Time result = timed.getRemainingTime();
        Time expResult = timed.timeFormat(90*60000);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuration method, of class CentralControl.
     */
    @Test
    public void test9GetDuration() throws InterruptedException {
        System.out.println("getDuration");
        //Thread.currentThread().sleep(1000);
        Time result = timed.getDuration();
        Time expResult = timed.timeFormat(90*60000);
        System.out.println(result);
        System.out.println(expResult);
        assertEquals(expResult, result);
    }
    
}
