package propertytycoongame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Board
 *
 * Class that provides functionality for running the board.
 *
 * Documented by Haotian Jiao
 *
 * @author Zhenyu
 */
public class Board {

    protected HashMap<Integer, Cell> theboard = new HashMap<>();
    private Park park;
    private Jail jail;
    private PotluckCard potluckCard;
    private OpportunityknockCard opportunityknockCard;

    /**
     * Constructor for Board
     */
    public Board() {
        jail = new Jail(11);
        park = new Park(21);
        potluckCard = new PotluckCard();
        opportunityknockCard = new OpportunityknockCard();
        readCsvFile("csv_board.csv");
    }

    /**
     * ReadCsvFile
     * 
     * @param fileName The file name
     */
    public void readCsvFile(String fileName) {
        String line = "";
        String splitBy = ",";
        try {
            //InputStream in = ClassLoader.getSystemResourceAsStream(filePath);
            System.out.println("Checking file");
            //FileReader f = new FileReader("src/propertytycoongame/csv_board.csv");
            //System.out.println();
            File g = new File("src/csv_board.csv");
            System.out.println(g.exists());
            System.out.println(g.getAbsolutePath());
//            File c = new File("csv_board.csv");
//            System.out.println(c.exists());
            Property p1;
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            BufferedReader br = new BufferedReader(new FileReader(g));
            while ((line = br.readLine()) != null) //returns a Boolean value
            {
                String[] current = line.split(splitBy);
                csvList.add(current);

            }
            //delete head of csv
            csvList.remove(0);

            for (int row = 0; row < csvList.size(); row++) {
                p1 = new Property(Integer.valueOf((csvList.get(row)[0])), csvList.get(row)[1], csvList.get(row)[2], Integer.valueOf(csvList.get(row)[3]), Integer.valueOf(csvList.get(row)[4]), Integer.valueOf(csvList.get(row)[5]), Integer.valueOf(csvList.get(row)[6]), Integer.parseInt(csvList.get(row)[7]), Integer.parseInt(csvList.get(row)[8]), Integer.parseInt(csvList.get(row)[9]));
                theboard.put(row, p1);
                if (csvList.get(row)[10].equals("Yes")) {
                    p1.setAvailable(true);
                }
                if (csvList.get(row)[1].equals("Opportunity Knocks")) {
                    theboard.put(row + 1, opportunityknockCard);
                }
                if (csvList.get(row)[1].equals("Pot Luck")) {
                    theboard.put(row + 1, potluckCard);
                }
                if (csvList.get(row)[10].equals("No")) {
                    p1.setAvailable(false);
                }
                if (csvList.get(row)[1].equals("Jail/Just visiting")) {
                    theboard.put(row + 1, jail);
                }
                if (csvList.get(row)[1].equals("Free Parking")) {
                    theboard.put(row + 1, park);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Accesses and returns the field jail.
     *
     * @return Jail - the Jail instance
     */
    public Jail getJail() {
        return jail;
    }

    /**
     * Accesses and returns the field park.
     *
     * @return Park - the Park instance
     */
    public Park getPark() {
        return park;
    }

    /**
     * Gets a cell with specific location.
     *
     * @param loc The specific location
     * @return Cell - a specific cell
     */
    public Cell getCell(int loc) {
        return theboard.get(loc);
    }

    /**
     * Accesses and returns the field potluckCard.
     *
     * @return PotluckCard - the PotluckCard instance
     */
    public PotluckCard getPotluckCard() {
        return potluckCard;
    }

    /**
     * Accesses and returns the field opportunityknockCard.
     *
     * @return OpportunityknockCard - the OpportunityCard instance
     */
    public OpportunityknockCard getOpportunityknockCard() {
        return opportunityknockCard;
    }
}
