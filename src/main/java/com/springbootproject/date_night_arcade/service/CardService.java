package com.springbootproject.date_night_arcade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbootproject.date_night_arcade.model.Card;
import com.springbootproject.date_night_arcade.repo.CardRepo;

@Service
public class CardService {
	@Autowired
	private CardRepo repo;

	public List<Card> getAllCards() {
		return repo.findAll();
	}

	public Card createCard(Card card) {
		card.setCreditBalance(0);
		card.setTicketBalance(0);
		return repo.save(card);
	}

	public Card addCredits(int cardId, Integer amount) {
		Card card = repo.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));
		card.setCreditBalance(card.getCreditBalance() + amount);
		return repo.save(card);
	}
	public Card decrementCredits(int cardId, Integer amount) {
	    Card card = repo.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));

	    if (card.getCreditBalance() < amount) {
	        throw new IllegalArgumentException("Insufficient credits to decrement");
	    }

	    card.setCreditBalance(card.getCreditBalance() - amount);
	    return repo.save(card);
	}

	public Card getCard(int cardId) {
		return repo.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found with ID: " + cardId));
	}

	public Card save(Card card) {
		return repo.save(card);
	}

	public void deleteGame(int cardId) {
		repo.deleteById(cardId);
		
	}
}
