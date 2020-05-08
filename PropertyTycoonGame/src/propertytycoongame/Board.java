package propertytycoongame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    protected HashMap<Integer, Cell> theboard = new HashMap<>();

    private Park park ;
    private Jail jail ;
    private PotluckCard potluckCard;
    private OpportunityknockCard opportunityknockCard;
    public  Board () {
    	jail = new Jail(11);
    	park = new Park(21);
    	potluckCard = new PotluckCard();
    	opportunityknockCard = new OpportunityknockCard();
    	readCsvFile("src/propertytycoongame/csv_board.csv");
    }
    
    public void readCsvFile(String fileName){
        String line = "";
        String splitBy = ",";
        try {
            //InputStream in = ClassLoader.getSystemResourceAsStream(filePath);
           Property p1;
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] current= line.split(splitBy);
                csvList.add(current);

            }
            //delete head of csv
            csvList.remove(0);

            for(int row=1;row<csvList.size();row++){
               p1=new Property(Integer.valueOf((csvList.get(row)[0])),csvList.get(row)[1],csvList.get(row)[2],Integer.valueOf(csvList.get(row)[3]),Integer.valueOf(csvList.get(row)[4]),Integer.valueOf(csvList.get(row)[5]),Integer.valueOf(csvList.get(row)[6]),Integer.parseInt(csvList.get(row)[7]),Integer.parseInt(csvList.get(row)[8]),Integer.parseInt(csvList.get(row)[9]));
                CentralControl.board.theboard.put(row,p1);
                if(csvList.get(row)[10].equals("Yes")){
                    p1.setAvailable(true);
                }
                if(csvList.get(row)[1] .equals( "Opportunity Knocks")){
                   theboard.put(row,opportunityknockCard);
                }
                if(csvList.get(row)[1].equals( "Pot Luck")){
                    theboard.put(row,potluckCard);
                }
                if(csvList.get(row)[10].equals("No")){
                    p1.setAvailable(false);
                }
                if(csvList.get(row)[1].equals("Jail/Just visiting")){
                    theboard.put(row,jail);
                }
                if(csvList.get(row)[1].equals( "Free Parking")){
                   theboard.put(row,park);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Jail getJail() {
    	return jail;
    }
    
    public Park getPark() {
        return park;
    }

    public Cell getCell(int loc){
        return theboard.get(loc);
    }
    
    public PotluckCard getPotluckCard() {
		return potluckCard;
	}
    
    public OpportunityknockCard getOpportunityknockCard() {
		return opportunityknockCard;
	}
}