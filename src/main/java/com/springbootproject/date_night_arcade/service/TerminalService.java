package com.springbootproject.date_night_arcade.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbootproject.date_night_arcade.model.PrizeCategory;
import com.springbootproject.date_night_arcade.model.Card;

import jakarta.annotation.PostConstruct;

@Service
public class TerminalService {
	@Autowired
	private CardService cardService;
	@Autowired
	private PrizeCategoryService prizeCategoryService;

	/* 轉換卡片裡全部的代碼 */
	public void transferCredits(Card sourceCard, Card targetCard) {
		if (sourceCard == null || targetCard == null) {
			throw new RuntimeException("Both source and target cards must exist");
		}

		// Add validation for same card
		if (sourceCard.getCardId() == targetCard.getCardId()) {
			throw new RuntimeException("Source and target cards cannot be the same");
		}

		if (sourceCard.getCreditBalance() <= 0) {
			throw new RuntimeException("Source card has no credits to transfer");
		}

		targetCard.setCreditBalance(targetCard.getCreditBalance() + sourceCard.getCreditBalance());
		sourceCard.setCreditBalance(0);
	}

	/* 轉換全部的票券 */
	public void transferTickets(Card sourceCard, Card targetCard) {
		if (sourceCard == null || targetCard == null) {
			throw new RuntimeException("Both source and target cards must exist");
		}

		// Add validation for same card
		if (sourceCard.getCardId() == targetCard.getCardId()) {
			throw new RuntimeException("Source and target cards cannot be the same");
		}

		if (sourceCard.getTicketBalance() <= 0) {
			throw new RuntimeException("Source card has no tickets to transfer");
		}

		targetCard.setTicketBalance(targetCard.getTicketBalance() + sourceCard.getTicketBalance());
		sourceCard.setTicketBalance(0);
	}

	public void requestPrize(int cardId, int prizeNumber) {
		Card card = cardService.getCard(cardId);
		PrizeCategory prize = prizeCategoryService.getPrizeById(prizeNumber);

		if (prize == null) {
			throw new RuntimeException("Prize not found");
		}

		if (prize.getStockQuantity() <= 0) {
			throw new RuntimeException("Prize out of stock");
		}

		if (card.getTicketBalance() < prize.getRequiredTickets()) {
			throw new RuntimeException(
					"Insufficient tickets: " + card.getTicketBalance() + "/" + prize.getRequiredTickets());
		}

		// Deduct tickets
		card.setTicketBalance(card.getTicketBalance() - prize.getRequiredTickets());
		// Reduce stock
		prize.consumeItem();

		// Save changes
		cardService.save(card);
		prizeCategoryService.savePrize(prize);
	}

	public Card playGame(int cardId, int creditsToDeduct, int ticketsToAdd) {
		if (creditsToDeduct <= 0) {
			throw new IllegalArgumentException("creditsToDeduct must be greater than zero");
		}

		Card card = cardService.getCard(cardId);

		if (card.getCreditBalance() < creditsToDeduct) {
			throw new IllegalArgumentException("Not enough credits");
		}

		card.setCreditBalance(card.getCreditBalance() - creditsToDeduct);

		if (ticketsToAdd > 0) {
			card.setTicketBalance(card.getTicketBalance() + ticketsToAdd);
		}

		return cardService.save(card);
	}

//  public void transferCredits(Card sourceCard, Card targetCard, int amount) {
//  if (sourceCard == null || targetCard == null) {
//      throw new RuntimeException("Both source and target cards must exist");
//  }
//  
//  if (sourceCard.getCreditBalance() < amount) {
//      throw new RuntimeException("Source card has insufficient credits to transfer");
//  }
//  
//  targetCard.setCreditBalance(targetCard.getCreditBalance() + amount);
//  sourceCard.setCreditBalance(sourceCard.getCreditBalance() - amount);
//  
//  // Save changes
//  cardService.createCard(sourceCard);
//  cardService.createCard(targetCard);
//}
//
//public void transferTickets(Card sourceCard, Card targetCard, int amount) {
//  if (sourceCard == null || targetCard == null) {
//      throw new RuntimeException("Both source and target cards must exist");
//  }
//  
//  if (sourceCard.getTicketBalance() < amount) {
//      throw new RuntimeException("Source card has insufficient tickets to transfer");
//  }
//  
//  targetCard.setTicketBalance(targetCard.getTicketBalance() + amount);
//  sourceCard.setTicketBalance(sourceCard.getTicketBalance() - amount);
//  
//  // Save changes
//  cardService.createCard(sourceCard);
//  cardService.createCard(targetCard);
//}
}
