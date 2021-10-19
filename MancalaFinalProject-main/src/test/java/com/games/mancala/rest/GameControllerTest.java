package com.games.mancala.rest;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.games.mancala.rest.*;
import com.games.mancala.controller.GameController;
import com.games.mancala.repository.PitRepository;
import com.games.mancala.repository.PlayerRepository;
import com.games.mancala.service.GameService;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes = {GameConfig.class, GameController.class})
public class GameControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	@Autowired
	private GameController gameController;
	
	@Autowired 
	private GameService gameService;
	
	@Autowired
	private PitRepository pitRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	
	@Before
	public void setUp(){
		final StaticApplicationContext applicationContext = new StaticApplicationContext();	     
		 
		 final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
	     webMvcConfigurationSupport.setApplicationContext(applicationContext);
		 
		 mockMvc = MockMvcBuilders.standaloneSetup(gameController)
				 .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
				 .build();
		 
	}
	
	@Test
	public void post_createGame_withSuccess() throws Exception{
		
		this.mockMvc.perform(post("/game")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void put_sortPiecesAndDontFinishTheGame_WithSucess() throws Exception{
		
   }
	
	
	@Test
	public void put_sortPiecesAndFinishTheGame_WithSucess() throws Exception{

	}
}

