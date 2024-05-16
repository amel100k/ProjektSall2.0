package guifx;

import application.controller.Controller;
import application.model.Destillering;
import application.model.Fad;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.util.Optional;

public class LagerPane extends VBox {
    private Lager lager;
    private ListView<Lager> lagerListView;
    private ListView<Fad> fadListView;
    private TextField adresse;
    private TextField maxAntalFad;

    public LagerPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        Label lagerLabel = new Label("Lager:");
        pane.add(lagerLabel, 17, 0);
        lagerListView = new ListView<>();
        lagerListView.getItems().setAll(Storage.getLagere());
        lagerListView.setPrefSize(250, 100);
        pane.add(lagerListView, 17, 1, 1, 1);

        Label fadeLabel = new Label("Fade på lageret:");
        pane.add(fadeLabel, 17, 2);
        fadListView = new ListView<>();
        fadListView.getItems().setAll(Storage.getLagere().getFirst().getFade());
        fadListView.setPrefSize(250, 100);
        pane.add(fadListView, 17, 3, 1, 1);
        fadListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lagerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fadListView.getItems().setAll(newValue.getFade());
            }
        });

        Label opretLagerLabel = new Label("Opret lager:");
        pane.add(opretLagerLabel, 1, 0);

        Label adresseLabel = new Label("Adresse:");
        adresse = new TextField();
        pane.add(adresse, 1, 1);

        Label maxAntalFadLabel = new Label("Max antal fad:");
        maxAntalFad = new TextField();
        pane.add(maxAntalFad, 1, 2);

        Button gemButton = new Button("Gem");
        gemButton.setOnAction(event -> gemButtonAction());

        Button annullerButton = new Button("Annuller");
        annullerButton.setOnAction(event -> annullerButtonAction());

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(gemButton, annullerButton);
        pane.add(buttonBox, 1, 2, 2, 1);

        VBox lagerBox = new VBox(25);
        lagerBox.getChildren().addAll(adresseLabel, maxAntalFadLabel);
        pane.add(lagerBox, 0, 1);

        VBox textBox = new VBox(15);
        textBox.getChildren().addAll(adresse, maxAntalFad);
        pane.add(textBox, 1, 1);

        this.getChildren().add(pane);

    }

    private void annullerButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft Annullering");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på, at du vil annullere og starte forfra?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            adresse.clear();
            maxAntalFad.clear();
        }
    }

    private void gemButtonAction(){
        String adresse1 = adresse.getText();
          int maxAntalFadValue = Integer.parseInt(maxAntalFad.getText());

          lagerListView.getItems().add(Controller.createLager(adresse1, maxAntalFadValue));
    }
}
