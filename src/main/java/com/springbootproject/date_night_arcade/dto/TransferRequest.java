package com.springbootproject.date_night_arcade.dto;

//DTO(Data Transfer Object)
//The TransferRequest class only contains the relevant information required to perform the transfer.
//The Card class is tied to the database structure and may contain unnecessary fields for the transfer process
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