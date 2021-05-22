package mibay;

import main.Menu;

public class Product {
	private String name;
	private int weight;
	private double cost;
	
	public Product(String name, int weight, double cost) {
		this.name = name;
		
//		try {
//			this.weight = weight;
//		}catch(NumberFormatException e) {
//			//throw new ("First Name cannot be null");
//			System.err.println(" cannot be null");
//			System.out.println();
//			new Menu().run();
//		}
		this.weight = weight;
		this.cost = cost;
	}

	public String getName() {return name; }
	public int getWeight() { return weight; }
	public double getCost() { return cost; }

}
