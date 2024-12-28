package com.springbootproject.date_night_arcade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.date_night_arcade.model.PrizeCategory;
import com.springbootproject.date_night_arcade.service.PrizeCategoryService;

@RestController
@RequestMapping("/api") 
public class PrizeCategoryController {

	@Autowired
	private PrizeCategoryService service;

	@GetMapping("/prizes")
	public List<PrizeCategory> getPrizes() {
		//System.out.println("Getting all prizes"); 
		return service.getPrizes();
	}

	@PostMapping("/prizes")
	public PrizeCategory createPrize(@RequestBody PrizeCategory prize) {
		return service.createPrize(prize);
	}

	@PutMapping("/prizes/{prizeNumber}")
	public PrizeCategory updatePrize(@PathVariable int prizeNumber, @RequestBody PrizeCategory prize) {
		return service.updatePrize(prizeNumber, prize);
	}
	
    @DeleteMapping("/prizes/{prizeNumber}")
    public void deletePrize(@PathVariable int prizeNumber) {
        service.deletePrize(prizeNumber);
    }

}
