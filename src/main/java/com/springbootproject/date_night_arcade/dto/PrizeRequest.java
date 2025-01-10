package com.springbootproject.date_night_arcade.dto;

public class PrizeRequest {
    private int cardId;
    private int prizeNumber;

 
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(int prizeNumber) {
        this.prizeNumber = prizeNumber;
    }
}