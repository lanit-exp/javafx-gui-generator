package sample.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.geometry.Geometry;
import sample.json.Parser;
import sample.utils.*;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import java.util.Iterator;
import java.util.List;

import static sample.utils.ConfigFile.*;
import static sample.utils.Generation.getRandom;

public class Main extends Application implements Tags {
    StackPane rootPane;
    Scene scene;
    Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        rootPane = new StackPane();
        rootPane.getStyleClass().add("pane");

        scene = new Scene(rootPane, Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        ConfigFile.getConfig();
        rootPane.setMaxSize(windowWidth, windowHeight);
        scene.getStylesheets().add(ConfigFile.getApiFonts());

        Thread switchSceneTread = new Thread(this::switchScene);
        switchSceneTread.start();

        ProgressIndicator indicator = new ProgressIndicator();
        indicator.setMaxSize(100, 100);
        rootPane.getChildren().add(indicator);
    }

    private void switchScene() {
        Geometry geometry = new Geometry();
        List<ParentChild> rootWidgetList = Parser.parseTree(inputDir + PATH_TREE);
        Generation.createText();

        Iterator<ParentChild> it = rootWidgetList.iterator();
        while (it.hasNext()) {
            ParentChild rootWidget = it.next();
            rootWidget.initialization();

            if (rootWidget.getChildrenList().isEmpty())
                rootPane.setMinSize(getRandom(400, 1900), getRandom(200, 1000));

            Platform.runLater(() -> {
                rootPane.getChildren().clear();
                stage.getScene().getStylesheets().clear();

                stage.getScene().getStylesheets().add("file:///" + UtilsFile.getRandomFile(inputDir + PATH_STYLE).getAbsolutePath().replace("\\", "/"));
                rootPane.setStyle("-fx-font-family:" + ConfigFile.getRandomFonts() + "; -fx-font-size: " + getRandom(fontSizeMin, fontSizeMax) + ";");
                rootPane.getChildren().add(rootWidget.getWidget().getNode());

                stage.sizeToScene();
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

                Widget.sizeCheckBox(rootWidget);
                Widget.sizeRadioButton(rootWidget);
            });
            Platform.runLater(() -> {
                Widget.searchTextElement(rootWidget);
                Widget.searchButtons(rootWidget);
            });
            Wait.sec(timeSwitch);
            Platform.runLater(() -> {
                Widget.searchScrollBars(rootWidget);
                geometry.createGeometry(stage, rootWidget, rootPane);
            });
            rootPane.setMinSize(0, 0);
            it.remove();
            System.gc();
        }
        Wait.sec(timeSwitch);
        if (ConfigFile.isCocoGeometry)
            geometry.createCocoFile();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}