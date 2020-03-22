package propertytycoongame;

import java.util.HashMap;

public class Board {

    public HashMap<Integer, Cell> theboard ;

/*
       board=new HashMap<>();
        Cell c1 = new Cell("go", false, null, "Collect £200",null,null,0,0,null);
        Cell c2 = new Cell("Crapper Street", true, "Brown", null,60,2,0,0,null);
        Cell c3 = new Cell("Pot Luck", false, null, "Take card",null,null,0,0,null);
        Cell c4 = new Cell("Gangsters Paradise", true, "Brown", null,60,4,0,0,null);
        Cell c5 = new Cell("Income Tax", false, null, "Pay £200",null,null,0,0,null);
        Cell c6 = new Cell("Brighton Station", true, "Station", null,200,999999999,0,0,null);/////////
        Cell c7 = new Cell("Weeping Angel", true, "Blue", null,100,6,0,0,null);
        Cell c8 = new Cell("Opportunity Knocks", false, null, "Take card",null,null,0,0,null);
        Cell c9 = new Cell("Potts Avenue", true, "Blue", null,100,6,0,0,null);
        Cell c10 = new Cell("Nardole Drive", true, "Blue", null,120,8,0,0,null);
        Cell c11 = new Cell("Jail/Just visiting", false, null, null,null,null,0,0,null);
        Cell c12 = new Cell("Skywalker Drive", true, "Purple", null,140,10,0,0,null);
        Cell c13 = new Cell("Tesla Power Co", true, "Utilities", null,150,9999999,0,0,null);//////
        Cell c14 = new Cell("Wookie Hole", true, "Purple", null,140,10,0,0,null);
        Cell c15 = new Cell("Rey Lane", true, "Purple", null,160,12,0,0,null);
        Cell c16 = new Cell("Hove Station", true, "Station", null,200,999999,0,0,null);////
        Cell c17 = new Cell("Cooper Drive", true, "Orange", null,180,14,0,0,null);
        Cell c18 = new Cell("Pot Luck", false, null, "Take card",null,null,0,0,null);
        Cell c19 = new Cell("Wolowitz Street", true, "Orange", null,180,14,0,0,null);
        Cell c20 = new Cell("Penny Lane", true, "Orange", null,200,16,0,0,null);
        Cell c21 = new Cell("Free Parking", false, null, "Collect fines",null,null,0,0,null);
        Cell c22 = new Cell("Yue Fei Square", true, "Red", null,220,18,0,0,null);
        Cell c23 = new Cell("Opportunity Knocks", false, null, "Take card",null,null,0,0,null);
        Cell c24 = new Cell("Mulan Rouge", true, "Red", null,220,18,0,0,null);
        Cell c25 = new Cell("Han Xin Gardens", true, "Red", null,240,20,0,0,null);
        Cell c26 = new Cell("Falmer Station", true, "Station", null,200,9999999,0,0,null);/////
        Cell c27 = new Cell(" Kirk Close", true, "Yellow", null,260,22,0,0,null);
        Cell c28 = new Cell("Picard Avenue", true, "Yellow", null,260,22,0,0,null);
        Cell c29 = new Cell("Edison Water", true, "Utilities", null,150,999999,0,0,null);////
        Cell c30 = new Cell("Crusher Creek", true, "Yellow", null,280,22,0,0,null);
        Cell c31 = new Cell("Go to Jail", false, null, null,null,null,0,0,null);
        Cell c32 = new Cell("Sirat Mews", true, "Green", null,300,26,0,0,null);
        Cell c33 = new Cell("Ghengis Crescent", true, "Green", null,300,26,0,0,null);
        Cell c34 = new Cell("Pot Luck", false, null, "Take card",null,null,0,0,null);
        Cell c35 = new Cell("Ibis Close", true, "Green", null,320,28,0,0,null);
        Cell c36 = new Cell("Lewes Station", true, "Station", null,200,99999,0,0,null);/////
        Cell c37 = new Cell("Opportunity Knocks", false, null, "Take card",null,null,0,0,null);
        Cell c38 = new Cell("Hawking Way", true, "Deep blue", null,350,35,0,0,null);
        Cell c39 = new Cell("Super Tax", false, null, "Pay £100",null,null,0,0,null);
        Cell c40 = new Cell("Turing Heights", true, "Deep blue", null,400,50,0,0,null);
        board.put(1, c1);
        board.put(2, c2);
        board.put(3, c3);
        board.put(4, c4);
        board.put(5, c5);
        board.put(6, c6);
        board.put(7, c7);
        board.put(8, c8);
        board.put(9, c9);
        board.put(10, c10);
        board.put(11, c11);
        board.put(12, c12);
        board.put(13, c13);
        board.put(14, c14);
        board.put(15, c15);
        board.put(16, c16);
        board.put(17, c17);
        board.put(18, c18);
        board.put(19, c19);
        board.put(20, c20);
        board.put(21, c21);
        board.put(22, c22);
        board.put(23, c23);
        board.put(24, c24);
        board.put(25, c25);
        board.put(26, c26);
        board.put(27, c27);
        board.put(28, c28);
        board.put(29, c29);
        board.put(30, c30);
        board.put(31, c31);
        board.put(32, c32);
        board.put(33, c33);
        board.put(34, c34);
        board.put(35, c35);
        board.put(36, c36);
        board.put(37, c37);
        board.put(38, c38);
        board.put(39, c39);
        board.put(40, c40);

        Cell h = board.get(12);
        System.out.println(h.group);
        //Brown, Blue;
        //Purple, Orange;
        //Red, Yellow;
        // Green, Deep blue;'
    }
    public int buyHouse(Integer i,String h){
        int x=0;

        return x;
    }



*/

    static class Cell {

        public String property;
        public boolean available;
        public String group;
        public String action;
        public String ownedby;
        public Integer cost;
        public Integer housebuilded;
        public Integer hotelbuilded;
        public Integer rent;//unimproved
        public Cell(String property, boolean available, String group, String action, Integer cost, Integer rent ,Integer housebuilded,Integer hotelbuilded,String ownedby) {
            this.property = property;
            this.available=available;
            this.housebuilded=housebuilded;
            this.hotelbuilded=hotelbuilded;
            this.group=group;
            this.action=action;
            this.group=group;
            this.cost=cost;
            this.rent=rent;
            this.ownedby=ownedby;
        }



    }}
