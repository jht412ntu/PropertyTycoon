/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertytycoongame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        System.out.println("Main Menu\n? for help\n \n ");
        //System.out.println("")
        while (ptg.mainmenu) {

            System.out.print("> ");
            while (!ptg.reader.ready()) {

            }
            try {
                final String input[] = ptg.reader.readLine().split(" ");
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
                        ptg.game = new CentralControl(Long.getLong(input[1]))
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
                        try{
     
                            final Result result = junit.run(Boardtest.class,DiceTest.class,CentralControlTest.class,PlayerTest.class);
                            if(result.wasSuccessful()){
                                System.out.println("All "+result.getRunCount()+" tests passed in "+ (result.getRunTime()/1000) + " seconds");
                            }else{
                                System.out.println(result.getFailureCount() +"/" +result.getRunCount() +"failed in "+ (result.getRunTime()/1000) + " seconds");
                            }
                        }catch (final Exception e){
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
        final boolean setup = true;
        System.out.println("Enter player name and token\n? for help\n \n ");
        while (!reader.ready()) {

        }
        while (setup) {
            try {
                final String[] input = reader.readLine().split(" ");
                if (input.length != 2 && input[0] != "?") {
                    System.out.println("usage: playername token\n? for help");
                } else {
                    switch (input[0]) {
                        case "?":
                            System.out.println("Enter playername and token\nList of tokens:");

                            for (final Token t : Token.values()) {
                                System.out.println(t.toString());
                            }
                            break;
                        default:
                            game.addPlayer(new Player(input[0], Token.valueOf(input[1].toLowerCase())));
                            break;
                    }
                }

            } catch (final Exception e) {

            }
        }
    }
    public void startGame() throws IOException {
        boolean running = true;
        game.firstroll();
        System.out.println("Property Tycoon Game\n? for help\n");
        while(running){
            Player p = CentralControl.getCurrentPlayer();
            Object[] args = {p.getName(),p.getLocation(),p.getMoney()};
            System.out.printf("{%1s} [{%2s}] [{%3s}]", args);
            while(!reader.ready()){
                //wait for input
            }
            try{
                final String[] input = reader.readLine().split(" ");
                switch(input[0]){
                    default: 
                    break;
                    case "roll":
                    break;
                    case ""
                }
            }
        }
    }

//    
}
