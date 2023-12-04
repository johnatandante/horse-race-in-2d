package it.volta.ts.starhausnicolas.testmultithreading.main;

import java.util.ArrayList;

import it.volta.ts.starhausnicolas.testmultithreading.bean.Horse;
import it.volta.ts.starhausnicolas.testmultithreading.business.Match;

public class Console {

	private ArrayList<Horse> horses;
	private Match            match;
	
	//----------------------------------------------------------------------------------------------
	
	public Console() {
		horses = new ArrayList<>();
		initializeHorses();
		
		this.match = new Match(horses);
	}
	
	private void initializeHorses() {
		horses.add(new Horse("Saetta", 100));
		horses.add(new Horse("Bob", 100));
		horses.add(new Horse("Xi Jim Ping", 100));
		horses.add(new Horse("Pong", 100));
	}
	
	//----------------------------------------------------------------------------------------------
	
	public void execute() {
		for(Horse horse : horses) {
			horse.start();
		}
		match.start();
	}
	
}
