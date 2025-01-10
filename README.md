
# 遊戲場系統後端
		
```mermaid
classDiagram
    class Card {
        - int cardId
        - int creditBalance
        - int ticketBalance
        + Card()
        + getCardId() int
        + setCardId(int)
        + getCreditBalance() int
        + setCreditBalance(int)
        + getTicketBalance() int
        + setTicketBalance(int)
        + toString() String
    }

    class CardRepo {
        + findAll() : List<Card>
        + save(Card) : Card
        + findById(int) : Optional<Card>
        + deleteById(int) : void
    }

    class CardService {
        - CardRepo repo
        + getAllCards() : List<Card>
        + createCard(Card) : Card
        + addCredits(int, Integer) : Card
        + decrementCredits(int, Integer) : Card
        + getCard(int) : Card
        + saveCard(Card) : Card
        + deleteGame(int) : void
    }

    class CardController {
        - CardService service
        + getAllCards() : List<Card>
        + createCard(Card) : Card
        + addCredits(int, Map<String, Integer>) : Card
        + decrementCredits(int, Map<String, Integer>) : Card
        + deleteGame(int) : void
        + getCard(int) : Card
        + getCardById(int) : ResponseEntity<Card>
    }

    CardRepo --> Card : manages
    CardService --> CardRepo : uses
    CardController --> CardService : uses



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
