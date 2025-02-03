package sample.widget.atomic;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import sample.utils.Generation;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;


public class ListWidget implements Widget {
    StackPane stackPane;
    ListView listView;
    String listViewIsEnabled = "disabled";

    @Override
    public void createNode(ParentChild parentChild) {
        listView = new ListView(Generation.createObservableList(40, 7));
        listView.setMinSize(150, 50);
        listView.setEditable(true);
        listView.getStyleClass().add("listView");
        listView.setStyle("-fx-padding: 0px");

        listViewIsEnabled = "enabled";
        parentChild.setEnabled(listViewIsEnabled);
        parentChild.setSometimes(true);
        parentChild.setNodeHasScrollBar(true);
        parentChild.setNodeHasLabel(true);
        stackPane = new StackPane();
        stackPane.setMinSize(150, 50);
        stackPane.getChildren().add(listView);
    }


    @Override
    public Node getNode() {
        return stackPane;
    }

}
