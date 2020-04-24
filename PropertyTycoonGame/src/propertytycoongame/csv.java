package propertytycoongame;


import java.nio.charset.Charset;
import java.util.ArrayList;


import com.csvreader.CsvReader;

import java.util.HashMap;

import com.csvreader.CsvReader;


public class csv {
    public static void readCsvFile(String filePath){
        try {

           Property p1;
            HashMap<Integer, Property> the = new HashMap();
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(filePath,',',Charset.forName("GBK"));
            reader.readHeaders();

            while(reader.readRecord()){
                csvList.add(reader.getValues()); //按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数："+csvList.size());
            for(int row=0;row<csvList.size();row++){
               p1=new Property(Integer.valueOf((csvList.get(row)[0])),csvList.get(row)[1],csvList.get(row)[2],Integer.valueOf(csvList.get(row)[3]),Integer.valueOf(csvList.get(row)[4]),Integer.valueOf(csvList.get(row)[5]),Integer.valueOf(csvList.get(row)[6]),Integer.parseInt(csvList.get(row)[7]),Integer.parseInt(csvList.get(row)[8]),Integer.parseInt(csvList.get(row)[9]));
                the.put(row,p1);
                if(csvList.get(row)[10].equals("Yes")){
                    p1.available=true;
                }
                if(csvList.get(row)[10].equals("No")){
                    p1.available=false;
                }

            }

            Property yuuy=  the.get(2);
            System.out.println("  ewwe "+yuuy.name);
            System.out.println("  ewwe "+yuuy.available);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/zhenyutang/Downloads/Book1.csv";
        readCsvFile(filePath);
    }
}

