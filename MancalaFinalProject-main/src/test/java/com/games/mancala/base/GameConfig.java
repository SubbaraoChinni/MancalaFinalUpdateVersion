package com.games.mancala.base;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.games.mancala.service.GameServiceImpl;
import com.games.mancala.service.GameService;
import com.games.mancala.repository.PitRepository;
import com.games.mancala.repository.PlayerRepository;

@Configuration
public class GameConfig {

	@Bean
	public GameService gameService(){
		GameService gameService = new GameServiceImpl();
		return gameService;
	}
	
	@Bean
  	public PitRepository pitRepository(){
  		return Mockito.mock(PitRepository.class);
  	}
	
	@Bean
  	public PlayerRepository playerRepository(){
  		return Mockito.mock(PlayerRepository.class);
  	}
	
}