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

	public void transferCredits(Card sourceCard, Card targetCard, int amount) {
		if (sourceCard == null || targetCard == null) {
			throw new RuntimeException("來源卡片或目標卡片不存在");
		}

		if (sourceCard.getCardId() == targetCard.getCardId()) {
			throw new RuntimeException("來源卡片及目標卡片不能相同");
		}

		if (amount <= 0) {
			throw new RuntimeException("轉換數量必須大於0");
		}

		if (sourceCard.getCreditBalance() < amount) {
			throw new RuntimeException("來源卡片代碼不足");
		}

		targetCard.setCreditBalance(targetCard.getCreditBalance() + amount);
		sourceCard.setCreditBalance(sourceCard.getCreditBalance() - amount);
	}

	public void transferTickets(Card sourceCard, Card targetCard, int amount) {
		if (sourceCard == null || targetCard == null) {
			throw new RuntimeException("來源卡片或目標卡片不存在");
		}

		if (sourceCard.getCardId() == targetCard.getCardId()) {
			throw new RuntimeException("來源卡片及目標卡片不能相同");
		}

		if (amount <= 0) {
			throw new RuntimeException("轉換數量必須大於0");
		}

		if (sourceCard.getTicketBalance() < amount) {
			throw new RuntimeException("來源卡片票券不足");
		}

		targetCard.setTicketBalance(targetCard.getTicketBalance() + amount);
		sourceCard.setTicketBalance(sourceCard.getTicketBalance() - amount);
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

		return cardService.saveCard(card);
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
		cardService.saveCard(card);
		prizeCategoryService.savePrize(prize);
	}

}
