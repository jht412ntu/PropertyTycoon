package propertytycoongame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Agent extends Player{

	public Agent(String name, Token token) {
		super(name, token);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * @author: Mingfeng
	 * @throws BankException 
     * @methodsName: run
     * @description: run agent
     */
	public void run() throws PropertyException, LackMoneyException, BankException {
		// Stay in Jail two turns
		if(CentralControl.board.getJail().turnInJail(this) == 2)
			// Use card or money to release itself
			if(released() || payReleased()) {
				rollDices();
				autoBuyProperty();
				autoBuildHouse();
			}
			else 
				CentralControl.board.getJail().pass(this);
	}
	
	/**
     * @author: Mingfeng
     * @throws PropertyException
     * @methodsName: autoBuyProperty
     * @description: try automatically to buy property in suitable condition(as much as possible)
     */
	public void autoBuyProperty() throws PropertyException {
		Property p = (Property)CentralControl.board.theboard.get(getLocation());
		if (p.isAvailable() == true && p.getOwner() == null && getMoney() >= p.getCost()) {
			CentralControl.bank.buyProperty(this, p);
		}
	}
	
	/**
     * @author: Mingfeng
     * @throws PropertyException,BankException 
     * @methodsName: autoBuildHouse
     * @description: try automatically to buy house in suitable condition( money is greater than 400)
     */
	public void autoBuildHouse() throws PropertyException, BankException {
		ArrayList<Property> propertiesBuild = searchPropertyBuild();
		while (getMoney() >= 400) 
			for (Property property : propertiesBuild) 
				if (getMoney()>= 400) 
					if (property.getNumOfHouse() != 4) 
						CentralControl.bank.buildHouse(this, property);
					else 
						CentralControl.bank.buildHotel(this, property);
	}
	
	/**
     * @author: Mingfeng
     * @return ArrayList<Property>
     * @methodsName: searchPropertyBuild
     * @description: search properties can be build
     */
	public ArrayList<Property> searchPropertyBuild() {
		ArrayList<Property> propertiesBuild = new ArrayList<Property>();
		for (Property property : Properties) {
			if (CentralControl.bank.checkPermission(this, property)) {
				propertiesBuild.add(property);
			}
		}
		Collections.sort(propertiesBuild);
		return propertiesBuild;
	}
}
