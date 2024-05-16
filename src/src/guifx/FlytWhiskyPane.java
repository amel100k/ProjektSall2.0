package guifx;

import application.model.Aftapning;
import application.model.Fad;
import application.model.Mængde;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;

public class FlytWhiskyPane extends VBox {
    private ListView<Aftapning> aftapningListView;
    private ListView<Fad> fadListView;
    private TextField literTextField;
    private Button flytWhiskyButton;
    private Label ledigPladsLabel;

    public FlytWhiskyPane() {
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

    private void flytWhisky() {
        Aftapning selectedAftapning = aftapningListView.getSelectionModel().getSelectedItem();
        Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
        int antalLiter = Integer.parseInt(literTextField.getText());

        if (selectedAftapning != null & selectedFad != null){
            selectedAftapning.flytTilFad(antalLiter);
            selectedFad.fyldPåFad(antalLiter);
            updateLedigPladsLabel(selectedFad);
        }
        else if (selectedAftapning.getLiter() < antalLiter){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Der er ikke så mange litere!");
            alert.showAndWait();
        }
        else if (selectedFad.getLedigPlads() < antalLiter) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Der er ikke nok plads på fadet!");
            alert.showAndWait();
        }

        // Nulstil inputfelter
        aftapningListView.getSelectionModel().clearSelection();
        fadListView.getSelectionModel().clearSelection();
        literTextField.clear();
    }
}
