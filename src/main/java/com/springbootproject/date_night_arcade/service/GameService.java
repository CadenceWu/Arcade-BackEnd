package com.springbootproject.date_night_arcade.service;

import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springbootproject.date_night_arcade.model.Game;
import com.springbootproject.date_night_arcade.repo.GameRepo;

@Service
public class GameService {

	@Autowired
	private GameRepo repo;

	public List<Game> getGames() {
		return repo.findAll();
	}

	public Game createGame(Game game) {
		return repo.save(game);
	}

	public Game updateGame(int gameNumber, Game updatedGame) {
		Game existingGame = repo.findById(gameNumber)
				.orElseThrow(() -> new RuntimeException("Game not found with number: " + gameNumber));

		// Update the fields
		existingGame.setCreditNeeded(updatedGame.getCreditNeeded());
		existingGame.setTicketWon(updatedGame.getTicketWon());

		return repo.save(existingGame);
	}

	public void deleteGame(int gameNumber) {
		Game game = repo.findById(gameNumber)
				.orElseThrow(() -> new RuntimeException("Game not found with number: " + gameNumber));

		repo.delete(game);
	}
}
