package com.springbootproject.date_night_arcade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
	
	@Id
	@Column(name = "card_id")
	private int cardId;
	private int creditBalance;
	private int ticketBalance;
	
	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", creditBalance=" + creditBalance + ", ticketBalance=" + ticketBalance + "]";
	}

	public Card() {
        this.creditBalance = 0;
        this.ticketBalance = 0;
	}

	public int getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(int creditBalance) {
		this.creditBalance = creditBalance;
	}

	public int getTicketBalance() {
		return ticketBalance;
	}

	public void setTicketBalance(int ticketBalance) {
		this.ticketBalance = ticketBalance;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

}
