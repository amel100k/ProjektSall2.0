package guifx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sall Whisky");
        GridPane pane = new GridPane();

        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20.0));
        pane.setHgap(20.0);
        pane.setVgap(10.0);

        TabPane tabPane = new TabPane();
        pane.add(tabPane, 0, 0);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        AftapningPane aftapningPane = new AftapningPane();
        FadePane fadePane = new FadePane();
        LagerPane lagerPane = new LagerPane(fadePane);
        fadePane.setLagerPane(lagerPane);
        FlytWhiskyPane flytWhiskyPane = new FlytWhiskyPane(aftapningPane);

        Tab fadeTab = new Tab();
        fadeTab.setText("Registrer fad");
        fadeTab.setContent(fadePane);
        tabPane.getTabs().add(fadeTab);

        DestillaterPane destillaterPaneTest = new DestillaterPane(aftapningPane,flytWhiskyPane);
        Tab destillaterTab = new Tab();
        destillaterTab.setText("Destillater oversigt");
        destillaterTab.setContent(destillaterPaneTest);
        tabPane.getTabs().add(destillaterTab);

        Tab destilleringerTab = new Tab();
        destilleringerTab.setText("Registrer destillering");
        destilleringerTab.setContent(new DestilleringerPane(destillaterPaneTest));
        tabPane.getTabs().add(destilleringerTab);

        Tab aftapTab = new Tab();
        aftapTab.setText("Aftap fad");
        aftapTab.setContent(aftapningPane);
        tabPane.getTabs().add(aftapTab);

        Tab lagerTab = new Tab();
        lagerTab.setText("Lager oversigt");
        lagerTab.setContent(lagerPane);
        tabPane.getTabs().add(lagerTab);

        Tab flytWhiskyTilAndetFad = new Tab();
        flytWhiskyTilAndetFad.setText("Flyt whisky til nyt fad");
        flytWhiskyTilAndetFad.setContent(flytWhiskyPane);
        tabPane.getTabs().add(flytWhiskyTilAndetFad);

        pane.setStyle("-fx-background-image: url('https://mydailyspace.dk/wp-content/uploads/2021/02/natasha-arefyeva-u1pYVFDS2CI-unsplash-scaled-e1620811866156-772x1024.jpg')");
    }
}
