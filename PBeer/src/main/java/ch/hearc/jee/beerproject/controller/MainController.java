package ch.hearc.jee.beerproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee.beerproject.form.BeerForm;
import ch.hearc.jee.beerproject.model.Beer;

@Controller
public class MainController {

	private static List<Beer> beers = new ArrayList<Beer>();
	private static int index = 3;

	static {
		beers.add(new Beer("Lupulus", 10, 0));
		beers.add(new Beer("Chouffe", 5, 1));
		beers.add(new Beer("Namur", 15, 2));
	}

	// Injectez (inject) via application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/beer/{index}/delete" }, method = RequestMethod.GET)
	public String beerDelete(Model model, @PathVariable("index") int index) {

		List<Beer> tBeers = new ArrayList<Beer>();
		int newIndex = 0;
		for (Beer beer : beers) {
			if (beer.getIndex() != index) {
				tBeers.add(new Beer(beer.getName(), beer.getPrice(), newIndex++));
			}
		}
		beers.clear();
		beers = tBeers;
		this.index = newIndex;
		return "redirect:/beerList";
	}

	@GetMapping(value = { "beer/{index}" })
	public String beer(Model model, @PathVariable("index") int index) {
		List<Beer> tBeers = new ArrayList<Beer>();
		tBeers.add(this.beers.get(index));
		model.addAttribute("beers", tBeers);
		BeerForm beerForm = new BeerForm();
		model.addAttribute("beerForm", beerForm);
		return "beerDetail";
	}

	@RequestMapping(value = { "/beerList" }, method = RequestMethod.GET)
	public String beerList(Model model) {

		model.addAttribute("beers", beers);

		return "beerList";
	}

	@RequestMapping(value = { "/addBeer" }, method = RequestMethod.GET)
	public String showAddBeerPage(Model model) {

		BeerForm beerForm = new BeerForm();
		model.addAttribute("beerForm", beerForm);

		return "addBeer";
	}

	@RequestMapping(value = { "/addBeer" }, method = RequestMethod.POST)
	public String saveBeer(Model model, //
			@ModelAttribute("beerForm") BeerForm beerForm) {

		String name = beerForm.getName();
		double price = beerForm.getPrice();

		if (name != null && name.length() > 0 //
				&& price > 0) {
			Beer newBeer = new Beer(name, price, index++);
			beers.add(newBeer);

			return "redirect:/beerList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addBeer";
	}

	@RequestMapping(value = { "/beer/{index}/update" }, method = RequestMethod.POST)
	public String updateBeer(Model model, //
			@ModelAttribute("beerForm") BeerForm beerForm, @PathVariable("index") int index) {

		String name = beerForm.getName();
		double price = beerForm.getPrice();

		if (name != null && name.length() > 0 //
				&& price > 0) {
			beers.get(index).setName(beerForm.getName());
			beers.get(index).setPrice(beerForm.getPrice());
			return "redirect:/beerList";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "redirect:/beerList";
	}

}