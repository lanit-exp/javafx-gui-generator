package sample.widget.atomic;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import sample.utils.Tags;
import sample.utils.Generation;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

public class TextAreaWidget implements Widget, Tags {
    TextArea textArea;
    boolean flag;
    String textAreaIsEnabled = "disabled";

    @Override
    public void createNode(ParentChild parentChild) {
        flag = parentChild.getWidthWidget() % 2 == 0;
        textArea = new TextArea();
        textArea.setMinWidth(200);
        textArea.setMinHeight(100);
        textArea.setPrefHeight(100);
        textArea.getStyleClass().add("textArea");
        textArea.setWrapText(true);
        textArea.setText(Generation.getText(WORD, 30, 300));

        textAreaIsEnabled = "enabled";
        parentChild.setEnabled(textAreaIsEnabled);
        parentChild.setSometimes(true);
        parentChild.setNodeHasScrollBar(true);
    }

    @Override
    public Node getNode() {
//        if (flag)
//            return tabPane;
//        else
        return textArea;
    }
}
