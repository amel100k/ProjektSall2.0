package guifx;
import application.controller.Controller;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

public class FadePane extends VBox {
    private Fad fad;
    private TextField fadHistorie;
    private TextField tidligereBruger;
    private TextField placering;
    private TextField koebssted;
    private TextField fadNavn;
    private TextField fadKapacitet;
    private ListView<Fad> fadListView = new ListView<>();


    public FadePane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        fadListView = new ListView<>();
        fadListView.getItems().setAll(Storage.getFade());
        fadListView.setPrefSize(200,150);
        pane.add(fadListView,0,7,2,1);

        Label fadHistorieLabel = new Label("Fadhistorie:");
        fadHistorie = new TextField();
        pane.add(fadHistorieLabel, 0, 0);
        pane.add(fadHistorie, 1, 0);

        Label tidligereBrugLabel = new Label("Tidligere brug:");
        tidligereBruger = new TextField();
        pane.add(tidligereBrugLabel, 0, 1);
        pane.add(tidligereBruger, 1, 1);

        Label placeringLabel = new Label("Placering:");
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
        pane.add(fadKapacitetLabel,0,5);
        pane.add(fadKapacitet,1,5);

        Button gemButton = new Button("Gem");
        gemButton.setOnAction(event -> gemButtonAction());
        Button annullerButton = new Button("Annuller");

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(gemButton, annullerButton);

        pane.add(buttonBox, 0, 6, 2, 1);

        getChildren().add(pane);
    }
    private void gemButtonAction(){
        String fadHistorieValue = fadHistorie.getText();
        String tidligereBrugValue = tidligereBruger.getText();
        int placeringValue = Integer.parseInt(placering.getText());
        String koebsstedValue = koebssted.getText();
        String fadNavnValue = fadNavn.getText();
        int fadKapacitetValue = Integer.parseInt(fadKapacitet.getText());

        fadListView.getItems().add(Controller.createFad(fadHistorieValue,tidligereBrugValue,placeringValue,koebsstedValue,
                fadNavnValue,fadKapacitetValue));
    }
}
