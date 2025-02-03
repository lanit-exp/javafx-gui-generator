package sample.widget.atomic;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import sample.utils.Tags;
import sample.utils.Generation;
import sample.widget.child.ParentChild;
import sample.widget.interfaces.Widget;

import java.util.ArrayList;

import static sample.utils.ConfigFile.*;

public class ComboBoxWidget implements Widget, Tags {
    ParentChild child;
    ComboBox<HBox> comboBox;
    CheckBoxWidget checkBoxWidget;
    HBox hBox;
    String comboBoxIsEnabled = "disabled";
    String comboBoxHasText = "not_has_text";

    @Override
    public void createNode(ParentChild parentChild) {
        int parentNumb = parentChild.getParent().getParent().getWidthWidget();

        comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("comboBox");

        if (parentNumb % 2 == 0) {

            if (Math.random() < probabilityLongInput) {
                comboBox.setMinWidth(Generation.getRandom(widthInputMin, widthInputMax));
            } else {
                comboBox.setMinWidth(parentNumb / 1.3);
                comboBox.setMaxWidth(parentNumb / 1.0);
            }
            hBox = new HBox();
            hBox.getChildren().addAll(new Label(Generation.getText(WORD, 1, 1)));

        } else {

            if (Math.random() < probabilityLongInput) {
                comboBox.setMinWidth(Generation.getRandom(widthInputMin, widthInputMax));
            } else {
                comboBox.setMinWidth(parentNumb / 1.0);
                comboBox.setMaxWidth(parentNumb / 1.0);
            }

            child = new ParentChild();
            child.setName(CHECK_BOX);
            child.setType(ATOMIC_WIDGET);
            child.setChildrenList(new ArrayList<>());

            checkBoxWidget = new CheckBoxWidget();
            checkBoxWidget.createNode(parentChild);
            child.setWidget(checkBoxWidget);
            parentChild.getChildrenList().add(child);

            hBox = new HBox();
            hBox.getChildren().addAll(child.getWidget().getNode(), new Label(Generation.getText(WORD, 1, 1)));
        }

        comboBox.setValue(hBox);
        comboBox.getSelectionModel().select(0);
        parentChild.setNodeHasLabel(true);
        comboBoxHasText = "has_text";
        comboBoxIsEnabled = "enabled";

        parentChild.getParent().setEnabled(comboBoxIsEnabled);
        parentChild.getParent().setHasText(comboBoxHasText);
        parentChild.setAddToGeometry(false);
    }

    @Override
    public Node getNode() {
            return comboBox;
    }
}
