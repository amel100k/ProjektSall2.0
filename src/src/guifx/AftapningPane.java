package guifx;

import application.model.Aftapning;
import application.model.Fad;
import application.model.Flaske;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;

public class AftapningPane extends VBox {
    private ListView<Aftapning> aftapningListView;
    private ListView<Flaske> flaskeListView;
    private TextField fortyndingTF;

    public AftapningPane(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);


        aftapningListView = new ListView<>();
        aftapningListView.getItems().setAll(Storage.getAftapninger());
        aftapningListView.setPrefSize(600,100);
        pane.add(aftapningListView,0,0,2,1);
        aftapningListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        fortyndingTF = new TextField();
        fortyndingTF.setPromptText("Indtast fortynding i L");
        pane.add(fortyndingTF,0,2);

        Button fyldPaaFlaskeButton = new Button("Fyld på flaske");
        pane.add(fyldPaaFlaskeButton,0,3);

        Button seFortyndingHis = new Button("Historik");
        pane.add(seFortyndingHis,0,4);

        getChildren().add(pane);

        fyldPaaFlaskeButton.setOnAction(event -> fyldPaaFlaske());
    }
    private void fyldPaaFlaske(){
        int antalLiterIAlt = 0;
        for (Aftapning selectedItem : aftapningListView.getSelectionModel().getSelectedItems()) {
            antalLiterIAlt += selectedItem.getLiter();
            selectedItem.setLiter(0);
            if(selectedItem.getLiter() == 0){
                aftapningListView.getItems().remove(selectedItem);
            }
        }
        antalLiterIAlt +=  Integer.parseInt(fortyndingTF.getText());
    }
}
