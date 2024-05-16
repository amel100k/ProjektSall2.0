package guifx;

import application.model.Lager;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;

public class LagerPane extends VBox {
    private Lager lager;
    private ListView<Lager> lagerListView;

    public LagerPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(5);

        lagerListView = new ListView<>();
        lagerListView.getItems().setAll(Storage.getLagere());
        lagerListView.setPrefSize(400, 100);
        pane.add(lagerListView, 0, 0, 2, 2);

        this.getChildren().add(pane);

    }

}
