package com.springbootproject.date_night_arcade.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@Column(name = "game_number")
	private int gameNumber;
	@Column(name = "game_name")
	private String gameName;

	@Column(name = "credit_needed")
	private int creditNeeded;
	@Column(name = "ticket_won")
	private int ticketWon;

	@Override
	public String toString() {
		return "Game [gameNumber=" + gameNumber + ", gameName=" + gameName + ", creditNeeded=" + creditNeeded
				+ ", ticketWon=" + ticketWon + "]";
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getCreditNeeded() {
		return creditNeeded;
	}

	public void setCreditNeeded(int creditNeeded) {
		this.creditNeeded = creditNeeded;
	}

	public int getTicketWon() {
		return ticketWon;
	}

	public void setTicketWon(int ticketWon) {
		this.ticketWon = ticketWon;
	}

}
