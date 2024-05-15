package guifx;

import application.model.Destillering;
import application.model.Mængde;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import storage.Storage;

public class PopupWindowDestillat {

    public static void display(Destillering selectedDestillering, DestillaterPane destillaterPane) {
        Stage popupStage = new Stage();
        GridPane pane = new GridPane();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        pane.setPadding(new Insets(10));

        Label alkoholprocentLabel = new Label("Indtast alkoholprocent:");
        pane.add(alkoholprocentLabel,0,0);

        TextField alkoholprocentField = new TextField();
        pane.add(alkoholprocentField,1,0);

        Label mængdeLabel = new Label("Indtast mængde:");
        pane.add(mængdeLabel,0,1);

        TextField mængdeField = new TextField();
        pane.add(mængdeField,1,1);

        Button gemButton = new Button("Gem");
        pane.add(gemButton,0,2);

        gemButton.setOnAction(event -> {
            try {
                double alkoholprocent = Double.parseDouble(alkoholprocentField.getText());
                int mængde = Integer.parseInt(mængdeField.getText());

                selectedDestillering.createDestillat(alkoholprocent, new Mængde(mængde));
                destillaterPane.updateDestillaterListView(Storage.getDestillater());

                popupStage.close();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ugyldig input!");
                alert.setContentText("Indtast venligst alkoholprocenten og mængden som tal.");
                alert.showAndWait();
            }
        });
        Scene scene = new Scene(pane);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}
