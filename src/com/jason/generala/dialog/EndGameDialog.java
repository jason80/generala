package com.jason.generala.dialog;

import com.jason.generala.Generala;
import com.jason.generala.player.Player;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndGameDialog {
	
	public static void show(Generala generala) {
		new EndGameDialog(generala);
	}
	
	private Stage stage = null;
	private Generala generala = null;
	
	private EndGameDialog(Generala generala) {
		this.generala = generala;
		
		stage = new Stage();
		stage.setTitle("Resultados");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(20));
		layout.setVgap(10);
		layout.setHgap(100);
		
		ObservableList<Player> players = generala.getPlayers();
		
		// Ordena los jugadores de mayor puntaje a menor
		for (int i = 0; i < players.size() - 1; i ++) {
			if (players.get(i).getTotal() < players.get(i + 1).getTotal()/* ||
					players.get(i + 1).isForceVictory()*/) {
				Player tmp = players.get(i);
				players.set(i, players.get(i + 1));
				players.set(i + 1, tmp); i = -1;
			}
		}
		
		int i;
		
		for (i = 0; i < players.size(); i ++) {
			
			String style = "-fx-font-size: 25;";
			if (i == 0) style = "-fx-font-size: 30; -fx-text-fill: green;";
			
			Label lblPlayer = new Label(players.get(i).getName());
			lblPlayer.setStyle(style);
			Label lblTotal = new Label(players.get(i).getTotal() + "");
			lblTotal.setStyle(style);
			GridPane.setConstraints(lblPlayer, 0, i);
			GridPane.setConstraints(lblTotal, 1, i);
			layout.getChildren().addAll(lblPlayer, lblTotal);
		}
		
		// Botones
		
		Button playAgainButton = new Button("_Jugar de nuevo");
		GridPane.setConstraints(playAgainButton, 0, i);
		GridPane.setColumnSpan(playAgainButton, 2);
		playAgainButton.setMaxWidth(Double.MAX_VALUE);
		playAgainButton.setOnAction(e -> playAgain());
		
		i ++;
		
		Button exitButton = new Button("_Salir de Generala");
		GridPane.setConstraints(exitButton, 0, i);
		GridPane.setColumnSpan(exitButton, 2);
		exitButton.setMaxWidth(Double.MAX_VALUE);
		exitButton.setOnAction(e -> exit());
		
		layout.getChildren().addAll(playAgainButton, exitButton);
		
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.sizeToScene();
		
		stage.showAndWait();
	}
	
	/*private void newGame() {
		stage.close();
		NewGameDialog.show(generala);
	}*/

	private void playAgain() {
		generala.newGame(generala.getPlayers());
		stage.close();
	}
	
	private void exit() {
		stage.close();
		generala.getStage().close();
	}
}
