package com.jason.generala.dialog;

import com.jason.generala.Generala;
import com.jason.generala.RuleSet.GeneralaServida;
import com.jason.generala.player.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewGameDialog {
	
	private Generala generala = null;
	
	private Stage stage = null;
	private ObservableList<Player> players = FXCollections.observableArrayList();
	
	private ListView<Player> lvPlayers = new ListView<>();
	private Button newPlayerButton = new Button("Nuevo Jugador");
	private Button editPlayerButton = new Button("Editar Nombre");
	private Button removePlayerButton = new Button("Quitar Jugador");
	
	private Button startGameButton = new Button("Comenzar la partida");
	private Button cancelButton = new Button("Cancelar");
	
	private CheckBox useAsEscaleraCheck = new CheckBox("Utilizar el As como comodín en la Escalera");
	private RadioButton genServWinsRadio = new RadioButton("Gana la partida.");
	private RadioButton genServScoreRadio = new RadioButton("Anota 200 puntos.");
	
	private GridPane playersLayout = new GridPane();
	private BorderPane layout = new BorderPane();
	private VBox buttonsLayout = new VBox(10);
	private VBox ruleSetLayout = new VBox(10);
	
	public static void show(Generala generala) {
		new NewGameDialog(generala);
	}
	
	public NewGameDialog(Generala generala) {
		this.generala = generala;
		
		stage = new Stage();
		
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Nueva Partida");
		stage.setResizable(false);
		
		initPlayersLayout();
		layout.setLeft(playersLayout);
		
		initRuleSetLayout();
		layout.setRight(ruleSetLayout);
		
		buttonsLayout.setPadding(new Insets(10));
		startGameButton.setMaxWidth(Double.MAX_VALUE);
		startGameButton.setOnAction(e -> newGame());
		cancelButton.setMaxWidth(Double.MAX_VALUE);
		cancelButton.setOnAction(e -> stage.close());
		buttonsLayout.getChildren().addAll(startGameButton, cancelButton);
		layout.setBottom(buttonsLayout);
		
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.sizeToScene();
		
		update();
		
		stage.showAndWait();
	}
	
	private void newGame() {
		
		generala.getRuleSet().setUseAsEscalera(useAsEscaleraCheck.isSelected());
		generala.getRuleSet().setGeneralaServida(genServWinsRadio.isSelected() ?
				GeneralaServida.WINS_GAME : GeneralaServida.SCORE);
		generala.getRuleSet().save();
		
		generala.newGame(players);
		
		stage.close();
	}

	private void initPlayersLayout() {
		
		playersLayout.setPadding(new Insets(10));
		playersLayout.setVgap(5);
		playersLayout.setHgap(10);
		
		GridPane.setConstraints(lvPlayers, 0, 0);
		GridPane.setRowSpan(lvPlayers, 3);
		lvPlayers.setItems(players);
		lvPlayers.setMaxHeight(200.0);
		lvPlayers.getSelectionModel().selectedItemProperty().addListener((o) -> update());
		
		GridPane.setConstraints(newPlayerButton, 1, 0);
		newPlayerButton.setMaxWidth(Double.MAX_VALUE);
		newPlayerButton.setOnAction(e -> newPlayer());
		
		GridPane.setConstraints(editPlayerButton, 1, 1);
		editPlayerButton.setMaxWidth(Double.MAX_VALUE);
		editPlayerButton.setOnAction(e -> editPlayer());
		
		GridPane.setConstraints(removePlayerButton, 1, 2);
		GridPane.setValignment(removePlayerButton, VPos.TOP);
		removePlayerButton.setMaxWidth(Double.MAX_VALUE);
		removePlayerButton.setOnAction(e -> removePlayer());
		
		playersLayout.getChildren().addAll(
				lvPlayers, newPlayerButton, editPlayerButton, removePlayerButton);
	}
	
	private void initRuleSetLayout() {
		ruleSetLayout.setPadding(new Insets(10));
		ruleSetLayout.getChildren().add(useAsEscaleraCheck);
		
		ruleSetLayout.getChildren().add(new Label("Cuando se obtiene Generala Servida:"));
		
		ToggleGroup tg = new ToggleGroup();
		//genServWinsRadio.setSelected(true);
		genServWinsRadio.setPadding(new Insets(0, 0, 0, 30));
		genServWinsRadio.setToggleGroup(tg);
		genServScoreRadio.setToggleGroup(tg);
		genServScoreRadio.setPadding(new Insets(0, 0, 0, 30));
		
		// Carga las reglas
		generala.getRuleSet().load();
		useAsEscaleraCheck.setSelected(generala.getRuleSet().isUseAsEscalera());
		if (generala.getRuleSet().getGeneralaServida() == GeneralaServida.WINS_GAME) {
			genServWinsRadio.setSelected(true);
		} else {
			genServScoreRadio.setSelected(true);
		}
		
		ruleSetLayout.getChildren().addAll(genServWinsRadio, genServScoreRadio);
	}
	
	private void newPlayer() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Nombre del jugador (10 car.):");
		dialog.showAndWait();
		
		String result = dialog.getResult();
		
		if (result == null) return;
		result = result.trim();
		if ("".equals(result)) return;
		
		if (playerExists(result)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("El jugador " + result.toUpperCase().trim() + " ya existe.");
			alert.showAndWait();
			return ;
		}
		
		if (result.length() > 10) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("El nombre no debe superar los 10 caracteres.");
			alert.showAndWait();
			return;
		}
		
		Player player = new Player(result.toUpperCase());
		
		players.add(player);
		
		update();
		
	}
	
	private void editPlayer() {
		Player player = players.get(lvPlayers.getSelectionModel().getSelectedIndex());
		
		TextInputDialog dialog = new TextInputDialog(player.getName());
		dialog.setContentText("Nombre del jugador:");
		dialog.showAndWait();
		
		String result = dialog.getResult();
		if (result == null) return ;
		if ("".equals(result.trim())) return ;
		
		player.setName(result.trim().toUpperCase());
		
		lvPlayers.refresh();
		
		update();
	}
	
	private void removePlayer() {
		Player player = players.get(lvPlayers.getSelectionModel().getSelectedIndex());
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "¿Quitar jugador " + player.getName()
							+ "?", ButtonType.YES, ButtonType.NO);
		
		alert.showAndWait();
		
		if (alert.getResult() == ButtonType.NO) return;
		
		players.remove(player);
		
		update();
	}
	
	public boolean playerExists(String name) {
		for (Player p : players) {
			if (name.trim().equalsIgnoreCase(p.getName())) return true;
		}
		
		return false;
	}
	
	private void update() {
		editPlayerButton.setDisable(true);
		removePlayerButton.setDisable(true);
		startGameButton.setDisable(true);
		
		if (lvPlayers.getSelectionModel().getSelectedIndex() != -1) {
			editPlayerButton.setDisable(false);
			removePlayerButton.setDisable(false);
		}
		
		if (players.size() >= 2) startGameButton.setDisable(false);
		
	}
}
