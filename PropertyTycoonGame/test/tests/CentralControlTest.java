/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Time;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import propertytycoongame.CentralControl;
import propertytycoongame.Player;

/**
 *
 * @author Hallton-PC
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CentralControlTest {
    private Player p1;
    private Player p2;
    private Player p3;
    private CentralControl normal;
    private CentralControl timed;
    
    public CentralControlTest() {
        p1 = new Player(0, Player.Token.boot);
        p2 = new Player(10, Player.Token.cat);
        p3 = new Player(10, Player.Token.spoon);
        normal = new CentralControl(0);
        timed = new CentralControl(90);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
    public void test1AddPlayer() {
        System.out.println("addPlayer");
        normal.addPlayer(p1);
        System.out.println(normal.getCurrentPlayer());
    }

    /**
     * Test of getCurrentPlayer method, of class CentralControl.
     */
    @Test
    public void test2GetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        normal.addPlayer(p2);
        normal.addPlayer(p1);
        Player result = normal.getCurrentPlayer();
        assertEquals(p2, result);
    }

    /**
     * Test of nextPlayer method, of class CentralControl.
     */
    @Test
    public void test3NextPlayer() {
        System.out.println("nextPlayer");
        normal.addPlayer(p2);
        normal.addPlayer(p1);
        normal.nextPlayer();
    }

    /**
     * Test of initPlayers method, of class CentralControl.
     */
    @Test
    public void test4InitPlayers() {
        System.out.println("initPlayers");
        normal.addPlayer(p3);
        normal.addPlayer(p2);
        normal.addPlayer(p1);
        System.out.println(normal.getPlayers());
    }

    /**
     * Test of getPlayers method, of class CentralControl.
     */
    @Test
    public void test5GetPlayers() {
        System.out.println("getPlayers");
        normal.addPlayer(p3);
        normal.addPlayer(p2);
        normal.addPlayer(p1);
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
