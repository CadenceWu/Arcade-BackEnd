package com.springbootproject.date_night_arcade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prizecategory")
public class PrizeCategory {

	@Id
	@Column(name = "prize_number")
	private int prizeNumber;
	@Column(name = "prize_name")
	private String prizeName;
	@Column(name = "required_tickets")
	private int requiredTickets;
	@Column(name = "stock_quantity")
	private int stockQuantity;
	
	public PrizeCategory() {
	    // Default constructor needed for JPA
	}
	

	@Override
	public String toString() {
		return "PrizeCategory [prizeNumber=" + prizeNumber + ", prizeName=" + prizeName + ", requiredTickets="
				+ requiredTickets + ", stockQuantity=" + stockQuantity + "]";
	}

	public PrizeCategory(String prizeName, int requiredTickets, int stockQuantity) {
		this.prizeName = prizeName;
		this.requiredTickets = requiredTickets;
		this.stockQuantity = stockQuantity;
	}

	public int getPrizeNumber() {
		return prizeNumber;
	}

	public void setPrizeNumber(int prizeNumber) {
		this.prizeNumber = prizeNumber;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public int getRequiredTickets() {
		return requiredTickets;
	}

	public void setRequiredTickets(int requiredTickets) {
		this.requiredTickets = requiredTickets;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void consumeItem() {
		stockQuantity--;
	}
}
