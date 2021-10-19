package com.games.mancala.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.games.mancala.service.GameService;
import com.games.mancala.repository.PlayerRepository;
import com.games.mancala.repository.PitRepository;
import com.games.mancala.dto.GameStatus;
import com.games.mancala.entity.Pit;
import com.games.mancala.entity.Player;
import com.games.mancala.exceptions.EmptyBoardException;
import com.games.mancala.exceptions.InvalidRequestException;

@RestController
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gameService;
	
	private static final Logger logger = LogManager.getLogger(GameController.class);
	
	@GetMapping("/status")
	public GameStatus getStatus(){
		return gameService.getStatus();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/pits", method = RequestMethod.GET)
	public List<Pit> getBoard() throws EmptyBoardException{
		
		List<Pit> board = gameService.findAllPits();
		
		if (board.isEmpty()){
			throw new EmptyBoardException("The board is empty, did it has been created?");
		}
		
		return board;
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createGame(){
		gameService.createGame();
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/pits/{pitId}/player/{playerName}", method = RequestMethod.PUT)
	public void sortPieces(@PathVariable String pitId, @PathVariable String playerName) throws InvalidRequestException{
		Player player = gameService.findPlayer(playerName);
		
		gameService.sortPieces(pitId, player);
	}
}
