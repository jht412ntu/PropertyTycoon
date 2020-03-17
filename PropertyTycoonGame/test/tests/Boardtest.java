import java.util.HashMap;

public class Boardtest {

    public static void main(String[] args) {
        Board board = new Board();
        Board.Cell c1 = new Board.Cell("go", false, null, "Collect £200",null,null,0,0,null);
        System.out.println(c1.available);
        board.theboard = new HashMap<>();
        board.theboard.put(1,c1);

        Board.Cell h=board.theboard.get(1);
        System.out.println(h.group);
    }
}
