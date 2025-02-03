package sample.widget.atomic;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import sample.widget.child.ParentChild;
import sample.utils.Tags;
import sample.utils.Generation;
import sample.widget.interfaces.Widget;

public class LabelWidget implements Widget, Tags {
    Label label;
    String labelIsEnabled = "disabled";
    String labelHasText = "not_has_text";

    @Override
    public void createNode(ParentChild parentChild) {
        int parentNumb = parentChild.getParent().getParent().getWidthWidget();
        label = new Label(Generation.getText(WORD, 1, 3));
        label.getStyleClass().add("label");
        label.setMinWidth(parentNumb/1.8);
        label.setMaxWidth(parentNumb/1.8);
        label.setMinHeight(20);
        label.setTextAlignment(TextAlignment.CENTER);
        labelHasText = "has_text";
        labelIsEnabled = "enabled";
        parentChild.setEnabled(labelIsEnabled);
        parentChild.setHasText(labelHasText);
    }
    @Override
    public Node getNode() {
        return label;
    }

}
