package com.games.mancala.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.games.mancala.entity.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String>{

	@Query("{ 'name' : ?0 }")
	Player findByName(String name);
}
