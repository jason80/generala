package com.jason.generala.draw;

import com.jason.generala.Generala;
import com.jason.generala.dice.Dice;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DiceDrawer {
	
	public static final float DSIZE = 70;
	private static final float DOTSIZE = DSIZE / 6;
	private static final float DROUND = DSIZE / 4;
	
	private static final Paint DICE_COLOR = Color.WHITE;
	private static final Paint BORDER_COLOR = Color.BLACK;
	private static final Paint DOT_COLOR = Color.BLACK;
	
	private static final Paint UDICE_COLOR = Color.GREY;
	private static final Paint UBORDER_COLOR = Color.DARKGRAY;
	private static final Paint UDOT_COLOR = Color.DARKGRAY;
	
	private Generala generala = null;
	
	public DiceDrawer(Generala generala) {
		this.generala = generala;
	}
	
	public void draw() {
		
		Canvas[] canvas = generala.getDiceCanvas();
		Dice dice = generala.getDice();
		
		for (int i = 0; i < canvas.length; i ++) {
			GraphicsContext g = canvas[i].getGraphicsContext2D();
			g.setFill(dice.isSelected(i) ? DICE_COLOR : UDICE_COLOR);
			g.fillRoundRect(0, 0, DSIZE, DSIZE, DROUND, DROUND);
			g.setStroke(dice.isSelected(i) ? BORDER_COLOR : UBORDER_COLOR);
			g.strokeRoundRect(0, 0, DSIZE, DSIZE, DROUND, DROUND);
			
			drawDots(g, dice.getValue(i), dice.isSelected(i));
		}
	}
	
	private void drawDots(GraphicsContext g, int value, boolean sel) {
		if (value % 2 == 1) {
			drawDot(DSIZE * 0.5f, DSIZE * 0.5f, g, sel);
		}
		
		if (value >= 2) {
			drawDot(DSIZE * 0.2f, DSIZE * 0.2f, g, sel);
			drawDot(DSIZE * 0.8f, DSIZE * 0.8f, g, sel);
		}
		
		if (value >= 4) {
			drawDot(DSIZE * 0.2f, DSIZE * 0.8f, g, sel);
			drawDot(DSIZE * 0.8f, DSIZE * 0.2f, g, sel);
		}
		
		if (value == 6) {
			drawDot(DSIZE * 0.2f, DSIZE * 0.5f, g, sel);
			drawDot(DSIZE * 0.8f, DSIZE * 0.5f, g, sel);
		}
	}
	
	private void drawDot(float x, float y, GraphicsContext g, boolean sel) {
		g.setFill(sel ? DOT_COLOR : UDOT_COLOR);
		g.fillOval(x - DOTSIZE / 2, y - DOTSIZE / 2, DOTSIZE, DOTSIZE);
		g.setStroke(sel ? BORDER_COLOR : UBORDER_COLOR);
		g.strokeOval(x - DOTSIZE / 2, y - DOTSIZE / 2, DOTSIZE, DOTSIZE);
	}
}
