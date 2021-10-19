package com.games.mancala.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.games.mancala.entity.Pit;

@Repository
public interface PitRepository extends MongoRepository<Pit, String>{

	@Query("{ 'seq': ?0}")
	Pit findById(int id);
	
}
