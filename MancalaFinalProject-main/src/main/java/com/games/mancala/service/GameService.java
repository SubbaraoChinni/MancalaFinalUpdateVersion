package com.games.mancala.service;

import java.util.List;

import com.games.mancala.dto.GameStatus;
import com.games.mancala.entity.Pit;
import com.games.mancala.entity.Player;

public interface GameService {

	void sortPieces(String pitId, Player player);

	void createGame();
	
	GameStatus getStatus();
	
	Player findPlayer(String player);
	
	List<Pit> findAllPits();

}
