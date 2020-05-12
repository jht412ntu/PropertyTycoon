/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoongame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import propertytycoongame.Player.Token;
import tests.*;

/**
 *
 * @author Hayden
 */
public class PropertyTycoonGame {

    CentralControl game;
    BufferedReader reader;
    boolean mainmenu;

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws IOException {
        final PropertyTycoonGame ptg = new PropertyTycoonGame();
        final JUnitCore junit = new JUnitCore();

        ptg.reader = new BufferedReader(new InputStreamReader(System.in));
        ptg.mainmenu = true;
        System.out.println("? for help\n \n ");
        //System.out.println("")
        while (ptg.mainmenu) {

            System.out.print("main menu> ");
            while (!ptg.reader.ready()) {

            }
            try {
                String input[] = ptg.reader.readLine().split(" ");
                switch (input[0]) {
                    case "gui":
                        System.out.println("Starting GUI \n\n\n");

                        GameGUI.Start();
                        ptg.mainmenu = false;
                        break;
                    case "full":
                        ptg.game = new CentralControl(0);
                        ptg.playerSetup();
                        ptg.startGame();
                        break;
                    case "abridged":
                        ptg.game = new CentralControl(Long.getLong(input[1]));
                        ptg.playerSetup();
                        ptg.startGame();
                    case "?":
                        System.out.println("List of commands:\n"
                                + "gui          starts the game with GUI\n"
                                + "full         starts a full game\n"
                                + "abridged     starts an abridged game\n"
                                + "test         runs built in tests\n"
                                + "q            quits the game\n");
                        break;
                    case "q":
                        System.out.println("Closing game...");
                        ptg.mainmenu = false;
                        break;
                    case "test":
                        System.out.println("Running tests");
                        try {

                            final Result result = junit.run(Boardtest.class, DiceTest.class, CentralControlTest.class, PlayerTest.class);
                            if (result.wasSuccessful()) {
                                System.out.println("All " + result.getRunCount() + " tests passed in " + (result.getRunTime() / 1000) + " seconds");
                            } else {
                                System.out.println(result.getFailureCount() + "/" + result.getRunCount() + "failed in " + (result.getRunTime() / 1000) + " seconds");
                            }
                        } catch (final Exception e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    default:
                        System.out.println("Command not recognised, use '?' for help");
                        break;
                }
            } catch (final Exception e) {

            }
        }

    }

    public void playerSetup() throws IOException, DuplicateException, Exception {
        boolean setup = true;
        System.out.println("Enter player name and token\ntype start when done\n? for help\n \n ");

        while (setup) {
            try {
                System.out.print("setup> ");
                while (!reader.ready()) {

                }
                final String[] input = reader.readLine().split(" ");
                if ((input.length != 2 && !input[0].equals("?")) && !input[0].equals("done")) {
                    System.out.println("usage: playername token\n? for help");
                } else {
                    switch (input[0]) {
                        case "?":
                            System.out.println("Enter playername and token\nList of tokens:");

                            for (final Token t : Token.values()) {
                                System.out.println(t.toString());
                            }
                            break;
                        case "done":
                            game.initPlayers();
                            setup = false;
                            break;
                        default:
                            game.addPlayer(new Player(input[0], Token.valueOf(input[1].toLowerCase())));
                            break;
                    }
                }

            } catch (final Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void startGame() throws IOException {
        boolean running = true;
        game.firstroll();
        System.out.println("Property Tycoon Game\n? for help\n");
        while (running) {
            Player p = CentralControl.getCurrentPlayer();
            ArrayList<Player> players = CentralControl.players;
            Object[] args = {p.getName(), p.getLocation(), p.getMoney()};
            System.out.printf("%1s [%2s] [%3s]> ", args);
            while (!reader.ready()) {
                //wait for input
            }
            try {
                String[] input = reader.readLine().split(" ");
                switch (input[0]) {
                    default:
                        System.out.println("Command not recognised, use ? for help");
                        break;
                    case "roll":
                        p.rollDices();
                        System.out.println("Rolled " + CentralControl.dices.getTotalVal());
                        break;
                    case "pass":
                        game.nextPlayer();
                        break;
                    case "buy":
                        CentralControl.bank.buyProperty(p, (Property) CentralControl.board.getCell(p.getLocation()));
                        break;
                    case "list":
                        for (Player pl : players) {
                            Object[] pLargs = {pl.getName(), pl.getToken()};
                            System.out.printf("Name: %1s Token: %2s\n", pLargs);
                        }
                        break;
                    case "?":
                        break;
                    case "prop":
                        propManage(p);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                for (StackTraceElement ste : e.getStackTrace()) {
                    System.out.println(ste.toString());
                }
            }
        }
    }
    
    public void propManage(Player p) throws IOException, PropertyException, LackMoneyException{
        boolean manage = true;
        System.out.println("type 'done' to return to game\n? for help");
        ArrayList<Property> props = p.getPropertiesList();
        int i = 1;
        for(Property prop: props){
            Object[] args = {prop.getGroup(), prop.getName(), prop.getRent(), prop.getNumOfHouse()};
            System.out.print(i + "> ");
            System.out.printf("%1s %2s Rent:%3s Houses:%3s", args);
            i++;
        }
         while (!reader.ready()) {
                //wait for input
            }
         while(manage){
            try {
                String[] input = reader.readLine().split(" ");
                switch(input[1]){
                    default:
                        System.out.println("Command not recognised\n? for help");
                        break;
                    case "upgrade":
                        CentralControl.bank.buildHouse(p, props.get(Integer.getInteger(input[0])-1));
                        break;
                    case "done":
                        manage = false;
                        break;
                }
            }catch (IOException | LackMoneyException | PropertyException e) {
                
            }
         }
    }

//    
}
