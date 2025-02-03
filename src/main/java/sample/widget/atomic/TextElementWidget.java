package sample.widget.atomic;

import javafx.scene.Node;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

public class TextElementWidget implements Widget {
    Node textElement;

    public TextElementWidget(ParentChild parent) {
        parent.setEnabled("has_text");
        parent.setHasText("enabled");
    }

    @Override
    public void createNode(ParentChild children) {
        throw new RuntimeException("Нельзя создать этот виджет");
    }

    @Override
    public Node getNode() {
        if (textElement != null)
            return textElement;
        throw new RuntimeException("TextElement == null");
    }

    public void setNode(Node textElement) {
        this.textElement = textElement;
    }

}
