package guifx;

import application.controller.Controller;
import application.model.Fad;
import application.model.Lager;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import storage.Storage;

import java.util.Optional;

public class FadePane extends VBox {
    private TextField fadHistorie;
    private TextField tidligereBruger;
    private TextField placering;
    private TextField koebssted;
    private TextField fadNavn;
    private TextField fadKapacitet;
    private ListView<Fad> fadListView = new ListView<>();
    private VBox fadeInfoBox;
    private ComboBox<Lager> lagerComboBox = new ComboBox<>();
    private LagerPane lagerPane;

    public FadePane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        fadListView = new ListView<>();
        fadListView.getItems().setAll(Storage.getFade());
        fadListView.setPrefSize(200, 150);
        pane.add(fadListView, 0, 8, 2, 1);

        fadeInfoBox = new VBox(5);
        fadeInfoBox.setPadding(new Insets(10));
        pane.add(fadeInfoBox, 3, 0, 1, 10);

        fadListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                visFadInfo(newValue);
            }
        });

        Label fadHistorieLabel = new Label("Fadhistorie:");
        fadHistorie = new TextField();
        pane.add(fadHistorieLabel, 0, 0);
        pane.add(fadHistorie, 1, 0);

        Label tidligereBrugLabel = new Label("Tidligere brug:");
        tidligereBruger = new TextField();
        pane.add(tidligereBrugLabel, 0, 1);
        pane.add(tidligereBruger, 1, 1);

        Label placeringLabel = new Label("Placering nummer:");
        placering = new TextField();
        pane.add(placeringLabel, 0, 2);
        pane.add(placering, 1, 2);

        Label koebsstedLabel = new Label("Købssted:");
        koebssted = new TextField();
        pane.add(koebsstedLabel, 0, 3);
        pane.add(koebssted, 1, 3);

        Label fadNavnLabel = new Label("Fadnavn:");
        fadNavn = new TextField();
        pane.add(fadNavnLabel, 0, 4);
        pane.add(fadNavn, 1, 4);

        Label fadKapacitetLabel = new Label("Kapacitet i liter:");
        fadKapacitet = new TextField();
        pane.add(fadKapacitetLabel, 0, 5);
        pane.add(fadKapacitet, 1, 5);

        Label fadLagerLabel = new Label("Fadets lager:");
        pane.add(fadLagerLabel, 0, 6);

        lagerComboBox = new ComboBox<>();
        pane.add(lagerComboBox, 1, 6);
        lagerComboBox.getItems().setAll(Storage.getLagere());

        Button gemButton = new Button("Gem");
        gemButton.setOnAction(event -> gemButtonAction());
        Button annullerButton = new Button("Annuller");
        annullerButton.setOnAction(event -> annullerButtonAction());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(gemButton, annullerButton);

        pane.add(buttonBox, 0, 7, 2, 1);

        getChildren().add(pane);
    }

    public void setLagerPane(LagerPane lagerPane) {
        this.lagerPane = lagerPane;
    }

    private void gemButtonAction() {
        String fadHistorieValue = fadHistorie.getText();
        String tidligereBrugValue = tidligereBruger.getText();
        int placeringValue = Integer.parseInt(placering.getText());
        String koebsstedValue = koebssted.getText();
        String fadNavnValue = fadNavn.getText();
        int fadKapacitetValue = Integer.parseInt(fadKapacitet.getText());
        Lager lager = lagerComboBox.getSelectionModel().getSelectedItem();

        Fad fad = Controller.createFad(fadHistorieValue, tidligereBrugValue, placeringValue, koebsstedValue,
                fadNavnValue, fadKapacitetValue, lager);
        fadListView.getItems().add(fad);

        if (lagerPane != null) {
            lagerPane.updateFadListView(lager.getFade());
        }
    }

    private void visFadInfo(Fad fad) {
        fadeInfoBox.getChildren().clear();
        fadeInfoBox.getChildren().addAll(
                new Label("Fadhistorie: " + fad.getFadHistore()),
                new Label("Tidligere brug: " + fad.getTidligereBrug()),
                new Label("Fadets lagerplacering: " + fad.getPlacering()),
                new Label("Købssted: " + fad.getKoebssted()),
                new Label("Fadnavn: " + fad.getFadNavn()),
                new Label("Kapacitet i liter: " + fad.getFadKapacitet())
        );

        TranslateTransition tt = new TranslateTransition(Duration.millis(500), fadeInfoBox);
        tt.setFromX(-20);
        tt.setToX(0);

        FadeTransition ft = new FadeTransition(Duration.millis(500), fadeInfoBox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        tt.play();
        ft.play();
    }

    public void updateLagerComboBox() {
        lagerComboBox.getItems().setAll(Storage.getLagere());
    }

    private void annullerButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft Annullering");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på, at du vil annullere og starte forfra?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            fadHistorie.clear();
            fadNavn.clear();
            tidligereBruger.clear();
            placering.clear();
            koebssted.clear();
            fadKapacitet.clear();
        }
    }
}
