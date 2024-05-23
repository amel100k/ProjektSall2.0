package guifx;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class FlytWhiskyPane extends VBox {
    private ListView<Aftapning> aftapningListView;
    private ListView<Fad> fadListView;
    private TextField literTextField;
    private Button flytWhiskyButton;
    private Label ledigPladsLabel;
    private AftapningPane aftapningPane;

    public FlytWhiskyPane(AftapningPane aftapningPane) {
        this.aftapningPane = aftapningPane;
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        aftapningListView = new ListView<>();
        aftapningListView.getItems().addAll(Storage.getAftapninger());
        pane.add(aftapningListView, 0, 1);

        fadListView = new ListView<>();
        fadListView.getItems().addAll(Storage.getFade());
        pane.add(fadListView, 1, 1);

        literTextField = new TextField();
        literTextField.setPromptText("Antal liter");
        pane.add(literTextField, 0, 3);

        Label vælgAftapningLabel = new Label("Vælg aftapning:");
        pane.add(vælgAftapningLabel,0,0);

        Label vælgNytFadLabel = new Label("Vælg nyt fad:");
        pane.add(vælgNytFadLabel,1,0);

        Label angivAntalLiterLabel = new Label("Angiv antal liter der skal flyttes over på det valgte fad: ");
        pane.add(angivAntalLiterLabel,0,2);

        flytWhiskyButton = new Button("Flyt Whisky");
        pane.add(flytWhiskyButton, 0, 4);
        flytWhiskyButton.setOnAction(event -> flytWhisky());

        ledigPladsLabel = new Label();
        pane.add(ledigPladsLabel,1,2);

        Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
        fadListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateLedigPladsLabel(newValue);
            }
        });

        getChildren().add(pane);
    }

        private void updateLedigPladsLabel(Fad selectedFad) {
            ledigPladsLabel.setText("Ledig plads på fad: " + selectedFad.getLedigPlads() + " liter");
        }
        public void updateAftapningerListView(List<Aftapning> aftapningList) {
        aftapningListView.getItems().setAll(aftapningList);
        }

    private void flytWhisky() {
        Aftapning selectedAftapning = aftapningListView.getSelectionModel().getSelectedItem();
        Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
        int antalLiter;

        if (selectedAftapning != null && selectedFad != null) {
            try {
                antalLiter = Integer.parseInt(literTextField.getText());
            if (antalLiter <= selectedFad.getLedigPlads() && antalLiter <= selectedAftapning.getLiter()) {
                selectedAftapning.flytTilNytFad(antalLiter);
                selectedFad.fyldPåFad(antalLiter);
                updateLedigPladsLabel(selectedFad);
                Controller.createAftapning(selectedFad,selectedAftapning.getDestillat(),antalLiter, LocalDate.now());
                List<Aftapning> aftapninger = Storage.getAftapninger();
                LocalDate threeYearsAgo = LocalDate.now().minusYears(3);
                List<Aftapning> filteredAftapninger = aftapninger.stream()
                        .filter(a -> Period.between(a.getStartDato(), LocalDate.now()).getYears() >= 3)
                        .collect(Collectors.toList());
                aftapningPane.updateAftapningerListView(filteredAftapninger);
                aftapningPane.updateIkkeKlarAftapningerListView(Storage.getAftapninger());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("MANGEL PÅ PLADS!");
                alert.setContentText("Du har indtastet et antal liter der overskrider den ledige plads på fadet eller aftapningen!");
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Indtast venligst et nummer som mængden");
            alert.showAndWait();
        }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("FEJL!");
            alert.setContentText("Vælg mindst ét destillat og et fad");
            alert.showAndWait();
        }
        aftapningListView.getSelectionModel().clearSelection();
        fadListView.getSelectionModel().clearSelection();
        literTextField.clear();
    }
}
