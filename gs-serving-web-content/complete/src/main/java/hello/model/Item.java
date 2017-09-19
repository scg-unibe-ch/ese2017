package hello.model;

public class Item {

	private String name;
	private String description;
	private String category;
	private double unitPrice;
	
	public Item(String name, String description, String category, double price) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.unitPrice = price;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}
