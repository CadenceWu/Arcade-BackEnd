package com.springbootproject.date_night_arcade.model;

public class TransferRequest {
    private int sourceCardId;
    private int targetCardId;
    private int amount;

    public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// Getters and Setters
    public int getSourceCardId() {
        return sourceCardId;
    }

    public void setSourceCardId(int sourceCardId) {
        this.sourceCardId = sourceCardId;
    }

    public int getTargetCardId() {
        return targetCardId;
    }

    public void setTargetCardId(int targetCardId) {
        this.targetCardId = targetCardId;
    }
}