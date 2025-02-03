package sample.widget.atomic;

import javafx.scene.control.*;
import javafx.scene.Node;
import sample.utils.Tags;
import sample.utils.Generation;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

public class TreeViewWidget implements Widget, Tags {
    TreeItem<Label> rootItem;
    TreeItem<Label> itemParent;
    TreeItem<Label> item;
    TreeView<Label> treeView;
    String treeViewIsEnabled = "disabled";

    @Override
    public void createNode(ParentChild parentChild) {
        rootItem = new TreeItem<>(new Label(Generation.getText(WORD, 4, 4)));
        rootItem.setExpanded(true);

        for (int i = 0; i < 6; i++) {
            itemParent = new TreeItem<>(new Label(Generation.getText(WORD, 4, 4)));

            if (i >= (int) Math.floor(Math.random() * 6))
                itemParent.setExpanded(true);

            for (int j = 0; j < 4; j++) {
                item = new TreeItem<>(new Label(Generation.getText(WORD, 4, 4)));
                itemParent.getChildren().add(item);
            }
            rootItem.getChildren().add(itemParent);

        }
        treeView = new TreeView<>(rootItem);
        treeView.setShowRoot(false);
        treeView.getStyleClass().add("treeView");
        treeView.setStyle("-fx-padding: 0px");
        treeView.setMinWidth(200);
        treeView.setMinHeight(50);

        treeViewIsEnabled = "enabled";
        parentChild.setEnabled(treeViewIsEnabled);
        parentChild.setSometimes(true);
        parentChild.setNodeHasScrollBar(true);
        parentChild.setNodeHasLabel(true);
    }

    @Override
    public Node getNode() {
        return treeView;
    }
}