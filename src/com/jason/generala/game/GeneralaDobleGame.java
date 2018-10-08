package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.player.Player;

public class GeneralaDobleGame extends Game {

	public GeneralaDobleGame(Generala generala) {
		super(generala);
	}

	@Override
	public void check(int left) {
		GeneralaGame genGame = new GeneralaGame(generala);
		genGame.check(generala.getLeft());
		if (genGame.getTotal() != 0) {
			
			if (generala.getCurrentPlayer().getGenerala() == null) {
				total = 0;
				return;
			}
			
			total = 100;
			if (generala.getLeft() == 2) {
				servido = true;
				total += 5;
			}
		} else {
			total = 0;
		}
	}
	
	@Override
	public void scoreTotal(Player player) {
		player.setGeneralaDoble(total);
	}
	
	@Override
	public String toString() {
		String serv = servido ? " (servida)" : "";
		return total != 0 ? total + " a la generala doble" + serv : "Tachar la generala doble";
	}
}
