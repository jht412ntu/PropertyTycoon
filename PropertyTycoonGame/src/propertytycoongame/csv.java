package propertytycoongame;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class csv {
    public static void readCsvFile(String fileName){
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
            br.close();

            for(int row=1;row<csvList.size();row++){
               p1=new Property(Integer.valueOf((csvList.get(row)[0])),csvList.get(row)[1],csvList.get(row)[2],Integer.valueOf(csvList.get(row)[3]),Integer.valueOf(csvList.get(row)[4]),Integer.valueOf(csvList.get(row)[5]),Integer.valueOf(csvList.get(row)[6]),Integer.parseInt(csvList.get(row)[7]),Integer.parseInt(csvList.get(row)[8]),Integer.parseInt(csvList.get(row)[9]));
                CentralControl.board.theboard.put(row,p1);
                if(csvList.get(row)[10].equals("Yes")){
                    p1.setAvailable(true);
                }
                if(csvList.get(row)[1] .equals( "Opportunity Knocks")){
                   CentralControl.board.theboard.put(row,CentralControl.board.getOpportunityknockCard());
                }
                if(csvList.get(row)[1].equals( "Pot Luck")){
                    CentralControl.board.theboard.put(row,CentralControl.board.getPotluckCard());
                }
                if(csvList.get(row)[10].equals("No")){
                    p1.setAvailable(false);
                }
                if(csvList.get(row)[1].equals("Jail/Just visiting")){
                    CentralControl.board.theboard.put(row,CentralControl.board.getJail());
                }
                if(csvList.get(row)[1].equals( "Free Parking")){
                    CentralControl.board.theboard.put(row,CentralControl.board.getPark());
                }

            }
           // System.out.println(csvList.size());
            //System.out.println(csvList.get(0)[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        String filePath = "csv_board.csv";
        readCsvFile(filePath);


    }
    */}

