package guifx;

import application.controller.Controller;
import application.model.Destillering;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import storage.Storage;
import javafx.animation.TranslateTransition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DestilleringerPane extends VBox {
    private ListView<Destillering> destilleringListView;
    private DatePicker startDato;
    private DatePicker slutDato;
    private TextField maltbatchIGram;
    private TextField kornsort;
    private TextField væskeMængdeIMl;
    private TextField alkoholprocent;
    private TextField kommentar;
    private TextField rygemateriale;
    private DestillaterPane destillaterPane;
    private VBox destilleringInfoBox;


    public DestilleringerPane(DestillaterPane destillaterPane) {

        GridPane pane = new GridPane();
        this.destillaterPane = destillaterPane;
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        destilleringListView = new ListView<>();
        destilleringListView.getItems().setAll(Storage.getDestilleringer());
        destilleringListView.setPrefSize(200, 150);
        pane.add(destilleringListView, 0, 9, 2, 1);

        destilleringInfoBox = new VBox(5);
        destilleringInfoBox.setPadding(new Insets(10));
        pane.add(destilleringInfoBox, 3, 0, 1, 10);

        destilleringListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                visDestilleringInfo(newValue);
            }
        });

        Label startDatoLabel = new Label("Startdato:");
        startDato = new DatePicker();
        pane.add(startDatoLabel, 0, 0);
        pane.add(startDato, 1, 0);

        Label slutDatoLabel = new Label("Slutdato:");
        slutDato = new DatePicker();
        pane.add(slutDatoLabel, 0, 1);
        pane.add(slutDato, 1, 1);

        Label maltbatchIGramLabel = new Label("Maltbatch i gram:");
        maltbatchIGram = new TextField();
        pane.add(maltbatchIGramLabel, 0, 2);
        pane.add(maltbatchIGram, 1, 2);

        Label kornsortLabel = new Label("Kornsort:");
        kornsort = new TextField();
        pane.add(kornsortLabel, 0, 3);
        pane.add(kornsort, 1, 3);

        Label væskeMængdeIMlLabel = new Label("Væskemængde i l:");
        væskeMængdeIMl = new TextField();
        pane.add(væskeMængdeIMlLabel, 0, 4);
        pane.add(væskeMængdeIMl, 1, 4);

        Label alkoholprocentLabel = new Label("Alkoholprocent:");
        alkoholprocent = new TextField();
        pane.add(alkoholprocentLabel, 0, 5);
        pane.add(alkoholprocent, 1, 5);

        Label kommentarLabel = new Label("Kommentar:");
        kommentar = new TextField();
        pane.add(kommentarLabel, 0, 6);
        pane.add(kommentar, 1, 6);

        Label rygematerialeLabel = new Label("Rygemateriale:");
        rygemateriale = new TextField();
        pane.add(rygematerialeLabel, 0, 7);
        pane.add(rygemateriale, 1, 7);

        Button gemButton = new Button("Gem");
        gemButton.setOnAction(event -> gemButtonAction());

        Button annullerButton = new Button("Annuller");
        annullerButton.setOnAction(event -> annullerButtonAction());

        Button lavDestillatButton = new Button("Lav Destillat");
        lavDestillatButton.setOnAction(event -> lavDestillatButton());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(gemButton, annullerButton);

        HBox buttonBox2 = new HBox();
        buttonBox2.getChildren().addAll(lavDestillatButton);

        pane.add(buttonBox, 0, 8, 2, 1);

        pane.add(buttonBox2,0,10,2,1);

        getChildren().add(pane);
    }


    private void gemButtonAction() {
        if (startDato.getValue().isAfter(slutDato.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FEJL");
            alert.setContentText("Startdatoen må IKKE være efter slutdatoen!");
            alert.showAndWait();
        } else if (
                startDato.getValue() == null ||
                        slutDato.getValue() == null ||
                        maltbatchIGram.getText().isEmpty() ||
                        kornsort.getText().isEmpty() ||
                        væskeMængdeIMl.getText().isEmpty() ||
                        alkoholprocent.getText().isEmpty() ||
                        kommentar.getText().isEmpty() ||
                        rygemateriale.getText().isEmpty()
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FEJL");
            alert.setContentText("Du mangler at udfylde et eller flere felter!");
            alert.showAndWait();
        } else {
            LocalDate startDatoValue = startDato.getValue();
            LocalDate slutDatoValue = slutDato.getValue();
            int maltbatchIGramValue = Integer.parseInt(maltbatchIGram.getText());
            String kornsortValue = kornsort.getText();
            int væskeMængdeIMlValue = Integer.parseInt(væskeMængdeIMl.getText());
            double alkoholprocentValue = Double.parseDouble(alkoholprocent.getText());
            String kommentarValue = kommentar.getText();
            String rygematerialeValue = rygemateriale.getText();

            Destillering destillering = Controller.createDestillering(startDatoValue, slutDatoValue, maltbatchIGramValue, kornsortValue, væskeMængdeIMlValue,
                    alkoholprocentValue, kommentarValue, rygematerialeValue);
            destilleringListView.getItems().add(destillering);
            destillaterPane.updateDestilleringListView(Storage.getDestilleringer());
        }
    }


    private void annullerButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft Annullering");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på, at du vil annullere og starte forfra?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
        startDato.setValue(null);
        slutDato.setValue(null);
        maltbatchIGram.clear();
        kornsort.clear();
        væskeMængdeIMl.clear();
        alkoholprocent.clear();
        kommentar.clear();
        rygemateriale.clear();
        }
    }
    public void updateDestilleringListView(List<Destillering> destilleringList) {
        destilleringListView.getItems().setAll(destilleringList);
    }
    public void lavDestillatButton() {
        Destillering selectedDestillering = destilleringListView.getSelectionModel().getSelectedItem();
        if (selectedDestillering != null) {
            PopupWindowDestillat.display(selectedDestillering,destillaterPane,this);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Du skal vælge en destillering for at lave et destillat!");
            alert.showAndWait();
        }
    }
    private void visDestilleringInfo(Destillering destillering) {
        destilleringInfoBox.getChildren().clear();
        destilleringInfoBox.getChildren().addAll(
                new Label("Startdato: " + destillering.getStartDato()),
                new Label("Slutdato: " + destillering.getSlutDato()),
                new Label("Maltbatch i gram: " + destillering.getMaltbatchIGram()),
                new Label("Kornsort: " + destillering.getKornsort()),
                new Label("Væskemængde i l: " + destillering.getVæskeMængdeIl()),
                new Label("Alkoholprocent: " + destillering.getAlkoholprocent()),
                new Label("Kommentar: " + destillering.getKommentar()),
                new Label("Rygemateriale: " + destillering.getRygemateriale())
        );

        TranslateTransition tt = new TranslateTransition(Duration.millis(500), destilleringInfoBox);
        tt.setFromY(200);
        tt.setToY(0);

        FadeTransition ft = new FadeTransition(Duration.millis(500), destilleringInfoBox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        tt.play();
        ft.play();
    }

}
