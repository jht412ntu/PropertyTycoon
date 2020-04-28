package propertytycoongame;

import java.util.HashMap;

public class Board {

    public HashMap<Integer, Cell> theboard = new HashMap<>();

    private Park park = new Park(21);
    private Jail jail = new Jail(11);
    
    public  Board () {

        //theboard.put(cell.location,cell);
        //Property p1=new Property(1,"go",null,0,0,0,0,0,0,0);
        Cell p2 = new Property(2,"Crapper Street","Brown",60,2,10,30,90, 160,250);
        /**
        PotluckCard potluck=new PotluckCard(3);
        Property p4=new Property(4,"Gangsters Paradise","Brown",60,4,20,60,180,320,450);
        Property p5=new Property(5,"Income Tax",null,0,0,0,0,0,0,0);
        Property p6=new Property(6,"Brighton Station","Station",200,0,0,0,0,0,0);
        Property p7=new Property(7,"Weeping Angel","Blue",100,6,30,90,270,400,550);
        Property p8=new Property(8,"Opportunity Knocks",null,0,0,0,0,0,0,0);
        Property p9=new Property(9,"Potts Avenue","Blue",100,6,30,90,270,400,550);
        Property p10=new Property(10,"Nardole Drive","Blue",120,8,40,100,300,450,600);
        Property p11=new Property(11,"Jail/Just visiting",null,0,0,0,0,0,0,0);
        Property p12=new Property(12,"Skywalker Drive","Purple",140,10,50,150,450,625,750);
        Property p13=new Property(13,"Tesla Power Co","Utilities",150,0,0,0,0,0,0);
        Property p14=new Property(14,"Wookie Hole","Purple",140,10,50,150,450,625,750);
        Property p15=new Property(15,"Rey Lane","Purple",160,12,60,180,500,700,900);
        Property p16=new Property(16,"Hove Station","Station",200,0,0,0,0,0,0);
        Property p17=new Property(17,"Cooper Drive","Orange",180,14,70,200,550,750,900);
        PotluckCard luck2=new PotluckCard(18);
        Property p19=new Property(19,"Wolowitz Street","Orange",180,14,70,200,550,750,950);
        Property p20=new Property(20,"Penny Lane","Orange",200,16,80,220,600,800,1000);
        Property p21=new Property(21,"Free Parking",null,0,0,0,0,0,0,0);
        Property p22=new Property(22,"Yue Fei Square","Red",220,18,90,250,700,875,1050);
        Property p23=new Property(23,"Opportunity Knocks",null,0,0,0,0,0,0,0);
        Property p24=new Property(24,"Mulan Rouge","Red",220,18,90,250,700,875,1050);
        Property p25=new Property(25,"Han Xin Gardens","Red",240,20,100,300,750,925,1100);
        Property p26=new Property(26,"Falmer Station","Station",200,0,0,0,0,0,0);
        Property p27=new Property(27,"Kirk Close","Yellow",260,22,110,330,800,975,1150);
        Property p28=new Property(28,"Picard Avenue","Yellow",260,22,110,330,800,975,1150);
        Property p29=new Property(29,"Edison Water","Utilities",150,0,0,0,0,0,0);
        Property p30=new Property(30,"Crusher Creek","Yellow",280,22,120,360,850,1025,1200);
        Property p31=new Property(31,"Go to Jail",null,0,0,0,0,0,0,0);
        Property p32=new Property(32,"Sirat Mews","Green",300,26,130,390,900,1100,1275);
        Property p33=new Property(33,"Ghengis Crescent","Green",300,26,130,390,900,1100,1275);
        PotluckCard luck3 = new PotluckCard(34);
        Property p35=new Property(35,"Ibis Close","Green",320,28,150,450,1000,1200,1400);
        Property p36=new Property(36,"Lewes Station","Station",200,0,0,0,0,0,0);
        Property p37=new Property(37,"Opportunity Knocks",null,0,0,0,0,0,0,0);
        Property p38=new Property(38,"Hawking Way","Deep blue",350,35,175,500,1100,1300,1500);
        Property p39=new Property(39,"Super Tax",null,0,0,0,0,0,0,0);
        Property p40=new Property(40,"Turing Heights","Deep blue",400,50,200,600,1400,1700,2000);
        p1.available=false;
        p5.available=false;
        p8.available=false;
        p11.available=false;
        p21.available=false;
        p23.available=false;
        p31.available=false;
        p37.available=false;
        p39.available=false;
        theboard.put(1,p1);
        theboard.put(2,p2);
        theboard.put(3,potluck);
        theboard.put(4,p4);
        theboard.put(5,p5);
        theboard.put(6,p6);
        theboard.put(7,p7);
        theboard.put(8,p8);
        theboard.put(9,p9);
        theboard.put(10,p10);
        theboard.put(11,p11);
        theboard.put(12,p12);
        theboard.put(13,p13);
        theboard.put(14,p14);
        theboard.put(15,p15);
        theboard.put(16,p16);
        theboard.put(17,p17);
        theboard.put(18,luck2);
        theboard.put(19,p19);
        theboard.put(20,p20);
        theboard.put(21,park);
        theboard.put(22,p22);
        theboard.put(23,p23);
        theboard.put(24,p24);
        theboard.put(25,p25);
        theboard.put(26,p26);
        theboard.put(27,p27);
        theboard.put(28,p28);
        theboard.put(29,p29);
        theboard.put(30,p30);
        theboard.put(31,jail);
        theboard.put(32,p32);
        theboard.put(33,p33);
        theboard.put(34,luck3);
        theboard.put(35,p35);
        theboard.put(36,p36);
        theboard.put(37,p37);
        theboard.put(38,p38);
        theboard.put(39,p39);
        theboard.put(40,p40);
        */
        //theboard.put(1,p1);
        theboard.put(2,p2);
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
}