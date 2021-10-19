package com.games.mancala.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.games.mancala.service.GameService;
import com.games.mancala.service.GameServiceImpl;

@Configuration
public class GameServiceConfig {

	@Bean
	public GameService gameService(){
		GameService gameService = new GameServiceImpl();
		return gameService;
	}
}
