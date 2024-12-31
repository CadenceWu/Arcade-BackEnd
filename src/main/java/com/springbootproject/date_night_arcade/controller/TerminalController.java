package com.springbootproject.date_night_arcade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.date_night_arcade.model.Card;
import com.springbootproject.date_night_arcade.model.PlayGameRequest;
import com.springbootproject.date_night_arcade.model.PrizeRequest;
import com.springbootproject.date_night_arcade.model.TransferRequest;
import com.springbootproject.date_night_arcade.service.CardService;
import com.springbootproject.date_night_arcade.service.TerminalService;

@RestController
@RequestMapping("/api/terminal")
public class TerminalController {
	private final TerminalService terminalService;
	private final CardService cardService;

	@Autowired
	public TerminalController(TerminalService terminalService, CardService cardService) {
		this.terminalService = terminalService;
		this.cardService = cardService;
	}

	@PostMapping("/transferCredits")
	public ResponseEntity<String> transferCredits(@RequestBody TransferRequest request) {
		try {
			Card sourceCard = cardService.getCard(request.getSourceCardId());
			Card targetCard = cardService.getCard(request.getTargetCardId());

			terminalService.transferCredits(sourceCard, targetCard);
			cardService.save(sourceCard);
			cardService.save(targetCard);

			return ResponseEntity.ok("Credits transferred successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/transferTickets")
	public ResponseEntity<String> transferTickets(@RequestBody TransferRequest request) {
		try {
			Card sourceCard = cardService.getCard(request.getSourceCardId());
			Card targetCard = cardService.getCard(request.getTargetCardId());

			terminalService.transferTickets(sourceCard, targetCard);
			cardService.save(sourceCard);
			cardService.save(targetCard);

			return ResponseEntity.ok("Tickets transferred successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@PostMapping("/playGame/{cardId}")
	public ResponseEntity<?> playGame(@PathVariable int cardId, @RequestBody PlayGameRequest request) {
	    try {
	        Card updatedCard = terminalService.playGame(
	            cardId,
	            request.getCreditsToDeduct(),
	            request.getTicketsToAdd()
	        );
	        return ResponseEntity.ok(updatedCard);  // Return the updated card
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());  // Return error message
	    }
	}
	
//	   @PostMapping("/transferCredits")
//	    public ResponseEntity<String> transferCredits(@RequestBody TransferRequest request) {
//	        try {
//	            Card sourceCard = cardService.getCard(request.getSourceCardId());
//	            Card targetCard = cardService.getCard(request.getTargetCardId());
//
//	            if (sourceCard.getCreditBalance() < request.getAmount()) {
//	                return ResponseEntity.badRequest().body("來源卡片餘額不足");
//	            }
//
//	            // Update balances
//	            sourceCard.setCreditBalance(sourceCard.getCreditBalance() - request.getAmount());
//	            targetCard.setCreditBalance(targetCard.getCreditBalance() + request.getAmount());
//
//	            // Save changes
//	            cardService.createCard(sourceCard);
//	            cardService.createCard(targetCard);
//
//	            return ResponseEntity.ok("轉換成功");
//	        } catch (Exception e) {
//	            return ResponseEntity.badRequest().body(e.getMessage());
//	        }
//	    }
//
//	    @PostMapping("/transferTickets")
//	    public ResponseEntity<String> transferTickets(@RequestBody TransferRequest request) {
//	        try {
//	            Card sourceCard = cardService.getCard(request.getSourceCardId());
//	            Card targetCard = cardService.getCard(request.getTargetCardId());
//
//	            if (sourceCard.getTicketBalance() < request.getAmount()) {
//	                return ResponseEntity.badRequest().body("來源卡片票券不足");
//	            }
//
//	            // Update balances
//	            sourceCard.setTicketBalance(sourceCard.getTicketBalance() - request.getAmount());
//	            targetCard.setTicketBalance(targetCard.getTicketBalance() + request.getAmount());
//
//	            // Save changes
//	            cardService.createCard(sourceCard);
//	            cardService.createCard(targetCard);
//
//	            return ResponseEntity.ok("轉換成功");
//	        } catch (Exception e) {
//	            return ResponseEntity.badRequest().body(e.getMessage());
//	        }
//	    }
//	}
	 @PostMapping("/requestPrize")
	    public ResponseEntity<String> requestPrize(@RequestBody PrizeRequest request) {
	        try {
	            terminalService.requestPrize(request.getCardId(), request.getPrizeNumber());
	            return ResponseEntity.ok("Prize redeemed successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
}
