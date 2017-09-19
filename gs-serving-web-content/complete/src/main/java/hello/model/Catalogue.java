package hello.model;

import java.util.ArrayList;

public class Catalogue {
	
	private ArrayList<Item> items;
	
	public Catalogue() {
		build();
	}
	
	public Item getById(int id) {
		return items.get(id);
	}
	
	private void build() {
		items = new ArrayList();
		items.add(new Item("Lenovo ThinkPad T460s", "High-end business notebook. Intel i7 Processor, 20GB RAM, 512GB SSD", "Notebooks", 1599.00));
		items.add(new Item("Logitech G910 Orion Spectrum", "Romer-G Switch gaming keyboard with 9 programmable keys", "Periphery", 159.00));
		items.add(new Item("Logitech G502 Proteus Spectrum", "High precision gaming mouse", "Periphery", 79.70));
		items.add(new Item("Nordic Thingy 52", "IoT sensor box (air pressure, CO2, temperature, acceleration and more) with bluetooth interface", "IoT", 41.20));
		items.add(new Item("Arduino UNO", "Low power microcontroller with 12 digital and 5 analogue GPIO pins", "IoT", 32.00));
	}

}
