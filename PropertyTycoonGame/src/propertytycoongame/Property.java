package propertytycoongame;

import java.util.Stack;

public class Property extends Cell{
	private int price;
	private Stack<House> houses = new Stack<House>();
	private Player owner;
	private int fee;
	private String group;
	
	public Property(int price,String group) {
		// TODO Auto-generated constructor stub
		this.group = group;
		this.price = price;
	}
	
	public boolean buildHouse() {
		if (!houses.isEmpty()) {
			houses.add(new House());
		}
		return houses.isEmpty();
	}
	
	public boolean sellHouse() {
		houses.clear();
		return houses.isEmpty();
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int calculateFee() {
		//wait for improvement
		return fee;
	}
}
