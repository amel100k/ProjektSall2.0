package guifx;

import application.controller.Controller;
import application.model.Aftapning;
import application.model.Destillat;
import application.model.Produkt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.Storage;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class AftapningPane extends VBox {
    private ListView<Aftapning> aftapningListView;
    private ListView<Produkt> flaskeListView;
    private ListView<Aftapning> ikkeKlarAftapningListView;
    private TextField fortyndingTF;
    private TextField literAftap;
    private Label testLbl;
    int antalLiterIAlt = 0;
    private Label antalFlasker;
    private AftapningPane aftapningPane;
    private VBox aftapningInfoBox;

    public AftapningPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        aftapningListView = new ListView<>();
        List<Aftapning> aftapninger = Storage.getAftapninger();
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);

        List<Aftapning> filteredAftapninger = aftapninger.stream()
                .filter(a -> Period.between(a.getStartDato(), LocalDate.now()).getYears() >= 3)
                .collect(Collectors.toList());

        aftapningListView.getItems().setAll(filteredAftapninger);
        aftapningListView.setPrefSize(300, 100);
        pane.add(aftapningListView, 0, 0, 2, 1);

        Label alleAftapningerLabel = new Label("Alle aftapninger kan ses herunder:");
        pane.add(alleAftapningerLabel,0,6);

        ikkeKlarAftapningListView = new ListView<>();
        ikkeKlarAftapningListView.getItems().setAll(Storage.getAftapninger());
        ikkeKlarAftapningListView.setPrefSize(300,100);
        pane.add(ikkeKlarAftapningListView,0,7,2,1);
        ikkeKlarAftapningListView.setMouseTransparent(true);
        ikkeKlarAftapningListView.setFocusTraversable(false);

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

        aftapningInfoBox = new VBox();
        pane.add(aftapningInfoBox,0,5);

        getChildren().add(pane);

        fyldPaaFlaskeButton.setOnAction(event -> fyldPaaFlaske());

        aftapningListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                visAftapningInfo(newValue);
            }
        });
    }

    public void updateAftapningerListView(List<Aftapning> aftapningList) {
        aftapningListView.getItems().setAll(aftapningList);
    }
    public void updateIkkeKlarAftapningerListView(List<Aftapning> aftapningList){
        ikkeKlarAftapningListView.getItems().setAll(aftapningList);
    }
    private void visAftapningInfo(Aftapning aftapning){
        for (Destillat destillat : aftapning.getDestillat()) {
            aftapningInfoBox.getChildren().clear();
            aftapningInfoBox.getChildren().addAll(
                    new Label("Alkoholprocent: " + destillat.getAlkoholProcent()),
                    new Label("Fra fad: " + aftapning.getFad().getFadNavn())
            );
        }
    }

    private void fyldPaaFlaske() {
        if (aftapningListView.getSelectionModel().getSelectedItem() != null) {
            try {
                antalLiterIAlt = 0;
                antalLiterIAlt += Integer.parseInt(literAftap.getText());
                Aftapning test = aftapningListView.getSelectionModel().getSelectedItem();

                antalLiterIAlt += Integer.parseInt(fortyndingTF.getText());
                double beregnTest = Controller.beregnAlkoholProcent(test,Integer.parseInt(literAftap.getText()), Integer.parseInt(fortyndingTF.getText()));
                Produkt produkt = Controller.createProdukt(test, beregnTest,antalLiterIAlt);

                popUpFlaske().showAndWait();
                flaskeListView.getItems().add(produkt);
                List<Aftapning> aftapninger = Storage.getAftapninger();
                LocalDate threeYearsAgo = LocalDate.now().minusYears(3);

                List<Aftapning> filteredAftapninger = aftapninger.stream()
                        .filter(a -> Period.between(a.getStartDato(), LocalDate.now()).getYears() >= 3)
                        .collect(Collectors.toList());
                updateAftapningLV(filteredAftapninger);

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

        testLbl = new Label("Du har lavet: " + antalLiterIAlt + "L produkt");
        pane.add(testLbl, 0, 0);

        return stage;
    }


    private void updateAftapningLV(List<Aftapning> aftapningsList) {
        aftapningListView.getItems().setAll(aftapningsList);
    }

}