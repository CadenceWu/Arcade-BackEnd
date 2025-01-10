
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
        + createCard(card : Card) : Card
        + addCredits(cardId : int, amount : Integer) : Card
        + decrementCredits(cardId : int, amount : Integer) : Card
        + getCard(cardId : int) : Card
        + saveCard(card : Card) : Card
        + deleteGame(cardId : int) : void
    }
	CardService ..> CardRepo : uses

    class CardController {
        - CardService service
        + getAllCards() : List~Card~
        + createCard(card : Card) : Card
        + addCredits(cardId : int, request : Map~String, Integer~) : Card
        + decrementCredits(cardId: int, request : Map~String, Integer~) : Card
        + deleteGame(cardId: int) : void
        + getCard(cardId: int) : Card
        + getCardById(cardId: int) : ResponseEntity<Card>
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
