package sample.widget.atomic;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import sample.utils.Generation;
import sample.utils.Tags;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import java.util.List;

import static sample.utils.ConfigFile.equalLetter;
import static sample.utils.Generation.getColor;
import static sample.utils.Generation.getRandom;

public class TableWidget implements Widget, Tags {
    TableView<Label> tableView;
    TableColumn<Label, Label> nameColumn;
    String tableViewIsEnabled = "disabled";
    TabPane tabPane;
    VBox stackedTitledPanes;
    List<TitledPane> listTitlePane;

    @Override
    public void createNode(ParentChild parentChild) {
        int parentNumb = parentChild.getWidthWidget();
        tableViewIsEnabled = "enabled";
//        if (parentNumb % 2 == 0) {
        tableView = new TableView<>();
        tableView.getStyleClass().add("tableView");
        tableView.setMinWidth(150);

        for (int i = 0; i < 30; i++) {
            if (equalLetter) {
                nameColumn = new TableColumn<>(Generation.getText(EQ_WORD, 1, 1));
            } else
                nameColumn = new TableColumn<>(Generation.getText(WORD, 1, 1));

            if (i == 0) {
                nameColumn.setMaxWidth(40);
                nameColumn.setCellValueFactory(cd -> new SimpleObjectProperty<>(new Label(Generation.getText(ICON, 1, 1))));
            } else {
                if (equalLetter) {
                    nameColumn.setCellValueFactory(cd -> new SimpleObjectProperty<>(new Label(Generation.getText(EQ_WORD, 1, 1))));
                } else
                    nameColumn.setCellValueFactory(cd -> new SimpleObjectProperty<>(new Label(Generation.getText(WORD, 1, 1))));
            }
            tableView.getItems().add(new Label());
            tableView.getColumns().add(nameColumn);
        }
        tableView.setMinWidth(200);
        parentChild.setNodeHasScrollBar(true);

//        } else {
//            stackedTitledPanes = new VBox();
//            stackedTitledPanes.minWidth(50);
//            listTitlePane = new ArrayList<>();
//
//            for (int i = 0; i < 5; i++) {
//
//                VBox pane = new VBox();
//                for (int j = 0; j < 1 + (int) (Math.random() * 5); j++) {
//                    HBox hBoxRoot = new HBox();
//
//                    HBox hBox = new HBox();
//                    hBox.getChildren().add(new Label(TextGeneration.getText(WORD, 1, 1)));
//                    hBox.setStyle("-fx-border-color: black");
//
//                    HBox hBox2 = new HBox();
//                    hBox2.setStyle("-fx-border-color: black");
//                    int random = 1 + (int) (Math.random() * 5);
//                    switch (random) {
//                        case 1:
//                            hBox2.getChildren().add(new CheckBox());
//                            hBox2.getChildren().add(new Label(TextGeneration.getText(WORD, 1, 1)));
//                            break;
//                        case 2:
//                            Image image = new Image(String.valueOf(UtilsFile.getRandomFile(PATH_ICONS).toURI()));
//                            ImageView imageView = new ImageView(image);
//                            imageView.setFitHeight(25);
//                            imageView.setFitWidth(25);
//                            hBox2.getChildren().add(imageView);
//                            hBox2.getChildren().add(new Label(TextGeneration.getText(WORD, 1, 1)));
//                            break;
//                        default:
//                            hBox2.getChildren().add(new Label(TextGeneration.getText(WORD, 1, 3)));
//                            break;
//                    }
//                    HBox.setHgrow(hBox, Priority.ALWAYS);
//                    HBox.setHgrow(hBox2, Priority.ALWAYS);
//                    hBox.setPrefWidth(50);
//                    hBox2.setPrefWidth(50);
//                    hBoxRoot.getChildren().addAll(hBox, hBox2);
//                    pane.getChildren().add(hBoxRoot);
//                }
//                TitledPane titledPane = new TitledPane(TextGeneration.getText(WORD, 1, 1), pane);
//                titledPane.getStyleClass().add("titledPane");
//
//                listTitlePane.add(titledPane);
//            }
//            stackedTitledPanes.getChildren().setAll(listTitlePane);
//            ((TitledPane) stackedTitledPanes.getChildren().get(0)).setExpanded(true);
//            tabPane = new TabPane();
//            tabPane.setStyle("-fx-border-color: black");
//            tabPane.getStyleClass().add("tabPane");
//            for (int i = 0; i < 5; i++) {
//                Tab tab = new Tab(TextGeneration.getText(WORD, 1, 1));
//                tabPane.getTabs().add(tab);
//            }
//            tabPane.getTabs().get(0).setContent(stackedTitledPanes);
//        }
        parentChild.setEnabled(tableViewIsEnabled);
        parentChild.setSometimes(true);
        int i = getRandom(0, 10);
        tableView.setRowFactory(tv -> new TableRow<Label>() {
            @Override
            protected void updateItem(Label item, boolean empty) {
                super.updateItem(item, empty);
                if (getIndex() == i)
                    setStyle("-fx-background-color:" + getColor() + ";");
            }
        });
    }

    @Override
    public Node getNode() {
        if (tableView != null) {
            return tableView;
        } else
            return tabPane;
    }
}
