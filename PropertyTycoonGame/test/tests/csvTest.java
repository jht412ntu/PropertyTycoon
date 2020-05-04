package tests;
import propertytycoongame.csv;
import org.junit.Test;

public class csvTest {
    @Test
    public void testreadCsvFile(){
        csv.readCsvFile("csv_board.csv");

    }

}
