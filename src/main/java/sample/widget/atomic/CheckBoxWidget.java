package sample.widget.atomic;

import javafx.scene.control.CheckBox;
import javafx.scene.Node;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

public class CheckBoxWidget implements Widget {
    CheckBox checkBox;
    String checkBoxIsEnabled = "enabled";
    String checkBoxIsSelected = "unchecked";

    @Override
    public void createNode(ParentChild parentChild) {
        int parentNumber = parentChild.getParent().getWidthWidget();

        checkBox = new CheckBox();
        checkBox.getStyleClass().add("checkBox");
        if (parentNumber % 2 == 0){
            checkBox.fire();
            checkBoxIsSelected = "checked";
        }
        if (parentNumber < 130){
            checkBox.setDisable(true);
            checkBoxIsEnabled = "disabled";
        }

        parentChild.setEnabled(checkBoxIsEnabled);
        parentChild.setCheckable(checkBoxIsSelected);
        parentChild.setNodeCheckbox(true);
    }

    @Override
    public Node getNode() {
        return checkBox;
    }

}
