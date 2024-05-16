package guifx;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Produkt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class AftapningPane extends VBox {
    private ListView<Aftapning> aftapningListView;
    private ListView<Produkt> flaskeListView;
    private TextField fortyndingTF;
    private TextField literAftap;
    private Label testLbl;
    int antalLiterIAlt = 0;
    private Label antalFlasker;
    private AftapningPane aftapningPane;

    public AftapningPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        aftapningListView = new ListView<>();
        aftapningListView.getItems().setAll(Storage.getAftapninger());
        aftapningListView.setPrefSize(300, 100);
        pane.add(aftapningListView, 0, 0, 2, 1);


        fortyndingTF = new TextField();
        fortyndingTF.setPromptText("Indtast fortynding i L");
        pane.add(fortyndingTF, 0, 2);

        Button fyldPaaFlaskeButton = new Button("Skab produkt");
        pane.add(fyldPaaFlaskeButton, 0, 3);

        Button seFortyndingHis = new Button("Historik");
        pane.add(seFortyndingHis, 0, 4);


        flaskeListView = new ListView<>();
        flaskeListView.getItems().setAll(Storage.getFlasker());
        flaskeListView.setPrefSize(300, 100);
        pane.add(flaskeListView, 3, 0, 2, 1);

        literAftap = new TextField();
        literAftap.setPromptText("Liter aftapning");
        pane.add(literAftap, 1, 2);

        getChildren().add(pane);


        fyldPaaFlaskeButton.setOnAction(event -> fyldPaaFlaske());

    }

    public void updateAftapningerListView(List<Aftapning> aftapningList) {
        aftapningListView.getItems().setAll(aftapningList);
    }

    private void fyldPaaFlaske() {
        if (aftapningListView.getSelectionModel().getSelectedItem() != null) {
            try {
                antalLiterIAlt = 0;
                ArrayList<Aftapning> aftapningArrayList = new ArrayList<>();
                for (Aftapning selectedItem : aftapningListView.getSelectionModel().getSelectedItems()) {
                    aftapningArrayList.add(selectedItem);
                }
                for (Aftapning selectedItem : aftapningListView.getSelectionModel().getSelectedItems()) {
                    selectedItem.fyldPaaFlaske(selectedItem.getLiter(), Integer.parseInt(literAftap.getText()), aftapningListView.getSelectionModel().getSelectedItem());
                    if (selectedItem.getLiter() == 0) {
                        aftapningListView.getItems().remove(selectedItem);
                    }
                    antalLiterIAlt += Integer.parseInt(literAftap.getText());
                }
                antalLiterIAlt += +Integer.parseInt(fortyndingTF.getText());
                double test = Controller.beregnAlkoholProcent(aftapningArrayList.getFirst().getLiter(), aftapningArrayList.getFirst().getDestillat().getFirst().getAlkoholProcent(), Integer.parseInt(fortyndingTF.getText()));
                Produkt produkt = Controller.createProdukt(aftapningArrayList, test, antalLiterIAlt);
                popUpFlaske().showAndWait();
                flaskeListView.getItems().add(produkt);
                updateAftapningLV(Storage.getAftapninger());

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Indtast venligst et nummer som mængden");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vælg et fad");
            alert.showAndWait();
        }
    }

    public Stage popUpFlaske() {
        Stage stage = new Stage();
        stage.setWidth(250);
        stage.setHeight(100);
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        testLbl = new Label("Med " + antalLiterIAlt + "L væske har du lavet: ");
        pane.add(testLbl, 0, 0);

        antalFlasker = new Label();
        antalFlasker.setText("" + antalLiterIAlt + " flasker whiskey");
        pane.add(antalFlasker, 1, 0);
        return stage;
    }


    private void updateAftapningLV(List<Aftapning> aftapningsList) {
        aftapningListView.getItems().setAll(aftapningsList);
    }

}
