package com.games.mancala.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="pit")
public class Pit {
	
	@Id
	private int id;
	
	private int seq;
	
	private int ammount;
	
	private boolean main;
	
	public Pit(){
		
	}
	
	public Pit(int seq, int ammount, Player owner, boolean larger){
		this.seq = seq;
		this.ammount = ammount;
		this.main = larger;
	}

		public void add(int ammount){
		this.ammount += ammount;
	}

}
