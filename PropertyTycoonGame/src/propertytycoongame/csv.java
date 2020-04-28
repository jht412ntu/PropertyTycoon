package propertytycoongame;


import java.nio.charset.Charset;
import java.util.ArrayList;


import com.csvreader.CsvReader;

import java.util.HashMap;

import com.csvreader.CsvReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class csv {
    public static void readCsvFile(String filePath){
        try {
            //InputStream in = ClassLoader.getSystemResourceAsStream(filePath);
           Property p1;
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(filePath,',',Charset.forName("GBK"));
            reader.readHeaders();

            while(reader.readRecord()){
                csvList.add(reader.getValues()); //
            }
            reader.close();

            for(int row=0;row<csvList.size();row++){
               p1=new Property(Integer.valueOf((csvList.get(row)[0])),csvList.get(row)[1],csvList.get(row)[2],Integer.valueOf(csvList.get(row)[3]),Integer.valueOf(csvList.get(row)[4]),Integer.valueOf(csvList.get(row)[5]),Integer.valueOf(csvList.get(row)[6]),Integer.parseInt(csvList.get(row)[7]),Integer.parseInt(csvList.get(row)[8]),Integer.parseInt(csvList.get(row)[9]));
                CentralControl.board.theboard.put(row,p1);
                if(csvList.get(row)[10].equals("Yes")){
                    p1.available=true;
                }
                if(csvList.get(row)[1] .equals( "Opportunity Knocks")){
                   CentralControl.board.theboard.put(row,CentralControl.opportunityknockCard);
                }
                if(csvList.get(row)[1].equals( "Pot Luck")){
                    CentralControl.board.theboard.put(row,CentralControl.potluckCard);
                }
                if(csvList.get(row)[10].equals("No")){
                    p1.available=false;
                }
                if(csvList.get(row)[1].equals("Jail/Just visiting")){
                    CentralControl.board.theboard.put(row,CentralControl.jail);
                }
                if(csvList.get(row)[1].equals( "Free Parking")){
                    CentralControl.board.theboard.put(row,CentralControl.park);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        String filePath = "/Users/zhenyutang/Downloads/Book1.csv";
        readCsvFile(filePath);
    }*/
}

