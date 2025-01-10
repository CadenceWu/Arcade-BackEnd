package com.springbootproject.date_night_arcade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbootproject.date_night_arcade.model.Card;
import com.springbootproject.date_night_arcade.repo.CardRepo;

@Service  //Often used to contain business logic
public class CardService {
	@Autowired  //dependency injection. Automatically injects a bean into the class where it is declared.
	private CardRepo repo; //CardRepo(required bean). CardService(target class)

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

	public Card saveCard(Card card) {
		return repo.save(card);
	}

	public void deleteGame(int cardId) {
		repo.deleteById(cardId);
		
	}
}
