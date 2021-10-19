package com.games.mancala.dto;

import lombok.Data;

@Data
public class GameStatus {
	
	private boolean finished;
	
	private String winner;
}