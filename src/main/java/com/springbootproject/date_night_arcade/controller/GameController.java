package com.springbootproject.date_night_arcade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.date_night_arcade.model.Game;
import com.springbootproject.date_night_arcade.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {
	
	@Autowired
	private GameService service;
	
	@GetMapping("/games")
	public List<Game> getGames(){
		return service.getGames();
	}
	@PostMapping("/games")
    public Game createGame(@RequestBody Game game) {
        return service.createGame(game);
    }
    
    @PutMapping("/games/{gameNumber}")
    public Game updateGame(@PathVariable int gameNumber, @RequestBody Game game) {  
        return service.updateGame(gameNumber, game);
    }
    
    @DeleteMapping("/games/{gameNumber}")
    public void deleteGame(@PathVariable int gameNumber) { 
        service.deleteGame(gameNumber);
    }
	
}
