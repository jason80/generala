package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.dice.Dice;
import com.jason.generala.player.Player;

public class FullGame extends Game {

	public FullGame(Generala gen) {
		super(gen);
	}

	@Override
	public void check(int left) {
		if (checkFirst() || checkSecond()) {
			total = 30;
			if (left == 2) {
				servido = true;
				total += 5;
			}
		} else total = 0;
	}
	
	private boolean checkFirst() {
		Dice dice = generala.getDice();
		if (dice.getValue(0) == dice.getValue(1) &&
			dice.getValue(2) == dice.getValue(3) &&
			dice.getValue(2) == dice.getValue(4) &&
			dice.getValue(0) != dice.getValue(4)) return true;
		return false;
	}
	
	private boolean checkSecond() {
		Dice dice = generala.getDice();
		if (dice.getValue(0) == dice.getValue(1) &&
			dice.getValue(0) == dice.getValue(2) &&
			dice.getValue(3) == dice.getValue(4) &&
			dice.getValue(0) != dice.getValue(4)) return true;
		return false;
	}
	
	@Override
	public void scoreTotal(Player player) {
		player.setFull(total);
	}
	
	@Override
	public String toString() {
		String serv = servido ? " (servido)" : "";
		return total != 0 ? total + " al full" + serv : "Tachar el full";
	}
}
