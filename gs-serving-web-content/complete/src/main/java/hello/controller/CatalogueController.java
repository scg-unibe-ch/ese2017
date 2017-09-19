package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.model.Catalogue;
import hello.model.Item;

@Controller
public class CatalogueController {

	Catalogue catalogue = new Catalogue();
	
	@RequestMapping("/catalogue")
	public String catalogue(@RequestParam(value="itemId", required=true) String itemIdString, Model model) {
		int itemId = Integer.parseInt(itemIdString);
		Item item = catalogue.getById(itemId);
		model.addAttribute("item", item); //the model object "carries" objects from the controller to the view
		return "item";
	}
	
}
