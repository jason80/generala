package com.jason.generala.dice;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Dice implements Iterable<Die> {
	
	private Die[] dies = new Die[5];
	private Random random = null;
	private boolean disabled = false;
	
	public Dice() {
		random = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < 5; i ++) {
			dies[i] = new Die(random);
		}
	}
	
	public void random() {
		for (Die die : dies) {
			die.random();
		}
	}
	
	public void sort() {
		for (int i = 0; i < 4; i ++) {
			if (dies[i].getValue() > dies[i + 1].getValue()) {
				Die tmp = dies[i];
				dies[i] = dies[i + 1];
				dies[i + 1] = tmp; i = -1;
			}
		}
	}
	
	public int getValue(int index) {
		return dies[index].getValue();
	}
	
	@Override
	public Iterator<Die> iterator() {
		List<Die> list = Arrays.asList(dies);
		return list.iterator();
	}
	
	@Override
	public String toString() {
		
		String str = "";
		
		for (Die die : dies) {
			str += die + " ";
		}
		return str;
	}
	
	public void selectAll() {
		for (Die die : dies) {
			die.setSelected(true);
		}
	}
	
	public void toggle(int index) {
		if (disabled) return;
		dies[index].setSelected(dies[index].isSelected() ? false : true);
	}
	
	public boolean isSelected(int index) {
		return dies[index].isSelected();
	}
	
	public void setSelectionDisabled(boolean val) {
		disabled = val;
	}
}
