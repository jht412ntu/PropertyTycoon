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

    BufferedReader reader;
    boolean mainmenu;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PropertyTycoonGame ptg = new PropertyTycoonGame();
        JUnitCore junit = new JUnitCore();
        
        ptg.reader = new BufferedReader(new InputStreamReader(System.in));
        ptg.mainmenu = true;
        System.out.println("Main Menu\n? for help\n \n ");
        //System.out.println("")
        while (ptg.mainmenu) {

            System.out.print("> ");
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
                        ptg.playerSetup(new CentralControl(0));
                        ptg.startGame();9
                        break;
                    case "abridged":
                        ptg.playerSetup(new CentralControl(Long.getLong(input[1])));
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
     
                            Result result = junit.run(Boardtest.class,DiceTest.class,CentralControlTest.class,PlayerTest.class);
                            if(result.wasSuccessful()){
                                System.out.println("All "+result.getRunCount()+" tests passed in "+ (result.getRunTime()/1000) + " seconds");
                            }else{
                                System.out.println(result.getFailureCount() +"/" +result.getRunCount() +"failed in "+ (result.getRunTime()/1000) + " seconds");
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        
                        break;
                    default:
                        System.out.println("Command not recognised, use '?' for help");
                        break;
                }
            } catch (Exception e) {

            }
        }

    }

    public void playerSetup(CentralControl game) throws IOException, DuplicateException, Exception {
        boolean setup = true;
        System.out.println("Enter player name and token\n? for help\n \n ");
        while (!reader.ready()) {

        }
        while (setup) {
            try {
                String[] input = reader.readLine().split(" ");
                if (input.length != 2 && input[0] != "?") {
                    System.out.println("usage: playername token\n? for help");
                } else {
                    switch (input[0]) {
                        case "?":
                            System.out.println("Enter playername and token\nList of tokens:");

                            for (Token t : Token.values()) {
                                System.out.println(t.toString());
                            }
                            break;
                        default:
                            game.addPlayer(new Player(input[0], Token.valueOf(input[1].toLowerCase())));
                            break;
                    }
                }

            } catch (Exception e) {

            }
        }
    }

//    
}
