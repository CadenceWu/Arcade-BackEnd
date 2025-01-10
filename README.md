
# 遊戲場系統後端
		
```mermaid
classDiagram
    class Card {
        - int cardId
        - int creditBalance
        - int ticketBalance
        + Card()
        + getCardId() int
        + setCardId(cardId : int) void
        + getCreditBalance() int
        + setCreditBalance(creditBalance : int) void
        + getTicketBalance() int
        + setTicketBalance(ticketBalance : int) void
        + toString() String
    }

    class CardRepo {
		<<interface>>
    }
	CardRepo ..> Card : manages
	
    class CardService {
        - CardRepo repo
        + getAllCards() : List~Card~
        + createCard(card : Card)  Card
        + addCredits(cardId : int, amount : Integer)  Card
        + decrementCredits(cardId : int, amount : Integer)  Card
        + getCard(cardId : int)  Card
        + saveCard(card : Card)  Card
        + deleteGame(cardId : int)  void
    }
	CardService ..> CardRepo : uses

    class CardController {
        - CardService service
        + getAllCards()  List~Card~
        + createCard(card : Card)  Card
        + addCredits(cardId : int, request : Map~String, Integer~)  Card
        + decrementCredits(cardId: int, request : Map~String, Integer~)  Card
        + deleteGame(cardId: int)  void
        + getCard(cardId: int)  Card
        + getCardById(cardId: int)  ResponseEntity~Card~
    }
    CardController ..> CardService : calls



    class Game {
        - int gameNumber
        - String gameName
        - int creditNeeded
        - int ticketWon
        + getGameNumber() int
        + setGameNumber(gameNumber : int) void
        + getGameName() String
        + setGameName(gameName : String) void
        + getCreditNeeded() int
        + setCreditNeeded(creditNeeded : int) void
        + getTicketWon() int
        + setTicketWon(ticketWon : int) void
        + toString() String
    }
    
    class GameRepo {
        <<interface>>
    }
    GameRepo ..> Game : manages
    
    class GameService {
        - GameRepo repo
        + getGames() List~Game~
        + createGame(game : Game) Game
        + updateGame(gameNumber : int, updatedGame : Game) Game
        + deleteGame(gameNumber : int) void
    }
    GameService ..> GameRepo : uses

    class GameController {
        - GameService service
        + getGames() List~Game~
        + createGame(game : Game) Game
        + updateGame(gameNumber : int, game : Game) Game
        + deleteGame(gameNumber : int) void
    }
    GameController ..> GameService : calls


	class PrizeCategory {
		- int prizeNumber
		- private prizeName
		- int requiredTickets
		- int stockQuantity
		+ toString() String
		+ getPrizeNumber() int
		+ setPrizeNumber(prizeNumber : int) void
		+ getPrizeName() String
		+ setPrizeName(prizeName : String) void
		+ getRequiredTickets() int
		+ setRequiredTickets(requiredTickets : int) void
		+ getStockQuantity() int
		+ setStockQuantity(stockQuantity : int) void
		+ consumeItem() void
    }
	

	class PrizeCategoryRepo {
		<<interface>>
	}
	PrizeCategoryRepo ..> PrizeCategory : manages
	
	class PrizeCategoryService{
		- PrizeCategory repo
		+ getPrizes List~PrizeCategory~
		+ createPrize(prize : PrizeCategory) PrizeCategory
		+ updatePrize(prizeNumber : int, prize : PrizeCategory) PrizeCategory
		+ deletePrize(prizeNumber : int) void
		+ getPrizeById(prizeNumber : int) PrizeCategory
		+ savePrize(prize : PrizeCategory) PrizeCategory
	}
	PrizeCategoryService ..> PrizeCategoryRepo : uses
	
	class PrizeCategoryController{
		- PrizeCategoryService service
		+ getPrizes List~PrizeCategory~
		+ createPrize(prize : PrizeCategory) PrizeCategory
		+ updatePrize(prizeNumber : int, prize PrizeCategory) PrizeCategory
		+ deletePrize(prizeNumber : int) void
	}
	PrizeCategoryController ..> PrizeCategoryService : calls
	

    %% TerminalController
    class TerminalController {
        - TerminalService terminalService
        - CardService cardService
        + TerminalController(TerminalService, CardService)
        + transferCredits(request : TransferRequest) ResponseEntity~String~
        + transferTickets(request : TransferRequest) ResponseEntity~String~
        + playGame(cardId : int, request : PlayGameRequest) ResponseEntity~?~
        + requestPrize(request : PrizeRequest) ResponseEntity~String~
    }
    
    %% DTOs
    class PlayGameRequest {
        - int cardId
        - int creditsToDeduct
        - int ticketsToAdd
        + getCardId() int
        + setCardId(cardId : int) void
        + getCreditsToDeduct() int
        + setCreditsToDeduct(creditsToDeduct : int) void
        + getTicketsToAdd() int
        + setTicketsToAdd(ticketsToAdd : int) void
    }
    
    class PrizeRequest {
        - int cardId
        - int prizeNumber
        + getCardId() int
        + setCardId(cardId : int) void
        + getPrizeNumber() int
        + setPrizeNumber(prizeNumber : int) void
    }
    
    class TransferRequest {
        - int sourceCardId
        - int targetCardId
        - int amount
        + getSourceCardId() int
        + setSourceCardId(amount : int) void
        + getTargetCardId() int
        + setTargetCardId(sourceCardId : int) void
        + getAmount() int
        + setAmount(targetCardId : int) void
    }

    %% Services
    class TerminalService {
        - CardService cardService
        - PrizeCategoryService prizeCategoryService
        + transferCredits(sourceCard : Card, targetCard : Card, amount : int) void
        + transferTickets(sourceCard : Card, targetCard : Card, amount : int) void
        + playGame(cardId : int, creditsToDeduct : int, ticketsToAdd : int) Card
        + requestPrize(cardId : int, prizeNumber : int) void
    }

    %% Relationships
    TerminalController --> TerminalService : calls
    TerminalController --> CardService : calls
    TerminalController --> PlayGameRequest : calls
    TerminalController --> PrizeRequest : calls
    TerminalController --> TransferRequest : calls

    TerminalService --> Card : uses
    TerminalService --> PrizeCategory : uses
    TerminalService --> CardService : uses
    TerminalService --> PrizeCategoryService : uses
