package com.jason.generala;

import java.util.Random;

import com.jason.generala.dialog.EndGameDialog;
import com.jason.generala.dice.Dice;
import com.jason.generala.draw.DiceDrawer;
import com.jason.generala.game.Game;
import com.jason.generala.game.Results;
import com.jason.generala.menu.GeneralaMenu;
import com.jason.generala.player.Player;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Generala extends Application {
	
	private boolean gameEnabled = false;
	
	private Stage stage = null;
	private PlayersTable table = new PlayersTable();
	
	private RuleSet ruleSet = new RuleSet();
	
	private HBox diceLayout = new HBox(10);
	private HBox statusLayout = new HBox(10);
	private Canvas[] diceCanvas = new Canvas[5];
	private DiceDrawer diceDrawer = new DiceDrawer(this);
	
	private Results results = new Results(this);
	
	private Label lblTurn = new Label("Turno de");
	private Label lblLeft = new Label("Left");
	
	private Button randomButton = new Button("Lanzar");
	private Button endsTurnButton = new Button("Finalizar Turno");
	
	private ListView<Game> lvResults = new ListView<>();
	
	private ObservableList<Player> players = FXCollections.observableArrayList();
	//private ObservableList<Game> resultList = FXCollections.observableArrayList();
	private int currentPlayer = -1;
	private int left = 3;
	
	private Dice dice = new Dice();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Generala");
		
		table.setItems(players);
		table.setFocusTraversable(false);
		
		
		lvResults.setFocusTraversable(false);
		lvResults.getSelectionModel().selectedItemProperty().addListener((o) -> update());
		lvResults.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		// Inicia los canvas para los dados
		for (int i = 0; i < diceCanvas.length; i ++) {
			diceCanvas[i] = new Canvas(DiceDrawer.DSIZE, DiceDrawer.DSIZE);
			diceLayout.getChildren().add(diceCanvas[i]);
		}
		
		randomButton.setPrefSize(150, 70);
		randomButton.setMinSize(150, 70);
		randomButton.setFocusTraversable(false);
		randomButton.setOnAction(e -> random());
		diceLayout.getChildren().add(randomButton);
		
		diceCanvas[0].setOnMouseClicked(e -> toggle(0));
		diceCanvas[1].setOnMouseClicked(e -> toggle(1));
		diceCanvas[2].setOnMouseClicked(e -> toggle(2));
		diceCanvas[3].setOnMouseClicked(e -> toggle(3));
		diceCanvas[4].setOnMouseClicked(e -> toggle(4));
		
		endsTurnButton.setFocusTraversable(false);
		endsTurnButton.setOnAction(e -> endsTurn());
		
		statusLayout.getChildren().addAll(lblTurn, lblLeft, endsTurnButton);
		
		VBox leftLayout = new VBox(10);
		leftLayout.setPadding(new Insets(10));
		leftLayout.getChildren().addAll(diceLayout, statusLayout, lvResults);
		
		BorderPane layout = new BorderPane();
		
		layout.setTop(new GeneralaMenu(this));
		layout.setLeft(leftLayout);
		layout.setRight(table);
		
		
		layout.setPrefSize(1000, 600);
		
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.sizeToScene();
		
		diceDrawer.draw();
		
		update();
		
		stage.show();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	private void toggle(int i) {
		dice.toggle(i);
		diceDrawer.draw();
	}

	private void random() {
		dice.random();
		dice.sort();
		diceDrawer.draw();
		
		left --;
		
		// Genera la lista de resultados
		lvResults.setItems(results.getResults());
		
		update();
	}
	
	private void endsTurn() {
		
		Game selected = lvResults.getSelectionModel().getSelectedItem();
		selected.scoreTotal(getCurrentPlayer());
		getCurrentPlayer().update();
		table.setItems(players);
		table.refresh();
		
		nextPlayer(); left = 3;
		
		lvResults.getItems().clear();
		
		// Chequea si terminó el juego
		if (players.get(players.size() - 1).isGameCompleted()) {
			gameEnabled = false;
			update();
			EndGameDialog.show(this);
		}
		
		update();
	}
	
	public Canvas[] getDiceCanvas() {
		return diceCanvas;
	}
	
	public Dice getDice() {
		return dice;
	}
	
	public int getLeft() {
		return left;
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	public ObservableList<Player> getPlayers() {
		return players;
	}
	
	public void nextPlayer() {
		currentPlayer ++;
		left = 3;
		lvResults.getItems().clear();
		if (currentPlayer == players.size()) {
			currentPlayer = 0;
		}
		
		update();
	}
	
	// Actualiza el estado de los controles
	private void update() {
		randomButton.setDisable(true);
		endsTurnButton.setDisable(true);
		
		if (!gameEnabled) {
			lvResults.setDisable(true);
			//table.setDisable(true);
			lblTurn.setText("Seleccioná \"Nueva Partida\" para comenzar");
			lblLeft.setText("");
			return;
		} else  {
			lvResults.setDisable(false);
			//table.setDisable(false);
		}
		
		if (left != 0) randomButton.setDisable(false);
		
		if (left != 3) endsTurnButton.setDisable(false);
		
		if (left == 3) {
			dice.selectAll();
			dice.setSelectionDisabled(true);
			diceDrawer.draw();
			lvResults.getItems().clear();
		} else dice.setSelectionDisabled(false);
		
		if (lvResults.getSelectionModel().getSelectedIndex() == -1)
			endsTurnButton.setDisable(true);
		
		lblTurn.setText("Turno de " + getCurrentPlayer() + ".");
		lblLeft.setText(left + " tiros.");
	}
	
	public void newGame(ObservableList<Player> pl) {
		players = pl;
		
		currentPlayer = -1;
		
		// Mezcla la lista de jugadores
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < players.size(); i ++) {
			int r = random.nextInt(players.size());
			Player tmp = players.get(i);
			players.set(i, players.get(r));
			players.set(r, tmp);
		}
		table.setItems(players);
		
		gameEnabled = true;
		
		nextPlayer();
	}
	
	public RuleSet getRuleSet() {
		return ruleSet;
	}
	
	public boolean isGameEnabled() {
		return gameEnabled;
	}
	
	public void forceVictory() {
		gameEnabled = false;

		update();
		
		Alert alert = new Alert(AlertType.INFORMATION, "El jugador " + getCurrentPlayer() + 
				" obtiene Generala Servida.");
		alert.showAndWait();
		
		getCurrentPlayer().setForceVictory(true);
		
		EndGameDialog.show(this);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
