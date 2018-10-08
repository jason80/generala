package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.dice.Dice;
import com.jason.generala.player.Player;

public class EscaleraGame extends Game {
	
	public EscaleraGame(Generala generala) {
		super(generala);
	}
	
	@Override
	public void check(int left) {
		
		boolean chk = false;
		Dice dice = generala.getDice();
		
		if (dice.getValue(0) == 1 &&
			dice.getValue(1) == 2 &&
			dice.getValue(2) == 3 &&
			dice.getValue(3) == 4 &&
			dice.getValue(4) == 5) chk = true;
		else if(dice.getValue(0) == 2 &&
				dice.getValue(1) == 3 &&
				dice.getValue(2) == 4 &&
				dice.getValue(3) == 5 &&
				dice.getValue(4) == 6) chk = true;
		else if (generala.getRuleSet().isUseAsEscalera()) {
			if(dice.getValue(0) == 1 &&
					dice.getValue(1) == 3 &&
					dice.getValue(2) == 4 &&
					dice.getValue(3) == 5 &&
					dice.getValue(4) == 6) chk = true;
		}
		
		total = chk ? 20 : 0;
		
		if (chk && left == 2) {
			servido = true;
			total += 5;
		}
	}
	
	@Override
	public void scoreTotal(Player player) {
		player.setEscalera(total);
	}
	
	@Override
	public String toString() {
		String serv = servido ? " (servida)" : "";
		return total != 0 ? total + " a la escalera" + serv : "Tachar la escalera";
	}
}
