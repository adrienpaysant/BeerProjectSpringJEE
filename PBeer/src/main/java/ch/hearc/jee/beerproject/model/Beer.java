package ch.hearc.jee.beerproject.model;

public class Beer {

	private String name;
	private double price;
	private int index;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Beer() {

	}

	public Beer(String name, double price, int index) {
		this.name = name;
		this.price = price;
		this.index = index;
	}

}
