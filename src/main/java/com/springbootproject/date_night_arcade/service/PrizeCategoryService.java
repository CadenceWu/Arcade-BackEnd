package com.springbootproject.date_night_arcade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.date_night_arcade.model.PrizeCategory;
import com.springbootproject.date_night_arcade.repo.PrizeCategoryRepo;

@Service
public class PrizeCategoryService {
	@Autowired
	private PrizeCategoryRepo repo;

	public List<PrizeCategory> getPrizes() {
		return repo.findAll();
	}

	public PrizeCategory createPrize(PrizeCategory prize) {
		return repo.save(prize);
	}
    public PrizeCategory updatePrize(int prizeNumber,  PrizeCategory prize) {  
    	PrizeCategory existingPrize = repo.findById(prizeNumber)
            .orElseThrow(() -> new RuntimeException("Prize not found with number: " + prizeNumber));
            
        // Update the fields
        existingPrize.setPrizeName(prize.getPrizeName());
        existingPrize.setRequiredTickets(prize.getRequiredTickets());
        existingPrize.setStockQuantity(prize.getStockQuantity());
        
        return repo.save(existingPrize);
    }
    public void deletePrize(int prizeNumber) {
        repo.deleteById(prizeNumber);
    }
    public PrizeCategory getPrizeById(int prizeNumber) {
        return repo.findById(prizeNumber)
            .orElseThrow(() -> new RuntimeException("Prize not found with ID: " + prizeNumber));
    }

    public PrizeCategory savePrize(PrizeCategory prize) {
        return repo.save(prize);
    }
    

}
