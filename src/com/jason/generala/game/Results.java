package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Results {
	private Generala generala = null;
	
	public Results(Generala generala) {
		this.generala = generala;
	}
	
	public ObservableList<Game> getResults() {
		
		ObservableList<Game> list = FXCollections.observableArrayList();
		Player player = generala.getCurrentPlayer();
		
		Game game = null;
		// Valores
		for (int i = 1; i <= 6; i ++) {
			
			if (player.getValue(i) != null) continue;
			
			game = new ValueGame(generala, i);
			game.check(generala.getLeft());
			list.add(game);
		}
		
		// Escalera
		if (player.getEscalera() == null) {
			game = new EscaleraGame(generala);
			game.check(generala.getLeft());
			list.add(game);
		}
		
		// Full
		if (player.getFull() == null) {
			game = new FullGame(generala);
			game.check(generala.getLeft());
			list.add(game);
		}
		
		// Poker
		if (player.getPoker() == null) {
			game = new PokerGame(generala);
			game.check(generala.getLeft());
			list.add(game);
		}
		
		// Generala
		if (player.getGenerala() == null) {
			game = new GeneralaGame(generala);
			game.check(generala.getLeft());
			list.add(game);
		}
		
		// Generala Doble
		if (player.getGeneralaDoble() == null) {
			game = new GeneralaDobleGame(generala);
			game.check(generala.getLeft());
			list.add(game);
		}
		
		// Ordena la lista de mayor a menor puntaje
		for (int i = 0; i < list.size() - 1; i ++) {
			if (list.get(i).getTotal() < list.get(i + 1).getTotal()) {
				Game tmp = list.get(i);
				list.set(i, list.get(i + 1));
				list.set(i + 1, tmp); i = -1;
			}
		}
		
		return list;
	}
}
