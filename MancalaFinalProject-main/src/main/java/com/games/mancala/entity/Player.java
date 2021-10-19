package com.games.mancala.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "player")
public class Player {
	
	@Id
	private String id;
	
	private String name;
	
	@DBRef
	private ArrayList<Pit> pits;

	public Pit getMainPit(){
		return pits.stream().filter(x -> x.isMain()).findFirst().get();
	}	


}
