package sample.widget.atomic;

import javafx.scene.control.RadioButton;
import javafx.scene.Node;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;


public class RadioWidget implements Widget {
    RadioButton radioButton;
    String radioButtonIsEnabled = "enabled";
    String radioButtonIsSelected = "unchecked";

    @Override
    public void createNode(ParentChild parentChild) {
        radioButton = new RadioButton();

        int parentNumber = parentChild.getParent().getWidthWidget();
        if (parentNumber % 2 == 0){
            radioButton.fire();
            radioButtonIsSelected = "checked";
        }
        if (parentNumber < 140){
            radioButton.setDisable(true);
            radioButtonIsEnabled = "disabled";
        }

        parentChild.setEnabled(radioButtonIsEnabled);
        parentChild.setCheckable(radioButtonIsSelected);
        parentChild.setNodeRadioButton(true);
    }
    @Override
    public Node getNode() {
        return radioButton;
    }

}
