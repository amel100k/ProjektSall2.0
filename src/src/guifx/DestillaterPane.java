package guifx;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Destillat;
import application.model.Destillering;
import application.model.Fad;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DestillaterPane extends VBox {

    private ListView<Destillat> destillatListView;
    private ListView<Fad> fadListView;
    private ListView<Destillering> destilleringListView;
    private TextField mængdeTextField;
    private Label ledigPladsLabel;
    private ListView<Destillat> valgteDestillatListView;
    private AftapningPane aftapningPane;

    public DestillaterPane(AftapningPane aftapningPane) {
        this.aftapningPane = aftapningPane;
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        destilleringListView = new ListView<>();
        destilleringListView.getItems().setAll(Storage.getDestilleringer());
        destilleringListView.setPrefSize(400, 100);
        pane.add(destilleringListView, 0, 0, 2, 1);

        destillatListView = new ListView<>();
        destillatListView.setPrefSize(400, 100);
        pane.add(destillatListView, 0, 1);
        destillatListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        fadListView = new ListView<>();
        fadListView.getItems().setAll(Storage.getFade());
        fadListView.setPrefSize(200, 100);
        pane.add(fadListView, 1, 1);

        valgteDestillatListView = new ListView<>();
        valgteDestillatListView.setPrefSize(200,100);
        pane.add(valgteDestillatListView,1,4);

        Label destillatLabel = new Label("Destillat Oversigt");
        Label fadLabel = new Label("Vælg fad");

        Button fyldPaaFadButton = new Button("Fyld på fad");

        HBox headerBox = new HBox(10);
        headerBox.getChildren().addAll(destillatLabel, fyldPaaFadButton);
        pane.add(headerBox, 0, 3);

        HBox fadHeaderBox = new HBox(10);
        fadHeaderBox.getChildren().addAll(fadLabel);
        pane.add(fadHeaderBox, 1, 2);

        mængdeTextField = new TextField();
        mængdeTextField.setPromptText("Indtast mængde i liter som du ønsker at fylde på fad");
        pane.add(mængdeTextField, 0, 2);

        ledigPladsLabel = new Label();
        pane.add(ledigPladsLabel, 1, 3);

        Label destillaterFyldtPåFadLabel = new Label("Destillater der er fyldt på fad vises ovenfor");
        pane.add(destillaterFyldtPåFadLabel,1,5);

        getChildren().add(pane);

        destilleringListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateDestillatListView(newValue);
            }
        });

        fadListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateLedigPladsLabel(newValue);
            }
        });

        fyldPaaFadButton.setOnAction(event -> fyldPaFad());
    }

    public void updateDestillatListView(Destillering selectedDestillering) {
        destillatListView.getItems().setAll(selectedDestillering.getDestillater());
    }

    public void updateDestilleringListView(List<Destillering> destilleringList){
        destilleringListView.getItems().setAll(destilleringList);
    }
    public void updateDestillaterListView(List<Destillat> destillatList){
        destillatListView.getItems().setAll(destillatList);
    }
    private void updateLedigPladsLabel(Fad selectedFad) {
        ledigPladsLabel.setText("Ledig plads på fad: " + selectedFad.getLedigPlads() + " liter");
    }

    private void fyldPaFad() {
        List<Destillat> selectedDestillater = destillatListView.getSelectionModel().getSelectedItems();
        Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();

        if (!selectedDestillater.isEmpty() && selectedFad != null) {
            try {
                int mængde = Integer.parseInt(mængdeTextField.getText()) * destillatListView.getSelectionModel().getSelectedItems().size();

                if (mængde <= selectedFad.getLedigPlads()) {
                    selectedFad.fyldPåFad(mængde);

                    for (Destillat destillat : selectedDestillater) {
                    Storage.addDestillat(destillat);
                    }

                    updateLedigPladsLabel(selectedFad);
                    valgteDestillatListView.getItems().addAll(selectedDestillater);

                    Aftapning aftapning = Controller.createAftapning(selectedFad, new ArrayList<>(selectedDestillater), mængde, LocalDate.now());
                    aftapningPane.updateAftapningerListView(Storage.getAftapninger());

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("MANGEL PÅ PLADS!");
                    alert.setContentText("Du har indtastet et antal liter der overskrider den ledige plads på fadet!");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Indtast venligst et nummer som mængden");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("FEJL!");
            alert.setContentText("Vælg mindst ét destillat og et fad");
            alert.showAndWait();
        }
    }
}
