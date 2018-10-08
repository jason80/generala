package com.jason.generala;

import com.jason.generala.player.Player;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayersTable extends TableView<Player> {
	@SuppressWarnings("unchecked")
	public PlayersTable() {
		TableColumn<Player, String> nameColumn = new TableColumn<>("Jugador");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setSortable(false);
		
		this.getColumns().add(nameColumn);
		
		for (int i = 1; i <= 6; i ++) {
			TableColumn<Player, String> c = new TableColumn<>(i + "");
			c.setCellValueFactory(new PropertyValueFactory<>("str" + i));
			c.setPrefWidth(30);
			c.setSortable(false);
			c.setStyle("-fx-alignment: center;");
			this.getColumns().add(c);
		}
		
		TableColumn<Player, String> escColumn = new TableColumn<>("Escalera");
		escColumn.setCellValueFactory(new PropertyValueFactory<>("strEscalera"));
		escColumn.setSortable(false);
		escColumn.setStyle("-fx-alignment: center;");
		
		TableColumn<Player, String> fullColumn = new TableColumn<>("Full");
		fullColumn.setCellValueFactory(new PropertyValueFactory<>("strFull"));
		fullColumn.setSortable(false);
		fullColumn.setPrefWidth(50);
		fullColumn.setStyle("-fx-alignment: center;");
		
		TableColumn<Player, String> pokerColumn = new TableColumn<>("Poker");
		pokerColumn.setCellValueFactory(new PropertyValueFactory<>("strPoker"));
		pokerColumn.setSortable(false);
		pokerColumn.setPrefWidth(60);
		pokerColumn.setStyle("-fx-alignment: center;");
		
		TableColumn<Player, String> genColumn = new TableColumn<>("Generala");
		genColumn.setCellValueFactory(new PropertyValueFactory<>("strGenerala"));
		genColumn.setSortable(false);
		genColumn.setStyle("-fx-alignment: center;");
		
		TableColumn<Player, String> dobleColumn = new TableColumn<>("G. Doble");
		dobleColumn.setCellValueFactory(new PropertyValueFactory<>("strGeneralaDoble"));
		dobleColumn.setSortable(false);
		dobleColumn.setStyle("-fx-alignment: center;");
		
		TableColumn<Player, Integer> totalColumn = new TableColumn<>("Total");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
		totalColumn.setSortable(false);
		totalColumn.setStyle("-fx-alignment: center;");
		
		this.getColumns().addAll(escColumn, fullColumn, pokerColumn, genColumn, dobleColumn, totalColumn);
	}
}
