package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.dice.Die;
import com.jason.generala.player.Player;

public class ValueGame extends Game {

	private int value;
	
	public ValueGame(Generala gen, int number) {
		super(gen);
		this.value = number;
	}

	@Override
	public void check(int left) {
		
		total = 0;
		
		for (Die die : generala.getDice()) {
			if (die.getValue() == value) {
				total += value;
			}
		}
	}
	
	@Override
	public void scoreTotal(Player player) {
		player.setValue(value, total);
	}
	
	@Override
	public String toString() {
		return total != 0 ? total + " al " + value : "Tachar el " + value;
	}
}
