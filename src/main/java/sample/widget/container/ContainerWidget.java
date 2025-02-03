package sample.widget.container;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import sample.utils.Tags;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import static sample.utils.ConfigFile.markComposite;

public class ContainerWidget implements Widget, Tags {
    Pane pane;

    @Override
    public void createNode(ParentChild widget) {

        if (widget.getDirection().equals(TAG_V))
            pane = new VBox(10);
        else
            pane = new HBox(10);

        for (ParentChild children : widget.getChildrenList()) {
            children.initialization();
            Node node = children.getWidget().getNode();
            pane.setPadding(new Insets(1));
            pane.getChildren().add(node);
        }
        widget.setAddToGeometry(markComposite);
    }
    @Override
    public Node getNode() {
        return pane;
    }
}

