package com.jason.generala.dice;

import java.util.Random;

public class Die {
	private int value;
	private Random random = null;
	private boolean selected = true;
	
	public Die(Random r) {
		random = r;
		value = 6;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void random() {
		value = selected ? random.nextInt(6) + 1 : value;
	}
	
	@Override
	public String toString() {
		return value + " ";
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
