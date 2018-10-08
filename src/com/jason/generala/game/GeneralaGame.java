package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.RuleSet.GeneralaServida;
import com.jason.generala.dice.Dice;
import com.jason.generala.player.Player;

public class GeneralaGame extends Game {

	public GeneralaGame(Generala gen) {
		super(gen);
	}
	
	@Override
	public void check(int left) {
		
		Dice dice = generala.getDice();
		
		if (dice.getValue(0) == dice.getValue(1) &&
			dice.getValue(0) == dice.getValue(2) &&
			dice.getValue(0) == dice.getValue(3) &&
			dice.getValue(0) == dice.getValue(4)) {
			total = 60;
			if (left == 2) {
				
				if (generala.getRuleSet().getGeneralaServida() == GeneralaServida.WINS_GAME) {
					if (!generala.isGameEnabled()) return ;
					generala.forceVictory();
					return ;
				}
				
				servido = true;
				total += 5;
			}
		}
		else total = 0;
	}
	
	@Override
	public void scoreTotal(Player player) {
		player.setGenerala(total);
	}
	
	@Override
	public String toString() {
		String serv = servido ? " (servida)" : "";
		return total != 0 ? total + " a la generala" + serv : "Tachar la generala";
	}
}
