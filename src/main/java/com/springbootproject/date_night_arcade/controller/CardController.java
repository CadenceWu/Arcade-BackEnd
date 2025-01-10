package com.springbootproject.date_night_arcade.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.date_night_arcade.model.Card;
import com.springbootproject.date_night_arcade.model.PrizeCategory;
import com.springbootproject.date_night_arcade.service.CardService;
import com.springbootproject.date_night_arcade.service.PrizeCategoryService;

@RestController
@RequestMapping("/api")
public class CardController {

	@Autowired 
	private CardService service;

	@GetMapping("/cards")
	public List<Card> getAllCards() {
		return service.getAllCards();
	}

	@PostMapping("/cards")
	public Card createCard(@RequestBody Card card) {
		return service.createCard(card);
	}

	@PutMapping("/cards/{cardId}/increment")
	public Card addCredits(@PathVariable int cardId, @RequestBody Map<String, Integer> request) {
		return service.addCredits(cardId, request.get("amount"));
	}
	
	@PutMapping("/cards/{cardId}/decrement")
	public Card decrementCredits(@PathVariable int cardId, @RequestBody Map<String, Integer> request) {
	    Integer amount = request.get("amount");
	    if (amount == null || amount <= 0) {
	        throw new IllegalArgumentException("Amount must be greater than zero");
	    }
	    return service.decrementCredits(cardId, amount);
	}
    @DeleteMapping("/cards/{cardId}")
    public void deleteGame(@PathVariable int cardId) { 
        service.deleteGame(cardId);
    }

	@GetMapping("/{cardId}")
	public Card getCard(@PathVariable int cardId) {
		return service.getCard(cardId);
	}

	@GetMapping("/cards/{cardId}")
	public ResponseEntity<Card> getCardById(@PathVariable int cardId) {
		try {
			Card card = service.getCard(cardId);
			return ResponseEntity.ok(card);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
