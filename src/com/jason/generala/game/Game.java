package com.jason.generala.game;

import com.jason.generala.Generala;
import com.jason.generala.player.Player;

public abstract class Game {
	protected int total;
	protected Generala generala;
	protected boolean servido = false;
	
	public Game(Generala generala) {
		this.generala = generala;
	}
	
	public int getTotal() {
		return total;
	}
	
	public abstract void check(int left);
	public abstract void scoreTotal(Player player);
}
