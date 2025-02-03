package sample.widget.composite;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.Node;
import sample.utils.Tags;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import static sample.utils.ConfigFile.markComposite;

public class CompositeWidget implements Widget, Tags {
    GridPane gridPane;

    @Override
    public void createNode(ParentChild widget) {
        gridPane = new GridPane();
        gridPane.getStyleClass().add("gridWidget");

        for (ParentChild children : widget.getChildrenList()) {
            children.initialization();
            Node node = children.getWidget().getNode();

            GridPane.setConstraints(
                    node,
                    (int) children.getCol(),
                    (int) children.getRow(),
                    (int) children.getColSpan(),
                    (int) children.getRowSpan(),
                    HPos.LEFT,
                    VPos.TOP,
                    Priority.NEVER,
                    Priority.ALWAYS,
                    new Insets(1)
            );
            gridPane.getChildren().add(node);
        }
        if (!widget.getName().equals(INPUT))
            widget.setAddToGeometry(markComposite);


    }

    @Override
    public Node getNode() {
        return gridPane;
    }
}
