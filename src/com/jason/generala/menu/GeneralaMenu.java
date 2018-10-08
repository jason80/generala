package com.jason.generala.menu;

import com.jason.generala.Generala;
import com.jason.generala.dialog.NewGameDialog;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class GeneralaMenu extends MenuBar {
	
	private Generala generala = null;
	
	private Menu gameMenu = new Menu("_Juego");
	
	private MenuItem newGameMenuItem = new MenuItem("_Nueva Partida");
	private MenuItem exitMenuItem = new MenuItem("_Salir");
	
	
	public GeneralaMenu(Generala generala) {
		this.generala = generala;
		
		getMenus().add(gameMenu);
		gameMenu.getItems().addAll(newGameMenuItem, new SeparatorMenuItem(), exitMenuItem);
		newGameMenuItem.setOnAction(e -> newGame());
		newGameMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F2));
		exitMenuItem.setOnAction(e -> exit());
		exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F4,
				KeyCombination.ALT_DOWN));
	}
	
	private void newGame() {
		
		if (generala.isGameEnabled()) {
			Alert alert = new Alert(AlertType.CONFIRMATION,
					"¿Finalizar la partida actual?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			
			if (alert.getResult() == ButtonType.NO) return ;
			
		}
		
		NewGameDialog.show(generala);
	}
	
	private void exit() {
		if (generala.isGameEnabled()) {
			Alert alert = new Alert(AlertType.CONFIRMATION,
					"¿Finalizar la partida y salir?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			
			if (alert.getResult() == ButtonType.NO) return ;
			
		}
		
		generala.getStage().close();
	}
}
