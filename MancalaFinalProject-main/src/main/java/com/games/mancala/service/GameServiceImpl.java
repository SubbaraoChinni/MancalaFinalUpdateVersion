package com.games.mancala.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games.mancala.dto.GameStatus;
import com.games.mancala.entity.Pit;
import com.games.mancala.entity.Player;
import com.games.mancala.repository.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

import com.games.mancala.repository.PitRepository;

@Slf4j
@Service
public class GameServiceImpl implements GameService{
	
	@Autowired
	private PitRepository pitRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	private GameStatus status = new GameStatus();
	
	private Player player1 = new Player();
	private Player player2 = new Player();
	
	@Override
	public GameStatus getStatus(){
		return status;
	}
	
	@Override
	public void sortPieces(String pitId, Player currentPlayer) {
		log.debug("Putting pieces into the pits...");
		
		List<Pit> board = pitRepository.findAll();
		
		Pit pit = pitRepository.findById(Integer.valueOf(pitId));
		int initialPit = pit.getId();
		int initialAmmout = pit.getAmmount();
		
		board.get(initialPit).setAmmount(0);
		for (int i = initialPit + 1; i < (initialPit + pit.getAmmount() + 1); i++) {
			
			Pit p = board.get(i%board.size());
			if (!p.isMain()){
				if (!captureStones(p, initialAmmout, board, currentPlayer)){
					p.add(1);
				}
			} else {
				if (pitBelongsToPlayer(p, currentPlayer)){
					p.add(1);
				}
			}
		}
		
		pitRepository.saveAll(board);
		
		boolean finished = evaluateEndGame(board, currentPlayer);		
		status.setFinished(finished);
		status.setWinner(getWinner());
		
		printBoard(board);		
	}
	
	private String getWinner() {
		player1 = playerRepository.findByName(player1.getName());
		player2 = playerRepository.findByName(player2.getName());
		if (player1.getMainPit().getAmmount() > player2.getMainPit().getAmmount()){
			return player1.getName();
		} else {
			return player2.getName();
		}
	}

	private boolean captureStones(Pit p, int initialAmmout, List<Pit> board, Player currentPlayer) {
		if(p.getAmmount() == 0 && initialAmmout == 1 && pitBelongsToPlayer(p, currentPlayer)){
			int pitSeq = p.getSeq();
			int oppositeSideSeq = (pitSeq + (board.size()-2))-(2*pitSeq);
			if (board.get(oppositeSideSeq).getAmmount() > 0){
				
				Pit mainPit = currentPlayer.getMainPit();
				board.get(mainPit.getSeq()).add(board.get(oppositeSideSeq).getAmmount() + 1);
				board.get(oppositeSideSeq).setAmmount(0);
				board.get(pitSeq).setAmmount(0);
				return true;
			}
		}
		return false;
	}

	private boolean pitBelongsToPlayer(Pit pit, Player currentPlayer){
		boolean containsPit = currentPlayer.getPits().stream()
													.anyMatch(x -> x.getId() == pit.getId());
		return containsPit;
	}
	
	private boolean evaluateEndGame(List<Pit> board, Player currentPlayer) {
		
		Player player = playerRepository.findByName(currentPlayer.getName());
		
		int currentPlayerAmmountSum = player.getPits().stream()
						  .filter(x -> !x.isMain())
						  .mapToInt(x -> x.getAmmount())
						  .reduce((x,y) -> x+y)
						  .getAsInt();
		
		log.info(String.format("Total ammout player[%s] -> [%d]", currentPlayer.getName(), currentPlayerAmmountSum));
				
		return currentPlayerAmmountSum == 0;
	}
	
	@Override
	public Player findPlayer(String playerName) {
		return playerRepository.findByName(playerName);
	}
	
	@Override
	public List<Pit> findAllPits() {
		return pitRepository.findAll();
	}

	@Override
	public void createGame() {
		
		ArrayList<Pit> board = new ArrayList<>();
		int maxStones = 6;

		pitRepository.deleteAll();
		playerRepository.deleteAll();

		player1 = new Player();
		player1.setName("player1");
		ArrayList<Pit> player1Pits = new ArrayList<>();
		
		player2 = new Player();
		player2.setName("player2");
		ArrayList<Pit> player2Pits = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			Pit pit = new Pit(i, maxStones, player2, false);
			player2Pits.add(pit);
		}
		player2Pits.add(new Pit(6, 0, player2, true));
		for (int i = 7; i < 13; i++) {
			Pit pit = new Pit(i, maxStones, player1, false);
			player1Pits.add(pit);
		}
		player1Pits.add(new Pit(13, 0, player1, true));
		
		player1.setPits(player1Pits);
		player2.setPits(player2Pits);
		
		board.addAll(player2Pits);
		board.addAll(player1Pits);
		
		pitRepository.saveAll(board);
		playerRepository.save(player1);
		playerRepository.save(player2);
	}
	
	private void printBoard(List<Pit> board){
		
		log.info("***********************************");
		log.info("************* Mancala *************");
		log.info("***********************************");
		log.info("************* Player 1 ************");
		log.info("|{}|", board.get(13).getAmmount());
		for (int i = 12; i > 6; i--) {
			log.info("[{}] ", board.get(i).getAmmount());
		}
		log.info("|¯|");
		log.info("");
		log.info("|_|");
		for (int i = 0; i < 6; i++) {
			log.info("[{}] ", board.get(i).getAmmount());
		}
		log.info("|{}| ", board.get(6).getAmmount());
		log.info("\n************* Player 2 ************");
		
	}

}