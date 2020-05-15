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
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import propertytycoongame.Player.Token;
import tests.*;

/**
 *
 * @author Hayden Banes
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
        System.out.println("? for help\n");
        //System.out.println("")
        while (ptg.mainmenu) {

            System.out.print("main menu> ");
            while (!ptg.reader.ready()) {

            }
            try {
                String input[] = ptg.reader.readLine().split(" ");
                switch (input[0]) {
                    case "gui":
                        System.out.println("Starting GUI\n");

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

                            Result result = junit.run(
                                    //AgentTest.class,
                                    BankTest.class,
                                    Boardtest.class,
                                    CentralControlTest.class,
                                    DiceTest.class,
                                    ParkTest.class,
                                    PlayerTest.class,
                                    TradingTest.class
                            );
                            if (result.wasSuccessful()) {
                                System.out.println("All " + result.getRunCount()
                                        + " tests passed in " + (result.getRunTime() / 1000) + " seconds");
                            } else {
                                System.out.println(result.getFailureCount() + "/"
                                        + result.getRunCount()
                                        + " failed in " + (result.getRunTime() / 1000) + " seconds");
                            }
                        } catch (final Exception e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    default:
                        System.out.println("Command not recognised, use '?' for help");
                        break;
                }
            } catch (Exception e) {
                //System.out.println(e.getMessage());
//                e.printStackTrace();
            }
        }

    }

    public void playerSetup() throws IOException, DuplicateException, Exception {
        boolean setup = true;
        System.out.println("Enter player name and token\ntype 'start' when done\n? for help\n");

        while (setup) {
            try {
                System.out.print("setup> ");
                while (!reader.ready()) {

                }
                String in = reader.readLine();
                if (in.matches("(agent)?(\\s?\\w+\\s[a-z]+)")) {
                    String[] input = in.split(" ");
                    switch (input[0]) {
                        case "agent":
                            game.addAgent(input[1], Token.valueOf(input[2].toLowerCase()));
                            break;
                        default:
                            game.addPlayer(new Player(input[0], Token.valueOf(input[1].toLowerCase())));
                            break;
                    }
                } else {
                    if (in.matches("(\\?)|(start)|(q)")) {
                        switch (in) {
                            case "start":
                                game.firstroll();
                                setup = false;
                                break;
                            case "q":
                                setup = false;
                                break;
                            case "?":
                                System.out.println("Enter playername and token\n"
                                        + "'agent' playername token to add a agent\n\n"
                                        + "q        quit to the main menu\n"
                                        + "start    start the game\n\n"
                                        + "List of tokens:");

                                for (final Token t : Token.values()) {
                                    System.out.println(t.toString());
                                }
                                System.out.println("Type start when ready");
                                break;
                        }
                    } else {
                        System.out.println("usage: [agent] playername token\n? for help");
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
//                e.printStackTrace();
            }
        }
    }

    public void startGame() throws IOException {
        boolean running = true;
        game.firstroll();
        System.out.println("\nProperty Tycoon Game\n? for help\n");
        while (running) {
            try {
                Player p = CentralControl.getCurrentPlayer();
                if (Agent.class.isInstance(p)) {
                    System.out.println("Agent's turn");
                    Agent.class.cast(p).run();
                } else {
                    ArrayList<Player> players = CentralControl.getPlayers();
                    Cell c = CentralControl.board.getCell(p.getLocation());
                    String s = null;
                    if(c instanceof Jail){
                        s = "Jail";
                    } else if(c instanceof Park){
                       s = "Park";
                    } else if(c instanceof PotluckCard){
                       s = "Pot Luck";
                    } else if(c instanceof OpportunityknockCard){
                        s = "Opportunity Knock";
                    } else{
                        s = ((Property) c).getName();
                    }
                    Object[] args = {p.getName(), s, p.getMoney()};
                    System.out.printf("%1s [%2s] [%3s]> ", args);
                    while (!reader.ready()) {
                        //wait for input
                    }
                    String[] input = reader.readLine().split(" ");
                    switch (input[0]) {
                        default:
                            System.out.println("Command not recognised, use ? for help");
                            break;
                        case "roll":
                            if (CentralControl.dices.rollAgain) {
                                p.rollDices();
                                System.out.println("Rolled " + CentralControl.dices.getTotalVal());
                                if (CentralControl.dices.doub) {
                                    System.out.println("Double! roll again");
                                }
                            } else {
                                System.out.println("You cannot roll, pass turn to the next player with [pass]");
                            }
                            break;
                        case "pass":
                            CentralControl.nextPlayer();
                            break;
                        case "buy":
                            Property ppt = (Property) CentralControl.board.getCell(p.getLocation());
                            CentralControl.bank.buyProperty(p, ppt);
                            System.out.println("Purchased: " + ppt.getName() + " for " + ppt.getCost());
                            break;
                        case "list":
                            for (Player pl : players) {
                                Object[] pLargs = {pl.getName(), pl.getToken()};
                                System.out.printf("Name: %1s Token: %2s\n", pLargs);
                            }
                            break;
                        case "?":
                            System.out.println("Controls:\n"
                                    + "roll     rolls the dice\n"
                                    + "pass     passes the turn to the next player\n"
                                    + "buy      buys the property the player is currently on\n"
                                    + "list     lists the players in the game\n"
                                    + "prop     starts property management\n"
                                    + "leave    leave the game\n"
                                    + "voteend  vote to end the current game");
                            break;
                        case "prop":
                            propManage(p);
                            break;
                        case "leave":
                            CentralControl.leaveGame();
                            break;
                        case "voteend":
                            game.voteEnd();
                            break;
                        case "":
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void propManage(Player p) throws IOException, PropertyException, LackMoneyException {
        boolean manage = true;
        System.out.println("type 'done' to return to game\n? for help");
        ArrayList<Property> props = p.getPropertiesList();
        int i = 1;
        if (props.isEmpty()) {
            System.out.println("you do not own any properties");
        } else {
            for (Property prop : props) {
                Object[] args = {prop.getGroup(), prop.getName(), prop.getRent(), prop.getNumOfHouse()};
                System.out.print(i + "> ");
                System.out.printf("[%1s]    %2s     Rent:%3s    Houses:%3s\n", args);
                i++;
            }

            while (manage) {
                System.out.print("prop > ");
                while (!reader.ready()) {
                    //wait for input
                }
                try {
                    String[] input = reader.readLine().split(" ");
                    switch (input[0]) {
                        default:
                            System.out.println("Command not recognised\n? for help");
                            break;
                        case "upgrade":
                            CentralControl.bank.buildHouse(p, props.get(Integer.parseInt(input[1]) - 1));
                            break;
                        case "mortgage":
                            props.get(Integer.parseInt(input[1])-1).mortgage(CentralControl.bank, p);
                            break;
                        case "trade":
                            
                            break;
                        case "done":
                            manage = false;
                            break;
                        case "?":
                            System.out.println("type action and then property number\n"
                                    + "upgrade      builds a house on the chosen property\n"
                                    + "mortgage     mortgages the chosen property\n"
                                    + "done         returns to the game");
                            break;

                    }
                } catch (NullPointerException | IOException | LackMoneyException | PropertyException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }  
}
