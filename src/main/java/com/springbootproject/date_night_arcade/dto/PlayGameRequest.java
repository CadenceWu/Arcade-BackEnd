package com.springbootproject.date_night_arcade.model;

public class PlayGameRequest {
	private int cardId;
	private int creditsToDeduct;
	private int ticketsToAdd;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getCreditsToDeduct() {
		return creditsToDeduct;
	}

	public void setCreditsToDeduct(int creditsToDeduct) {
		this.creditsToDeduct = creditsToDeduct;
	}

	public int getTicketsToAdd() {
		return ticketsToAdd;
	}

	public void setTicketsToAdd(int ticketsToAdd) {
		this.ticketsToAdd = ticketsToAdd;
	}

}
